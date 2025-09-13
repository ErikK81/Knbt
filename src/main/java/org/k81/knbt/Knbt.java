package org.k81.knbt;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Knbt extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("knbt")).setExecutor(new Commands());
    }

    @Override
    public void onDisable() {
        // Pluin hutdwn log
    }
}
