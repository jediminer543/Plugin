package com.jediminer543.plugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TpaSendEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private Player sender;
	private Player reciver;
	private boolean Canceled;
	
	public TpaSendEvent(Player sender, Player reciver)
	{
		this.sender = sender;
		this.reciver = reciver;
		this.reciver.sendMessage(this.sender.getDisplayName() + "is requesting teleportation.");
		this.reciver.sendMessage("Type /tpaccept to accept or /tpdeny to decline");
	}
	
	@Override
	public boolean isCancelled()
	{
		return Canceled;
	}

	@Override
	public void setCancelled(boolean Canceled)
	{
		this.Canceled = Canceled;

	}

	@Override
	public HandlerList getHandlers()
	{
		return handlers;
	}

}
