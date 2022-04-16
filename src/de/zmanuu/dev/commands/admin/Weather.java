package de.zmanuu.dev.commands.admin;

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.utils.MainAPI;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Weather implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("rpg.commands.admin.weather")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("clear") || args[0].equals("0")) {
                        World world = player.getWorld();
                        world.setStorm(false);
                        world.setThundering(false);
                        player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                                + "§7Das Wetter in der Welt §a" + world.getName() + "§7 ist nun sonnig!");
                    } else
                        player.sendMessage(RPGSystem.getAPI().getMessages().wrongArgs);
                } else
                    player.sendMessage(RPGSystem.getAPI().getMessages().wrongArgs);
            } else
                player.sendMessage(RPGSystem.getAPI().getMessages().noPerms);
        } else
            sender.sendMessage(RPGSystem.getAPI().getMessages().playerCMD);
        return false;
    }
}
