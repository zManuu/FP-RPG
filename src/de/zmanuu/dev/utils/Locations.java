package de.zmanuu.dev.utils;

import de.zmanuu.dev.main.RPGSystem;
import org.bukkit.Location;

public class Locations {

    private Location spawn;
    public Location getSpawn() {
        return spawn;
    }

    public Locations() {
        spawn = new Location(RPGSystem.world,1277.5,157,1032.5,270,0);
    }

}
