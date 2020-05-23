package com.minetribes.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StrengthCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args){

        if (sender.isOp()){

            int amount = (int) Integer.parseInt(args[1]);
            ItemStack strength = new ItemStack(Material.BLAZE_POWDER, amount);

            ItemMeta meta = strength.getItemMeta();
            meta.setDisplayName("§6Strength");
            strength.setItemMeta(meta);

            Player receiver = Bukkit.getPlayer(args[0]);

            receiver.getInventory().addItem(strength);

        }


        return true;
    }
}
