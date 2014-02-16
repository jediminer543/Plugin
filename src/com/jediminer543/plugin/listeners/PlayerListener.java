package com.jediminer543.plugin.listeners;

import java.util.List;

import com.jediminer543.plugin.*;
import com.jediminer543.plugin.config.PlayerConfigHandeler;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerListener implements Listener
{
	JavaPlugin plugin;
	public PlayerListener(JavaPlugin plugin)
	{
		this.plugin = plugin;
	}
	
    @EventHandler
    public void onLogin(PlayerLoginEvent event)
    {
    	plugin.getLogger().info("Player logged on");
    	Player p = event.getPlayer();
    	FileConfiguration pluginconfig = plugin.getConfig();
    	List<String> playerlist = pluginconfig.getStringList("Server.Players.List");
    	if (!playerlist.contains(p.getName()))
    	{
    		Bukkit.getServer().broadcastMessage("Welcome "+p.getDisplayName()+" to the server");
    		p.sendMessage("Welcom to "+plugin.getConfig().getString("Server.Name"));
    		CustomConfig pc = PlayerConfigHandeler.getPlayerConfig(p, plugin);
    		pc.getConfig().set("Faction", "Default");
    		pc.getConfig().set("Server.Rank", "Standard");
    		playerlist.add(p.getDisplayName());
    		pluginconfig.set("Server.Players.List", playerlist);
    		plugin.saveConfig();
    		plugin.getLogger().info("New Player added to config");
    	}
        Plugin.playerList.add(p);
    }
    
    @EventHandler
    public void onDeath(EntityDeathEvent event)
    {
    	

    }

}
