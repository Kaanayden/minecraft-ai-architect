package io.github.kaanayden.MinecraftAIArchitect.Memory;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class AIManager {

    private static final HashSet<UUID> aiEnabledPlayers = new HashSet<UUID>();
    private static final HashMap<UUID, ChatHistory> chatHistories = new HashMap<UUID, ChatHistory>();

    public static boolean isPlayerEnabled(Player player) {
        return aiEnabledPlayers.contains(player.getUniqueId());
    }

    public static void enablePlayerAi(Player player) {
        aiEnabledPlayers.add(player.getUniqueId());
        ChatHistory chatHistory = new ChatHistory(player);
        chatHistories.put(player.getUniqueId(), chatHistory);
    }

    public static void disablePlayerAi(Player player) {
        aiEnabledPlayers.remove(player.getUniqueId());
        chatHistories.remove(player.getUniqueId());
    }

    public static ChatHistory getChatHistory(Player player) {
        return chatHistories.get(player.getUniqueId());
    }

    public static void addMessageToChatHistory(Player player, String message, ChatHistory.Role role) {
        ChatHistory chatHistory = chatHistories.get(player.getUniqueId());
        chatHistory.addMessage(message, role);
    }


}
