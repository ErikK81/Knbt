package org.k81.knbt;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class Knbt extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("knbt")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("Knbt")).setTabCompleter(this);
    }

    @Override
    public void onDisable() {
    }


    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, String label, String @NotNull [] args) {
        if (!label.equalsIgnoreCase("Knbt")) {
            return new ArrayList<>();
        }
        if (args.length == 1) {
            return Arrays.asList("set", "get", "remove", "list", "reload");
        }
        return new ArrayList<>();
    }
}
