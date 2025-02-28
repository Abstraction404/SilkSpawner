package plugin.testing.silkSpawner.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.testing.silkSpawner.config.SpawnerConfig;
import plugin.testing.silkSpawner.listener.SpawnerBreakListener;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EnableCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(
            @Nonnull CommandSender sender,
            @Nonnull Command command,
            @Nonnull String s,
            String[] args)
    {
        if (!(sender instanceof Player player))
        {
            sender.sendMessage(ChatColor.RED + "This is not a player.");
            return true;
        }

        if (args.length >= 1)
        {
            sender.sendMessage(ChatColor.YELLOW + "Quite unnecessary.");
            return true;
        }

        SpawnerBreakListener toggle = new SpawnerBreakListener();
        boolean state = toggle.getAllowState();


        toggle.setAllowState(true);

        //toggle.setAllowState(toggle.getAllowState() ? false : true);

        sender.sendMessage(ChatColor.YELLOW + "Toggle silkSpawner is now set to: " + toggle.getAllowState());

        return true;
    }
}
