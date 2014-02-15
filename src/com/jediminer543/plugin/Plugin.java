package com.jediminer543.plugin;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.jediminer543.plugin.config.LocationHandeler;



public final class Plugin extends JavaPlugin 
{
	CustomConfig FactionConfigHandeler = new CustomConfig(this, "Factions.yml");
	CustomConfig WarpConfigHandeler = new CustomConfig(this, "Locations.yml");
	
    @Override
    public void onEnable(){
    	getLogger().info("onEnable has been invoked!");
    	FactionConfigHandeler = new CustomConfig(this, "Factions.yml");
    	WarpConfigHandeler = new CustomConfig(this, "Locations.yml");
    	WarpConfigHandeler.reloadConfig();
    	FactionConfigHandeler.reloadConfig();
    }
 
    @Override
    public void onDisable() {
    	getLogger().info("onEnable has been invoked!");
    	WarpConfigHandeler.saveConfig();
    	FactionConfigHandeler.saveConfig();
    	

    }
    
    public void save()
    {
    	this.saveConfig();
    	WarpConfigHandeler.saveConfig();
    	FactionConfigHandeler.saveConfig();
    }
    
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args)
	{
		boolean player = false;
		Player senderp;
		if (sender instanceof Player)
			player = true;
			senderp = (Player) sender;
		switch (cmd.getName().toLowerCase())
		{
			case "plugin":
				return pluginHandeler(sender, args, this);
			case "faction":
				return factionHandeler(sender, args, FactionConfigHandeler.getConfig());
			case "random":
				if (player)
				{
					Random r = new Random();
					//Math Here
					//senderp.teleport(new Location(Bukkit.getServer().getWorlds().get(0), , 0, 0));
					return true;
				}
				break;
			case "sethome":
				if(player)
				{
					WarpConfigHandeler.getConfig().set(senderp.getName()+".Home.Location", LocationHandeler.fromLoc(senderp.getLocation()));
					sender.sendMessage("Home Set");
					return true;
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
			default: break;
		}
		return false;
	}
	
	public static boolean factionHandeler(CommandSender s, String[] args, FileConfiguration config)
	{
		boolean player = false;
		Player complayer = null;
		if (s instanceof Player)
			player = true;
			complayer = (Player) s;
		switch (args[0])
		{
		case "add":
			if (args.length == 2)
			{
				//config.set("");
			}
			else
			{
				s.sendMessage("You didn't specify the faction name");
			}
			return true;
		default:
			s.sendMessage("Invalid command use /faction help for more info");
		}
		return false;
	}

	public static boolean pluginHandeler(CommandSender s, String[] args, Plugin plugin)
	{
		switch (args[0])
		{
			case "save":
				plugin.save();
				
		}
		return false;
	}
}

