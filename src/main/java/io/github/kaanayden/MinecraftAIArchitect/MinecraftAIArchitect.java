package io.github.kaanayden.MinecraftAIArchitect;

import io.github.kaanayden.MinecraftAIArchitect.LLMTools.LLMTools;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class MinecraftAIArchitect extends JavaPlugin {

    @Override
    public void onEnable() {
        FileConfiguration config = getConfig();
        config.addDefault("OPENAI_API_KEY", "YOUR_OPENAI_API_KEY");
        config.options().copyDefaults(true);
        saveConfig();

        String apiKey = config.getString("OPENAI_API_KEY");

        if (apiKey == null || apiKey.equals("YOUR_OPENAI_API_KEY")) {
            getLogger().severe("You must set the OPENAI_API_KEY in the config.yml to use this plugin!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        LLMTools.buildClient(apiKey);

        // Registers
        getServer().getPluginManager().registerEvents(new ServerListener(), this);
        this.getCommand("ai").setExecutor(new AICommand());

        getLogger().info("onEnable is called. AI Architect is ready!");
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called. AI Architect is disabled");
    }
}
