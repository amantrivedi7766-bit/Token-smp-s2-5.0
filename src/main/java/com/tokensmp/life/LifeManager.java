package com.tokensmp.life;

import com.tokensmp.TokenSMPPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.UUID;

public class LifeManager {
    private final int MAX_LIVES = TokenSMPPlugin.getInstance().getConfig().getInt("max-lives", 5);

    public void removeLife(Player player) {
        UUID uuid = player.getUniqueId();
        int current = TokenSMPPlugin.getInstance().getDataManager().getLives(uuid);
        if (current <= 1) {
            TokenSMPPlugin.getInstance().getDataManager().setLives(uuid, 0);
            Bukkit.getBanList(org.bukkit.BanList.Type.NAME).addBan(player.getName(),
                "§cYou have died 5 times! Permanently banned.", null, "TokenSMP");
            player.kickPlayer("§cYou ran out of lives. A revive item can bring you back.");
        } else {
            TokenSMPPlugin.getInstance().getDataManager().setLives(uuid, current - 1);
            player.sendMessage("§cYou lost a life! Remaining: §e" + (current - 1));
            updateLifeDisplay(player);
        }
    }

    public void revivePlayer(String playerName) {
        Player target = Bukkit.getPlayerExact(playerName);
        if (target != null) {
            TokenSMPPlugin.getInstance().getDataManager().setLives(target.getUniqueId(), MAX_LIVES);
            target.sendMessage("§aYou have been revived!");
            updateLifeDisplay(target);
            if (Bukkit.getBanList(org.bukkit.BanList.Type.NAME).isBanned(playerName)) {
                Bukkit.getBanList(org.bukkit.BanList.Type.NAME).pardon(playerName);
            }
        }
    }

    public void updateLifeDisplay(Player player) {
        int lives = TokenSMPPlugin.getInstance().getDataManager().getLives(player.getUniqueId());
        String hearts = "❤".repeat(Math.max(0, lives)) + "§7" + "🖤".repeat(MAX_LIVES - lives);
        player.sendActionBar("§cLives: " + hearts);
    }
}
