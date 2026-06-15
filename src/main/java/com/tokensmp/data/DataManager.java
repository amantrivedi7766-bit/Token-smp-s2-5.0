package com.tokensmp.data;
import com.tokensmp.TokenSMPPlugin; import java.util.*;
public class DataManager { private final TokenSMPPlugin plugin; private final Map<UUID, PlayerData> players = new HashMap<>(); public DataManager(TokenSMPPlugin plugin){this.plugin=plugin;} public void load(){} public void save(){} public PlayerData getPlayerData(UUID uuid){return players.computeIfAbsent(uuid, id -> new PlayerData(id, plugin.getConfig().getInt("starting-lives",3)));} }
