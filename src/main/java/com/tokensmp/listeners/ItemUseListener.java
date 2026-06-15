package com.tokensmp.listeners;
import com.tokensmp.abilities.AbilityManager; import org.bukkit.event.*; import org.bukkit.event.player.PlayerInteractEvent;
public class ItemUseListener implements Listener { private final AbilityManager abilityManager; public ItemUseListener(AbilityManager abilityManager){this.abilityManager=abilityManager;} @EventHandler public void onUse(PlayerInteractEvent event){ if(event.getAction().isRightClick()) abilityManager.activate(event.getPlayer()); } }
