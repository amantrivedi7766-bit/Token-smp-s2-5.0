package com.tokensmp.commands;
import com.tokensmp.TokenSMPPlugin; import org.bukkit.command.*;
public class AdminCommands implements CommandExecutor { private final TokenSMPPlugin plugin; public AdminCommands(TokenSMPPlugin plugin){this.plugin=plugin;} @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args){ if(!sender.hasPermission("tokensmp.admin")){sender.sendMessage("No permission."); return true;} plugin.reloadConfig(); sender.sendMessage("TokenSMP reloaded."); return true; } }
