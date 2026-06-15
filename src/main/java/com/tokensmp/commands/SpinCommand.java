package com.tokensmp.commands;

import com.tokensmp.TokenSMPPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cOnly players can spin.");
            return true;
        }
        if (!p.hasPermission("tokensmp.player")) {
            p.sendMessage("§cNo permission.");
            return true;
        }
        TokenSMPPlugin.getInstance().getSpinManager().startSpin(p);
        return true;
    }
}
