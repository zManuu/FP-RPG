package de.zmanuu.dev.lebenspunkte;

import de.zmanuu.dev.items.Menu;
import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.regions.Region;
import de.zmanuu.dev.utils.Locations;
import de.zmanuu.dev.utils.MainAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SoulPoints {

    private int maxpoints = 10;
    public static HashMap<Player, Integer> map;

    public void enable() {
        map = new HashMap<>();
        new BukkitRunnable(){
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    setPoints(player, getPoints(player)+1);
                    player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                            + "§aDu hast einen Lebenspunkt erhalten!");
                }
            }
        }.runTaskTimerAsynchronously(RPGSystem.getPlugin(),0,36000);
    }

    public void loadPlayer(Player player) {
        if(!map.containsKey(player)) {
            map.put(player, Integer.parseInt(RPGSystem.getAPI().getMySQL().get("leben", player, 3)));
        }
    }

    public void savePlayer(Player player) {
        RPGSystem.getAPI().getMySQL().set("leben", player, "amount", String.valueOf(getPoints(player)));
    }

    public int getPoints(Player player) {
        if(map.containsKey(player))
            return map.get(player);
        return -1;
    }

    public boolean takePoint(Player player, Location loc, Region r) {
        if(getPoints(player)-1<=0) {
            map.put(player,maxpoints);
            int x = (int) loc.getX(), y = (int) loc.getY(), z = (int) loc.getZ();
            String locString = x+"/"+y+"/"+z;
            if (r != null)
                locString = x+"/"+y+"/"+z+" ("+r.getName()+")";
            player.teleport(RPGSystem.getAPI().getLocations().getSpawn());
            player.sendTitle("§8[§4✝§8]","§7Du bist gestorben!",5,60,5);
            player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_DEATH,1,1);
            player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                    + "§cDu bist gestorben!§f\n"+
                    RPGSystem.getAPI().getMessages().prefix
                    + "§7Da du keine Lebenspunkte mehr hattest, verlierst du deine Items!§f\n"+
                    RPGSystem.getAPI().getMessages().prefix
                    + "§7Du bist gestorben bei: §a"+locString);
            Bukkit.getScheduler().runTaskLaterAsynchronously(RPGSystem.getPlugin(),
                    () -> player.getInventory().setItem(8, new Menu().buildItem()), 20);
            return false;
        } else {
            map.put(player, getPoints(player)-1);
            player.teleport(RPGSystem.getAPI().getLocations().getSpawn());
            player.sendTitle("§8[§4✝§8]","§7Du bist gestorben!",5,60,5);
            player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_DEATH,1,1);
            player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                    + "§cDu bist gestorben!§f\n"+RPGSystem.getAPI().getMessages().prefix
                    + "§7Da du noch Lebenspunkte hattest, verlierst du keine Items!");
            return true;
        }
    }

    public void setPoints(Player p, int points) {
        map.put(p, points);
    }

}
