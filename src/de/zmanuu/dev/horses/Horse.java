package de.zmanuu.dev.horses;
/*
———————————————————————————————————
 Author: zManuu
 Project: RPG
 File: de.zmanuu.dev.horses.Horse
 Date: 21.06.2020
———————————————————————————————————
*/

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Horse {

    private final Player owner;
    private org.bukkit.entity.Horse horse;
    private boolean living = false;

    public Horse(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public void spawn() {
        Location loc = new Location(owner.getWorld(), owner.getLocation().getX()+1, owner.getLocation().getY(), owner.getLocation().getZ()+1);
        org.bukkit.entity.Horse horse = (org.bukkit.entity.Horse) owner.getWorld().spawnEntity(loc, EntityType.HORSE);
        horse.setColor(org.bukkit.entity.Horse.Color.WHITE);
        horse.setStyle(org.bukkit.entity.Horse.Style.NONE);
        horse.setOwner(owner);
        horse.setAdult();
        horse.setCanPickupItems(false);
        horse.setCustomName(ChatColor.GRAY + getOwner().getName() + "'s Pferd");
        horse.setCustomNameVisible(true);
        horse.setInvulnerable(true);
        horse.setTamed(true);
        horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        horse.setMaxHealth(40);
        horse.setHealth(horse.getMaxHealth());
        this.horse = horse;
        living = true;
        Manager.spawnedHorses.add(owner);
    }

    public void remove() {
        if (horse == null)
            spawn();
        horse.remove();
        living = false;
        Manager.spawnedHorses.remove(owner);
    }

}
