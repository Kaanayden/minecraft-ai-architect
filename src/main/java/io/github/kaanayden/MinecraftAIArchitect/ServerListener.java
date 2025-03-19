package io.github.kaanayden.MinecraftAIArchitect;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class ServerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.broadcastMessage("Hello " + event.getPlayer().getName() + "!");
        Bukkit.broadcastMessage("If you say 'Please give me diamonds', I will give you 10 diamonds.");
    }
    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        getLogger().info("Player " + event.getPlayer().getName() + " said " + event.getMessage());
        if (event.getMessage().trim().equalsIgnoreCase("Please give me diamonds")) {
            event.getPlayer().sendMessage("10 diamonds have been added to your inventory.");
            event.getPlayer().getInventory().addItem(new org.bukkit.inventory.ItemStack(org.bukkit.Material.DIAMOND, 10));
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Bukkit.broadcastMessage("Player " + event.getPlayer().getName() + " moved to " + event.getTo());
    }

}
