package com.tokensmp.abilities.impl;

import com.tokensmp.token.Ability;
import com.tokensmp.TokenSMPPlugin;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import java.util.List;

public class NatureSpiritAbilities {
    public static List<Ability> getAll() {
        return List.of(naturesGrasp(), healingBlooms(), forestWrath());
    }

    private static Ability naturesGrasp() {
        return new Ability("Nature's Grasp",
            "Summon roots that trap nearby enemies and deal damage over time.",
            22,
            player -> {
                World w = player.getWorld();
                w.playSound(player.getLocation(), Sound.BLOCK_GRASS_BREAK, 1f, 0.6f);
                player.getNearbyEntities(5, 3, 5).forEach(e -> {
                    if (e instanceof Player target && target != player) {
                        target.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.SLOWNESS, 80, 3));
                        target.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, target.getLocation(), 30, 0.4, 0.4, 0.4, 0.05);
                        target.damage(2);
                    }
                });
                for (int i = 0; i < 100; i++) {
                    double x = (Math.random() - 0.5) * 8;
                    double z = (Math.random() - 0.5) * 8;
                    w.spawnParticle(Particle.DUST, player.getLocation().clone().add(x, 0, z), 1, 0, 0, 0, 0, new Particle.DustOptions(Color.GREEN, 1));
                }
            });
    }

    private static Ability healingBlooms() {
        return new Ability("Healing Blooms",
            "Create a field of flowers that heals all players over time.",
            35,
            player -> {
                World w = player.getWorld();
                Location center = player.getLocation();
                w.playSound(center, Sound.BLOCK_BEEHIVE_WORK, 1f, 1.2f);
                for (int i = 0; i < 200; i++) {
                    double x = (Math.random() - 0.5) * 7;
                    double z = (Math.random() - 0.5) * 7;
                    w.spawnParticle(Particle.HEART, center.clone().add(x, 1, z), 1, 0, 0.2, 0, 0);
                    w.spawnParticle(Particle.VILLAGER_HAPPY, center.clone().add(x, 0.5, z), 1, 0, 0, 0, 0);
                }
                for (Player p : w.getPlayers()) {
                    if (p.getLocation().distance(center) < 7) {
                        p.setHealth(Math.min(p.getHealth() + 6, p.getMaxHealth()));
                        p.sendMessage("§aNature's healing surrounds you!");
                    }
                }
            });
    }

    private static Ability forestWrath() {
        return new Ability("Forest Wrath",
            "Summon a forest guardian that attacks nearby enemies.",
            60,
            player -> {
                World w = player.getWorld();
                Location loc = player.getLocation();
                w.playSound(loc, Sound.ENTITY_EVOKER_PREPARE_WOLOLO, 1f, 0.8f);
                for (int i = 0; i < 360; i += 10) {
                    double rad = Math.toRadians(i);
                    double x = Math.cos(rad) * 4;
                    double z = Math.sin(rad) * 4;
                    w.spawnParticle(Particle.VILLAGER_HAPPY, loc.clone().add(x, 0, z), 1, 0, 0.5, 0, 0);
                }
                LivingEntity golem = (LivingEntity) w.spawnEntity(loc, EntityType.IRON_GOLEM);
                golem.setCustomName("§2Forest Guardian");
                golem.setCustomNameVisible(true);
                golem.setInvulnerable(true);
                Bukkit.getScheduler().runTaskTimer(TokenSMPPlugin.getInstance(), new Runnable() {
                    int ticks = 0;
                    @Override
                    public void run() {
                        ticks++;
                        if (ticks >= 100 || golem.isDead()) {
                            golem.remove();
                            this.cancel();
                            return;
                        }
                        golem.getNearbyEntities(5, 3, 5).stream()
                            .filter(e -> e instanceof Player && e != player)
                            .findFirst()
                            .ifPresent(target -> {
                                golem.teleport(target.getLocation());
                                ((Player) target).damage(4);
                            });
                    }
                }, 0L, 20L);
            });
    }
}
