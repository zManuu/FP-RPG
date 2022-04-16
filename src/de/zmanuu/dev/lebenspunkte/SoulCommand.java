package de.zmanuu.dev.lebenspunkte;

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.utils.MainAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SoulCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(cmd.getName().equalsIgnoreCase("lebenspunkte")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if(args.length==0) {
                    p.sendMessage(RPGSystem.getAPI().getMessages().prefix
                            + "§7Du hast im Moment §a"+ RPGSystem.sp.getPoints(p)+"§7 Lebenspunkte.");
                } else
                    p.sendMessage(RPGSystem.getAPI().getMessages().wrongArgs);
            } else
                sender.sendMessage(RPGSystem.getAPI().getMessages().playerCMD);
        }
        return false;
    }
}
