package com.jediminer543.plugin.config;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.jediminer543.plugin.CustomConfig;

public class PlayerConfigHandeler
{
	public static CustomConfig getPlayerConfig(Player p, JavaPlugin jp)
	{
		if (!(p == null))
		{
			return new CustomConfig(jp, "/players/"+p.getDisplayName()+".yml");
		}
		return null;
	}

}
