package com.jediminer543.plugin.claim;

import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
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
}
