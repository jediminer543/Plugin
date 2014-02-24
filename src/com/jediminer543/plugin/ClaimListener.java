package com.jediminer543.plugin;

import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.jediminer543.plugin.config.parsers.LocationHandeler;

public class ClaimListener implements Listener 
{
	FileConfiguration config = null;
	/*

	@EventHandler
    public void onBlockBreak(PlayerLoginEvent event)
    {
    */
	public ClaimListener(CustomConfig ClaimConfig)
	{
		this.config = ClaimConfig.getConfig();
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event)
    {
		Block block = event.getBlock();
		Chunk blockChunk = block.getChunk();
		if (config.getBoolean(LocationHandeler.toConfigHandler(blockChunk)+".claimed", false) == true)
		{
			if(!(config.getString(LocationHandeler.toConfigHandler(blockChunk)+".owner") == event.getPlayer().getName()))
			{
			event.getPlayer().sendMessage("This chunk is claimed by: "+config.getString(LocationHandeler.toConfigHandler(blockChunk)+".owner")+" thus you cant break blocks here.");
			event.setCancelled(true);
			}
		}
		
    }
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockIgnite(BlockPlaceEvent event)
    {
		Block block = event.getBlock();
		Chunk blockChunk = block.getChunk();
		if (config.getBoolean(LocationHandeler.toConfigHandler(blockChunk)+".claimed", false) == true)
		{
			if(!(config.getString(LocationHandeler.toConfigHandler(blockChunk)+".owner") == event.getPlayer().getName()))
			{
			event.getPlayer().sendMessage("This chunk is claimed by: "+config.getString(LocationHandeler.toConfigHandler(blockChunk)+".owner")+" thus you cant place blocks here.");
			event.setCancelled(true);
			}
		}
		
    }
}
