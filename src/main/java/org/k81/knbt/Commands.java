package org.k81.knbt;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getConsoleSender().sendMessage("Console cannot send this command!");
        return true;
        }
        if (args.length == 0) {
            sender.sendMessage("Incorrect Arguments.");
        return true;
        }


        if (Objects.equals(command.toString(), "Knbt")) {
            switch (args[0].toLowerCase()) {
                case "set":
                    if (args.length == 3) {
                        Utils.SetNbt(player, args[1], args[2]);
                    } else {
                        sender.sendMessage("Incorrect Arguments. Use Knbt set <ItemTag> <NewItemTag>");
                    }
                case "remove":

                case "list":

                case "reload":
            }


            return false;
        }









        return false;
    }
}
