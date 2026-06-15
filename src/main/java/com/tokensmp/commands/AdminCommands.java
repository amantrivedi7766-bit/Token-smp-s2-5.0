package com.tokensmp.commands;

import com.tokensmp.TokenSMPPlugin;
import com.tokensmp.token.TokenType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("tokensmp.admin")) {
            sender.sendMessage("§cNo permission.");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage("§cUsage: /tokensmp <give/remove/gui/reload> [player] [token]");
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "gui":
                if (sender instanceof Player p) {
                    TokenSMPPlugin.getInstance().getAdminGUI().openMainMenu(p);
                } else {
                    sender.sendMessage("§cConsole cannot open GUI.");
                }
                break;
            case "reload":
                TokenSMPPlugin.getInstance().reloadConfig();
                sender.sendMessage("§aConfig reloaded.");
                break;
            case "give":
                if (args.length < 3) {
                    sender.sendMessage("§cUsage: /tokensmp give <player> <token>");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage("§cPlayer not found.");
                    return true;
                }
                try {
                    TokenType type = TokenType.valueOf(args[2].toUpperCase());
                    TokenSMPPlugin.getInstance().getDataManager().addToken(target.getUniqueId(), type);
                    sender.sendMessage("§aGiven " + type.getDisplayName() + " to " + target.getName());
                } catch (IllegalArgumentException e) {
                    sender.sendMessage("§cInvalid token name.");
                }
                break;
            case "remove":
                if (args.length < 3) {
                    sender.sendMessage("§cUsage: /tokensmp remove <player> <token>");
                    return true;
                }
                Player target2 = Bukkit.getPlayer(args[1]);
                if (target2 == null) {
                    sender.sendMessage("§cPlayer not found.");
                    return true;
                }
                try {
                    TokenType type = TokenType.valueOf(args[2].toUpperCase());
                    TokenSMPPlugin.getInstance().getDataManager().removeToken(target2.getUniqueId(), type);
                    sender.sendMessage("§cRemoved " + type.getDisplayName() + " from " + target2.getName());
                } catch (IllegalArgumentException e) {
                    sender.sendMessage("§cInvalid token name.");
                }
                break;
            default:
                sender.sendMessage("§cUnknown subcommand.");
        }
        return true;
    }
}
