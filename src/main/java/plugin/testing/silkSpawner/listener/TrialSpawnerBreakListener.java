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

public class TrialSpawnerBreakListener extends SpawnerBreakListener implements Listener
{
    public TrialSpawnerBreakListener() {}

    @EventHandler @Override
    public void spawnerBreak(BlockBreakEvent event)
    {
        Material item = event.getPlayer().getInventory().getItemInMainHand().getType();

        boolean checkSilkTouch = event.getPlayer().getInventory().getItemInMainHand().
                getEnchantments().containsKey(Enchantment.SILK_TOUCH);
        boolean checkSpawner = event.getBlock().getType() == Material.TRIAL_SPAWNER;

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
                getBoolean("enable-silk-touch-spawner"))
        {
            return;
        }

        event.setExpToDrop(0);

        event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "Trial spawner item dropped!");

        Item spawner = (Item) event.getBlock().getWorld().spawnEntity
                (
                        event.getBlock().getLocation().add(0.5, 0.5, 0.5),
                        EntityType.ITEM
                );

        spawner.setItemStack(new ItemStack(Material.TRIAL_SPAWNER));

        CreatureSpawner spawnerBlock = (CreatureSpawner) event.getBlock().getState();
        EntitySnapshot entitySnapshot = spawnerBlock.getSpawnedEntity();

        if (entitySnapshot == null)
        {
            return;
        }

        String entityString = entitySnapshot.getEntityType() + "_SPAWN_EGG";

        ItemStack spawnEggItem = new ItemStack(Material.valueOf(entityString));
        SpawnEggMeta spawnEggMeta = (SpawnEggMeta) spawnEggItem.getItemMeta();
        spawnEggMeta.setSpawnedEntity(entitySnapshot);

        Item spawnEggDrop = (Item) event.getBlock().getWorld().spawnEntity
                (
                        event.getBlock().getLocation().add(0.5, 0.5, 0.5),
                        EntityType.ITEM
                );

        spawnEggDrop.setItemStack(spawnEggItem);

    }
}
