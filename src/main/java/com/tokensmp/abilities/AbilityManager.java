package com.tokensmp.abilities;
import com.tokensmp.TokenSMPPlugin; import org.bukkit.entity.Player;
public class AbilityManager { private final TokenSMPPlugin plugin; private final CooldownManager cooldownManager=new CooldownManager(); public AbilityManager(TokenSMPPlugin plugin){this.plugin=plugin;} public void activate(Player player){player.sendMessage("Ability activated.");} public CooldownManager getCooldownManager(){return cooldownManager;} public TokenSMPPlugin getPlugin(){return plugin;} }
