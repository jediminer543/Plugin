package com.jediminer543.plugin.config.objects;

import java.util.List;

public class Faction implements Owner
{

	public PlayerInfo founder; //playerinfo player only
	public List<PlayerInfo> recruits;  //playerinfo player only
	public List<PlayerInfo> members;  //playerinfo player only
	public List<PlayerInfo> officers;  //playerinfo player only
	public String factionName;
	public boolean open; //wether the faction is open or not
	public List<PlayerInfo> invited;  //playerinfo player only
	
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
	
	public boolean canJoin(PlayerInfo p)
	{
		if(p ==null)
		{
			return false;
		}
		else
		{
			if (open)
			{
			return true;
			}
			else
			{
				if (invited.contains(p))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
	}
}
