package io.github.kaanayden.MinecraftAIArchitect.Memory;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ChatHistory {

    public enum Role {
        user,
        assistant,
        system
    }

    public static class ChatMessage {
        private Role role;
        private String message;

        public ChatMessage(Role role, String message) {
            this.role = role;
            this.message = message;
        }

        public Role getRole() {
            return role;
        }

        public String getMessage() {
            return message;
        }
    }


    private final ArrayList<ChatMessage> messages;
    private final Player player;

    public ChatHistory(Player player) {
        this.player = player;
        this.messages = new ArrayList<ChatMessage>();
    }

    public void addMessage(String message, Role role) {
        messages.add(new ChatMessage(role, message));
    }

    public ArrayList<ChatMessage> getMessages() {
        return messages;
    }

    public Player getPlayer() {
        return player;
    }


}
