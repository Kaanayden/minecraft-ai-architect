package io.github.kaanayden.MinecraftAIArchitect;

import io.github.kaanayden.MinecraftAIArchitect.LLMTools.LLMTools;
import io.github.kaanayden.MinecraftAIArchitect.Memory.AIManager;
import io.github.kaanayden.MinecraftAIArchitect.Memory.ChatHistory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static org.bukkit.Bukkit.getLogger;


public class ServerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.broadcastMessage("Hello " + event.getPlayer().getName() + "!");
        Bukkit.broadcastMessage("Use /ai on or off, to use AI in chat.");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        AIManager.disablePlayerAi(event.getPlayer());
    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        getLogger().info("Player " + event.getPlayer().getName() + " said " + event.getMessage());
        Player player = event.getPlayer();
        if ( AIManager.isPlayerEnabled(player) ) {
            new Thread(new Runnable() {
                public void run() {
                    String aiResponse = LLMTools.messageLLM(event.getMessage());
                    AIManager.addMessageToChatHistory(player, event.getMessage(), ChatHistory.Role.PLAYER);
                    AIManager.addMessageToChatHistory(player, aiResponse, ChatHistory.Role.AI);
                    Bukkit.broadcastMessage("AI to " + player.getName() + ": " + aiResponse);
                }
            }).start();

        }

    }

}
