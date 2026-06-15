package com.tokensmp.abilities.impl;

import com.tokensmp.token.Ability;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import java.util.List;

public class ShadowDancerAbilities {
    public static List<Ability> getAll() {
        return List.of(shadowStep(), darkVeil(), voidSlice());
    }

    private static Ability shadowStep() {
        return new Ability("Shadow Step",
            "Teleport behind the nearest enemy, leaving shadow particles.",
            10,
            player -> {
                Player nearest = null;
                double minDist = 15;
                for (Player p : player.getWorld().getPlayers()) {
                    if (p != player && p.getLocation().distance(player.getLocation()) < minDist) {
                        minDist = p.getLocation().distance(player.getLocation());
                        nearest = p;
                    }
                }
                if (nearest != null) {
                    Location behind = nearest.getLocation().add(nearest.getLocation().getDirection().multiply(-1.5));
                    player.teleport(behind);
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1.2f);
                    for (int i = 0; i < 30; i++) {
                        player.getWorld().spawnParticle(Particle.SPELL_WITCH, behind, 1, 0.2, 0.2, 0.2, 0);
                    }
                } else {
                    player.sendMessage("§cNo enemy nearby to shadow step to!");
                }
            });
    }

    private static Ability darkVeil() {
        return new Ability("Dark Veil",
            "Become invisible to enemies and gain speed for a short duration.",
            25,
            player -> {
                player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.INVISIBILITY, 100, 1));
                player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.SPEED, 100, 2));
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1f, 0.5f);
                player.getWorld().spawnParticle(Particle.SMOKE_LARGE, player.getLocation(), 40, 0.5, 1, 0.5, 0.05);
                player.sendMessage("§8You fade into the shadows...");
            });
    }

    private static Ability voidSlice() {
        return new Ability("Void Slice",
            "Slice through reality, damaging all enemies in a cone in front of you.",
            30,
            player -> {
                World w = player.getWorld();
                Location origin = player.getEyeLocation();
                Vector dir = origin.getDirection();
                w.playSound(origin, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1f, 1.5f);
                for (int i = 0; i < 30; i++) {
                    double angle = Math.toRadians(i * 12);
                    Vector offset = new Vector(Math.sin(angle) * 0.5, Math.sin(angle * 2) * 0.3, Math.cos(angle) * 0.5);
                    Location point = origin.clone().add(dir.clone().multiply(2)).add(offset);
                    w.spawnParticle(Particle.SWEEP_ATTACK, point, 1, 0.1, 0.1, 0.1, 0);
                }
                for (Player target : w.getPlayers()) {
                    if (target != player && target.getLocation().distance(origin) < 5) {
                        Vector toTarget = target.getLocation().toVector().subtract(origin.toVector()).normalize();
                        if (toTarget.dot(dir) > 0.7) {
                            target.damage(10, player);
                            target.getWorld().spawnParticle(Particle.SOUL, target.getLocation(), 15, 0.3, 0.3, 0.3, 0.02);
                        }
                    }
                }
            });
    }
}
