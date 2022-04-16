package de.zmanuu.dev.dungeons;

import de.zmanuu.dev.main.RPGSystem;
import de.zmanuu.dev.utils.ItemFactory;
import de.zmanuu.dev.utils.MainAPI;
import de.zmanuu.dev.utils.DungeonRegions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class DungeonKey implements CommandExecutor {

    private ArrayList<Integer> dungeonIds;
    private ArrayList<Dungeon> dungeons;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 2) {
                if (args[0].equals("use")) {
                    if (dungeonIds == null) loadDungeonList();
                    int dungeonId = 0;
                    try {
                        dungeonId = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        System.out.println(RPGSystem.getAPI().getMessages().errorConsole);
                        e.printStackTrace();
                    }
                    if (dungeonId == 0 || !dungeonIds.contains(dungeonId))
                        player.sendMessage(RPGSystem.getAPI().getMessages().internError);

                    if (hasKey(player, dungeonId)) {
                        if (dungeons == null) loadDungeons();
                        Dungeon dungeon = dungeons.get(dungeonId-1);
                        if (dungeon.gateIsOpen()) {
                            player.sendMessage(RPGSystem.getAPI().getMessages().prefix
                                    + "§cDie Tür des Dungeons ist bereits geöffnet!");
                        } else {
                            dungeon.openGate();
                            ItemStack keys = player.getInventory().getItemInMainHand();
                            keys.setAmount(keys.getAmount() - 1);
                            player.getInventory().setItemInMainHand(keys);
                            dungeon.sendBar();
                        }
                    }
                }
            }
        } else if (sender instanceof ConsoleCommandSender) {
            if (args.length == 3 && args[0].equals("give")) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    int dungeonId = Integer.parseInt(args[2]);
                    giveKey(target, dungeonId);
                }
            }
        }

        return false;
    }

    public void giveKey(Player player, int dungeonId) {
        ItemStack key = new ItemFactory(Material.TRIPWIRE_HOOK, "§cDungeon Schlüssel §7|| §cDungeon " + dungeonId).build();
        player.getWorld().dropItemNaturally(player.getLocation(), key);
    }

    private void loadDungeonList() {
        dungeonIds = new ArrayList<>();
        dungeonIds.add(1);
        dungeonIds.add(2);
        dungeonIds.add(3);
    }

    private void loadDungeons() {
        dungeons = new ArrayList<>();
        String worldName = "Ephesus";
        World world = Bukkit.getWorld(worldName);
        ArrayList<Location> dun1Gate = new ArrayList<>();
        dun1Gate.add(new Location(world, 1341, 149, 1073));
        dun1Gate.add(new Location(world, 1342, 149, 1073));
        dun1Gate.add(new Location(world, 1343, 149, 1073));
        dun1Gate.add(new Location(world, 1344, 149, 1073));
        dun1Gate.add(new Location(world, 1345, 149, 1073));
        dun1Gate.add(new Location(world, 1341, 150, 1073));
        dun1Gate.add(new Location(world, 1342, 150, 1073));
        dun1Gate.add(new Location(world, 1343, 150, 1073));
        dun1Gate.add(new Location(world, 1344, 150, 1073));
        dun1Gate.add(new Location(world, 1345, 150, 1073));
        dun1Gate.add(new Location(world, 1341, 151, 1073));
        dun1Gate.add(new Location(world, 1342, 151, 1073));
        dun1Gate.add(new Location(world, 1343, 151, 1073));
        dun1Gate.add(new Location(world, 1344, 151, 1073));
        dun1Gate.add(new Location(world, 1345, 151, 1073));
        dun1Gate.add(new Location(world, 1341, 152, 1073));
        dun1Gate.add(new Location(world, 1342, 152, 1073));
        dun1Gate.add(new Location(world, 1343, 152, 1073));
        dun1Gate.add(new Location(world, 1344, 152, 1073));
        dun1Gate.add(new Location(world, 1345, 152, 1073));
        dun1Gate.add(new Location(world, 1341, 153, 1073));
        dun1Gate.add(new Location(world, 1342, 153, 1073));
        dun1Gate.add(new Location(world, 1343, 153, 1073));
        dun1Gate.add(new Location(world, 1344, 153, 1073));
        dun1Gate.add(new Location(world, 1345, 153, 1073));
        dun1Gate.add(new Location(world, 1341, 149, 1072));
        dun1Gate.add(new Location(world, 1342, 149, 1072));
        dun1Gate.add(new Location(world, 1343, 149, 1072));
        dun1Gate.add(new Location(world, 1344, 149, 1072));
        dun1Gate.add(new Location(world, 1345, 149, 1072));
        dun1Gate.add(new Location(world, 1341, 150, 1072));
        dun1Gate.add(new Location(world, 1342, 150, 1072));
        dun1Gate.add(new Location(world, 1343, 150, 1072));
        dun1Gate.add(new Location(world, 1344, 150, 1072));
        dun1Gate.add(new Location(world, 1345, 150, 1072));
        dun1Gate.add(new Location(world, 1341, 151, 1072));
        dun1Gate.add(new Location(world, 1342, 151, 1072));
        dun1Gate.add(new Location(world, 1343, 151, 1072));
        dun1Gate.add(new Location(world, 1344, 151, 1072));
        dun1Gate.add(new Location(world, 1345, 151, 1072));
        dun1Gate.add(new Location(world, 1341, 152, 1072));
        dun1Gate.add(new Location(world, 1342, 152, 1072));
        dun1Gate.add(new Location(world, 1343, 152, 1072));
        dun1Gate.add(new Location(world, 1344, 152, 1072));
        dun1Gate.add(new Location(world, 1345, 152, 1072));
        dun1Gate.add(new Location(world, 1341, 153, 1072));
        dun1Gate.add(new Location(world, 1342, 153, 1072));
        dun1Gate.add(new Location(world, 1343, 153, 1072));
        dun1Gate.add(new Location(world, 1344, 153, 1072));
        dun1Gate.add(new Location(world, 1345, 153, 1072));
        Location dun1Middle = new Location(world, 1343, 151, 1072);
        Dungeon dun1 = new Dungeon("Dungeon I");
        dun1.setGate(new DungeonRegions(dun1Gate, dun1Middle));
        ArrayList<Location> dunStage1Gate = new ArrayList<>();
        dunStage1Gate.add(new Location(world, 1359, 132, 1050));
        dunStage1Gate.add(new Location(world, 1359, 131, 1050));
        dunStage1Gate.add(new Location(world, 1359, 130, 1050));
        dunStage1Gate.add(new Location(world, 1359, 129, 1050));
        dunStage1Gate.add(new Location(world, 1359, 128, 1050));
        dunStage1Gate.add(new Location(world, 1359, 132, 1051));
        dunStage1Gate.add(new Location(world, 1359, 131, 1051));
        dunStage1Gate.add(new Location(world, 1359, 130, 1051));
        dunStage1Gate.add(new Location(world, 1359, 129, 1051));
        dunStage1Gate.add(new Location(world, 1359, 128, 1051));
        ItemStack token = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta tokenMeta = token.getItemMeta();
        tokenMeta.setDisplayName("§6Gold Token");
        token.setItemMeta(tokenMeta);
        token.setAmount(10);
        DungeonStage dun1Stage1 = new DungeonStage("Dun1Stage1", 1, new DungeonRegions(dunStage1Gate, new Location(world, 1359, 128, 1050)), token, new Location(world, 1357, 133, 1049));
        dun1.addStage(dun1Stage1);
        dungeons.add(dun1);
    }

    private boolean hasKey(Player player, int dungeonId) {
        boolean value = false;

        Material material = Material.TRIPWIRE_HOOK;
        String displayStart = "§cDungeon Schlüssel §7|| §cDungeon " + dungeonId;
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() != material) return false;
        if (item.getItemMeta() == null) return false;
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta.getDisplayName().equals(displayStart)) value = true;

        return value;
    }

}
