package com.tokensmp.utils;
import org.bukkit.Sound; import org.bukkit.entity.Player;
public final class SoundUtils { private SoundUtils(){} public static void play(Player player, Sound sound){player.playSound(player.getLocation(), sound, 1.0f, 1.0f);} }
