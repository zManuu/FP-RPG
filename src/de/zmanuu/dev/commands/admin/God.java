package de.zmanuu.dev.commands.admin;

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.utils.MainAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class God implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(RPGSystem.getAPI().getMessages().playerCMD);
            return true;
        }

        if(args.length != 0) {
            sender.sendMessage(RPGSystem.getAPI().getMessages().wrongArgs);
            return true;
        }

        Player player = (Player) sender;
        if(player.isInvulnerable()) {
            player.setInvulnerable(false);
            player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                    + "§7Du bist jetzt verwundbar!");
        } else {
            player.setInvulnerable(true);
            player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                    + "§7Du bist jetzt unverwundbar!");
        }

        return false;
    }
}
