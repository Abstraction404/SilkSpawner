package plugin.testing.silkSpawner.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.testing.silkSpawner.config.SpawnerConfig;

import javax.annotation.Nonnull;

public class ToggleCommand implements CommandExecutor
{
    //protected SpawnerBreakListener toggle = new SpawnerBreakListener();

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
                "enable-silk-touch-spawner",
                !SpawnerConfig.fileGet().getBoolean("enable-silk-touch-spawner")
        );
        SpawnerConfig.fileSave();
        SpawnerConfig.fileReload();

        //SpawnerBreakListener.setAllowState(!SpawnerBreakListener.getAllowState());

        boolean configState = SpawnerConfig.fileGet().getBoolean("enable-silk-touch-spawner");

        String output;

        //toggle.getAllowState() (deprecated)

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

        //This was a public one (Do not do this, it will fuck you over)
        //SpawnerBreakListener.allowSilkSpawner = !(SpawnerBreakListener.allowSilkSpawner);

        /*
        if (SpawnerBreakListener.allowSilkSpawner)
        {
            output = ChatColor.GREEN + String.valueOf(SpawnerBreakListener.allowSilkSpawner);
        }
        else
        {
            output = ChatColor.RED + String.valueOf(SpawnerBreakListener.allowSilkSpawner);
        }
        */

    }
}
