package com.tokensmp.utils;

import com.tokensmp.TokenSMPPlugin;

public class ConfigUtils {
    public static boolean spinOnJoin() {
        return TokenSMPPlugin.getInstance().getConfig().getBoolean("spin-on-join", true);
    }

    public static int getMaxLives() {
        return TokenSMPPlugin.getInstance().getConfig().getInt("max-lives", 5);
    }
}
