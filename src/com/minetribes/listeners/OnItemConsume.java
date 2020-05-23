package com.minetribes.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class OnItemConsume implements Listener {

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e){
        if (e.getItem().getType() == Material.SPIDER_EYE){
            e.setCancelled(true);
        }

    }
}
