package com.minetribes.commands;

import com.minetribes.UHCFAddons;
import com.minetribes.utils.FileUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ConfigReloadCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if (sender.isOp()) {
            FileUtil fileUtil = new FileUtil(UHCFAddons.instance);

            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("reload")) {
                    fileUtil.reloadConfig(args[1] + ".yml");
                    sender.sendMessage("§6§lFoxile §7» §fJe hebt de §6" + args[1] + ".yml gereload§f!");
                }else{
                    sender.sendMessage("§6§lFoxile §7» §fCorrect gebruik: §6/foxileuhcfaddons reload <filename>§f!");
                }
            }else{
                sender.sendMessage("§6§lFoxile §7» §fCorrect gebruik: §6/foxileuhcfaddons reload <filename>§f!");
            }
        }else{
            sender.sendMessage("§6§lFoxile §7» §fWat probeer je vriend?");

        }


        return true;
    }
}
