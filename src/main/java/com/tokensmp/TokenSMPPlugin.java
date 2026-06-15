package com.tokensmp;

import com.tokensmp.abilities.AbilityManager;
import com.tokensmp.commands.*;
import com.tokensmp.data.*;
import com.tokensmp.life.*;
import com.tokensmp.listeners.*;
import com.tokensmp.spin.SpinManager;
import com.tokensmp.token.TokenManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TokenSMPPlugin extends JavaPlugin {
    private DataManager dataManager; private TokenManager tokenManager; private AbilityManager abilityManager; private SpinManager spinManager; private LifeManager lifeManager; private BountyManager bountyManager;
    @Override public void onEnable() {
        saveDefaultConfig(); saveResource("messages.yml", false);
        dataManager = new DataManager(this); tokenManager = new TokenManager(dataManager); abilityManager = new AbilityManager(this); spinManager = new SpinManager(this, tokenManager); lifeManager = new LifeManager(this, dataManager); bountyManager = new BountyManager();
        getCommand("token").setExecutor(new TokenCommand(tokenManager)); getCommand("tokeninfo").setExecutor(new TokenInfoCommand()); getCommand("spin").setExecutor(new SpinCommand(spinManager)); getCommand("tokenadmin").setExecutor(new AdminCommands(this));
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(dataManager), this); getServer().getPluginManager().registerEvents(new PlayerDeathListener(lifeManager, bountyManager), this); getServer().getPluginManager().registerEvents(new ItemUseListener(abilityManager), this);
        new ReviveRecipe(this).register(); dataManager.load();
    }
    @Override public void onDisable() { if (dataManager != null) dataManager.save(); }
    public DataManager getDataManager(){return dataManager;} public TokenManager getTokenManager(){return tokenManager;} public AbilityManager getAbilityManager(){return abilityManager;} public SpinManager getSpinManager(){return spinManager;} public LifeManager getLifeManager(){return lifeManager;} public BountyManager getBountyManager(){return bountyManager;}
}
