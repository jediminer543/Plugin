package com.jediminer543.plugin.config;

import com.jediminer543.plugin.Plugin;

public class MultiBlockConfig extends CustomConfig
{

	Plugin plugin;
	
	public MultiBlockConfig(Plugin plugin, String fileName) {
		super(plugin, fileName);
		this.plugin = plugin;
	}

	
}
