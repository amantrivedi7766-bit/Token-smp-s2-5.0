package com.tokensmp.spin;

import com.tokensmp.TokenSMPPlugin;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpinAnimation {
    public static void playSpinEffect(Player player, Runnable onFinish) {
        Location loc = player.getLocation().add(0, 1, 0);
        new BukkitRunnable() {
            int ticks = 0;
            @Override
            public void run() {
                if (ticks >= 40) {
                    player.playSound(loc, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
                    onFinish.run();
                    this.cancel();
                    return;
                }
                double angle = ticks * Math.PI / 10;
                double radius = 1.5;
                double x = Math.cos(angle) * radius;
                double z = Math.sin(angle) * radius;
                player.getWorld().spawnParticle(Particle.END_ROD, loc.clone().add(x, 0.5, z), 0, 0, 0, 0, 1);
                player.getWorld().spawnParticle(Particle.WITCH, loc, 5, 0.3, 0.3, 0.3, 0.05);
                if (ticks % 5 == 0) player.playSound(loc, Sound.BLOCK_NOTE_BLOCK_PLING, 0.5f, 1f);
                ticks++;
            }
        }.runTaskTimer(TokenSMPPlugin.getInstance(), 0L, 1L);
    }
}
