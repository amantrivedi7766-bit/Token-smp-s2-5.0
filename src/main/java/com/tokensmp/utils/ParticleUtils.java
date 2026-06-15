package com.tokensmp.utils;
import org.bukkit.Particle; import org.bukkit.entity.Player;
public final class ParticleUtils { private ParticleUtils(){} public static void burst(Player player, Particle particle){player.getWorld().spawnParticle(particle, player.getLocation(), 30, 0.5, 0.8, 0.5, 0.02);} }
