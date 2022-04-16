package de.zmanuu.dev.listener;
/*
———————————————————————————————————
 Author: zManuu
 Project: RPG
 File: de.zmanuu.dev.listener.Death
 Date: 19.06.2020
———————————————————————————————————
*/

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.regions.RegionManager;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        
        event.setDeathMessage("§8[§4✝§8] §7Der Spieler §c" + player.getName() + "§7 ist gestorben!");
        event.setDroppedExp(0);
        event.getDrops().clear();

        // Lebenspunkte
        if(RPGSystem.sp.takePoint(player, player.getLocation(),
                RegionManager.getCurrentRegion(player))) {
            event.setKeepInventory(true);
        } else
            event.setKeepInventory(false);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(RPGSystem.getAPI().getLocations().getSpawn());
    }

}
