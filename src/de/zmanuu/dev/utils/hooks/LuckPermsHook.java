package de.zmanuu.dev.utils.hooks;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.entity.Player;

import java.util.Objects;

public class LuckPermsHook {

    private UserManager userManager;

    public LuckPermsHook() {
        userManager = LuckPermsProvider.get().getUserManager();
    }
    private User getUser(final Player player) {
        return userManager.getUser(player.getUniqueId());
    }

    public String getPrefix(final Player player) {
        return "§7Spieler";
    }

}
