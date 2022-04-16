package de.zmanuu.dev.commands.admin;
/*
———————————————————————————————————
 Author: zManuu
 Project: RPG
 File: de.zmanuu.dev.commands.admin.Skull
 Date: 16.06.2020
———————————————————————————————————
*/

import de.zmanuu.dev.main.RPGSystem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Skull implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("rpg.commands.admin.skull")) {
                if (args.length == 1) {
                    player.getInventory().addItem(buildSkull(args[0]));
                } else
                    player.sendMessage(RPGSystem.getAPI().getMessages().wrongArgs);
            } else
                player.sendMessage(RPGSystem.getAPI().getMessages().noPerms);
        } else
            sender.sendMessage(RPGSystem.getAPI().getMessages().playerCMD);
        return false;
    }

    private ItemStack buildSkull(String owner) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setDisplayName("§bKopf von " + owner);
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(owner));
        skull.setItemMeta(meta);
        return skull;
    }

}
