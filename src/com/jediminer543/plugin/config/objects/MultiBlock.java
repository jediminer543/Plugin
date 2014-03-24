package com.jediminer543.plugin.config.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;

public class MultiBlock
{
	public Location coreLocation;
	//public MultiBlockType type;
	public MultiBlockDirection direction;
	/**
	 * Multiblock frame dimensions
	 */
	public int frameH, frameW;
	public List<Material> frameMaterials = new ArrayList<Material>();
	
	public MultiBlock(int frameH,int frameW,int frameZ)
	{
		this(frameH, frameW, new ArrayList<Material>(Arrays.asList(Material.OBSIDIAN)));
		
	}
	
	public MultiBlock(int frameH,int frameW, List<Material> frameMaterials)
	{
		this.frameH = frameH;
		this.frameW = frameW;
		frameMaterials.addAll(frameMaterials);
	}
	
	public boolean validateCore()
	{
		if (coreLocation == null)
		{
		return false;
		}
		else
		{
			if (coreLocation.getBlock().getType() == Material.SPONGE)
			{
				
				return false;
			}
			else
			{
				return false;
			}
			
		}
	}
	
	public boolean validateFrame()
	{
		if (validateCore())
		{
		return false;
		}
		else
		{
		return false;
		}
	}
	
	
}
