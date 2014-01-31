package com.jediminer543.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;



public final class Plugin extends JavaPlugin 
{
	
    @Override
    public void onEnable(){
    	getLogger().info("onEnable has been invoked!");
    	this.saveDefaultConfig();
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
			case "sethome":
			//case "seth":
				if(player)
				{
					
					sender.sendMessage(senderp.getLocation().toString());
					sender.sendMessage("Home Set If It Was Implemented");
				}
			case "spawn":
				if(player)
				{
					senderp.teleport(senderp.getBedSpawnLocation());
				}
			default: break;
		}
		return false;
	}

}
