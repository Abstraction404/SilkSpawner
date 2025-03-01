package plugin.testing.silkSpawner.listener;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntitySnapshot;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.SpawnEggMeta;
import plugin.testing.silkSpawner.config.SpawnerConfig;

public class SpawnEggInsertListener implements Listener
{
    public SpawnEggInsertListener() {}


    /*
    @EventHandler
    public void spawnEggInsert(PlayerInteractEvent event)
    {
        if (!(event.getClickedBlock().getType() == Material.SPAWNER))
        {
            return;
        }

        if (!(event.getClickedBlock().getType() == Material.TRIAL_SPAWNER))
        {
            return;
        }

        if (!(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
        {
            return;
        }

        if (!SpawnerConfig.fileGet().
                getBoolean("allow-vanilla-spawn-egg-interact-bypass"))
        {
            return;
        }

        SpawnEggMeta spawnEggMeta = (SpawnEggMeta) event.getPlayer()
                .getInventory().getItemInMainHand().getItemMeta();
        EntitySnapshot entitySnapshot = spawnEggMeta.getSpawnedEntity();

        if (entitySnapshot == null)
        {
            return;
        }

        CreatureSpawner creatureSpawner = (CreatureSpawner) event.getClickedBlock();
        creatureSpawner.setSpawnedEntity(entitySnapshot);
    }
    */

}
