package com.tokensmp.token;

import com.tokensmp.data.DataManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.HashMap;
import java.util.Map;

public class TokenManager {
    private final DataManager dataManager;
    private final Map<TokenType, Token> tokenRegistry = new HashMap<>();

    public TokenManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void registerToken(TokenType type, Token token) {
        tokenRegistry.put(type, token);
    }

    public Token getToken(TokenType type) {
        return tokenRegistry.get(type);
    }

    public ItemStack getTokenItem(TokenType type) {
        Token token = tokenRegistry.get(type);
        return token != null ? token.getTokenItem() : null;
    }

    public void giveTokenToPlayer(Player player, TokenType type) {
        dataManager.addToken(player.getUniqueId(), type);
        player.getInventory().addItem(getTokenItem(type));
    }
}
