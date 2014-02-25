package com.jediminer543.plugin.claim;

import org.bukkit.Chunk;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.block.BrewingStand;
import org.bukkit.block.Chest;
import org.bukkit.block.Dispenser;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.Dropper;
import org.bukkit.block.Furnace;
import org.bukkit.block.Hopper;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

import com.jediminer543.plugin.config.parsers.LocationHandeler;

public class ClaimListener implements Listener 
{
	ClaimConfig config = null;
	/*

	@EventHandler
    public void onBlockBreak(PlayerLoginEvent event)
    {
    */
	public ClaimListener(ClaimConfig ClaimConfig)
	{
		this.config = ClaimConfig;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event)
    {
		Block block = event.getBlock();
		Chunk blockChunk = block.getChunk();
		if (config.getClaimed(blockChunk))
		{
			if(!config.isTrusted(blockChunk, event.getPlayer()))
			{
			event.getPlayer().sendMessage("This chunk is claimed by: "+config.getConfig().getString(LocationHandeler.toConfigHandler(blockChunk)+".owner")+" thus you cant break blocks here.");
			event.setCancelled(true);
			}
		}
		
    }
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockIgnite(BlockPlaceEvent event)
    {
		Block block = event.getBlock();
		Chunk blockChunk = block.getChunk();
		if (config.getClaimed(blockChunk))
		{
			if(!config.isTrusted(blockChunk, event.getPlayer()))
			{
			event.getPlayer().sendMessage("This chunk is claimed by: "+config.getConfig().getString(LocationHandeler.toConfigHandler(blockChunk)+".owner")+" thus you cant place blocks here.");
			event.setCancelled(true);
			}
		}
		
    }
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onFluidPlaced(PlayerBucketEmptyEvent event)
    {
		Block block = event.getBlockClicked();
		Chunk blockChunk = block.getChunk();
		if (config.getClaimed(blockChunk))
		{
			if(!config.isTrusted(blockChunk, event.getPlayer()))
			{
			event.getPlayer().sendMessage("This chunk is claimed by: "+config.getConfig().getString(LocationHandeler.toConfigHandler(blockChunk)+".owner")+" thus you cant empty buckets here.");
			event.setCancelled(true);
			}
		}
		
    }
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onCreatureSpawn(CreatureSpawnEvent event)
    {
		Chunk blockChunk = event.getEntity().getLocation().getChunk();
		if (config.getClaimed(blockChunk))
		{
			event.setCancelled(true);
		}
		
    }
	
	/*
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageEvent event)
    {
		System.out.println("Claim Event Detected");
		Chunk blockChunk = event.getEntity().getLocation().getChunk();
		DamageCause dc = event.getCause();
		if (config.getClaimed(blockChunk))
		{
			System.out.println("Spawn Detected in claimed chunk blocked");
			event.setCancelled(true);
		}
		
    }
    */
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryOpenEvent(InventoryOpenEvent event){
		if (event.getInventory().getHolder() instanceof Chest)
		{
			Chest c = (Chest) event.getInventory().getHolder();
			if(config.getClaimed(c.getChunk()));
			{
				if(!(config.isTrusted(c.getChunk(),(Player) event.getPlayer())))
					{
					event.setCancelled(true);
					}
			}
		}
		else if (event.getInventory().getHolder() instanceof DoubleChest)
		{
			DoubleChest c = (DoubleChest) event.getInventory().getHolder();
			if(config.getClaimed(c.getLocation().getChunk()));
			{
				if(!(config.isTrusted(c.getLocation().getChunk(),(Player) event.getPlayer())))
					{
					event.setCancelled(true);
					}
			}
		}
		else if (event.getInventory().getHolder() instanceof Beacon)
		{
			Beacon c = (Beacon) event.getInventory().getHolder();
			if(config.getClaimed(c.getChunk()));
			{
				if(!(config.isTrusted(c.getChunk(),(Player) event.getPlayer())))
					{
					event.setCancelled(true);
					}
			}
		}
		else if (event.getInventory().getHolder() instanceof BrewingStand)
		{
			BrewingStand c = (BrewingStand) event.getInventory().getHolder();
			if(config.getClaimed(c.getChunk()));
			{
				if(!(config.isTrusted(c.getChunk(),(Player) event.getPlayer())))
					{
					event.setCancelled(true);
					}
			}
		}
		else if (event.getInventory().getHolder() instanceof Dispenser)
		{
			Dispenser c = (Dispenser) event.getInventory().getHolder();
			if(config.getClaimed(c.getChunk()));
			{
				if(!(config.isTrusted(c.getChunk(),(Player) event.getPlayer())))
					{
					event.setCancelled(true);
					}
			}
		}
		else if (event.getInventory().getHolder() instanceof Furnace)
		{
			Furnace c = (Furnace) event.getInventory().getHolder();
			if(config.getClaimed(c.getChunk()));
			{
				if(!(config.isTrusted(c.getChunk(),(Player) event.getPlayer())))
					{
					event.setCancelled(true);
					}
			}
		}
		else if (event.getInventory().getHolder() instanceof Dropper)
		{
			Dropper c = (Dropper) event.getInventory().getHolder();
			if(config.getClaimed(c.getChunk()));
			{
				if(!(config.isTrusted(c.getChunk(),(Player) event.getPlayer())))
					{
					event.setCancelled(true);
					}
			}
		}
		else if (event.getInventory().getHolder() instanceof Hopper)
		{
			Hopper c = (Hopper) event.getInventory().getHolder();
			if(config.getClaimed(c.getChunk()));
			{
				if(!(config.isTrusted(c.getChunk(),(Player) event.getPlayer())))
					{
					event.setCancelled(true);
					}
			}
		}
		else if (event.getInventory().getHolder() instanceof Horse)
		{
			Horse c = (Horse) event.getInventory().getHolder();
			if(config.getClaimed(c.getLocation().getChunk()));
			{
				if(!(config.isTrusted(c.getLocation().getChunk(),(Player) event.getPlayer())))
					{
					event.setCancelled(true);
					}
			}
		}
		else if (event.getInventory().getHolder() instanceof HopperMinecart)
		{
			HopperMinecart c = (HopperMinecart) event.getInventory().getHolder();
			if(config.getClaimed(c.getLocation().getChunk()));
			{
				if(!(config.isTrusted(c.getLocation().getChunk(),(Player) event.getPlayer())))
					{
					event.setCancelled(true);
					}
			}
		}
		else if (event.getInventory().getHolder() instanceof StorageMinecart)
		{
			StorageMinecart c = (StorageMinecart) event.getInventory().getHolder();
			if(config.getClaimed(c.getLocation().getChunk()));
			{
				if(!(config.isTrusted(c.getLocation().getChunk(),(Player) event.getPlayer())))
					{
					event.setCancelled(true);
					}
			}
		}
		
		

    }
}
