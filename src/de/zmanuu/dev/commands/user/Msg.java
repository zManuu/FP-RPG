package de.zmanuu.dev.commands.user;

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.utils.MainAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Msg implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(RPGSystem.getAPI().getMessages().playerCMD);
            return true;
        }
        if(args.length < 2) {
            sender.sendMessage(RPGSystem.getAPI().getMessages().wrongArgs);
            return true;
        }

        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);

        if(target == null) {
            player.sendMessage(RPGSystem.getAPI().getMessages().playerOffline);
            return true;
        }

        StringBuilder b1 = new StringBuilder("§8[§f" + player.getName() + " §8» §fDu§8] ");
        for(int i=1; i<args.length; i++) {
            b1.append(i);
        }
        target.sendMessage(b1.toString());
        StringBuilder b2 = new StringBuilder("§8[§fDu §8» §f" + target.getName() + "§8] ");
        for(int i=1; i<args.length; i++) {
            b2.append(i);
        }
        target.sendMessage(b2.toString());

        return true;
    }
}
