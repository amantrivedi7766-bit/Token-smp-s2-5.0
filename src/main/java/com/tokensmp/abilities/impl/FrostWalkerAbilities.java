package com.tokensmp.abilities.impl;

import com.tokensmp.token.Ability;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.List;

public class FrostWalkerAbilities {
    public static List<Ability> getAll() {
        return List.of(iceBurst(), frozenAegis(), absoluteZero());
    }

    private static Ability iceBurst() {
        return new Ability("Ice Burst",
            "Release a burst of ice shards damaging and slowing enemies.",
            15,
            player -> {
                World w = player.getWorld();
                Location loc = player.getLocation();
                w.playSound(loc, Sound.ENTITY_SNOWBALL_THROW, 1f, 1.2f);
                for (int i = 0; i < 50; i++) {
                    double angle = Math.random() * 2 * Math.PI;
                    double radius = Math.random() * 4;
                    double x = Math.cos(angle) * radius;
                    double z = Math.sin(angle) * radius;
                    w.spawnParticle(Particle.ITEM_SNOWBALL, loc.clone().add(x, 1, z), 0, 0, 0, 0, 1);
                }
                player.getNearbyEntities(5, 3, 5).forEach(e -> {
                    if (e instanceof Player target && target != player) {
                        target.damage(5, player);
                        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 60, 2));
                        target.setFreezeTicks(100);
                    }
                });
            });
    }

    private static Ability frozenAegis() {
        return new Ability("Frozen Aegis",
            "Encase yourself in ice, gaining temporary damage reduction and reflecting projectiles.",
            30,
            player -> {
                World w = player.getWorld();
                Location loc = player.getLocation();
                for (int y = 0; y <= 2; y++) {
                    for (int x = -1; x <= 1; x++) {
                        for (int z = -1; z <= 1; z++) {
                            if (Math.abs(x) == 1 && Math.abs(z) == 1) continue;
                            w.spawnParticle(Particle.BLOCK, loc.clone().add(x, y, z), 3, 0, 0, 0, 0.1, Material.BLUE_ICE.createBlockData());
                        }
                    }
                }
                w.playSound(loc, Sound.BLOCK_GLASS_PLACE, 1f, 0.5f);
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 100, 2));
                player.sendMessage("§bFrozen Aegis active! You are highly resistant.");
            });
    }

    private static Ability absoluteZero() {
        return new Ability("Absolute Zero",
            "Freeze everything around you, dealing massive damage and encasing enemies in ice.",
            50,
            player -> {
                World w = player.getWorld();
                Location loc = player.getLocation();
                w.playSound(loc, Sound.ENTITY_WITHER_BREAK_BLOCK, 1f, 0.5f);
                w.playSound(loc, Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1f, 0.8f);
                for (int i = 0; i < 200; i++) {
                    double x = (Math.random() - 0.5) * 8;
                    double z = (Math.random() - 0.5) * 8;
                    w.spawnParticle(Particle.SNOWFLAKE, loc.clone().add(x, Math.random() * 2, z), 0, 0, 0, 0, 1);
                }
                player.getNearbyEntities(7, 4, 7).forEach(e -> {
                    if (e instanceof Player target && target != player) {
                        target.damage(12, player);
                        target.setFreezeTicks(200);
                        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 4));
                        target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 1));
                    }
                });
            });
    }
}
