package de.zmanuu.dev.commands.admin;

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.utils.ItemFactory;
import de.zmanuu.dev.utils.MainAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ClearItems implements CommandExecutor, Listener {

    private static final String GUI_NAME = "§4Bist du sicher?";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("clearitems")) {
            if (!(sender instanceof Player)) return true;
            Player player = (Player) sender;
            if (player.hasPermission("rpg.commands.admin.clearitems")) {
                buildConfirmGUI(player);
            }
        }
        return false;
    }

    private void buildConfirmGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 9*3, GUI_NAME);

        gui.setItem(11, new ItemFactory(Material.GREEN_CONCRETE, "§2Ja").build());
        gui.setItem(15, new ItemFactory(Material.RED_CONCRETE, "§4Nein").build());

        player.openInventory(gui);
    }

    public static void onClick(InventoryClickEvent event) {
        if (!(event.getView().getTitle().equals(GUI_NAME))) return;
        if (event.getClickedInventory() == null) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player p = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null) return;
        p.closeInventory();
        event.setCancelled(true);
        if (event.getCurrentItem().getType() == Material.GREEN_CONCRETE)
            deleteItems(p);
    }

    private static void deleteItems(Player p) {
        p.sendMessage(RPGSystem.getAPI().getMessages().prefix
                + "§aAlle Items auf deiner Welt wurden gelöscht!");
        p.getWorld().getEntities().forEach(current->{
            if (current.getType() == EntityType.DROPPED_ITEM)
                current.remove();
        });
    }

}
