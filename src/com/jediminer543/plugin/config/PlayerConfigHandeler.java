package com.jediminer543.plugin.config;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.jediminer543.plugin.Plugin;
import com.jediminer543.plugin.config.objects.PlayerInfo;

public class PlayerConfigHandeler
{
	public static CustomConfig getPlayerConfig(Player p, JavaPlugin jp)
	{
		if (!(p == null))
		{
			return new CustomConfig(jp, "/players/"+p.getName()+".yml");
		}
		return null;
	}

	public static void writePlayerInfo(PlayerInfo P, Plugin jp)
	{
		CustomConfig pc =  getPlayerConfig(P.attachedPlayer, jp);
		if (!(P.faction == null))
		{
		pc.getConfig().set("Faction", P.faction.factionName);
		
		}
	}
}
