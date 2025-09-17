package org.k81.knbt;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {

        if (!(sender instanceof Player player)) {
            Bukkit.getConsoleSender().sendMessage("Console cant execute this command!");
            return true;
        }

        if (!command.getName().equalsIgnoreCase("knbt")) {
            return false;
        }

        if (args.length == 0) {
            player.sendMessage("§cUse: /knbt <set|get|remove|list>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "set":
                if (args.length == 3){
                    Utils.SetTag(player, args[1], args[2]);
                }
                break;

            case "get":
                    Utils.GetTag(player, args[1]);
                break;

            case "remove":
                    // Utils.RemoveNbt(player, args[1]);
                break;

            case "list":
                    Utils.listTag(player); // Map<String, Object>
                    break;
            default:
                player.sendMessage("§cInvalid command! Use: /knbt <set|get|remove|list>");
                break;
        }
    return true;
    }
}
