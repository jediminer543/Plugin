package com.jediminer543.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin 
{
	
    @Override
    public void onEnable(){

    }
 
    @Override
    public void onDisable() {

    }
    
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args)
	{
		boolean player = false;
		if (sender instanceof Player)
			player = true;
		switch (cmd.getName().toLowerCase())
		{
			case "test":
				sender.sendMessage("Test Sucsesfull");
			default: break;
		}
		return false;
	}

}
