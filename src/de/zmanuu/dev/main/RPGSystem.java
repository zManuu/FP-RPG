package de.zmanuu.dev.main;

import de.zmanuu.dev.commands.admin.*;
import de.zmanuu.dev.commands.user.*;
import de.zmanuu.dev.dungeons.DungeonKey;
import de.zmanuu.dev.horses.CMD;
import de.zmanuu.dev.items.Menu;
import de.zmanuu.dev.lebenspunkte.SoulCommand;
import de.zmanuu.dev.lebenspunkte.SoulPoints;
import de.zmanuu.dev.listener.*;
import de.zmanuu.dev.regions.RegionCommand;
import de.zmanuu.dev.regions.RegionManager;
import de.zmanuu.dev.utils.MainAPI;
import de.zmanuu.dev.utils.hooks.LuckPermsHook;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class RPGSystem extends JavaPlugin {

    private static Plugin plugin;
    public static World world;
    public static SoulPoints sp;
    public static LuckPermsHook lphook;
    public static LockItem lock;
    public static Help help;
    public static HelpChat helpChat;
    public static MainAPI api;

    @Override
    public void onEnable() {
        plugin = this;
        world = Bukkit.getWorld("Ephesus");
        api = new MainAPI();

        // Load Hooks
        lphook = new LuckPermsHook();

        // Commands
        loadCommands();

        // Listener
        loadListeners();

        // SoulPoints
        sp = new SoulPoints();
        sp.enable();

        // Regions
        RegionManager.loadRegions();
        RegionManager.startScheduler();

        // ItemLock
        lock = new LockItem();
        lock.load();

        // Help
        help = new Help();
        help.load();

        // HelpChat
        helpChat = new HelpChat();
        helpChat.load();
    }

    @Override
    public void onDisable() {
        /*
            Close MySQL Database connection
         */
        try {
            getAPI().getMySQL().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadCommands() {
        getCommand("gm").setExecutor(new GM());
        getCommand("weather").setExecutor(new Weather());
        getCommand("dunkey").setExecutor(new DungeonKey());
        getCommand("skull").setExecutor(new Skull());
        getCommand("kill").setExecutor(new Kill());
        getCommand("horse").setExecutor(new CMD());
        getCommand("clearitems").setExecutor(new ClearItems());
        getCommand("region").setExecutor(new RegionCommand());
        getCommand("lebenspunkte").setExecutor(new SoulCommand());
        getCommand("god").setExecutor(new God());
        getCommand("lockitem").setExecutor(new LockItem());
        getCommand("help").setExecutor(new Help());
        getCommand("helpchat").setExecutor(new HelpChat());
        getCommand("msg").setExecutor(new Msg());
    }

    private void loadListeners() {
        addListener(new de.zmanuu.dev.horses.Listener());
        addListener(new Menu());
        addListener(new BuildListener());
        addListener(new ChatListener());
        addListener(new ConnectionListener());
        addListener(new DamageListener());
        addListener(new DeathListener());
        addListener(new InteractListener());
        addListener(new InventoryListener());
        addListener(new CraftListener());
    }

    private void addListener(Listener l) {
        Bukkit.getPluginManager().registerEvents(l, plugin);
    }

    public static Plugin getPlugin() {
        return plugin;
    }
    public static MainAPI getAPI() {
        return api;
    }
}
