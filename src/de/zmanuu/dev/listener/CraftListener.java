package de.zmanuu.dev.listener;

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.utils.MainAPI;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class CraftListener implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if(!(event.getWhoClicked() instanceof Player)) return;
        if(event.getWhoClicked().getGameMode() == GameMode.CREATIVE) return;
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        event.getWhoClicked().sendMessage(RPGSystem.getAPI().getMessages().prefix
                + "§cDu kannst nicht craften!");
    }

}
