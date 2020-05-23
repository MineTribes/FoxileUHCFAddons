package com.minetribes.listeners;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class OnFishHook implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent e){
        if (e.getState() == PlayerFishEvent.State.CAUGHT_FISH){
            e.setCancelled(true);
        }
    }
}
