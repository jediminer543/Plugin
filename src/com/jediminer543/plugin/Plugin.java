package com.jediminer543.plugin;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
			case "random":
				if (player)
				{
					Random r = new Random();
					//Math Here
					//senderp.teleport(new Location(Bukkit.getServer().getWorlds().get(0), , 0, 0));
				}
				break;
			case "sethome":
				if(player)
				{
					WarpConfigHandeler.getConfig().set(senderp.getName()+".Home.Location", LocationHandeler.fromLoc(senderp.getLocation()));
					sender.sendMessage("Home Set");
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
					
				}
				break;
			case "spawn":
				if(player)
				{
					senderp.teleport(senderp.getWorld().getSpawnLocation());
				}
				break;
			default: break;
		}
		return false;
	}

}

