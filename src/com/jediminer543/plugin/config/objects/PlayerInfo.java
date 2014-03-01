package com.jediminer543.plugin.config.objects;

import org.bukkit.entity.Player;

/**
 * Information About a player connected or not
 */
public class PlayerInfo implements Owner
{
	public Player attachedPlayer;
	public Faction faction;
	
	public PlayerInfo(Player p)
	{
		attachedPlayer = p;
	}
	
}
