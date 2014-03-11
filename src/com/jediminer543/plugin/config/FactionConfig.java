package com.jediminer543.plugin.config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.jediminer543.plugin.Plugin;
import com.jediminer543.plugin.config.objects.Faction;
import com.jediminer543.plugin.config.objects.PlayerInfo;

public class FactionConfig extends CustomConfig {

	Plugin plugin;
	
	public FactionConfig(Plugin plugin, String fileName)
	{
		super(plugin, fileName);
		this.plugin = plugin;
	}
	
	/**
	 * 
	 * @param name Name of the faction
	 * @return
	 */
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
			if (!(founderName == null))
			{
			PlayerInfo founder = new PlayerInfo(Bukkit.getPlayer(founderName));  // Error Here
			faction.founder = founder;
			List<PlayerInfo> recruit = new ArrayList<PlayerInfo>();
			for (String s: this.getConfig().getStringList(name+".Recruits"))
			{
				recruit.add(new PlayerInfo(Bukkit.getPlayer(s)));
			}
			List<PlayerInfo> members = new ArrayList<PlayerInfo>();
			for (String s: this.getConfig().getStringList(name+".Members"))
			{
				members.add(new PlayerInfo(Bukkit.getPlayer(s)));
				recruit.add(new PlayerInfo(Bukkit.getPlayer(s)));
			}
			List<PlayerInfo> officers = new ArrayList<PlayerInfo>();
			for (String s: this.getConfig().getStringList(name+".Officers"))
			{
				officers.add(new PlayerInfo(Bukkit.getPlayer(s)));
				members.add(new PlayerInfo(Bukkit.getPlayer(s)));
				recruit.add(new PlayerInfo(Bukkit.getPlayer(s)));
			}
			faction.recruits = recruit;
			faction.members = members;
			faction.officers = officers;
			faction.open = this.getConfig().getBoolean(faction.factionName+".Open", false);
			List<PlayerInfo> invited = new ArrayList<PlayerInfo>();
			for (String s: this.getConfig().getStringList(name+".Invited"))
			{
				invited.add(new PlayerInfo(Bukkit.getPlayer(s)));
			}
			faction.invited = invited;
			}
			else
			{
				faction.open = this.getConfig().getBoolean(faction.factionName+".Open", false);
				faction.invited = new ArrayList<PlayerInfo>();
				faction.members = new ArrayList<PlayerInfo>();
				faction.officers = new ArrayList<PlayerInfo>();
				faction.recruits = new ArrayList<PlayerInfo>();
			}
			return faction;
		}
	}

	/**
	 * 
	 * @param name Name of the faction
	 * @return
	 */
	public void writeFaction(Faction faction)
	{
		if (faction == null)
		{
		}
		else
		{	
			this.getConfig().set(faction.factionName+".Founder", faction.founder.attachedPlayer.getName());
			List<String> recruit = new ArrayList<String>();
			for (PlayerInfo P: faction.recruits)
			{
				recruit.add(P.attachedPlayer.getName());
			}
			this.getConfig().set(faction.factionName+".Recruits", recruit);
			List<String> members = new ArrayList<String>();
			for (PlayerInfo P: faction.members)
			{
				members.add(P.attachedPlayer.getName());
			}
			this.getConfig().set(faction.factionName+".Members", members);
			List<String> officers = new ArrayList<String>();
			for (PlayerInfo P: faction.officers)
			{
				officers.add(P.attachedPlayer.getName());
			}
			this.getConfig().set(faction.factionName+".Officers", officers);
			List<String> invited = new ArrayList<String>();
			for (PlayerInfo P: faction.invited)
			{
				invited.add(P.attachedPlayer.getName());
			}
			this.getConfig().set(faction.factionName+".Invited", invited);
			this.getConfig().set(faction.factionName+".Open", faction.open);
		}
	}
	
	
	public List<String> getFactionList()
	{
		return this.getConfig().getStringList("Factions.List");
	}
	
	public PlayerInfo getPlayerInfo(Player p)
	{
		if (p == null)
		{
		return null;
		}
		else
		{
			PlayerInfo pi = new PlayerInfo(p);
			pi.faction = this.getFaction(PlayerConfigHandeler.getPlayerConfig(p, plugin).getConfig().getString("Faction"));
			return pi;
		}
	}
}
