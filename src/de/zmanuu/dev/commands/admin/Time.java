package de.zmanuu.dev.commands.admin;
/*
———————————————————————————————————
 Author: zManuu
 Project: RPG
 File: de.zmanuu.dev.commands.admin.Time
 Date: 20.06.2020
———————————————————————————————————
*/

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.utils.MainAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Time implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("rpg.commands.admin.time")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("day") || args[0].equalsIgnoreCase("d")) {

                    }
                } else
                    player.sendMessage(RPGSystem.getAPI().getMessages().wrongArgs);
            } else
                player.sendMessage(RPGSystem.getAPI().getMessages().noPerms);
        } else
            sender.sendMessage(RPGSystem.getAPI().getMessages().playerCMD);
        return false;
    }
}
