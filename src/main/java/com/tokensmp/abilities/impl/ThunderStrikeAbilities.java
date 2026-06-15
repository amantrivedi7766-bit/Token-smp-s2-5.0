package com.tokensmp.abilities.impl;

import com.tokensmp.token.Ability;
import com.tokensmp.TokenSMPPlugin;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import java.util.List;

public class ThunderStrikeAbilities {
    public static List<Ability> getAll() {
        return List.of(lightningDash(), chainVolt(), tempestCall());
    }

    private static Ability lightningDash() {
        return new Ability("Lightning Dash",
            "Dash forward with lightning speed, striking enemies in your path.",
            18,
            player -> {
                World w = player.getWorld();
                Vector dir = player.getLocation().getDirection().normalize().multiply(1.8);
                player.setVelocity(dir);
                w.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1f, 1.2f);
                player.getNearbyEntities(2, 2, 2).forEach(e -> {
                    if (e instanceof Player target && target != player) {
                        target.damage(7, player);
                        w.strikeLightningEffect(target.getLocation());
                    }
                });
                for (int i = 0; i < 20; i++) {
                    Location loc = player.getLocation().clone().add(dir.clone().multiply(i * 0.2));
                    w.spawnParticle(Particle.ELECTRIC_SPARK, loc, 5, 0.1, 0.1, 0.1, 0.02);
                }
            });
    }

    private static Ability chainVolt() {
        return new Ability("Chain Volt",
            "Shoot a chain lightning that jumps between enemies.",
            25,
            player -> {
                World w = player.getWorld();
                w.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1f, 1.5f);
                player.getNearbyEntities(10, 5, 10).stream()
                    .filter(e -> e instanceof Player && e != player)
                    .limit(5)
                    .forEach(target -> {
                        Player t = (Player) target;
                        t.damage(5, player);
                        t.getWorld().strikeLightningEffect(t.getLocation());
                        t.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, t.getLocation(), 20, 0.5, 0.5, 0.5, 0.1);
                    });
            });
    }

    private static Ability tempestCall() {
        return new Ability("Tempest Call",
            "Summon a massive thunderstorm, repeatedly striking all nearby enemies.",
            55,
            player -> {
                World w = player.getWorld();
                w.setStorm(true);
                w.setThundering(true);
                w.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 2f, 0.8f);
                for (int i = 0; i < 8; i++) {
                    Bukkit.getScheduler().runTaskLater(TokenSMPPlugin.getInstance(), () -> {
                        player.getNearbyEntities(12, 6, 12).forEach(e -> {
                            if (e instanceof Player target && target != player) {
                                target.damage(4);
                                target.getWorld().strikeLightningEffect(target.getLocation());
                            }
                        });
                    }, i * 10L);
                }
                Bukkit.getScheduler().runTaskLater(TokenSMPPlugin.getInstance(), () -> {
                    w.setStorm(false);
                    w.setThundering(false);
                }, 100L);
            });
    }
}
