package de.zmanuu.dev.regions;

import de.zmanuu.dev.main.RPGSystem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class RegionManager {

    public static HashMap<Player, Region> playersRegions;
    private static ArrayList<Region> regions;

    public static Region getCurrentRegion(Player player) {
        if(!playersRegions.containsKey(player))
            playersRegions.put(player, getRegion(player));
        return playersRegions.get(player);
    }
    public static void setCurrentRegion(Player player) {
        playersRegions.put(player, getRegion(player));
    }

    public static Region getRegion(Player player) {
        Region[] crntRegions = new Region[1];
        regions.forEach(current->{
            if(current.getCuboid().isIn(player))
                crntRegions[0] = current;
        });
        return crntRegions[0];
    }

    public static void loadRegions() {
        playersRegions = new HashMap<>();
        regions = new ArrayList<>();
        World w = RPGSystem.world;
        Region r1 = new Region("Test", new Cuboid(new Location(w,1262,186,1029), new Location(w,1267,191,1024)));
        Region r2 = new Region("Test", new Cuboid(new Location(w,1262,186,1023), new Location(w,1267,191,1018)));
        Region r3 = new Region("Test", new Cuboid(new Location(w,1262,186,1017), new Location(w,1267,191,1012)));
        regions.add(r1);
        regions.add(r2);
        regions.add(r3);
    }

    public static void sendWelcomeMessage(Player player) {
        player.sendTitle("§7Du betrittst", "§8§o" + RegionManager.getRegion(player).getName(), 10, 20, 10);
        player.playSound(player.getLocation(), Sound.BLOCK_BELL_USE, 10, 1);
    }

    public static void startScheduler() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (RegionManager.getRegion(player) == null) {
                        if (RegionManager.getCurrentRegion(player) != null)
                            RegionManager.setCurrentRegion(player);
                    } else {
                        if (RegionManager.getCurrentRegion(player) == null) {
                            RegionManager.sendWelcomeMessage(player);
                            RegionManager.setCurrentRegion(player);
                        } else if (!RegionManager.getRegion(player).getName().equals(RegionManager.getCurrentRegion(player).getName())) {
                            RegionManager.sendWelcomeMessage(player);
                            RegionManager.setCurrentRegion(player);
                        }
                    }
                }
            }
        }.runTaskTimerAsynchronously(RPGSystem.getPlugin(),0,40);
    }

}
