package plugin.testing.silkSpawner.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SpawnerConfig
{
    private static File file;
    private static FileConfiguration fileConfig;

    public static void fileSetup()
    {
        file = new File(
                Bukkit.getServer().getPluginManager().
                        getPlugin("SilkSpawner").getDataFolder(),
                "spawnerconfig.yml"
        );

        if(!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException error)
            {
                System.out.println("Unable to create file.");
            }
        }

        fileConfig = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration fileGet()
    {
        return fileConfig;
    }

    public static void fileSave()
    {
        try
        {
            fileConfig.save(file);
        }
        catch (IOException error)
        {
            System.out.println("Unable to save file.");
        }
    }

    public static void fileReload()
    {
        fileConfig = YamlConfiguration.loadConfiguration(file);

    }
}
