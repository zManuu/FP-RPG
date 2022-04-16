package de.zmanuu.dev.listener;

import de.zmanuu.dev.commands.admin.ClearItems;
import de.zmanuu.dev.main.RPGSystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        ClearItems.onClick(event);
        RPGSystem.help.onClick(event);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        RPGSystem.lock.onDrop(event);
    }

}
