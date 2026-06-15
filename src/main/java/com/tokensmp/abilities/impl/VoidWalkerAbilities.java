package com.tokensmp.abilities.impl;

import com.tokensmp.token.Ability;
import com.tokensmp.TokenSMPPlugin;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import java.util.List;

public class VoidWalkerAbilities {
    public static List<Ability> getAll() {
        return List.of(voidPull(), gravityInversion(), blackHole());
    }

    private static Ability voidPull() {
        return new Ability("Void Pull",
            "Pull all nearby enemies toward you, damaging them.",
            20,
            player -> {
                World w = player.getWorld();
                w.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1f, 0.6f);
                player.getNearbyEntities(8, 4, 8).forEach(e -> {
                    if (e instanceof Player target && target != player) {
                        Vector pull = player.getLocation().toVector().subtract(target.getLocation().toVector()).normalize().multiply(1.2);
                        target.setVelocity(pull);
                        target.damage(4, player);
                        w.spawnParticle(Particle.PORTAL, target.getLocation(), 20, 0.3, 0.3, 0.3, 0.1);
                    }
                });
            });
    }

    private static Ability gravityInversion() {
        return new Ability("Gravity Inversion",
            "Reverse gravity for enemies, throwing them into the air.",
            32,
            player -> {
                World w = player.getWorld();
                w.playSound(player.getLocation(), Sound.ENTITY_SHULKER_TELEPORT, 1f, 1.0f);
                player.getNearbyEntities(7, 5, 7).forEach(e -> {
                    if (e instanceof Player target && target != player) {
                        target.setVelocity(new Vector(0, 2.5, 0));
                        target.damage(6, player);
                        target.getWorld().spawnParticle(Particle.REVERSE_PORTAL, target.getLocation(), 30, 0.2, 0.5, 0.2, 0.05);
                    }
                });
            });
    }

    private static Ability blackHole() {
        return new Ability("Black Hole",
            "Create a black hole that sucks in and annihilates all nearby entities.",
            70,
            player -> {
                World w = player.getWorld();
                Location center = player.getLocation();
                w.playSound(center, Sound.ENTITY_WITHER_SPAWN, 1f, 0.4f);
                for (int r = 1; r <= 10; r++) {
                    final int radius = r;
                    Bukkit.getScheduler().runTaskLater(TokenSMPPlugin.getInstance(), () -> {
                        for (int angle = 0; angle < 360; angle += 20) {
                            double rad = Math.toRadians(angle);
                            double x = Math.cos(rad) * radius;
                            double z = Math.sin(rad) * radius;
                            w.spawnParticle(Particle.SQUID_INK, center.clone().add(x, 0.5, z), 5, 0, 0, 0, 0.02);
                        }
                        w.getNearbyEntities(center, radius, 3, radius).forEach(e -> {
                            if (e instanceof Player target && target != player) {
                                Vector pull = center.toVector().subtract(target.getLocation().toVector()).multiply(0.3);
                                target.setVelocity(pull);
                                if (radius == 10) {
                                    target.damage(15, player);
                                    target.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, target.getLocation(), 1, 0, 0, 0, 0);
                                }
                            }
                        });
                    }, r * 2L);
                }
            });
    }
}
