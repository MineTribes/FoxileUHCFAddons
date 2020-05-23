package com.minetribes.listeners;


import me.qiooip.lazarus.timer.TimerManager;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnEntityDamage implements Listener {

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent e) {

        if (e.getEntity() instanceof Enderman){
            e.setCancelled(true);
            if(e.getDamager() instanceof Player){
                e.setCancelled(false);
            }
        }


        if (e.getEntity() instanceof Player) {
            Player damaged = (Player) e.getEntity();
            if (damaged instanceof Player) {
                Double damage = e.getDamage(EntityDamageEvent.DamageModifier.BASE) * 0.8125;
                e.setDamage(damage);
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player) {
            if (TimerManager.getInstance().getPvpProtTimer().isActive((Player) e.getEntity())){
                e.setCancelled(true);
            }
        }
    }

}
