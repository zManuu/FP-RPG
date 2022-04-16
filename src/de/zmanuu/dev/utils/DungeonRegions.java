package de.zmanuu.dev.utils;
/*
———————————————————————————————————
 Author: zManuu
 Project: RPG
 File: de.zmanuu.dev.utils.Region
 Date: 18.06.2020
———————————————————————————————————
*/

import org.bukkit.Location;

import java.util.ArrayList;

public class DungeonRegions {

    private String name;
    private ArrayList<Location> content;
    private Location middle;

    public DungeonRegions(String name, ArrayList<Location> content, Location middle) {
        this.name = name;
        this.middle = middle;
        this.content = content;
    }

    public DungeonRegions(ArrayList<Location> content, Location middle) {
        this.middle = middle;
        this.content = content;
        this.name = "/";
    }

    public String getName() {
        return name;
    }
    public ArrayList<Location> getContent() {
        return content;
    }
    public Location getMiddle() {
        return middle;
    }

}
