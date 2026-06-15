package com.tokensmp.data;

import com.tokensmp.TokenSMPPlugin;
import com.tokensmp.token.TokenType;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class DataManager {
    private final Map<UUID, PlayerData> playerDataMap = new HashMap<>();
    private final File dataFolder;

    public DataManager() {
        dataFolder = new File(TokenSMPPlugin.getInstance().getDataFolder(), "playerdata");
        if (!dataFolder.exists()) dataFolder.mkdirs();
        loadAll();
    }

    public PlayerData getPlayerData(UUID uuid) {
        return playerDataMap.computeIfAbsent(uuid, u -> {
            PlayerData pd = new PlayerData(u, TokenSMPPlugin.getInstance().getConfig().getInt("max-lives", 5));
            loadPlayerData(u, pd);
            return pd;
        });
    }

    public int getLives(UUID uuid) { return getPlayerData(uuid).getLives(); }
    public void setLives(UUID uuid, int lives) { getPlayerData(uuid).setLives(lives); savePlayerData(uuid); }

    public void addToken(UUID uuid, TokenType token) { getPlayerData(uuid).addToken(token); savePlayerData(uuid); }
    public void removeToken(UUID uuid, TokenType token) { getPlayerData(uuid).removeToken(token); savePlayerData(uuid); }
    public boolean hasToken(UUID uuid, TokenType token) { return getPlayerData(uuid).hasToken(token); }
    public Set<TokenType> getTokens(UUID uuid) { return getPlayerData(uuid).getTokens(); }

    private void loadPlayerData(UUID uuid, PlayerData pd) {
        File f = new File(dataFolder, uuid.toString() + ".yml");
        if (!f.exists()) return;
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
        pd.setLives(cfg.getInt("lives", 5));
        for (String token : cfg.getStringList("tokens")) {
            try {
                pd.addToken(TokenType.valueOf(token));
            } catch (IllegalArgumentException ignored) {}
        }
    }

    private void savePlayerData(UUID uuid) {
        PlayerData pd = playerDataMap.get(uuid);
        if (pd == null) return;
        File f = new File(dataFolder, uuid.toString() + ".yml");
        YamlConfiguration cfg = new YamlConfiguration();
        cfg.set("lives", pd.getLives());
        cfg.set("tokens", pd.getTokens().stream().map(Enum::name).toList());
        try { cfg.save(f); } catch (IOException e) { e.printStackTrace(); }
    }

    public void loadAll() {
        File[] files = dataFolder.listFiles((dir, name) -> name.endsWith(".yml"));
        if (files == null) return;
        for (File f : files) {
            String name = f.getName().replace(".yml", "");
            try {
                UUID uuid = UUID.fromString(name);
                loadPlayerData(uuid, getPlayerData(uuid));
            } catch (IllegalArgumentException ignored) {}
        }
    }

    public void saveAll() {
        for (UUID uuid : playerDataMap.keySet()) savePlayerData(uuid);
    }
}
