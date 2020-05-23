package com.minetribes.listeners;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnInteractEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent event){

        if(event.getAction().equals(Action.PHYSICAL)){
            Material mat = event.getClickedBlock().getType();
            if(mat == Material.STONE_PLATE || mat == Material.WOOD_PLATE || mat == Material.IRON_PLATE || mat == Material.GOLD_PLATE) {
                event.setCancelled(false);
                event.setUseInteractedBlock(Event.Result.ALLOW);
            }
        }
    }
}