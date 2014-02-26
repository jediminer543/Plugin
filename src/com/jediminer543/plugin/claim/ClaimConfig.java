package com.jediminer543.plugin.claim;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.jediminer543.plugin.CustomConfig;
import com.jediminer543.plugin.config.parsers.LocationHandeler;

public class ClaimConfig extends CustomConfig
{

	public ClaimConfig(JavaPlugin plugin, String fileName)
	{
		super(plugin, fileName);
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
		return false;
		}
	}
	


}
