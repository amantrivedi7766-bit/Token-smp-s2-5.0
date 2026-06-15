package com.tokensmp.life;

import com.tokensmp.TokenSMPPlugin;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class ReviveRecipe {
    public ReviveRecipe() {
        if (!TokenSMPPlugin.getInstance().getConfig().getBoolean("revive-item-enabled", true)) return;
        ItemStack reviveItem = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta meta = reviveItem.getItemMeta();
        meta.setDisplayName("§6Heart of Rebirth");
        meta.setLore(java.util.List.of("§7Use this to revive a banned player."));
        reviveItem.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(TokenSMPPlugin.getInstance(), "revive_item");
        ShapedRecipe recipe = new ShapedRecipe(key, reviveItem);
        recipe.shape("ABA", "BCB", "ABA");
        recipe.setIngredient('A', Material.NETHERITE_INGOT);
        recipe.setIngredient('B', Material.TOTEM_OF_UNDYING);
        recipe.setIngredient('C', Material.DRAGON_EGG);
        TokenSMPPlugin.getInstance().getServer().addRecipe(recipe);
    }
}
