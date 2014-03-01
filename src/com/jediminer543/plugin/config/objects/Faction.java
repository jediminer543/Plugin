package com.jediminer543.plugin.config.objects;

import java.util.List;

public class Faction implements Owner
{

	public PlayerInfo founder;
	public List<PlayerInfo> recruts;
	public List<PlayerInfo> members;
	public List<PlayerInfo> officers;
	public String factionName;
	
	public Faction(String name)
	{
		factionName = name;
	}
	
	
}
