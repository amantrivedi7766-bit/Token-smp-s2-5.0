package com.tokensmp.listeners;

import com.tokensmp.TokenSMPPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        TokenSMPPlugin.getInstance().getLifeManager().removeLife(p);
    }
}
