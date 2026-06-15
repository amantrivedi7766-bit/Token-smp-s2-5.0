package com.tokensmp.abilities;

import com.tokensmp.TokenSMPPlugin;
import com.tokensmp.token.TokenType;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {
    private final Map<String, Long> cooldowns = new HashMap<>();
    private final Map<UUID, Map<String, BossBar>> activeBossBars = new HashMap<>();
    private final String indicatorType = TokenSMPPlugin.getInstance().getConfig().getString("cooldown-indicator", "BOSSBAR");

    private String getKey(UUID uuid, TokenType type, int index) {
        return uuid.toString() + "_" + type.name() + "_" + index;
    }

    public void setCooldown(UUID uuid, TokenType type, int index, int seconds) {
        String key = getKey(uuid, type, index);
        long expiry = System.currentTimeMillis() + (seconds * 1000L);
        cooldowns.put(key, expiry);
        startCooldownDisplay(uuid, type, index, seconds);
    }

    public boolean isOnCooldown(UUID uuid, TokenType type, int index) {
        String key = getKey(uuid, type, index);
        Long expiry = cooldowns.get(key);
        if (expiry == null) return false;
        if (expiry < System.currentTimeMillis()) {
            cooldowns.remove(key);
            removeBossBar(uuid, type, index);
            return false;
        }
        return true;
    }

    public long getRemainingCooldown(UUID uuid, TokenType type, int index) {
        String key = getKey(uuid, type, index);
        Long expiry = cooldowns.get(key);
        if (expiry == null) return 0;
        return Math.max(0, (expiry - System.currentTimeMillis()) / 1000);
    }

    private void startCooldownDisplay(UUID uuid, TokenType type, int index, int totalSeconds) {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null) return;
        String abilityName = TokenSMPPlugin.getInstance().getAbilityManager().getToken(type).getAbilities().get(index).getName();

        if (indicatorType.equalsIgnoreCase("BOSSBAR")) {
            BossBar bossBar = Bukkit.createBossBar(
                "§6" + abilityName + " §7Cooldown: §e" + totalSeconds + "s",
                BarColor.YELLOW, BarStyle.SEGMENTED_10
            );
            bossBar.addPlayer(player);
            bossBar.setProgress(1.0);
            activeBossBars.computeIfAbsent(uuid, k -> new HashMap<>()).put(type.name() + "_" + index, bossBar);

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!player.isOnline() || !isOnCooldown(uuid, type, index)) {
                        bossBar.removePlayer(player);
                        bossBar.setVisible(false);
                        activeBossBars.getOrDefault(uuid, new HashMap<>()).remove(type.name() + "_" + index);
                        this.cancel();
                        return;
                    }
                    long remaining = getRemainingCooldown(uuid, type, index);
                    double progress = remaining / (double) totalSeconds;
                    bossBar.setProgress(Math.max(0, progress));
                    bossBar.setTitle("§6" + abilityName + " §7Cooldown: §e" + remaining + "s");
                    if (remaining <= 0) {
                        bossBar.removePlayer(player);
                        this.cancel();
                    }
                }
            }.runTaskTimer(TokenSMPPlugin.getInstance(), 0L, 20L);
        } else {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!player.isOnline() || !isOnCooldown(uuid, type, index)) {
                        this.cancel();
                        return;
                    }
                    long remaining = getRemainingCooldown(uuid, type, index);
                    player.sendActionBar("§c⏳ " + abilityName + " §7cooldown: §e" + remaining + "s");
                }
            }.runTaskTimer(TokenSMPPlugin.getInstance(), 0L, 20L);
        }
    }

    private void removeBossBar(UUID uuid, TokenType type, int index) {
        Map<String, BossBar> bars = activeBossBars.get(uuid);
        if (bars != null) {
            BossBar bar = bars.remove(type.name() + "_" + index);
            if (bar != null) {
                Player p = Bukkit.getPlayer(uuid);
                if (p != null) bar.removePlayer(p);
                bar.setVisible(false);
            }
        }
    }
}
