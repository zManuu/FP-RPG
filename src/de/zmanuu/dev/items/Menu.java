package de.zmanuu.dev.items;

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.regions.RegionManager;
import de.zmanuu.dev.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Menu implements Listener {

    public ItemStack buildItem() {
        return new ItemFactory(Material.CARTOGRAPHY_TABLE,"§5Charakter Info").build();
    }

    public void buildGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 9*3, "§8[§5§lMenü§8]");

        if(RegionManager.getCurrentRegion(player)==null)
            gui.setItem(10, new ItemFactory(Material.MAP,"§7Region§8:","§8 ➥ §5/").build());
        else
            gui.setItem(10, new ItemFactory(Material.MAP,"§7Region§8:","§8 ➥ §5"+ RegionManager.getCurrentRegion(player).getName()).build());


        gui.setItem(11, new ItemFactory(Material.LAPIS_LAZULI,"§7Lebenspunkte§8:","§8 ➥ §5"+ RPGSystem.sp.getPoints(player),1).build());
        gui.setItem(12, new ItemFactory(Material.RED_BANNER, "§cClan", "§8➥ §7§oKlicke, um zu öffnen").build());
        gui.setItem(13, new ItemFactory(player, "§7Profil", "§8➥ §7§oKlicke, um zu öffnen").build());
        gui.setItem(14, new ItemFactory(Material.IRON_SWORD, "§5Skills", "§8➥ §7§oKlicke, um zu öffnen").build());
        gui.setItem(15, new ItemFactory(Material.NETHER_STAR, "§eEigenschaften", "§8➥ §7§oKlicke, um zu öffnen").build());
        gui.setItem(16, new ItemFactory(Material.EXPERIENCE_BOTTLE, "§aParty", "§8➥ §7§oKlicke, um zu öffnen").build());

        player.openInventory(gui);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.getItem()==null) return;
        if(event.getItem().getType()!=Material.CARTOGRAPHY_TABLE) return;
        if(event.getItem().getItemMeta()==null) return;
        if(!event.getItem().getItemMeta().getDisplayName().equals(buildItem().getItemMeta().getDisplayName())) return;
        event.setCancelled(true);
        event.setUseItemInHand(Event.Result.DENY);
        buildGUI(event.getPlayer());
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof Player)) return;
        if(!event.getView().getTitle().equals("§8[§5§lMenü§8]")) return;
        if(event.getCurrentItem()==null) return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        switch (event.getCurrentItem().getType()) {
            case RED_BANNER:
                close(p, "clan");
                break;
            case PLAYER_HEAD:
                close(p, "player");
                break;
            case IRON_SWORD:
                close(p, "skills");
                break;
            case NETHER_STAR:
                close(p, "attributes");
                break;
            case EXPERIENCE_BOTTLE:
                close(p, "party");
                break;
            default:
                break;
        }

    }

    private void close(Player player, String command) {
        player.closeInventory();
        player.performCommand(command);
    }
}
