package plugin.testing.silkSpawner;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.testing.silkSpawner.commands.ToggleCommand;
import plugin.testing.silkSpawner.config.SpawnerConfig;
import plugin.testing.silkSpawner.listener.SpawnerBreakListener;
import plugin.testing.silkSpawner.listener.TrialSpawnerBreakListener;

import java.util.Objects;

public final class SilkSpawner extends JavaPlugin implements Listener {

    @Override
    public void onEnable()
    {
        SpawnerConfig.fileSetup();
        SpawnerConfig.fileGet().addDefault("enable-silk-touch-spawner", true);
        SpawnerConfig.fileGet().options().copyDefaults(true);
        SpawnerConfig.fileSave();

        Objects.requireNonNull(getCommand("silkspawner")).setExecutor(new ToggleCommand());

        getServer().getPluginManager().registerEvents(new SpawnerBreakListener(), this);
        getServer().getPluginManager().registerEvents(new TrialSpawnerBreakListener(), this);

    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }
}
