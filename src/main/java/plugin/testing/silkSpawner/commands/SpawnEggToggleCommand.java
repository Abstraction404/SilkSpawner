package plugin.testing.silkSpawner.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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

        return true;
    }

}
