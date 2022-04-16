package de.zmanuu.dev.listener;

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.regions.RegionManager;
import de.zmanuu.dev.utils.MainAPI;
import de.zmanuu.dev.utils.hooks.MMOCoreHook;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onChat(AsyncPlayerChatEvent event) {
        if(event.getMessage().contains("%")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(RPGSystem.getAPI().getMessages().prefix
                    +"§7Bitte gebe kein '§c%§7' in deiner Nachricht an!");
            return;
        }
        Player player = event.getPlayer();
        int level = getLevel(player);
        String clazz = getClazz(player);
        String prefix = getRank(player);
        String region = getRegion(player);
        event.setFormat("");
        event.setCancelled(true);
        TextComponent message = new TextComponent("§8["+prefix+"§8] §7"+player.getName()+"§8: §f"+event.getMessage());
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§8(§7Klasse: "+clazz+" Lvl. "+level+"§8 | §7Region: "+region+"§8)").create()));
        RPGSystem.getPlugin().getServer().spigot().broadcast(message);
        RPGSystem.getPlugin().getLogger().info(player.getName()+": "+event.getMessage());

    }

    private String getRegion(Player player) {
        if(RegionManager.getCurrentRegion(player)==null) return "/";
        else return RegionManager.getCurrentRegion(player).getName();
    }
    private String getRank(Player player) {
        return RPGSystem.lphook.getPrefix(player);
    }
    private String getClazz(Player player) {
        return MMOCoreHook.getClazz(player);
    }
    private int getLevel(Player player) {
        return MMOCoreHook.getLevel(player);
    }

}
