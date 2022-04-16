package de.zmanuu.dev.horses;
/*
———————————————————————————————————
 Author: zManuu
 Project: RPG
 File: de.zmanuu.dev.horses.Buy
 Date: 21.06.2020
———————————————————————————————————
*/
import de.zmanuu.dev.main.RPGSystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender console = (ConsoleCommandSender) sender;
            if (args.length == 2 && args[0].equals("give")) {
                Player target = Bukkit.getPlayer(args[1]);
                if(target == null) {
                    console.sendMessage(RPGSystem.getAPI().getMessages().playerOffline);
                    return true;
                }
                target.getInventory().addItem(Manager.buildItem(target));
            } else
                console.sendMessage(RPGSystem.getAPI().getMessages().wrongArgs);
        } else
            sender.sendMessage(RPGSystem.getAPI().getMessages().consoleCMD);
        return false;
    }

}
