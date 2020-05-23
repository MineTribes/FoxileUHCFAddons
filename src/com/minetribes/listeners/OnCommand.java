package com.minetribes.listeners;

import com.minetribes.UHCFAddons;
import com.minetribes.utils.FileUtil;
import me.qiooip.lazarus.factions.FactionsManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.concurrent.TimeUnit;

public class OnCommand implements Listener {

    FileUtil fileUtil = new FileUtil(UHCFAddons.instance);
    FileUtil.Config cooldownConfig =  fileUtil.getConfig("cooldowns.yml");

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().toLowerCase().startsWith("/sotw enable")){
            e.setCancelled(true);
            e.getPlayer().sendMessage("§6§lFoxile §7» §fJe kan je §6SOTW §fprotection niet uitschakelen. Bedoelde je §6/pvp enable§f?");
        }

        if (e.getMessage().toLowerCase().startsWith("/revive") || e.getMessage().toLowerCase().startsWith("/recoverinv")){
            String[] args;

            int spaceCount =  (e.getMessage().split(" ").length) -1;

            if (e.getPlayer().hasPermission("invrestore.recoverinv")) {
                    e.setCancelled(true);

                    if (spaceCount == 2) {
                        args = e.getMessage().split(" ");
                        if (Bukkit.getServer().getPlayer(args[1]) == null){
                                e.getPlayer().sendMessage("§6§lFoxile §7» §fDeze speler is niet §6online§f.");
                                return;
                            }
                            if (e.getPlayer().getName().equalsIgnoreCase(args[1])) {
                                    e.getPlayer().sendMessage("§6§lFoxile §7» §fJe kan jezelf niet §6reviven§f.");
                                    return;
                                }
                                if (FactionsManager.getInstance().getPlayerFaction(args[1]) != FactionsManager.getInstance().getPlayerFaction(e.getPlayer()) ||
                                            FactionsManager.getInstance().getPlayerFaction(args[1]) == null || FactionsManager.getInstance().getPlayerFaction(e.getPlayer()) == null) {
                                        e.getPlayer().performCommand(e.getMessage().replace("/", ""));
                                    } else {
                                        e.getPlayer().sendMessage("§6§lFoxile §7» §fJe kan faction members niet §6reviven§f.");
                                        return;
                    }
                }else{
                e.getPlayer().sendMessage("§6§lFoxile §7» §fCommand niet compleet. Gebruik: §6/revive <speler> <reden>§f.");
                return;
                    }
            }
        }

        if (e.getMessage().toLowerCase().startsWith("/f claim")) {
            e.setCancelled(true);
            e.getPlayer().performCommand("f claimchunk");

        }

        if (e.getMessage().toLowerCase().startsWith("/reclaim")) {

            if (!(e.getPlayer().isOp())) {
                if (!(e.getPlayer().hasPermission("foxile.reclaim"))){
                    e.getPlayer().sendMessage("§6§lFoxile §7» §fJe moet een§6 donator rank §fhebben voor je §6/reclaim §fkan.");
                    e.setCancelled(true);
                    return;
                }else {
                    String rank = "";

                    if (e.getPlayer().hasPermission("lazarus.reclaim.thief")){
                        rank = "Thief";
                    }
                    if (e.getPlayer().hasPermission("lazarus.reclaim.baron")){
                        rank = "Baron";
                    }
                    if (e.getPlayer().hasPermission("lazarus.reclaim.sentinel")){
                        rank = "Sentinel";
                    }
                    if (e.getPlayer().hasPermission("lazarus.reclaim.bandit")){
                        rank = "Bandit";
                    }
                    if (e.getPlayer().hasPermission("lazarus.reclaim.hunter")){
                        rank = "Hunter";
                    }
                    if (e.getPlayer().hasPermission("lazarus.reclaim.ranger")){
                        rank = "Ranger";
                    }


                    e.setCancelled(true);
                    if (cooldownConfig.get().get(e.getPlayer().getUniqueId() + ".Reclaim", false) == null){
                        cooldownConfig.get().set(e.getPlayer().getUniqueId() + ".Reclaim", System.currentTimeMillis() + 172800000);
                        cooldownConfig.get().set(e.getPlayer().getUniqueId() + ".ReclaimRank", rank);
                        e.getPlayer().performCommand("reclaim");
                        fileUtil.getConfig("cooldowns.yml").save();

                        return;
                    }else {
                        e.setCancelled(true);

                        if (rank == cooldownConfig.get().getString(e.getPlayer().getUniqueId() + ".ReclaimRank")) {

                            if (System.currentTimeMillis() < cooldownConfig.get().getLong(e.getPlayer().getUniqueId() + ".Reclaim")) {
                                Long millisDifference = cooldownConfig.get().getLong(e.getPlayer().getUniqueId() + ".Reclaim") - System.currentTimeMillis();
                                e.getPlayer().sendMessage("§6§lFoxile §7» §fJe kan je §6reclaim §fweer gebruiken over§6 " + toTimeFormat(millisDifference));
                                return;
                            } else {
                                e.setCancelled(true);
                                if (System.currentTimeMillis() >= cooldownConfig.get().getLong(e.getPlayer().getUniqueId() + ".Reclaim")) {
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "setreclaim " + e.getPlayer().getName() + " false");
                                    cooldownConfig.get().set(e.getPlayer().getUniqueId() + ".Reclaim", System.currentTimeMillis() + 172800000);
                                    cooldownConfig.get().set(e.getPlayer().getUniqueId() + ".ReclaimRank", rank);
                                    fileUtil.getConfig("cooldowns.yml").save();
                                    e.getPlayer().performCommand("reclaim");

                                    return;
                                }
                            }
                        } else {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "setreclaim " + e.getPlayer().getName() + " false");
                            cooldownConfig.get().set(e.getPlayer().getUniqueId() + ".Reclaim", System.currentTimeMillis() + 172800000);
                            cooldownConfig.get().set(e.getPlayer().getUniqueId() + ".ReclaimRank", rank);
                            fileUtil.getConfig("cooldowns.yml").save();
                            e.getPlayer().performCommand("reclaim");

                            return;
                        }
                    }
                }
            }else {
                return;
            }
        }
        }

    public static String toTimeFormat(Long millisLeft){
        String dyRemaining = "";
        String hrRemaining = "";
        String minRemaining = "";


        long milliseconds = millisLeft;
         long dy = TimeUnit.MILLISECONDS.toDays(milliseconds);
         long hr = TimeUnit.MILLISECONDS.toHours(milliseconds)
                - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));
         long min = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
         long sec = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
                 - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));

        if (dy < 1){
            dyRemaining = "";
        }else{
            dyRemaining = dy + " dag§f,§6 ";
        }
        if (hr < 1){
            hrRemaining = "";
        }else{
            hrRemaining = hr + " uur§f,§6 ";
        }
        if (min < 1){
            minRemaining = "";
        }else{
            minRemaining = min + " minuten§f en§6 ";
        }
        return dyRemaining +  hrRemaining + minRemaining + sec + " seconden§f!";
    }
}

