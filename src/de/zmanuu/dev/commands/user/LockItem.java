package de.zmanuu.dev.commands.user;

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.utils.MainAPI;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LockItem implements CommandExecutor {

    private static HashMap<Player, List<ItemStack>> items;
    public void load() {
        items = new HashMap<>();
    }

    private boolean isLocked(Player player, ItemStack item) {
        for (ItemStack itemStack : items.get(player)) {
            if(itemStack.getType() != item.getType())
                continue;
            if(itemStack.getItemMeta() == null)
                continue;
            if(item.getItemMeta() == null)
                continue;
            if(itemStack.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName()))
                return true;
        }
        return false;
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
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();
        if(item.getType() == Material.AIR) {
            player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                    + "§cDu kannst dieses Item nicht sichern!");
            return true;
        }

        if(!isLocked(player, item)){
            List<ItemStack> playerItems = items.get(player);
            playerItems.add(item.clone());
            items.put(player, playerItems);
            player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                    + "§aDas Item in deiner Hand ist jetzt gesichert!");
            return true;
        }

        List<ItemStack> playerItems = items.get(player);
        playerItems.remove(item);
        items.put(player, playerItems);
        player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                + "§7Das Item in deiner Hand ist jetzt nichtmehr gesichert!");
        return true;
    }

    public void onJoin(PlayerJoinEvent event, ItemStack[] instantApply) {
        items.put(event.getPlayer(), Arrays.asList(instantApply));
    }

    public void onDrop(PlayerDropItemEvent event) {
        if(isLocked(event.getPlayer(), event.getItemDrop().getItemStack()))
            event.setCancelled(true);
    }
}
