package com.tokensmp.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

public class ParticleUtils {
    public static void winEffect(Location loc) {
        World w = loc.getWorld();
        for (int i = 0; i < 100; i++) {
            double x = (Math.random() - 0.5) * 2;
            double z = (Math.random() - 0.5) * 2;
            w.spawnParticle(Particle.FIREWORK, loc.clone().add(x, 1, z), 0, 0, 0, 0, 1);
            w.spawnParticle(Particle.VILLAGER_HAPPY, loc.clone().add(x, 0.5, z), 0, 0, 0, 0, 1);
        }
    }

    public static void spinWheel(Location loc) {
        // Already implemented in SpinAnimation
    }
}
