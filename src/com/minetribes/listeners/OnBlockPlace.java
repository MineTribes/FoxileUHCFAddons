package com.minetribes.listeners;

import me.qiooip.lazarus.factions.claim.ClaimManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
public class OnBlockPlace implements Listener {

    @EventHandler
    public void onBlockPlacement(BlockPlaceEvent e){
        if (ClaimManager.getInstance().getClaimAt(e.getPlayer().getLocation()) == null){
            if (e.getBlock().getType() == Material.SAPLING){
                e.setCancelled(true);
            }
        }

    }
}
