package com.tokensmp.listeners;

import com.tokensmp.TokenSMPPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        // Update life display
        TokenSMPPlugin.getInstance().getLifeManager().updateLifeDisplay(p);
        // Auto-spin if enabled and player has no tokens? Or every join?
        if (TokenSMPPlugin.getInstance().getConfig().getBoolean("spin-on-join", true)) {
            // Only spin if player has no tokens? Or always? Let's do always (configurable)
            TokenSMPPlugin.getInstance().getSpinManager().startSpin(p);
        }
    }
}
