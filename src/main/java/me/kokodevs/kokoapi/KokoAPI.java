package me.kokodevs.kokoapi;

import org.bukkit.plugin.java.JavaPlugin;

public final class KokoAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // TODO: add if you need to access the API (better to not access it in a static way).
        // getServer().getServicesManager().register(KokoAPI.class, this, this, ServicePriority.Highest);
        // getServer().getServicesManager().getRegistration(KokoAPI.class);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
