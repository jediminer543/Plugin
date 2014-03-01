package com.jediminer543.plugin.config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import com.jediminer543.plugin.Plugin;
import com.jediminer543.plugin.config.objects.Faction;
import com.jediminer543.plugin.config.objects.PlayerInfo;

public class FactionConfig extends CustomConfig {

	public FactionConfig(Plugin plugin, String fileName)
	{
		super(plugin, fileName);
	}
	
	public Faction getFaction(String name)
	{
		if (name == null)
		{
			return null;
		}
		else
		{
			Faction faction = new Faction(name);
			String founderName = this.getConfig().getString(name+".Founder");
			PlayerInfo founder = new PlayerInfo(Bukkit.getPlayer(founderName)); 
			faction.founder = founder;
			List<PlayerInfo> recruts = new ArrayList<PlayerInfo>();
			for (String s: this.getConfig().getStringList(name+".Recrutes"))
			{
				recruts.add(new PlayerInfo(Bukkit.getPlayer(s)));
			}
			List<PlayerInfo> members = new ArrayList<PlayerInfo>();
			for (String s: this.getConfig().getStringList(name+".Members"))
			{
				members.add(new PlayerInfo(Bukkit.getPlayer(s)));
				recruts.add(new PlayerInfo(Bukkit.getPlayer(s)));
			}
			List<PlayerInfo> officers = new ArrayList<PlayerInfo>();
			for (String s: this.getConfig().getStringList(name+".Officers"))
			{
				officers.add(new PlayerInfo(Bukkit.getPlayer(s)));
				members.add(new PlayerInfo(Bukkit.getPlayer(s)));
				recruts.add(new PlayerInfo(Bukkit.getPlayer(s)));
			}
			faction.recruts = recruts;
			faction.members = members;
			faction.officers = officers;
			return faction;
		}
	}

}
