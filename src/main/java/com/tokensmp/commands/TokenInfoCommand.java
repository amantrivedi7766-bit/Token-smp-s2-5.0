package com.tokensmp.commands;

import com.tokensmp.gui.TokenInfoGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TokenInfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cOnly players can use this.");
            return true;
        }
        new TokenInfoGUI().open(p);
        return true;
    }
}
