package io.github.kaanayden.MinecraftAIArchitect;

import io.github.kaanayden.MinecraftAIArchitect.Memory.AIManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.UUID;

public class AICommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage("You should specify a parameter: on or off");
                return false;
            }

            if (args[0].equalsIgnoreCase("on")) {
                if(!AIManager.isPlayerEnabled(player)) {
                    AIManager.enablePlayerAi(player);
                    player.sendMessage("AI is now enabled! Every message you send will be sent to the AI.");
                } else {
                    player.sendMessage("AI is already enabled!");
                }
            } else if (args[0].equalsIgnoreCase("off")) {
                if(AIManager.isPlayerEnabled(player)) {
                    AIManager.disablePlayerAi(player);
                    player.sendMessage("AI is now disabled!");
                } else {
                    player.sendMessage("AI is already disabled!");
                }
            } else {
                player.sendMessage("You should specify the parameters 'on' or 'off'");
                return false;
            }

            return true;
        }

        return false;
    }



}
