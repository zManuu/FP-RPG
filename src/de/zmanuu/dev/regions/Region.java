package de.zmanuu.dev.regions;

public class Region {

    private String name;
    private Cuboid cuboid;

    public Region(String name, Cuboid cuboid) {
        this.name = name;
        this.cuboid = cuboid;
    }

    public String getName() {
        return name;
    }
    public Cuboid getCuboid() {
        return cuboid;
    }
}
