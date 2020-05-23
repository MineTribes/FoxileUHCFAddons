package com.minetribes.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.Iterator;

public class SmeltCMD implements CommandExecutor {
    int amount;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
        }else{
            Player p = (Player) sender;
            if (sender.hasPermission("foxile.smelt")){


        for(int i=0;i<36;i++)
        {
            if(p.getInventory().getItem(i) != null) {
                ItemStack result = null;
                Iterator<Recipe> iter = Bukkit.recipeIterator();

                while (iter.hasNext()) {
                    Recipe recipe = iter.next();
                    if (!(recipe instanceof FurnaceRecipe)) continue;
                    if (p.getInventory().getItem(i).getType().name().toLowerCase().contains("log")||(p.getInventory().getItem(i).getType().name().toLowerCase().
                            contains("sand"))) continue;

                    if (((FurnaceRecipe) recipe).getInput().getType() != p.getInventory().getItem(i).getType()) continue;
                    amount = p.getInventory().getItem(i).getAmount();

                    result = recipe.getResult();
                    result.setAmount(amount);

                    break;
                }
                if (result != null) {
                        p.getInventory().setItem(i, result);
                    }
                }
                }
        }else{
                p.sendMessage("§6§lFoxile §7» §fJe moet minimaal een §6Ranger§f rank hebben voor je §6/smelt§f kan gebruiken.");

            }
        }
        return true;
    }
}

