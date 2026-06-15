package com.tokensmp.commands;
import com.tokensmp.token.TokenType; import org.bukkit.command.*;
public class TokenInfoCommand implements CommandExecutor { @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args){sender.sendMessage("Available tokens: "+java.util.Arrays.toString(TokenType.values())); return true;} }
