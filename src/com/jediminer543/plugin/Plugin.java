package com.jediminer543.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.jediminer543.plugin.config.PlayerConfigHandeler;
import com.jediminer543.plugin.config.parsers.LocationHandeler;
import com.jediminer543.plugin.listeners.PlayerListener;



public final class Plugin extends JavaPlugin 
{
	public static List<Player> playerList = new ArrayList<Player>();
	CustomConfig FactionConfigHandeler = new CustomConfig(this, "factions.yml");
	CustomConfig WarpConfigHandeler = new CustomConfig(this, "locations.yml");
	
    @Override
    public void onEnable(){
    	getLogger().info("Plugin Loading Please Wait");
    	FactionConfigHandeler = new CustomConfig(this, "factions.yml");
    	WarpConfigHandeler = new CustomConfig(this, "locations.yml");
    	WarpConfigHandeler.reloadConfig();
    	FactionConfigHandeler.reloadConfig();
    	if (this.getConfig().getBoolean("Plugin.Config.NotReload") == false)
    	{
    		this.saveDefaultConfig();
    	}
    	Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    	for (Player player : this.getServer().getOnlinePlayers()) {
    		 playerList.add(player);
    	}
    }
 
    @Override
    public void onDisable() {
    	getLogger().info("Plugin shutting down, plese wait");
    	getLogger().info("Writing configs");
    	WarpConfigHandeler.saveConfig();
    	FactionConfigHandeler.saveConfig();
    	

    }
    
    public void save()
    {
    	this.saveConfig();
    	WarpConfigHandeler.saveConfig();
    	FactionConfigHandeler.saveConfig();
    	for (Player player : playerList) {
    		FileConfiguration pconfig = PlayerConfigHandeler.getPlayerConfig(player,this).getConfig();
    		pconfig.set("Backup.Loc", LocationHandeler.fromLoc(player.getLocation()));
    		
   	}
    }
    
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args)
	{
		boolean player = false;
		Player senderp = null;
		if (sender instanceof Player)
		{
			player = true;
			senderp = (Player) sender;
		}
		switch (cmd.getName().toLowerCase())
		{
			case "plugin":
				return pluginHandeler(sender, args, this);
			case "faction":
				return factionHandeler(sender, args, FactionConfigHandeler.getConfig(), this);
			case "random":
				if (player)
				{
					if (senderp.getWorld() == Bukkit.getServer().getWorlds().get(0))
					{
					Random rand = new Random();
					int r = 1000;
					int x = rand.nextInt(r - 100)+100;
					int z = (int) Math.sqrt(r^2-x^2);
					World world = Bukkit.getServer().getWorlds().get(0);
					int y;
					y = world.getHighestBlockAt(x, z).getY() + 1;
					senderp.teleport(new Location(world, x + senderp.getLocation().getX(), y, z + senderp.getLocation().getZ()));
					}
					else
					{
						sender.sendMessage("This Command only works in the overworld");
					}
					return true;
					
				}
				else
				{
					sender.sendMessage("Only players can execute this command");
				}			
				break;
			case "sethome":
				if(player)
				{
					WarpConfigHandeler.getConfig().set(senderp.getName()+".Home.Location", LocationHandeler.fromLoc(senderp.getLocation()));
					sender.sendMessage("Home Set");
					return true;
				}
				else
				{
					sender.sendMessage("Only players can execute this command");
				}			
				break;
			case "home":
				if(player)
				{
					try{
						senderp.teleport(LocationHandeler.toLoc(WarpConfigHandeler.getConfig().getStringList(senderp.getName()+".Home.Location")));
						sender.sendMessage("You Are Now At Home");
					}
					catch(Exception e)
					{
						sender.sendMessage("Server Error");
						e.printStackTrace();
					}
					return true;
					
				}
				else
				{
					sender.sendMessage("Only players can execute this command");
				}			
				break;
			case "spawn":
				if(player)
				{
					senderp.teleport(senderp.getWorld().getSpawnLocation());
					
				}
				else
				{
					sender.sendMessage("Only players can execute this command");
				}	
				return true;
			case "tpa":
				if(player)
				{
					if(args.length == 1)
					{
						
					}
					else
					{
						sender.sendMessage("You Must Specify a player");
					}
					
				}
				else
				{
					sender.sendMessage("Only players can execute this command");
				}	
				return true;
			default: break;
		}
		return false;
	}
	
	public static boolean factionHandeler(CommandSender s, String[] args, FileConfiguration config, JavaPlugin plugin)
	{
		boolean player = false;
		Player splayer = null;
		if (s instanceof Player)
		{
			player = true;
			splayer = (Player) s;
		}
		switch (args[0])
		{
		case "found":
			if (args.length == 2)
			{
				if(PlayerConfigHandeler.getPlayerConfig(splayer, plugin).getConfig().getString("Faction") == null || PlayerConfigHandeler.getPlayerConfig(splayer, plugin).getConfig().getString("Faction") == "Default")
				{
					if (config.getStringList("Factions.List").contains(args[1]) | args[1] == "Default")
						{
					s.sendMessage("That name is taken");
					s.sendMessage("Try another one");
						}
				else
				{
					List<String> l = config.getStringList("Factions.List");
					l.add(args[1]);
					config.set("Factions.List", l);
					if (player)
					{
					config.set(args[1]+".Founder", s.getName());
					List<String> players = config.getStringList("args[1]+.Members");
					players.add(splayer.getName());
					config.set(args[1]+".Members", players);
					CustomConfig founderconfig = PlayerConfigHandeler.getPlayerConfig(splayer, plugin);
					founderconfig.getConfig().set("Faction.Rank", "Founder");
					founderconfig.getConfig().set("Faction", args[1]);
					founderconfig.saveConfig();
					}
					else
					{
						config.set(args[1]+".Founder", "Console");
					}
					s.sendMessage("Faction Created");
				}
				}
			}
			else
			{
				s.sendMessage("You didn't specify the faction name");
			}
			return true;
		case "leave":
			if (player)
			{
				List<String> players = config.getStringList("args[1]+.Members");
				players.remove(splayer.getName());
				config.set(args[1]+".Members", players);
				CustomConfig founderconfig = PlayerConfigHandeler.getPlayerConfig(splayer, plugin);
				founderconfig.getConfig().set("Faction.Rank", "Normal");
				founderconfig.getConfig().set("Faction", "Default");
				founderconfig.saveConfig();
			}
			else
			{
				s.sendMessage("Only players can execute this command");
			}
			return true;
		default:
			s.sendMessage("Invalid command, use /faction help for more info");
		}
		return false;
	}

	public static boolean pluginHandeler(CommandSender s, String[] args, Plugin plugin)
	{
		switch (args[0])
		{
			case "save":
				plugin.save();
				s.sendMessage("Files Saved");
				return true;
			case "default":
				plugin.saveDefaultConfig();;
				s.sendMessage("Files Saved");
				return true;
		}
		return false;
	}
}

