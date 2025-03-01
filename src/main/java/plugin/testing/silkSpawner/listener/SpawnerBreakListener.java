package plugin.testing.silkSpawner.listener;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntitySnapshot;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SpawnEggMeta;
import plugin.testing.silkSpawner.config.SpawnerConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpawnerBreakListener implements Listener
{
    final protected ArrayList<Material> pickaxes = new ArrayList<>
            (Arrays.asList
                    (
                        Material.WOODEN_PICKAXE,
                        Material.STONE_PICKAXE,
                        Material.GOLDEN_PICKAXE,
                        Material.IRON_PICKAXE,
                        Material.DIAMOND_PICKAXE,
                        Material.NETHERITE_PICKAXE
                    )
            );

    public SpawnerBreakListener() {}

    @EventHandler
    public void spawnerBreak(BlockBreakEvent event)
    {
        Material item = event.getPlayer().getInventory().getItemInMainHand().getType();

        boolean checkSilkTouch = event.getPlayer().getInventory().getItemInMainHand().
                getEnchantments().containsKey(Enchantment.SILK_TOUCH);
        boolean checkSpawner = event.getBlock().getType() == Material.SPAWNER;

        boolean checkCreative = event.getPlayer().getGameMode() == GameMode.CREATIVE;
        boolean checkSpectator = event.getPlayer().getGameMode() == GameMode.SPECTATOR;

        if (!checkSpawner)
        {
            return;
        }

        if (!(pickaxes.contains(item)) || !checkSilkTouch)
        {
            return;
        }

        if (checkCreative || checkSpectator)
        {
            return;
        }

        if (!SpawnerConfig.fileGet().
                getBoolean("allow-silk-touch-spawner"))
        {
            return;
        }

        event.setExpToDrop(0);

        event.getPlayer().sendMessage(ChatColor.GOLD + "Spawner item dropped!");

        Item spawner = (Item) event.getBlock().getWorld().spawnEntity
                (
                        event.getBlock().getLocation().add(0.5, 0.5, 0.5),
                        EntityType.ITEM
                );

        spawner.setItemStack(new ItemStack(Material.SPAWNER));

        CreatureSpawner spawnerBlock = (CreatureSpawner) event.getBlock().getState();
        EntitySnapshot entitySnapshot = spawnerBlock.getSpawnedEntity();

        if (entitySnapshot == null)
        {
            return;
        }

        String entitySnapshotAsString = entitySnapshot.getAsString();
        System.out.println(entitySnapshotAsString);

        Pattern pattern = Pattern.compile("\"(text|color)\"\\s*:\\s*\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(entitySnapshotAsString);

        Map<String, String> valueMap = new HashMap<>();

        while (matcher.find())
        {
            String key = matcher.group(1);
            String value = matcher.group(2);

            valueMap.put(key, value);
        }

        String entityString = entitySnapshot.getEntityType() + "_SPAWN_EGG";

        ItemStack spawnEggItem = new ItemStack(Material.valueOf(entityString));
        SpawnEggMeta spawnEggMeta = (SpawnEggMeta) spawnEggItem.getItemMeta();
        spawnEggMeta.setSpawnedEntity(entitySnapshot);
        spawnEggMeta.setDisplayName(String.valueOf(ChatColor.valueOf(valueMap.get("color").toUpperCase()))
                + valueMap.get("text") + ChatColor.RESET + " Spawn Egg");
        spawnEggItem.setItemMeta(spawnEggMeta);

        Item spawnEggDrop = (Item) event.getBlock().getWorld().spawnEntity
                (
                      event.getBlock().getLocation().add(0.5, 0.5, 0.5),
                      EntityType.ITEM
                );

        spawnEggDrop.setItemStack(spawnEggItem);
    }
}
