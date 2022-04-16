package de.zmanuu.dev.listener;
/*
———————————————————————————————————
 Author: zManuu
 Project: RPG
 File: de.zmanuu.dev.listener.Build
 Date: 19.06.2020
———————————————————————————————————
*/

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("rpg.build") || player.getGameMode() != GameMode.CREATIVE) {
            event.setDropItems(false);
            event.setCancelled(true);
            event.setExpToDrop(0);
        }
    }

    @EventHandler
    public void onBuild(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("rpg.build") || player.getGameMode() != GameMode.CREATIVE) {
            event.setBuild(false);
            event.setCancelled(true);
        }
    }

}
