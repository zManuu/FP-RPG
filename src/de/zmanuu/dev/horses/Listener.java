package de.zmanuu.dev.horses;
/*
———————————————————————————————————
 Author: zManuu
 Project: RPG
 File: de.zmanuu.dev.horses.Listener
 Date: 21.06.2020
———————————————————————————————————
*/

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.utils.MainAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Listener implements org.bukkit.event.Listener {

    private Horse horse;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (itemInHand.getType() != Material.SADDLE || itemInHand.getItemMeta() == null || !itemInHand.getItemMeta().getDisplayName().equals("§7Pferd")) return;
        if (itemInHand.getItemMeta().getLore() == null || itemInHand.getItemMeta().getLore().get(3) == null || itemInHand.getItemMeta().getLore().size()!=4) return;
        if (!itemInHand.getItemMeta().getLore().get(3).replace("§8§k§o", "").equals(player.getUniqueId().toString())) return;
        if (Manager.spawnedHorses == null) Manager.spawnedHorses = new ArrayList<>();
        if (!Manager.spawnedHorses.contains(player)) {
            horse = new Horse(player);
            horse.spawn();
        } else
            player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                    + "§7Um dein Pferd einzusammeln, halte deinen Sattel in der Hand und klicke auf das Pferd.");
    }

    @EventHandler
    public void onHit(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() != EntityType.HORSE) return;
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (itemInHand.getType() != Material.SADDLE || itemInHand.getItemMeta() == null || !itemInHand.getItemMeta().getDisplayName().equals("§7Pferd")) return;
        org.bukkit.entity.Horse clickedHorse = (org.bukkit.entity.Horse) event.getRightClicked();
        if (clickedHorse.getCustomName() == null) return;
        if (clickedHorse.getCustomName().equals(ChatColor.GRAY + player.getName() + "'s Pferd")) {
            event.setCancelled(true);
            horse.remove();
        }
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        if (Manager.spawnedHorses == null) return;
        if (Manager.spawnedHorses.contains(event.getPlayer()) && horse != null) {
            horse.remove();
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        if (event.getInventory() instanceof HorseInventory) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEnter(VehicleEnterEvent event) {
        if (event.getEntered().getType() != EntityType.PLAYER) return;
        if (event.getVehicle().getType() != EntityType.HORSE) return;
        Player player = (Player) event.getEntered();
        org.bukkit.entity.Horse clickedHorse = (org.bukkit.entity.Horse) event.getVehicle();
        if (clickedHorse.getCustomName() == null) return;
        if (!clickedHorse.getCustomName().equals(ChatColor.GRAY + player.getName() + "'s Pferd")) {
            event.setCancelled(true);
            player.sendMessage(RPGSystem.getAPI().getMessages().prefix + "§cDieses Pferd gehört nicht dir!");
        }
    }

}
