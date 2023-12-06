package me.hadioread.command;

import me.hadioread.BetterChat;
import me.hadioread.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class IgnoreListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command is only available for players!");
            return true;
        }

        Map<UUID, List<UUID>> ignores = BetterChat.getInstance().getMessageManager().getIgnores();
        sender.sendMessage(Config.IGNORE_LIST_TITLE);

        for (UUID ignored : ignores.keySet()) {
            if (ignores.get(ignored).contains(((Player) sender).getUniqueId())) {
                sender.sendMessage(Config.IGNORED_ENTRY.replace("%target%",  Bukkit.getOfflinePlayer(ignored).getName() + ""));
            }
        }

        return true;
    }

}
