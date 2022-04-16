package de.zmanuu.dev.listener;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class DestroyListener implements Listener {

    public static void onFieldDestroy(PlayerInteractEvent event) {
        if(event.getAction() != Action.PHYSICAL) return;
        if(event.getClickedBlock() == null) return;
        if(event.getClickedBlock().getType() == Material.FARMLAND) {
            event.setCancelled(true);
            event.setUseInteractedBlock(Event.Result.DENY);
        }
    }

}
