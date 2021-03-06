package com.jediminer543.plugin.listeners;

import java.util.List;

import com.jediminer543.plugin.config.CustomConfig;
import com.jediminer543.plugin.config.PlayerConfigHandeler;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerListener implements Listener
{
	JavaPlugin plugin;
	public PlayerListener(JavaPlugin plugin)
	{
		this.plugin = plugin;
	}
	
    @EventHandler(priority = EventPriority.MONITOR)
    public void onLogin(PlayerJoinEvent event)
    {
    	plugin.getLogger().info("Player logged on");
    	Player p = event.getPlayer();
    	FileConfiguration pluginconfig = plugin.getConfig();
    	List<String> playerlist = pluginconfig.getStringList("Server.Players.List");
    	if (!playerlist.contains(p.getName()))
    	{
    		Bukkit.getServer().broadcastMessage("Welcome "+p.getDisplayName()+" to the server");
    		p.sendMessage("Welcome to "+plugin.getConfig().getString("Server.Name"));
    		CustomConfig pc = PlayerConfigHandeler.getPlayerConfig(p, plugin);
    		pc.getConfig().set("Faction", "Default");
    		pc.getConfig().set("Server.Rank", "Standard");
    		playerlist.add(p.getName());
    		pluginconfig.set("Server.Players.List", playerlist);
    		plugin.saveConfig();
    		plugin.getLogger().info("New Player added to config");
    	}
    }
    
    @EventHandler
    public void onDeath(EntityDeathEvent event)
    {
    	

    }

}
