package com.tokensmp.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminGUI {
    public void openMainMenu(Player admin) {
        Inventory inv = Bukkit.createInventory(null, 54, "§8TokenSMP Admin Panel");
        inv.setItem(10, createItem(Material.PLAYER_HEAD, "§aManage Tokens", "§7Give/Remove tokens"));
        inv.setItem(12, createItem(Material.REDSTONE, "§cBounty System", "§7Place or remove bounty"));
        inv.setItem(14, createItem(Material.CLOCK, "§eSpin Control", "§7Force spin for player"));
        inv.setItem(16, createItem(Material.HEART_OF_THE_SEA, "§dLife Manager", "§7Revive or edit lives"));
        inv.setItem(28, createItem(Material.BOOK, "§bAll Players' Tokens", "§7View every player's tokens"));
        inv.setItem(30, createItem(Material.BARRIER, "§4Disable Tokens", "§7Enable/disable specific tokens"));
        inv.setItem(32, createItem(Material.FIREWORK_ROCKET, "§6Cooldown Override", "§7Reset cooldowns"));
        inv.setItem(34, createItem(Material.BEACON, "§dReload Config", "§7Reload plugin"));
        admin.openInventory(inv);
    }

    private ItemStack createItem(Material mat, String name, String... lore) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(java.util.List.of(lore));
        item.setItemMeta(meta);
        return item;
    }
}
