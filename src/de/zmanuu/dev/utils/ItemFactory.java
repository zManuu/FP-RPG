package de.zmanuu.dev.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemFactory {

    private ItemStack item;

    public ItemFactory(Material material, String displayname) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayname);
        item.setItemMeta(itemMeta);
        this.item = item;
    }

    public ItemFactory(Material material, String displayname, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayname);
        List<String> lore1 = new ArrayList<>();
        lore1.add(lore);
        itemMeta.setLore(lore1);
        item.setItemMeta(itemMeta);
        this.item = item;
    }

    public ItemFactory(Material material, String displayname, String lore, int model) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayname);
        List<String> lore1 = new ArrayList<>();
        lore1.add(lore);
        itemMeta.setLore(lore1);
        itemMeta.setCustomModelData(model);
        item.setItemMeta(itemMeta);
        this.item = item;
    }

    public ItemFactory(Player owner, String displayname, String lore) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
        itemMeta.setDisplayName(displayname);
        itemMeta.setOwningPlayer(owner);
        itemMeta.setLore(Collections.singletonList(lore));
        item.setItemMeta(itemMeta);
        this.item = item;
    }

    public void addEnchantment(Enchantment enchantment, int level) {
        item.addEnchantment(enchantment, level);
    }

    public ItemFactory setLore(ArrayList<String> lore) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return this;
    }

    public void addLoreLine(String line) {
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta.getLore() == null || itemMeta.getLore().isEmpty()) {
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(line);
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
        } else {
            List<String> lore = itemMeta.getLore();
            lore.add(line);
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
        }

    }

    public void setAmount(int amount) {
        this.item.setAmount(amount);
    }

    public ItemStack build() {
        return item;
    }

    public static String toString(ItemStack[] items) {
        StringBuilder s = new StringBuilder();
        for (ItemStack item : items) {
            s.append(item.toString());
        }
        return s.toString();
    }

    public static ItemStack[] toItems(String itemString) {
        ItemStack[] items = new ItemStack[27];
        String[] i1 = itemString.split("#");
        for (int i2=0; i2<i1.length; i2++) {
            String[] values = i1[i2].split(";");
            Material material = Material.valueOf(values[0]);
            String display = values[1];
            ItemStack item = new ItemFactory(material, display).build();
            items[i2] = item;
        }
        return items;
    }

}
