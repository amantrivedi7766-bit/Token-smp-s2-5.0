package com.tokensmp.gui;

import com.tokensmp.token.TokenType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TokenInfoGUI {
    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, "§eAll Tokens");
        int slot = 0;
        for (TokenType type : TokenType.values()) {
            if (slot >= 27) break;
            inv.setItem(slot++, getInfoItem(type));
        }
        player.openInventory(inv);
    }

    private ItemStack getInfoItem(TokenType type) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§" + (type.isAdminToken() ? "4" : "6") + type.getDisplayName());
        meta.setLore(java.util.List.of(
            "§7Rarity: §" + (type.isAdminToken() ? "4" : "e") + type.getRarity(),
            type.isAdminToken() ? "§cAdmin only – not obtainable via spin" : "§aObtainable via spin wheel"
        ));
        item.setItemMeta(meta);
        return item;
    }
}
