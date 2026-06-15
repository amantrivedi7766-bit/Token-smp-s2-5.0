package com.tokensmp.utils;

import com.tokensmp.TokenSMPPlugin;
import org.bukkit.ChatColor;

public class MessageUtils {
    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String getPrefix() {
        return color(TokenSMPPlugin.getInstance().getConfig().getString("messages.prefix", "&8[&6TokenSMP&8] &r"));
    }

    public static void send(org.bukkit.entity.Player player, String msg) {
        player.sendMessage(getPrefix() + color(msg));
    }
}
