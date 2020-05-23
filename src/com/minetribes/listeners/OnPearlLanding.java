package com.minetribes.listeners;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import static com.sk89q.worldguard.bukkit.BukkitUtil.toVector;


public class OnPearlLanding implements Listener {

    @EventHandler

    public void onPearl(ProjectileHitEvent e){

        if (e.getEntity() instanceof EnderPearl) {


            WorldGuardPlugin worldGuard = WorldGuardPlugin.inst();
            Vector pt = toVector(e.getEntity().getLocation());
            RegionManager regionManager = worldGuard.getRegionManager(e.getEntity().getWorld());
            ApplicableRegionSet set = regionManager.getApplicableRegions(pt);

            if (!set.allows(DefaultFlag.ENDERPEARL)) {

                Player p = (Player) e.getEntity().getShooter();

                    p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
                    return;

            }


            pt = toVector(e.getEntity().getShooter().getLocation());
            regionManager = worldGuard.getRegionManager(e.getEntity().getShooter().getWorld());
            set = regionManager.getApplicableRegions(pt);

            if (!set.allows(DefaultFlag.ENDERPEARL)) {

                Player p = (Player) e.getEntity().getShooter();
                p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
                return;

            }

            }
        }
    }
