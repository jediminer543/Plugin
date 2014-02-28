package com.jediminer543.plugin.config.objects;

import java.util.List;

import org.bukkit.entity.Player;

public class Faction implements Owner
{

	public Player founder;
	public List<Player> members;
	public List<Player> officers;
	public List<Player> recrutes;
	public String factionName;
	
}
