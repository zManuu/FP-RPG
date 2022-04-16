package de.zmanuu.dev.dungeons;
/*
———————————————————————————————————
 Author: zManuu
 Project: RPG
 File: de.zmanuu.dev.dungeons.DungeonStage
 Date: 24.06.2020
———————————————————————————————————
*/

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.utils.MainAPI;
import de.zmanuu.dev.utils.DungeonRegions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class DungeonStage implements Listener {

    private String name;
    private int ID;
    private DungeonRegions gate;
    private HashMap<Location, Material> materials;
    private ItemStack requiredItem;
    private Location giveLoc;

    public DungeonStage(String name, int ID, DungeonRegions gate, ItemStack requiredItem, Location giveLoc) {
        this.name = name;
        this.ID = ID;
        this.gate = gate;
        this.requiredItem = requiredItem;
        this.giveLoc = giveLoc;
        Bukkit.getPluginManager().registerEvents(this, RPGSystem.getPlugin());
    }

    // Getter
    public String getName() {
        return name;
    }

    public DungeonRegions getGate() {
        return gate;
    }
    public boolean gateIsOpen() {
        return getGate().getMiddle().getBlock().getType() == Material.AIR;
    }

    // ActionPerformer
    public void openGate() {
        materials = new HashMap<>();
        getGate().getContent().forEach(current -> {
            materials.put(current, current.getBlock().getType());
            current.getBlock().setType(Material.AIR);
        });
        Bukkit.getScheduler().runTaskLater(RPGSystem.getPlugin(), ()->{
            getGate().getContent().forEach(current->{
                current.getBlock().setType(materials.get(current));
            });
        }, 50);
    }
    public void explodeEffect(Location location) {
        /*

        LIB

        EffectManager manager = new EffectManager(RPGSystem.getPlugin());
        de.slikey.effectlib.Effect effect = new ExplodeEffect(manager);
        effect.setLocation(location);
        effect.start();
         */
    }

    // Listener
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock() == null) return;
        if (event.getClickedBlock().getType() != giveLoc.getBlock().getType()) return;
        if (!event.getClickedBlock().getLocation().equals(giveLoc)) return;
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (itemInHand.getType() != requiredItem.getType()) return;
        if (itemInHand.getItemMeta() == null || !itemInHand.getItemMeta().getDisplayName().equals(requiredItem.getItemMeta().getDisplayName())) return;
        if (itemInHand.getAmount() >= requiredItem.getAmount()) {
            if (!gateIsOpen())
                openGate();
            else
                player.sendMessage(RPGSystem.getAPI().getMessages().prefix + "§cDas Tor ist bereits geöffnet!");
        } else
            player.sendMessage(RPGSystem.getAPI().getMessages().prefix + "§cDu brauchst mindestens " + requiredItem.getAmount() + " Tokens!");
    }

}
