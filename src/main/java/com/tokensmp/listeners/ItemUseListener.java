package com.tokensmp.listeners;

import com.tokensmp.TokenSMPPlugin;
import com.tokensmp.token.TokenType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUseListener implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) return;
        Player p = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null || !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if (!meta.hasDisplayName()) return;
        String display = meta.getDisplayName();
        // Find token type from display name
        TokenType found = null;
        for (TokenType type : TokenType.values()) {
            if (display.contains(type.getDisplayName())) {
                found = type;
                break;
            }
        }
        if (found == null) return;
        event.setCancelled(true);
        // Open ability selection GUI (simplified: just use first ability for demo)
        // In a full implementation, you'd open a sub-GUI to choose ability 1,2,3.
        // For simplicity, we'll use ability 1.
        var abilities = TokenSMPPlugin.getInstance().getAbilityManager().getToken(found).getAbilities();
        if (!abilities.isEmpty()) {
            TokenSMPPlugin.getInstance().getAbilityManager().useAbility(p, found, 0);
        }
    }
}
