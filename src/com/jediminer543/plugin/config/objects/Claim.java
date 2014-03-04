package com.jediminer543.plugin.config.objects;

import java.util.List;

import org.bukkit.Chunk;

/**
 * Represents A Claim In World
 */
public class Claim
{
	public Owner owner;
	public List<Owner> trusted;
	public Chunk chunk;
	public boolean isClaimed;
	
	public String getOwnerName()
	{
		if (this.owner instanceof Faction)
		{
			Faction owner = (Faction)this.owner;
			return owner.factionName;
		}
		else
		{
			PlayerInfo owner = (PlayerInfo)this.owner;
			return owner.attachedPlayer.getName();
		}
	}
	
	public boolean isTrusted(Owner owner)
	{
		for (Owner thisowner: trusted)
		{
		if (thisowner instanceof PlayerInfo)
		{
			if (owner instanceof PlayerInfo)
			{
				PlayerInfo ownerThis = (PlayerInfo)thisowner;
				PlayerInfo ownerTest = (PlayerInfo)owner;
				if (ownerThis.attachedPlayer.getName() == ownerTest.attachedPlayer.getName())
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else if (owner instanceof Faction)
			{
				return false;
			}
		}
		else if (thisowner instanceof Faction)
		{
			if (owner instanceof PlayerInfo)
			{
				return false;
			}
			else if (owner instanceof Faction)
			{
				if (owner instanceof PlayerInfo)
				{
					Faction ownerThis = (Faction)thisowner;
					Faction ownerTest = (Faction)owner;
					if (ownerThis.factionName == ownerTest.factionName)
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
		return false;
	}
	
	public boolean isOwner(Owner owner)
	{
		if (this.owner instanceof PlayerInfo)
		{
			if (owner instanceof PlayerInfo)
			{
				PlayerInfo ownerThis = (PlayerInfo)this.owner;
				PlayerInfo ownerTest = (PlayerInfo)owner;
				if (ownerThis.attachedPlayer.getName() == ownerTest.attachedPlayer.getName())
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else if (owner instanceof Faction)
			{
				return false;
			}
		}
		else if (this.owner instanceof Faction)
		{
			if (owner instanceof PlayerInfo)
			{
				return false;
			}
			else if (owner instanceof Faction)
			{
				if (owner instanceof PlayerInfo)
				{
					Faction ownerThis = (Faction)this.owner;
					Faction ownerTest = (Faction)owner;
					if (ownerThis.factionName == ownerTest.factionName)
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
		return false;
	}
}
