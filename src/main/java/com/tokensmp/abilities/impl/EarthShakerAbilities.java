package com.tokensmp.abilities.impl;

import com.tokensmp.token.Ability;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import java.util.List;

public class EarthShakerAbilities {
    public static List<Ability> getAll() {
        return List.of(tremorStomp(), stoneSkin(), quakeFissure());
    }

    private static Ability tremorStomp() {
        return new Ability("Tremor Stomp",
            "Stomp the ground, sending a shockwave that damages and knocks back enemies.",
            12,
            player -> {
                World w = player.getWorld();
                Location loc = player.getLocation();
                w.playSound(loc, Sound.ENTITY_IRON_GOLEM_ATTACK, 1f, 0.8f);
                for (int r = 1; r <= 4; r++) {
                    for (int angle = 0; angle < 360; angle += 15) {
                        double rad = Math.toRadians(angle);
                        double x = Math.cos(rad) * r;
                        double z = Math.sin(rad) * r;
                        w.spawnParticle(Particle.BLOCK, loc.clone().add(x, 0.5, z), 3, 0, 0, 0, 0.1, Material.STONE.createBlockData());
                    }
                }
                player.getNearbyEntities(5, 2, 5).forEach(e -> {
                    if (e instanceof Player target && target != player) {
                        target.damage(5, player);
                        Vector away = target.getLocation().toVector().subtract(player.getLocation().toVector()).normalize().multiply(1.5);
                        target.setVelocity(away);
                    }
                });
            });
    }

    private static Ability stoneSkin() {
        return new Ability("Stone Skin",
            "Turn your skin into stone, gaining massive damage reduction but slowing you down.",
            40,
            player -> {
                player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.RESISTANCE, 200, 3));
                player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.SLOWNESS, 200, 1));
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_STONE_PLACE, 1f, 0.5f);
                player.getWorld().spawnParticle(Particle.BLOCK, player.getLocation(), 50, 0.5, 1, 0.5, 0.2, Material.STONE.createBlockData());
                player.sendMessage("§7Stone Skin activated! You are very tough but slow.");
            });
    }

    private static Ability quakeFissure() {
        return new Ability("Quake Fissure",
            "Split the earth in a line, damaging and pulling enemies into a crack.",
            50,
            player -> {
                World w = player.getWorld();
                Location start = player.getLocation();
                Vector dir = start.getDirection().normalize();
                for (int step = 0; step < 15; step++) {
                    Location point = start.clone().add(dir.clone().multiply(step));
                    w.spawnParticle(Particle.BLOCK, point, 20, 0.3, 0.5, 0.3, 0.1, Material.STONE.createBlockData());
                    w.playSound(point, Sound.ENTITY_GENERIC_EXPLODE, 0.8f, 0.6f);
                    point.getNearbyEntities(2, 2, 2).forEach(e -> {
                        if (e instanceof Player target && target != player) {
                            target.damage(8, player);
                            Vector pull = point.toVector().subtract(target.getLocation().toVector()).normalize().multiply(0.8);
                            target.setVelocity(pull);
                        }
                    });
                }
            });
    }
}
