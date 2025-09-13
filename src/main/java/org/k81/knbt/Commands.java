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
            Bukkit.getConsoleSender().sendMessage("Console cannot send this command!");
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
                if (args.length >= 3) {
                    // Junta args[2...] no caso de o valor ter espaço
                    StringBuilder sb = new StringBuilder();
                    for (int i = 2; i < args.length; i++) {
                        if (i > 2) sb.append(" ");
                        sb.append(args[i]);
                    }
                    Utils.SetNbt(player, args[1], sb.toString());
                    player.sendMessage("§aTag '" + args[1] + "' definida para '" + sb + "'.");
                } else {
                    player.sendMessage("§cUse: /knbt set <tag> <valor>");
                }
                break;

            case "get":
                if (args.length == 2) {
                    Object value = Utils.GetNbt(player, args[1]);
                    if (value != null) {
                        player.sendMessage("§aTag '" + args[1] + "' = " + value);
                    } else {
                        player.sendMessage("§eTag '" + args[1] + "' não encontrada.");
                    }
                } else {
                    player.sendMessage("§cUse: /knbt get <tag>");
                }
                break;

            case "remove":
                if (args.length == 2) {
                    Utils.RemoveNbt(player, args[1]);
                    player.sendMessage("§aTag '" + args[1] + "' removida.");
                } else {
                    player.sendMessage("§cUse: /knbt remove <tag>");
                }
                break;

            case "list":
                if (args.length == 1) {
                    var keys = Utils.ListNbt(player);
                    if (keys.isEmpty()) {
                        player.sendMessage("§eNenhuma tag encontrada neste item.");
                    } else {
                        player.sendMessage("§aTags do item:");
                        for (String key : keys) {
                            Object value = Utils.GetNbt(player, key);
                            player.sendMessage(" - " + key + " = " + value);
                        }
                    }
                } else {
                    player.sendMessage("§cUse: /knbt list");
                }
                break;

            default:
                player.sendMessage("§cComando inválido! Use: /knbt <set|get|remove|list>");
                break;
        }

        return true;
    }
}
