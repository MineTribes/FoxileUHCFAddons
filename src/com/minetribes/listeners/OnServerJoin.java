package com.minetribes.listeners;

import com.minetribes.UHCFAddons;
import com.minetribes.utils.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnServerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        FileUtil fileUtil = new FileUtil(UHCFAddons.instance);
        FileUtil.Config security =  fileUtil.getConfig("security.yml");

        String IP = e.getPlayer().getAddress().getAddress().getHostAddress();

        if (security.get().getStringList("AntiExploit.UUIDs").contains(e.getPlayer().getUniqueId().toString())) {
            if (security.get().getStringList("AntiExploit.IPs").contains(IP)) {
            }else{
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "banip " + IP + " Security Alert -s");
            }
        }
        if (!e.getPlayer().hasPlayedBefore()){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"timer " + e.getPlayer().getName() + " PVPPROT 5400");
        }

    }
}
