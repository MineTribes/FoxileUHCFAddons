package com.minetribes.commands;

import com.minetribes.UHCFAddons;
import com.minetribes.listeners.OnCommand;
import com.minetribes.utils.FileUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.Map;

public class FixCMD implements CommandExecutor {
    public static Map<String, Long> fixCooldowns = new HashMap<String, Long>();


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        int fixCooldownTime = 0;
        if (!(sender instanceof Player)) return true;

        if (args.length == 0) {

            Player p = (Player) sender;

            if (p.hasPermission("foxile.fix.thief")) {
                fixCooldownTime = 60 * 15;
            } else if (p.hasPermission("foxile.fix.sentinel")) {
                fixCooldownTime = 60 * 30;
            } else if (p.hasPermission("foxile.fix.bandit")) {
                fixCooldownTime = 60 * 45;
            } else if (p.hasPermission("foxile.fix.hunter")) {
                fixCooldownTime = 60 * 60;
            } else if (p.hasPermission("foxile.fix.ranger")) {
                fixCooldownTime = 60 * 90;
            }
            if (fixCooldownTime == 0) {
                sender.sendMessage("§6§lFoxile §7» §fJe moet minimaal een §6Ranger§f rank hebben voor je §6/fix§f kan gebruiken.");
                return true;
            } else {
                if (fixCooldowns.containsKey(sender.getName())) {
                    long secondsLeft = ((fixCooldowns.get(sender.getName()) / 1000)) - (System.currentTimeMillis() / 1000);

                    if (secondsLeft > 0) {
                        if (secondsLeft < 61) {
                            sender.sendMessage("§6§lFoxile §7» §fJe moet nog §6" + secondsLeft + " seconden §fwachten voor je weer §6/fix§f kan gebruiken!");
                            return true;
                        } else {
                            int minutesLeft = (int) secondsLeft / 60;
                            secondsLeft = secondsLeft - minutesLeft * 60;
                            sender.sendMessage("§6§lFoxile §7» §fJe moet nog §6" + minutesLeft + " minuten §fen §6" + secondsLeft + " seconden §fwachten voor je weer " +
                                    "§6/fix§f kan gebruiken!");

                            return true;
                        }
                    } else {
                        fixCooldowns.remove(sender.getName());
                        fixCooldowns.put(sender.getName(), System.currentTimeMillis() + fixCooldownTime * 1000);
                        ((Player) sender).getItemInHand().setDurability((short) 0);

                        sender.sendMessage("§6§lFoxile §7» §fHet item in je hand is §6gerepaired§f.");
                        return true;

                    }
                }
                if (!(fixCooldowns.containsKey(sender.getName()))) {
                    fixCooldowns.put(sender.getName(), System.currentTimeMillis() + fixCooldownTime * 1000);
                    ((Player) sender).getItemInHand().setDurability((short) 0);

                    sender.sendMessage("§6§lFoxile §7» §fHet item in je hand is §6gerepaired§f.");
                    return true;
                }
            }
        }


        if (args.length == 1) {
            int fixAllCooldownTime = 0;
            Boolean fixed = false;


            if (args[0].equalsIgnoreCase("all")) {
                FileUtil fileUtil = new FileUtil(UHCFAddons.instance);
                FileUtil.Config cooldownConfig = fileUtil.getConfig("cooldowns.yml");
                Player p = (Player) sender;


                if (p.hasPermission("foxile.fixall.thief")) {
                    fixAllCooldownTime = 43200000;
                } else if (p.hasPermission("foxile.fixall.sentinel")) {
                    fixAllCooldownTime = 64800000;
                } else if (p.hasPermission("foxile.fixall.bandit")) {
                    fixAllCooldownTime = 86400000;
                }

                if (fixAllCooldownTime == 0) {
                    sender.sendMessage("§6§lFoxile §7» §fJe moet minimaal een §6Bandit§f rank hebben voor je §6/fix all§f kan gebruiken.");

                    return true;
                }

                if (cooldownConfig.get().get(p.getUniqueId() + ".FixAll", false) == null) {
                    cooldownConfig.get().set(p.getUniqueId() + ".FixAll", System.currentTimeMillis() + fixAllCooldownTime);
                    fileUtil.getConfig("cooldowns.yml").save();

                    PlayerInventory inv = ((Player) sender).getInventory();

                    if (inv.getHelmet() != null) {
                        inv.getHelmet().setDurability((short) 0);
                    }

                    if (inv.getChestplate() != null) {
                        inv.getChestplate().setDurability((short) 0);
                    }

                    if (inv.getLeggings() != null) {
                        inv.getLeggings().setDurability((short) 0);
                    }

                    if (inv.getBoots() != null) {
                        inv.getBoots().setDurability((short) 0);
                    }

                    for (int i = 0; i < 36; i++) {

                        if (((Player) sender).getInventory().getItem(i) != null) {
                            if (!((Player) sender).getInventory().getItem(i).getType().toString().toLowerCase().contains("potion")) {

                                    ItemStack result = null;
                                    p.getInventory().getItem(i).setDurability((short) 0);
                            }

                        }
                        sender.sendMessage("§6§lFoxile §7» §fAlles in je inventory is §6gerepaired§f.");
                        return true;
                    }
                    return true;
                } else {


                    if (System.currentTimeMillis() < cooldownConfig.get().getLong(p.getUniqueId() + ".FixAll")) {

                        Long millisDifference = cooldownConfig.get().getLong(p.getUniqueId() + ".FixAll") - System.currentTimeMillis();
                        p.getPlayer().sendMessage("§6§lFoxile §7» §fJe kan §6/fix all §fweer gebruiken over§6 " + OnCommand.toTimeFormat(millisDifference));

                        return true;
                    } else {

                        if (System.currentTimeMillis() >= cooldownConfig.get().getLong(p.getUniqueId() + ".FixAll")) {

                            PlayerInventory inv = ((Player) sender).getInventory();

                            if (inv.getHelmet() != null) {
                                inv.getHelmet().setDurability((short) 0);
                            }

                            if (inv.getChestplate() != null) {
                                inv.getChestplate().setDurability((short) 0);
                            }

                            if (inv.getLeggings() != null) {
                                inv.getLeggings().setDurability((short) 0);
                            }

                            if (inv.getBoots() != null) {
                                inv.getBoots().setDurability((short) 0);
                            }

                            cooldownConfig.get().set(p.getUniqueId() + ".FixAll", System.currentTimeMillis() + fixAllCooldownTime);
                            fileUtil.getConfig("cooldowns.yml").save();
                            for (int i = 0; i < 36; i++) {

                                if (((Player) sender).getInventory().getItem(i) != null) {
                                    if (!((Player) sender).getInventory().getItem(i).getType().toString().toLowerCase().contains("potion")) {

                                            ItemStack result = null;
                                            p.getInventory().getItem(i).setDurability((short) 0);
                                    }

                                }
                                sender.sendMessage("§6§lFoxile §7» §fAlles in je inventory is §6gerepaired§f.");
                                return true;
                            }
                        }
                    }
                }
            }

            if (args.length != 0 && args.length != 1) {
                sender.sendMessage("§6§lFoxile §7» §fGebruik §6/fix §fof §6/fix all§f.");


                return true;
            }


        } return true;

    }
}