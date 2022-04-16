package de.zmanuu.dev.dungeons;
/*
———————————————————————————————————
 Author: zManuu
 Project: RPG
 File: de.zmanuu.dev.dungeons.Dungeon
 Date: 17.06.2020
———————————————————————————————————
*/

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.utils.DungeonRegions;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

import java.util.ArrayList;
import java.util.HashMap;

public class Dungeon {

    private String name;
    private DungeonRegions gate;
    private HashMap<Location, Material> materials;
    private Location middle;
    private int openTime = 200;
    private ArrayList<DungeonStage> stages;

    public Dungeon(String name) {
        this.name = name;
    }

    // Setter & Adder
    public void setGate(DungeonRegions region) {
        this.gate = region;
        this.middle = region.getMiddle();
    }
    public void addStage(DungeonStage stage) {
        if (stages == null)
            stages = new ArrayList<>();
        stages.add(stage);
    }

    // Getter
    public String getName() {
        return name;
    }
    public DungeonRegions getGate() {
        return gate;
    }
    public Location getGateMiddle() {
        return gate.getMiddle();
    }
    public boolean gateIsOpen() {
        boolean value = false;
        Block middleBlock = getGateMiddle().getBlock();
        if (middleBlock.getType() == Material.AIR) {
            value = true;
        }
        return value;
    }
    public ArrayList<DungeonStage> getStages() {
        return stages;
    }

    // ActionPerformer
    public void openGate() {
        materials = new HashMap<>();
        World world = Bukkit.getWorld("Ephesus");
        getGate().getContent().forEach(current -> {
            if (world == null) {
                System.out.println("ERROR || Dungeon.java:78 || world == null");
            } else {
                world.spawnParticle(Particle.EXPLOSION_NORMAL, current, 2);
                explodeEffect(middle);
                materials.put(current, current.getBlock().getType());
                current.getBlock().setType(Material.AIR);
            }
        });
        Bukkit.getScheduler().runTaskLater(RPGSystem.getPlugin(), () -> getGate().getContent().forEach(current -> {
            current.getBlock().setType(materials.get(current));
            explodeEffect(middle);
        }), openTime);
    }
    public void explodeEffect(Location location) {
        /*

        LIB

        EffectManager manager = new EffectManager(RPGSystem.getPlugin());
        de.slikey.effectlib.Effect effect = new ExplodeEffect(manager);
        effect.setLocation(location);
        effect.start();
         */
    }
    public void sendBar() {
        BossBar bar = Bukkit.createBossBar("§cDer Dungeon §4§o" + getName() + "§c ist geöffnet!", BarColor.RED, BarStyle.SEGMENTED_6);
        bar.setVisible(true);
        Bukkit.getOnlinePlayers().forEach(bar::addPlayer);
        Bukkit.getScheduler().runTaskLater(RPGSystem.getPlugin(), () -> {
            Bukkit.getOnlinePlayers().forEach(bar::removePlayer);
            bar.setVisible(false);
        }, openTime);
    }

}
