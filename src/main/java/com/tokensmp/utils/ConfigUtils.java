package com.tokensmp.utils;
import org.bukkit.configuration.file.FileConfiguration;
public final class ConfigUtils { private ConfigUtils(){} public static int positiveInt(FileConfiguration config, String path, int fallback){return Math.max(0, config.getInt(path, fallback));} }
