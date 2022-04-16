package de.zmanuu.dev.regions;

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.utils.MainAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(cmd.getName().equalsIgnoreCase("region")) {
            if(sender instanceof Player) {
                if(args.length==0) {
                    Player p = (Player) sender;
                    p.sendMessage(RPGSystem.getAPI().getMessages().prefix +
                            "§7Du befindest dich im Moment in §a"+RegionManager.getCurrentRegion(p).getName()+"§7.");
                }
            }
        }

        return false;
    }
}
