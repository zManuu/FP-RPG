package de.zmanuu.dev.utils;

import de.zmanuu.dev.utils.hooks.MySQLAPI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class MainAPI {

    public MainAPI() {
        messages = new Messages();
        mysql = new MySQLAPI();
        locations = new Locations();
    }

    private final Messages messages;
    public Messages getMessages() {
        return messages;
    }

    private final MySQLAPI mysql;
    public MySQLAPI getMySQL() {
        return mysql;
    }

    private final Locations locations;
    public Locations getLocations() {
        return locations;
    }

    public void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
    }

}
