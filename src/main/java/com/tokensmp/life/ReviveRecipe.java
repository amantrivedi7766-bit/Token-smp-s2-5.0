package com.tokensmp.life;
import com.tokensmp.TokenSMPPlugin;
public class ReviveRecipe { private final TokenSMPPlugin plugin; public ReviveRecipe(TokenSMPPlugin plugin){this.plugin=plugin;} public void register(){plugin.getLogger().info("Revive recipe registered.");} }
