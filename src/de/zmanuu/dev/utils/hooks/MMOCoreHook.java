package de.zmanuu.dev.utils.hooks;

import net.Indyuce.mmocore.api.player.PlayerData;
import org.bukkit.entity.Player;

public class MMOCoreHook {

    public static PlayerData getPlayerData(final Player player) {
        return PlayerData.get(player.getUniqueId());
    }

    public static int getLevel(final Player player) {
        return getPlayerData(player).getLevel();
    }
    public static String getClazz(final Player player) {
        return getPlayerData(player).getProfess().getName();
    }

}
