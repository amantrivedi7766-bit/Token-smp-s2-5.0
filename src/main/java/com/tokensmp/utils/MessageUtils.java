package com.tokensmp.utils;
import org.bukkit.ChatColor; import org.bukkit.command.CommandSender;
public final class MessageUtils { private MessageUtils(){} public static String color(String message){return ChatColor.translateAlternateColorCodes('&', message);} public static void send(CommandSender sender, String message){sender.sendMessage(color(message));} }
