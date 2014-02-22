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
	/**
	 * A list of players on server
	 */
	public static List<Player> playerList = new ArrayList<Player>();
	
	/**
	 * The faction config
	 */
	CustomConfig FactionConfigHandeler = new CustomConfig(this, "factions.yml");
	
	/**
	 * The location config
	 */
	CustomConfig WarpConfigHandeler = new CustomConfig(this, "locations.yml");
	
	/**
	 * Called when the plugin loads up
	 */
    @Override
    public void onEnable(){
    	//Tells the console the plugin is loading
    	getLogger().info("Plugin Loading Please Wait");
    	//Initiates the configs
    	FactionConfigHandeler = new CustomConfig(this, "factions.yml");
    	WarpConfigHandeler = new CustomConfig(this, "locations.yml");
    	WarpConfigHandeler.reloadConfig();
    	FactionConfigHandeler.reloadConfig();
    	//Loads a list of players on the server
    	if (this.getConfig().getBoolean("Plugin.Config.NotReload") == false)
    	{
    		this.saveDefaultConfig();
    	}
    	/*Registers PlayerEventHadndler
    	 * @see com.jediminer543.plugin.listeners.PlayerListener
    	 */
    	Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    	for (Player player : this.getServer().getOnlinePlayers()) {
    		 playerList.add(player);
    	}
    }
 
    /**
     * Called when the server shuts down
     */
    @Override
    public void onDisable() {
    	//Alerts Console
    	getLogger().info("Plugin shutting down, plese wait");
    	getLogger().info("Writing configs");
    	//Writes Configs
    	this.save();
    	

    }
    
    /**
     * Saves plugin data
     */
    public void save()
    {
    	//Saves Configs
    	this.saveConfig();
    	WarpConfigHandeler.saveConfig();
    	FactionConfigHandeler.saveConfig();
    	//Writes Player Location To Thier Player Config
    	for (Player player : playerList) {
    		FileConfiguration pconfig = PlayerConfigHandeler.getPlayerConfig(player,this).getConfig();
    		pconfig.set("Backup.Loc", LocationHandeler.fromLoc(player.getLocation()));
    		
   	}
    }
    
    /**
     * Handles commands
     * Some Commands are passed to separate handlers
     * @return Command Valid (not successful)
     */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args)
	{
		//Varifies if sender is player
		boolean player = false;
		Player senderp = null;
		if (sender instanceof Player)
		{
			player = true;
			senderp = (Player) sender;
		}
		
		//standardises command
		switch (cmd.getName().toLowerCase())
		{
			case "plugin":
				return pluginHandeler(sender, args, this);
			case "faction":
				return factionHandeler(sender, args, FactionConfigHandeler.getConfig(), this);
			
				/*
				 * Handles Random command 
				 * Sends player to random location
				 */ 
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
				//Handels Spawn Command
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
						// tpa code here
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
	
	/**
	 * TODO Move to own class
	 * 
	 * @param s Command sender
	 * @param args Command arguments
	 * @param config Faction configuration file
	 * @param plugn The plugin (pass 'this')
	 * @return Did sender use correct syntax for command
	 */
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
					//Adds Faction To Faction Config
					List<String> l = config.getStringList("Factions.List");
					l.add(args[1]);
					config.set("Factions.List", l);
					if (player)
					{
						//Adds Player Data to Faction Config
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
		case "join":
			if (player)
			{
				if (!(args.length == 2))
				{
					s.sendMessage("You didn't specify a faction");
					s.sendMessage("Correct usage /faction join <faction to join>");
				}
				else
				{
					if (config.getBoolean(args[1]+".Joinable", false))
					{
						CustomConfig joinerconfig = PlayerConfigHandeler.getPlayerConfig(splayer, plugin);
						joinerconfig.getConfig().set("Faction.Rank", "Normal");
						joinerconfig.getConfig().set("Faction", args[1]);
						joinerconfig.saveConfig();
					}
					else
					{
						if (config.getBoolean(args[1]+"."+s.getName()+".Invited", false))
						{
							CustomConfig joinerconfig = PlayerConfigHandeler.getPlayerConfig(splayer, plugin);
							joinerconfig.getConfig().set("Faction.Rank", "Normal");
							joinerconfig.getConfig().set("Faction", args[1]);
							joinerconfig.saveConfig();
						}
						else
						{
							s.sendMessage("This Faction is unjoinable");
						}
					}
				}

			}

			else
			{
				s.sendMessage("Only players can execute this command");
			}
			return true;
		case "sethome":
			if (player)
			{
				FileConfiguration playerconfig = PlayerConfigHandeler.getPlayerConfig(splayer, plugin).getConfig();
				if (playerconfig.getString("Faction.Rank", "Normal") == "Founder")
						{
					config.set(playerconfig.getString("Faction")+".Home.Loc", LocationHandeler.fromLoc(splayer.getLocation()));
						}
				else
				{
					s.sendMessage("Only Faction Founders can execute this command");
				}


			}
			else
			{
				s.sendMessage("Only players can execute this command");
			}
			return true;
		case "home":
			if (player)
			{
				FileConfiguration playerconfig = PlayerConfigHandeler.getPlayerConfig(splayer, plugin).getConfig();
				splayer.teleport(LocationHandeler.toLoc(config.getStringList(playerconfig.getString("Faction")+".Home.Loc")));
			}
			else
			{
				s.sendMessage("Only players can execute this command");
			}
			return true;
		case "open":
			if (player)
			{
			FileConfiguration playerconfig = PlayerConfigHandeler.getPlayerConfig(splayer, plugin).getConfig();
			if (playerconfig.getString("Faction.Rank", "Normal") == "Founder")
					{
				config.set(playerconfig.getString("Faction")+".Joinable", true);
					}
			else
			{
				s.sendMessage("Only Faction Founders can execute this command");
			}
			}
			else
			{
				s.sendMessage("Only players can execute this command");
				s.sendMessage("Non player implementation will be implemented soon");
			}
			return true;
		case "close":
			if (player)
			{
			FileConfiguration playerconfig = PlayerConfigHandeler.getPlayerConfig(splayer, plugin).getConfig();
			if (playerconfig.getString("Faction.Rank", "Normal") == "Founder")
					{
				config.set(playerconfig.getString("Faction")+".Joinable", true);
					}
			else
			{
				s.sendMessage("Only Faction Founders can execute this command");
			}
			}
			else
			{
				s.sendMessage("Only players can execute this command");
				s.sendMessage("Non player implementation will be implemented soon");
			}
			return true;
		default:
			s.sendMessage("Invalid command, use /faction help for more info");
		}
		return false;
	}

	/**
	 * TODO move to own class
	 * 
	 * @param s The Commend Sender
	 * @param args command arguments
	 * @param plugin The Plugin ( Pass 'this')
	 * @return Did command use correct syntax
	 */
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

