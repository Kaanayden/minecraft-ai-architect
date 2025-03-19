package io.github.kaanayden.MinecraftAIArchitect;

import org.bukkit.plugin.java.JavaPlugin;

public class MinecraftAIArchitect extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("onEnable is called. AI Architect is ready!");
        getServer().getPluginManager().registerEvents(new ServerListener(), this);
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called. AI Architect is disabled");
    }
}
