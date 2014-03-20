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
	public int frameH, frameV, frameZ;
	public List<Material> frameMaterials = new ArrayList<Material>();
	
	public MultiBlock(int frameH,int frameV,int frameZ)
	{
		this(frameH, frameV, frameZ, new ArrayList<Material>(Arrays.asList(Material.OBSIDIAN)));
		
	}
	
	public MultiBlock(int frameH,int frameV,int frameZ, List<Material> frameMaterials)
	{
		this.frameH = frameH;
		this.frameV = frameV;
		this.frameZ = frameZ;
		frameMaterials.addAll(frameMaterials);
	}
	
	public boolean validateFrame()
	{
		if (coreLocation == null)
		{
		return false;
		}
		else
		{
			return false;
		}
	}
	
	
}
