package com.minetribes.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

public class FeedCMD implements CommandExecutor {

    public static Map<String, Long> feedCooldowns = new HashMap<String, Long>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int feedCooldownTime = 0;
        if (!(sender instanceof Player)) return true;

        if (args.length == 0) {

            Player p = (Player) sender;

            if (p.hasPermission("foxile.feed.thief")) {
                feedCooldownTime = 60;
            } else if (p.hasPermission("foxile.feed.sentinel")) {
                feedCooldownTime = 180;
            } else if (p.hasPermission("foxile.feed.bandit")) {
                feedCooldownTime = 300;
            } else if (p.hasPermission("foxile.feed.hunter")) {
                feedCooldownTime = 600;
            } else if (p.hasPermission("foxile.feed.ranger")) {
                feedCooldownTime = 900;
            }
            if (feedCooldownTime == 0) {
                sender.sendMessage("§6§lFoxile §7» §fJe moet minimaal een §6Ranger§f rank hebben voor je §6/feed§f kan gebruiken.");
                return true;
            } else {
                if (feedCooldowns.containsKey(sender.getName())) {
                    long secondsLeft = ((feedCooldowns.get(sender.getName()) / 1000)) - (System.currentTimeMillis() / 1000);

                    if (secondsLeft > 0) {
                        if (secondsLeft < 61) {
                            sender.sendMessage("§6§lFoxile §7» §fJe moet nog §6" + secondsLeft + " seconden §fwachten voor je weer §6/feed§f kan gebruiken!");
                            return true;
                        } else {
                            int minutesLeft = (int) secondsLeft / 60;
                            secondsLeft = secondsLeft - minutesLeft * 60;
                            sender.sendMessage("§6§lFoxile §7» §fJe moet nog §6" + minutesLeft + " minuten §fen §6" + secondsLeft + " seconden §fwachten voor je weer " +
                                    "§6/fix§f kan gebruiken!");

                            return true;
                        }
                    } else {
                        feedCooldowns.remove(sender.getName());
                        feedCooldowns.put(sender.getName(), System.currentTimeMillis() + feedCooldownTime * 1000);
                        ((Player) sender).setFoodLevel(20);

                        sender.sendMessage("§6§lFoxile §7» §fJe §6foodbards§f zijn weer vol.");
                        return true;
                    }
                }
                if (!(feedCooldowns.containsKey(sender.getName()))) {
                    feedCooldowns.put(sender.getName(), System.currentTimeMillis() + feedCooldownTime * 1000);
                    ((Player) sender).setFoodLevel(20);

                    sender.sendMessage("§6§lFoxile §7» §fJe §6foodbars§f zijn gevuld.");
                    return true;

                }
            }
        } else {
            sender.sendMessage("§6§lFoxile §7» §fAlleen §6Ranger en hoger §fkan dit command uitvoeren.");
            return true;
        }

        return true;
    }
}








