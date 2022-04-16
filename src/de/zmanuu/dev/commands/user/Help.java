package de.zmanuu.dev.commands.user;

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.utils.ItemFactory;
import de.zmanuu.dev.utils.MainAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;

public class Help implements CommandExecutor {

    private static Inventory guiMain;
    private static Inventory guiFaq;

    public void load() {
        /*
            MainGUI
         */
        guiMain = Bukkit.createInventory(null, 9*3, "§e§lHilfe");
        guiMain.setItem(10, new ItemFactory(Material.BOOK, "§eFAQ", "§8  ➥ §7Klicke, um zu öffnen").build());



        /*
            FAQ
         */
        guiFaq = Bukkit.createInventory(null, 9*5, "§e§lHilfe §e➟ FAQ");
        guiFaq.setItem(10, new ItemFactory(Material.BOOKSHELF, "§7§nTexturepack:").setLore(new ArrayList<>(Arrays.asList(
                "a", "b", "c", "d"
        ))).build());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(RPGSystem.getAPI().getMessages().playerCMD);
            return true;
        }
        if(args.length != 0) {
            sender.sendMessage(RPGSystem.getAPI().getMessages().wrongArgs);
            return true;
        }
        ((Player) sender).openInventory(guiMain);
        return true;
    }

    public void onClick(InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof Player)) return;
        if(event.getCurrentItem() == null) return;
        switch (event.getView().getTitle()) {
            case "§e§lHilfe":
                event.setCancelled(true);
                event.setResult(Event.Result.DENY);
                switch (event.getCurrentItem().getType()) {
                    case BOOK:
                        event.getWhoClicked().openInventory(guiFaq);
                        break;
                }
                break;
            case "§e§lHilfe §e➟ FAQ":
                event.setCancelled(true);
                event.setResult(Event.Result.DENY);
                break;
            default:
                break;
        }
    }

}
