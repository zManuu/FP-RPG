package de.zmanuu.dev.horses;
/*
———————————————————————————————————
 Author: zManuu
 Project: RPG
 File: de.zmanuu.dev.horses.Manager
 Date: 21.06.2020
———————————————————————————————————
*/

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Manager {

    public static ArrayList<Player> spawnedHorses;

    public static ItemStack buildItem(Player player) {
        ItemStack item = new ItemStack(Material.SADDLE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§7Pferd");
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add("§8• §7Besitzer§8: §7§o" + player.getName());
        itemLore.add(" ");
        itemLore.add("§7§m                                                    §f");
        itemLore.add("§8§k§o" + player.getUniqueId().toString());
        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);
        return item;
    }

}
