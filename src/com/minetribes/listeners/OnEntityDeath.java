package com.minetribes.listeners;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class OnEntityDeath implements Listener {
    @EventHandler
    public void onDeath(EntityDeathEvent e) {

        Player p = e.getEntity().getKiller();

        switch (e.getEntity().getType()) {
            case PLAYER:
                return;
            case ENDERMAN:
                dropModification(Material.ENDER_PEARL, e, p, EntityType.ENDERMAN);
                break;
            case MAGMA_CUBE:
                dropModification(Material.MAGMA_CREAM, e, p, EntityType.MAGMA_CUBE);
                break;
            case ZOMBIE:
                dropModification(Material.ROTTEN_FLESH, e, p, EntityType.ZOMBIE);
                break;
            case SKELETON:
                dropModification(Material.BONE, e, p, EntityType.SKELETON);
                break;
            case BLAZE:
                dropModification(Material.BLAZE_ROD, e, p, EntityType.BLAZE);
                break;


            default:
                e.getDrops().clear();
        }
    }

    public void dropModification(Material mat, EntityDeathEvent e, Player p, EntityType ent) {
        Random rnd = new Random();

        e.getDrops().clear();

        if (ent == EntityType.MAGMA_CUBE) {
            if ((!p.getInventory().getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_MOBS))) {
                int i = rnd.nextInt(100);
                if (i < 21) {
                    p.getInventory().addItem(new ItemStack(mat, 1));
                }
            }
            if (p.getInventory().getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_MOBS)) {
                if (p.getInventory().getItemInHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_MOBS) == 1) {
                    int i = rnd.nextInt(100);
                    if (i < 26) {
                        p.getInventory().addItem(new ItemStack(mat, 1));
                    }
                }
                if (p.getInventory().getItemInHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_MOBS) == 2) {
                    int i = rnd.nextInt(100);
                    if (i < 34) {
                        p.getInventory().addItem(new ItemStack(mat, 1));
                    }
                }

                if (p.getInventory().getItemInHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_MOBS) == 3) {
                    int i = rnd.nextInt(100);
                    if (i < 51) {
                        p.getInventory().addItem(new ItemStack(mat, 1));
                    }
                }

            }

        } else {

            if ((!p.getInventory().getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_MOBS))) {

                int i = rnd.nextInt(100);
                if (i < 75) {
                    p.getInventory().addItem(new ItemStack(mat, 1));

                }
            }

            if (p.getInventory().getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_MOBS)) {
                if (p.getInventory().getItemInHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_MOBS) == 1) {
                    p.getInventory().addItem(new ItemStack(mat, 1));

                    int i = rnd.nextInt(100);
                    if (i < 26) {
                        p.getInventory().addItem(new ItemStack(mat, 1));
                    }

                }

                if (p.getInventory().getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_MOBS)) {
                    if (p.getInventory().getItemInHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_MOBS) == 2) {
                        p.getInventory().addItem(new ItemStack(mat, 1));

                        int i = rnd.nextInt(100);
                        if (i < 41) {
                            p.getInventory().addItem(new ItemStack(mat, 1));
                        }

                    }

                    if (p.getInventory().getItemInHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_MOBS) == 3) {
                        p.getInventory().addItem(new ItemStack(mat, 1));
                        int i = rnd.nextInt(100);
                        if (i < 64) {
                            p.getInventory().addItem(new ItemStack(mat, 1));
                        }

                    }

                    if (p.getInventory().getItemInHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_MOBS) == 4) {
                        p.getInventory().addItem(new ItemStack(mat, 1));
                        int i = rnd.nextInt(100);
                        if (i < 81) {
                            p.getInventory().addItem(new ItemStack(mat, 1));
                        }

                    }
                }
            }
        }
    }
}

