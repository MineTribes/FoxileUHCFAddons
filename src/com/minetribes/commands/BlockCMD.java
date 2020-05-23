package com.minetribes.commands;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

public class BlockCMD implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Je moet hiervoor een speler zijn!");
            return true;
        }
        if (!(sender.hasPermission("foxile.block"))){
            sender.sendMessage("§6§lFoxile §7» §fJe moet minimaal een §6Ranger§f rank hebben voor je §6/block§f kan gebruiken.");
            return true;
        }
        final Player player = (Player)sender;

        try {
            int amountOfDiamonds = 0;
            int amountOfEmeralds = 0;
            int amountOfIron = 0;
            int amountOfGold = 0;
            int amountOfGlowstone = 0;
            int coal = 0;
            int redstone = 0;
            int lapis = 0;
            ItemStack[] contents;
            for (int length = (contents = player.getInventory().getContents()).length, i = 0; i < length; ++i) {
                final ItemStack is = contents[i];
                if (is != null) {
                    if (is.getType() == Material.DIAMOND) {
                        player.getInventory().remove(is);
                        amountOfDiamonds += is.getAmount();
                    }
                    if (is.getType() == Material.EMERALD) {
                        amountOfEmeralds += is.getAmount();
                        player.getInventory().remove(is);
                    }
                    if (is.getType() == Material.IRON_INGOT) {
                        player.getInventory().remove(is);
                        amountOfIron += is.getAmount();
                    }
                    if (is.getType() == Material.GLOWSTONE_DUST) {
                        amountOfGlowstone += is.getAmount();
                        player.getInventory().remove(is);
                    }
                    if (is.getType() == Material.GOLD_INGOT) {
                        player.getInventory().remove(is);
                        amountOfGold += is.getAmount();
                    }
                    if (is.getType() == Material.COAL) {
                        player.getInventory().remove(is);
                        coal += is.getAmount();
                    }
                    if (is.getType() == Material.REDSTONE) {
                        redstone += is.getAmount();
                        player.getInventory().remove(is);
                    }
                    if (is.getType() == Material.INK_SACK && ((Dye)is.getData()).getColor() == DyeColor.BLUE) {
                        player.getInventory().remove(is);
                        lapis += is.getAmount();
                    }
                }
            }
            player.updateInventory();
            final int diamondsToTransform = amountOfDiamonds / 9;
            final int diamondOverflow = amountOfDiamonds % 9;
            final int emeraldsToTransform = amountOfEmeralds / 9;
            final int emeraldsOverflow = amountOfEmeralds % 9;
            final int ironToTransform = amountOfIron / 9;
            final int ironOverflow = amountOfIron % 9;
            final int goldToTransform = amountOfGold / 9;
            final int goldOverflow = amountOfGold % 9;
            final int glowstoneToTransform = amountOfGlowstone / 9;
            final int glowstoneOverflow = amountOfGlowstone % 9;
            final int rT = redstone / 9;
            final int rO = redstone % 9;
            final int lT = lapis / 9;
            final int lO = lapis % 9;
            final int cT = coal / 9;
            final int cO = coal % 9;
            player.getInventory().addItem(new ItemStack[] { new ItemStack((diamondsToTransform > 0) ? Material.DIAMOND_BLOCK : Material.AIR, diamondsToTransform), new ItemStack((emeraldsToTransform > 0) ? Material.EMERALD_BLOCK : Material.AIR, emeraldsToTransform), new ItemStack((diamondOverflow > 0) ? Material.DIAMOND : Material.AIR, diamondOverflow), new ItemStack((emeraldsOverflow > 0) ? Material.EMERALD : Material.AIR, emeraldsOverflow), new ItemStack((ironToTransform > 0) ? Material.IRON_BLOCK : Material.AIR, ironToTransform), new ItemStack((goldToTransform > 0) ? Material.GOLD_BLOCK : Material.AIR, goldToTransform), new ItemStack((glowstoneToTransform > 0) ? Material.GLOWSTONE : Material.AIR, glowstoneToTransform), new ItemStack((ironOverflow > 0) ? Material.IRON_INGOT : Material.AIR, ironOverflow), new ItemStack((goldOverflow > 0) ? Material.GOLD_INGOT : Material.AIR, goldOverflow), new ItemStack((glowstoneOverflow > 0) ? Material.GLOWSTONE_DUST : Material.AIR, glowstoneOverflow) });
            player.getInventory().addItem(new ItemStack[] { new ItemStack((rT > 0) ? Material.REDSTONE_BLOCK : Material.AIR, rT) });
            player.getInventory().addItem(new ItemStack[] { new ItemStack((lT > 0) ? Material.LAPIS_BLOCK : Material.AIR, lT) });
            player.getInventory().addItem(new ItemStack[] { new ItemStack((cT > 0) ? Material.COAL_BLOCK : Material.AIR, cT) });
            player.getInventory().addItem(new ItemStack[] { new ItemStack((rO > 0) ? Material.REDSTONE : Material.AIR, rO) });
            player.getInventory().addItem(new ItemStack[] { new ItemStack((lO > 0) ? Material.INK_SACK : Material.AIR, lO, (short)4) });
            player.getInventory().addItem(new ItemStack[] { new ItemStack((cO > 0) ? Material.COAL : Material.AIR, cO) });
            player.updateInventory();
        }
        catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return true;
    }
}