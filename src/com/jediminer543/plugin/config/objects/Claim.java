package com.jediminer543.plugin.config.objects;

import java.util.List;

import org.bukkit.Chunk;

/**
 * Represents A Claim In World
 */
public class Claim
{
	public Owner owner;
	public List<Owner> trusted;
	public Chunk chunk;
}
