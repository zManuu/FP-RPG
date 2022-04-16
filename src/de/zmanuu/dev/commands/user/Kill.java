package de.zmanuu.dev.commands.user;
/*
———————————————————————————————————
 Author: zManuu
 Project: RPG
 File: de.zmanuu.dev.commands.user.Kill
 Date: 18.06.2020
———————————————————————————————————
*/

import de.zmanuu.dev.items.Menu;
import de.zmanuu.dev.main.RPGSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Kill implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("kill")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.hasPermission("rpg.commands.admin.kill")) {
                    player.sendMessage(RPGSystem.getAPI().getMessages().noPerms);
                    return true;
                }
                if (args.length == 0) {
                    player.setHealth(player.getMaxHealth());
                    if((RPGSystem.sp.getPoints(player)-1) >= 0) {
                        RPGSystem.sp.setPoints(player, RPGSystem.sp.getPoints(player) - 1);
                        player.teleport(RPGSystem.getAPI().getLocations().getSpawn());
                        player.setHealth(player.getMaxHealth());
                        player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                                + "§aDu wurdest erfolgreich getötet!");
                        player.getInventory().setItem(8, new Menu().buildItem());
                    } else {
                        player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                        + "§cDu hast nicht genügend Lebenspunkte!");
                    }
                } else if (args.length == 1) {
                    if (args[0].equals("@e")) {
                        ArrayList<EntityType> notRemove = new ArrayList<>();
                        notRemove.add(EntityType.PLAYER);
                        notRemove.add(EntityType.VILLAGER);
                        notRemove.add(EntityType.HORSE);
                        player.getWorld().getEntities().forEach(current->{
                            if (!notRemove.contains(current.getType()))
                                current.remove();
                        });
                    } else if (args[0].equals("@all")) {
                        player.getWorld().getEntities().stream().filter(
                                entity -> (entity.getType() != EntityType.PLAYER && entity.getType() != EntityType.VILLAGER)
                        ).forEach(Entity::remove);
                    } else
                        player.sendMessage(RPGSystem.getAPI().getMessages().wrongArgs);
                } else
                    player.sendMessage(RPGSystem.getAPI().getMessages().wrongArgs);
            } else
                sender.sendMessage(RPGSystem.getAPI().getMessages().playerCMD);
        }
        return false;
    }
}
