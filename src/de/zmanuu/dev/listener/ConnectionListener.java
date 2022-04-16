package de.zmanuu.dev.listener;

import de.zmanuu.dev.items.Menu;
import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.regions.RegionManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class ConnectionListener implements Listener {

    private final Menu menu;

    public ConnectionListener() {
        menu = new Menu();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage("§5§lRPG §7» §a+ " + player.getName());

        ItemStack menuItem = menu.buildItem();
        player.getInventory().setItem(8, menuItem);

        // Regions
        RegionManager.setCurrentRegion(player);

        // Lebenspunkte
        RPGSystem.sp.loadPlayer(player);

        // LockItem
        RPGSystem.lock.onJoin(event, new ItemStack[]{
                menuItem
        });

        // HelpChat
        RPGSystem.helpChat.login(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.setQuitMessage("§5§lRPG §7» §c- " + player.getName());

        // Lebenspunkte
        RPGSystem.sp.savePlayer(player);

        // HelpChat
        RPGSystem.helpChat.logout(player);
    }

}
