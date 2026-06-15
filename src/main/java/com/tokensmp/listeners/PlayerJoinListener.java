package com.tokensmp.listeners;
import com.tokensmp.data.DataManager; import org.bukkit.event.*; import org.bukkit.event.player.PlayerJoinEvent;
public class PlayerJoinListener implements Listener { private final DataManager dataManager; public PlayerJoinListener(DataManager dataManager){this.dataManager=dataManager;} @EventHandler public void onJoin(PlayerJoinEvent event){dataManager.getPlayerData(event.getPlayer().getUniqueId());} }
