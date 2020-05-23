package com.minetribes.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTeleportEvent;

public class OnEntityTeleportation implements Listener {

    @EventHandler
    public void onTeleport(EntityTeleportEvent e){
        if (e.getEntity().getType() == EntityType.ENDERMAN){
            e.setTo(e.getFrom());
        }
    }
}
