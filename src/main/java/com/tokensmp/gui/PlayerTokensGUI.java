package com.tokensmp.gui;

import com.tokensmp.TokenSMPPlugin;
import com.tokensmp.token.TokenType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerTokensGUI {
    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, "§6Your Tokens");
        var tokens = TokenSMPPlugin.getInstance().getDataManager().getTokens(player.getUniqueId());
        int slot = 0;
        for (TokenType type : tokens) {
            if (slot >= 27) break;
            inv.setItem(slot++, getTokenDisplay(type));
        }
        player.openInventory(inv);
    }

    private ItemStack getTokenDisplay(TokenType type) {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6" + type.getDisplayName());
        meta.setLore(java.util.List.of("§7Rarity: " + type.getRarity(), "§eRight-click to use abilities!"));
        item.setItemMeta(meta);
        return item;
    }
}
