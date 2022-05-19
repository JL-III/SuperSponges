package com.nessxxiii.supersponges;

import com.nessxxiii.supersponges.listeners.SpongePlaceEvent;
import com.nessxxiii.supersponges.util.TogglePower;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Supersponges extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        Bukkit.getPluginManager().registerEvents(new SpongePlaceEvent(),this);
        Bukkit.getPluginManager().registerEvents(new TogglePower(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
