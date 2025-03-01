package plugin.testing.silkSpawner.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.testing.silkSpawner.config.SpawnerConfig;

import javax.annotation.Nonnull;

public class SpawnEggToggleCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(
            @Nonnull CommandSender sender,
            @Nonnull Command command,
            @Nonnull String s,
            String[] args)
    {
        if (!(sender instanceof Player))
        {
            return true;
        }

        if (args.length >= 1)
        {
            return true;
        }

        SpawnerConfig.fileGet().set("allow-vanilla-spawn-egg-interact-bypass",
                !SpawnerConfig.fileGet().getBoolean("allow-vanilla-spawn-egg-interact-bypass"));
        SpawnerConfig.fileSave();
        SpawnerConfig.fileReload();

        boolean configState = SpawnerConfig.fileGet().getBoolean("allow-vanilla-spawn-egg-interact-bypass");

        String output;

        if (configState)
        {
            output = ChatColor.GREEN + String.valueOf(configState);
        }
        else
        {
            output = ChatColor.RED + String.valueOf(configState);
        }

        sender.sendMessage(ChatColor.YELLOW + "Gamerule allowSpawnEggBypass is now set to: " + output);


        return true;
    }

}
