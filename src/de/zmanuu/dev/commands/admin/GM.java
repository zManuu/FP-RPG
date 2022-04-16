package de.zmanuu.dev.commands.admin;

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.utils.MainAPI;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GM implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("rpg.commands.admin.gm") ||
                    (player.hasPermission("rpg.builder") && player.getWorld().getName().equals("Bauwelt"))) {

                if (args.length == 0) {
                    if (player.getGameMode() == GameMode.SURVIVAL) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                                + "§7Dein neuer Spielmodus ist §a§lCREATIVE§7!");
                    } else if (player.getGameMode() == GameMode.CREATIVE) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                                + "§7Dein neuer Spielmodus ist §a§lSURVIVAL§7!");
                    } else
                        player.sendMessage(RPGSystem.getAPI().getMessages().wrongArgs);
                } else if (args.length == 1) {
                    String arg1 = args[0];
                    if (arg1.equalsIgnoreCase("c") || arg1.equals("1")) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                                + "§7Dein neuer Spielmodus ist §a§lCREATIVE§7!");
                    } else if (arg1.equalsIgnoreCase("s") || arg1.equals("0")) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                                + "§7Dein neuer Spielmodus ist §a§lSURVIVAL§7!");
                    } else if (arg1.equalsIgnoreCase("sp") || arg1.equals("3")) {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                                + "§7Dein neuer Spielmodus ist §a§lSPECTATOR§7!");
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
