package plugin.testing.silkSpawner.listener;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import plugin.testing.silkSpawner.config.SpawnerConfig;

import java.util.ArrayList;
import java.util.Arrays;

public class SpawnerBreakListener implements Listener
{
    //protected static boolean allowSilkSpawner;

    @Deprecated protected static boolean checkConfigState = SpawnerConfig.fileGet().
            getBoolean("enable-silk-touch-spawner");

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

    public SpawnerBreakListener()
    {
        //allowSilkSpawner = false;
    }


    @Deprecated
    public static boolean getAllowState()
    {
        return checkConfigState;
    }

    @Deprecated
    public static void setAllowState(boolean value)
    {
        checkConfigState = value;
    }


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
                getBoolean("enable-silk-touch-spawner"))
        {
            //event.getPlayer().sendMessage("Is " + checkConfigState);
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

        //event.getPlayer().sendMessage(ChatColor.GOLD + "Value in YAML config file: " + ChatColor.AQUA +
                //SpawnerConfig.fileGet().getBoolean("enable-silk-touch-spawner"));

    }
}
