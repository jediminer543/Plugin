package com.jediminer543.plugin.config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import com.jediminer543.plugin.Plugin;
import com.jediminer543.plugin.config.objects.Claim;
import com.jediminer543.plugin.config.objects.Owner;
import com.jediminer543.plugin.config.objects.PlayerInfo;
import com.jediminer543.plugin.config.parsers.LocationHandeler;

public class ClaimConfig extends CustomConfig
{

	Plugin plugin;
	
	public ClaimConfig(Plugin plugin, String fileName)
	{
		super(plugin, fileName);
		this.plugin = plugin;
	}
	
	public boolean getClaimed(Chunk chunk)
	{
		if (chunk == null)
		{
		return false;
		}
		else 
		{
			if(this.getConfig().getBoolean(LocationHandeler.toConfigHandler(chunk)+".claimed"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	public Player getOwner(Chunk chunk)
	{
		if (this.getClaimed(chunk))
		{
			return Bukkit.getPlayer(this.getConfig().getString(LocationHandeler.toConfigHandler(chunk)+".owner"));
		}
		else
		{
			return null;
		}
	}
	
	public boolean isOwner(Chunk chunk, Player p)
	{
		if (this.getClaimed(chunk))
		{
			if (this.getOwner(chunk) == p)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
		return true;
		}
	}
	
	public boolean isTrusted(Chunk chunk, Player p)
	{
		if (this.getClaimed(chunk))
		{
			List<String> trustedList = this.getConfig().getStringList(LocationHandeler.toConfigHandler(chunk)+".trusted");
			trustedList.add(this.getConfig().getString(LocationHandeler.toConfigHandler(chunk)+".owner"));
			if (trustedList.contains(p.getName()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
		return true;
		}
	}
	
	public Claim fillClaim(Chunk chunk)
	{
		if (chunk == null)
		{
			return null;
		}
		else
		{
			Claim claim = new Claim();
			claim.chunk = chunk;
			String ownerName = this.getConfig().getString(LocationHandeler.toConfigHandler(chunk)+".owner");
			if (ownerName.startsWith("Faction-"))
			{
				claim.owner = plugin.FactionConfigHandeler.getFaction(ownerName.split("Faction-")[1]);
			}
			else
			{
				claim.owner = new PlayerInfo(Bukkit.getPlayer(ownerName));
			}
			List<Owner> trusted = new ArrayList<Owner>();
			for (String trustedName: this.getConfig().getStringList(LocationHandeler.toConfigHandler(chunk)+".trusted"))
			{
				if (trustedName.startsWith("(Faction)"))
				{
					trusted.add(plugin.FactionConfigHandeler.getFaction(trustedName.split("Faction-")[1]));
				}
				else
				{
					 trusted.add(new PlayerInfo(Bukkit.getPlayer(trustedName)));
				}
			}
			claim.trusted = trusted;
			
		}
		return null;
	}
	
}
