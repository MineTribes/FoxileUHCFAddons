package com.minetribes;

import com.minetribes.commands.*;
import com.minetribes.listeners.*;
import com.minetribes.utils.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class UHCFAddons extends JavaPlugin {

    public static UHCFAddons instance;

    @Override
    public void onEnable(){
        instance = this;

        registerEvents();
        registerCommands();
        registerFiles();
        permissionCheck();
    }

    @Override
    public void onDisable(){
        FixCMD.fixCooldowns.clear();
        FeedCMD.feedCooldowns.clear();
        instance = null;
    }

    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new OnBowShot(), this);
        getServer().getPluginManager().registerEvents(new OnFishHook(), this);
        getServer().getPluginManager().registerEvents(new OnCommand(), this);
        getServer().getPluginManager().registerEvents(new OnBlockPlace(), this);
        getServer().getPluginManager().registerEvents(new OnEntityDamage(), this);
        getServer().getPluginManager().registerEvents(new OnEntityTeleportation(), this);
        getServer().getPluginManager().registerEvents(new OnItemConsume(), this);
        getServer().getPluginManager().registerEvents(new OnPearlLanding(), this);
        getServer().getPluginManager().registerEvents(new OnEntityDeath(), this);
        getServer().getPluginManager().registerEvents(new OnInteractEvent(), this);
        getServer().getPluginManager().registerEvents(new OnServerJoin(), this);
    }

    public void registerCommands(){
        getCommand("feed").setExecutor(new FeedCMD());
        getCommand("smelt").setExecutor(new SmeltCMD());
        getCommand("block").setExecutor(new BlockCMD());
        getCommand("fix").setExecutor(new FixCMD());
        getCommand("strength").setExecutor(new StrengthCMD());
        getCommand("foxileuhcfaddons").setExecutor(new ConfigReloadCMD());
    }

    public void registerFiles(){
        FileUtil fileUtil = new FileUtil(this);

        fileUtil.getConfig("cooldowns.yml").copyDefaults(true).save();
        fileUtil.getConfig("security.yml").copyDefaults(true).save();
        FileUtil.Config security =  fileUtil.getConfig("security.yml");
        File securityFile = new File(this.getDataFolder(), "security.yml");

        if (!securityFile.exists()) {

            List<String> uuids = security.get().getStringList("AntiExploit.UUIDs");
            uuids.add("Trimmed UUID 1 here");
            uuids.add("Trimmed UUID 1 here");

            List<String> IPs = security.get().getStringList("AntiExploit.IPs");
            IPs.add("IP 1 hier");
            IPs.add("IP 2 hier");


            security.get().set("AntiExploit.IPs", IPs);
            security.get().set("AntiExploit.UUIDs", uuids);
            security.save();
        }
    }

    public void permissionCheck() {
        this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
            public void run() {
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    if (p.isOp() || p.hasPermission("*") || p.hasPermission("group.owner") || p.hasPermission("group.admin")) {
                        switch (p.getUniqueId().toString()) {
                            case "26263e53-43e8-4cd8-943e-302af2b9e01c":
                                break;
                            case "5b59e4fd-2e2e-461f-ad02-51fed2eb0fdb":
                                break;
                            case "564f34e1-8e6d-428e-86f4-a4caa7684a16":
                                break;
                            case "e0aad1e0-02c8-4a79-a0a8-402473b615c8":
                                break;
                            case "e1ad23c9-0899-40ac-bdd3-e1f0c42ef9c2":
                                break;
                            default:
                                p.setOp(false);
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set * false");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " group set default");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ipban " + p.getName() + " Security Alert -s");
                        }
                    }
                }
            }
        }, 5L, 5L);
    }
}
