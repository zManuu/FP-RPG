package de.zmanuu.dev.listener;
/*
———————————————————————————————————
 Author: zManuu
 Project: RPG
 File: de.zmanuu.dev.listener.Damage
 Date: 18.06.2020
———————————————————————————————————
*/

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.utils.MainAPI;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) return;
        if (event.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
            event.setDamage(0);
            event.setCancelled(true);
            RPGSystem.getAPI().sendActionBar((Player) event.getEntity(), RPGSystem.getAPI().getMessages().prefix
                    + "§7Du kannst dich mit §c/kill §7töten!");
        } else if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            event.setDamage(event.getDamage()/3);
        }
    }

}
