package com.jediminer543.plugin;

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
    }
 
    @Override
    public void onDisable() {
    	getLogger().info("onEnable has been invoked!");

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
			case "test":
				sender.sendMessage("Test Sucsesfull");
				break;
			case "sethome":
				if(player)
				{
					
					sender.sendMessage(senderp.getLocation().toString());
					WarpConfigHandeler.getConfig().set(senderp.getName()+".Home.Location", LocationHandeler.fromLoc(senderp.getLocation()));
					sender.sendMessage("Home Set");
				}
				break;
			case "home":
				if(player)
				{
					senderp.teleport(LocationHandeler.toLoc(WarpConfigHandeler.getConfig().getStringList(senderp.getName()+".Home.Location")));
					sender.sendMessage("You Are Now At Home");
				}
				break;
			case "spawn":
				if(player)
				{
					senderp.sendMessage(senderp.getBedSpawnLocation().toString());
					senderp.teleport(senderp.getBedSpawnLocation());
				}
				break;
			default: break;
		}
		return false;
	}

}
