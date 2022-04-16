package de.zmanuu.dev.utils.items;

import de.zmanuu.dev.utils.ItemFactory;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ItemManager {

    private static ArrayList<ItemStack> items;

    public static ItemStack getItem(String[] s) {
        int key = Integer.parseInt(s[0]);
        ItemStack i = items.get(key);
        i.setAmount(Integer.parseInt(s[1]));
        return i;
    }

    public static void onEnable() {
        items = new ArrayList<>();
        loadItems();
    }

    private static void loadItems() {
        items.add(new ItemFactory(Material.DIRT,"§5Test").build());
    }

    public static String toString(ItemStack item) {
        for (int i = 0; i < items.size(); i++) {
            if(!(item.getType()==items.get(i).getType())) continue;
            if(item.getItemMeta().getDisplayName().equals(items.get(i).getItemMeta().getDisplayName()))
                return i+":"+item.getAmount()+";";
        }
        return "ERROR";
    }

}
