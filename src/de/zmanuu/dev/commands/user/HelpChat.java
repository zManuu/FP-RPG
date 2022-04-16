package de.zmanuu.dev.commands.user;

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.utils.MainAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HelpChat implements CommandExecutor {

    private static List<Player> teamMembers;
    public void load() {
        teamMembers = new ArrayList<>();
    }
    public void login(final Player player) {
        if(player.hasPermission("rpg.team.helpchat")) {
            teamMembers.add(player);
            player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                    + "§aDu bist nun im Helpchat eingeloggt!");
            for (Player teamMember : teamMembers) {
                teamMember.sendMessage(RPGSystem.getAPI().getMessages().prefix
                        + "§7Das Teammitglied §a" + player.getName() + "§7 ist jetzt im Helpchat eingeloggt!");
            }
        }
    }
    public void logout(final Player player) {
        teamMembers.remove(player);
        for (Player teamMember : teamMembers) {
            teamMember.sendMessage(RPGSystem.getAPI().getMessages().prefix
                    + "§7Das Teammitglied §a" + player.getName() + "§7 ist jetzt aus dem Helpchat ausgeloggt!");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(RPGSystem.getAPI().getMessages().playerCMD);
            return true;
        }
        if(args.length < 1) {
            sender.sendMessage(RPGSystem.getAPI().getMessages().wrongArgs);
            return true;
        }

        StringBuilder msg = new StringBuilder();
        for(int i=0; i<args.length; i++) {
            msg.append(args[i]).append(" ");
        }

        for (Player teamMember : teamMembers) {
            teamMember.sendMessage("§8[§cHelpchat§8] §7"+ (((Player) sender).getName()) + "§8: §f " + msg.toString());
        }

        return true;
    }
}
