package com.minetribes.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import java.text.DecimalFormat;

public class OnBowShot implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if (!(e.getDamager() instanceof Arrow)) {
            return;
        }
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) e.getEntity();

        Arrow arrow = (Arrow) e.getDamager();
        Player shooter = (Player) arrow.getShooter();
        DecimalFormat df = new DecimalFormat("#.#");

        String HP = df.format(p.getHealth()/2);


        if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
            shooter.sendMessage("§6§lFoxile §7» §fDeze speler heeft nog §6" + HP + " HP§f.");
        } else {
            shooter.sendMessage("§6§lFoxile §7» §6" + p.getName() + " §fheeft nog §6" + HP + " HP§f.");
        }
    }
}


