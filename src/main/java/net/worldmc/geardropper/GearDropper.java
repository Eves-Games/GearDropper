package net.worldmc.geardropper;

import net.worldmc.geardropper.listeners.PlayerDeathListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class GearDropper extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerDeathListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
