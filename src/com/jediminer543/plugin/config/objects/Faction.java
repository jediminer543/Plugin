package com.jediminer543.plugin.config.objects;

import java.util.List;

public class Faction implements Owner
{

	public PlayerInfo founder;
	public List<PlayerInfo> recruits;
	public List<PlayerInfo> members;
	public List<PlayerInfo> officers;
	public String factionName;
	public boolean open;
	
	public Faction(String name)
	{
		factionName = name;
	}
	
	public void addRecruit(PlayerInfo p)
	{
		recruits.add(p);
	}
	
	public void addMember(PlayerInfo p)
	{
		recruits.add(p);
		members.add(p);
	}
	
	public void addOfficer(PlayerInfo p)
	{
		recruits.add(p);
		members.add(p);
		officers.add(p);
	}
	
}
