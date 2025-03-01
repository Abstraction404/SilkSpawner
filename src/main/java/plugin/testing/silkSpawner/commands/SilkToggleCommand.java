package plugin.testing.silkSpawner.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.testing.silkSpawner.config.SpawnerConfig;

import javax.annotation.Nonnull;

public class SilkToggleCommand implements CommandExecutor
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
            sender.sendMessage(ChatColor.RED + "This is not a player.");
            return true;
        }

        if (args.length >= 1)
        {
            sender.sendMessage(ChatColor.YELLOW + "Quite unnecessary.");
            return true;
        }

        SpawnerConfig.fileGet().set(
                "allow-silk-touch-spawner",
                !SpawnerConfig.fileGet().getBoolean("allow-silk-touch-spawner")
        );
        SpawnerConfig.fileSave();
        SpawnerConfig.fileReload();

        boolean configState = SpawnerConfig.fileGet().getBoolean("allow-silk-touch-spawner");

        String output;

        if (configState)
        {
            output = ChatColor.GREEN + String.valueOf(configState);
        }
        else
        {
            output = ChatColor.RED + String.valueOf(configState);
        }

        sender.sendMessage(ChatColor.YELLOW + "Gamerule silkSpawner is now set to: " + output);

        return true;
    }
}
