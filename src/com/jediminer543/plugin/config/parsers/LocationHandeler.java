package com.jediminer543.plugin.config.parsers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationHandeler {

	public static List<String> fromLoc(Location l)
	{
		if (!(l == null))
		{
			//{l.getWorld().getName(), l.getBlockX(), l.getBlockY(), l.getBlockZ()}
			List<String> loc = new ArrayList<String>();
			loc.add(l.getWorld().getName());
			loc.add(Integer.toString(l.getBlockX()));
			loc.add(Integer.toString(l.getBlockY()));
			loc.add(Integer.toString(l.getBlockZ()));
			return  loc;
		}
		return null;
		
	}
	
	public static Location toLoc(List<String> s)
	{
		if (!(s == null))
		{
			return new Location(Bukkit.getWorld(s.get(0)), Integer.valueOf(s.get(1)), Integer.valueOf(s.get(2)), Integer.valueOf(s.get(3)));
		}
		return null;
	}


}
