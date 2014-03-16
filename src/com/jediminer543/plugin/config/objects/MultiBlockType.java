package com.jediminer543.plugin.config.objects;

public enum MultiBlockType
{
	//Generator(),
	TEST("TEST", 2, 2, 2);
	
	 public String guiName;
	
	MultiBlockType(String GUIName, int xPlaneSize, int yPlaneSize, int zPlaneSize)
	{
		this.guiName = GUIName;
	}
	
	
}
