package com.tokensmp.abilities.impl;

import com.tokensmp.token.Ability;
import com.tokensmp.TokenSMPPlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import java.util.List;

public class AdminAbilitiesPlaceholder {

    // GODLIKE token abilities
    public static List<Ability> getGodlikeAbilities() {
        return List.of(
            new Ability("Divine Smite", "Strike all players with lightning.", 10, player -> {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.getWorld().strikeLightning(p.getLocation());
                }
                player.sendMessage("§6Divine Smite executed!");
            }),
            new Ability("God Mode", "Become invincible for 30 seconds.", 60, player -> {
                player.setInvulnerable(true);
                Bukkit.getScheduler().runTaskLater(TokenSMPPlugin.getInstance(), () -> player.setInvulnerable(false), 600L);
                player.sendMessage("§cGod Mode active!");
            }),
            new Ability("World Edit", "Instantly clear entities in world.", 120, player -> {
                World w = player.getWorld();
                w.getEntities().forEach(e -> {
                    if (!(e instanceof Player)) e.remove();
                });
                player.sendMessage("§aWorld cleansed.");
            })
        );
    }

    // OMNI token abilities
    public static List<Ability> getOmniAbilities() {
        return List.of(
            new Ability("Omniheal", "Heal all players to full health.", 30, player -> {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.setHealth(p.getMaxHealth());
                    p.setFoodLevel(20);
                }
                player.sendMessage("§aEveryone is fully healed!");
            }),
            new Ability("Omnisight", "Reveal all players' locations.", 15, player -> {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    player.sendMessage("§e" + p.getName() + " is at " + p.getLocation().getBlockX() + ", " + p.getLocation().getBlockZ());
                }
            }),
            new Ability("Omnikinesis", "Launch all enemies away.", 25, player -> {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p != player) p.setVelocity(p.getLocation().getDirection().multiply(-3));
                }
            })
        );
    }

    // CREATIVE token abilities
    public static List<Ability> getCreativeAbilities() {
        return List.of(
            new Ability("Creative Flight", "Grants creative flight for 1 minute.", 90, player -> {
                player.setAllowFlight(true);
                player.setFlying(true);
                Bukkit.getScheduler().runTaskLater(TokenSMPPlugin.getInstance(), () -> {
                    player.setAllowFlight(false);
                    player.setFlying(false);
                }, 1200L);
                player.sendMessage("§eYou can now fly!");
            }),
            new Ability("Item Duplicator", "Duplicates the item in your hand.", 5, player -> {
                if (player.getInventory().getItemInMainHand() != null && !player.getInventory().getItemInMainHand().getType().isAir()) {
                    player.getInventory().addItem(player.getInventory().getItemInMainHand().clone());
                    player.sendMessage("§aItem duplicated!");
                } else {
                    player.sendMessage("§cYou need an item in your hand.");
                }
            }),
            new Ability("Creative Destruction", "Destroy all blocks in a 10-block radius.", 120, player -> {
                player.getLocation().getNearbyLivingEntities(10).forEach(e -> {
                    if (!(e instanceof Player)) e.remove();
                });
                player.sendMessage("§cEntities removed.");
            })
        );
    }
}
