package com.palmergames.bukkit.TownyChat.events;

import com.palmergames.bukkit.TownyChat.channels.Channel;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Set;

/*
 * Allows other plugins to hook into a chat message being accepted into any of the channels
 * 
 * In order to use this event, you will have to add the hooked: true flag to the channel you wish you hook in the channels.yml
 * 
 * ex:
 * 
 *Channels:
 *  general:
 *      commands: [g]
 *      type: GLOBAL
 *      hooked: true
 *      channeltag: '&f[g]'
 *      messagecolour: '&f'
 *      permission: 'towny.chat.general'
 *      craftIRCTag: 'admin'
 *      range: '-1'
 */
public class AsyncChatHookEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
	protected AsyncPlayerChatEvent event;
	protected boolean changed;
	protected Channel channel;

	public AsyncChatHookEvent(AsyncPlayerChatEvent event, Channel channel) {
		super(true);
		this.event = event;
		this.changed = false;
		this.channel = channel;
	}

	public Channel getChannel() {
		return channel;
	}

	/*
	 * Returns true if the hooked event was changed
	 */
	public boolean isChanged() {
		return changed;
	}

	/*
	 * Informs Chat if the event was changed or not
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public AsyncPlayerChatEvent getAsyncPlayerChatEvent() {
		return event;
	}
	
	public String getFormat() {
		return event.getFormat();
	}
	
	public String getMessage() {
		return event.getMessage();
	}
	
	public Set<Player> getRecipients() {
		return event.getRecipients();
	}
	
	public boolean isCancelled() {
		return event.isCancelled();
	}
	
	public Player getPlayer() {
		return event.getPlayer();
	}
	
	public void setRecipients(Set<Player> recipients) {
		changed = true;
		event.getRecipients().clear();
		event.getRecipients().addAll(recipients);
	}
	
	public void setFormat(String format) {
		changed = true;
		event.setFormat(format);
	}
	
	public void setMessage(String message) {
		changed = true;
		event.setFormat(message);
	}
	
	public void setCancelled(boolean cancel) {
		changed = (cancel);
		event.setCancelled(cancel);
	}

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}