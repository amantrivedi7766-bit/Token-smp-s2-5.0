package com.tokensmp.data;

import com.tokensmp.token.TokenType;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerData {
    private final UUID uuid;
    private int lives;
    private final Set<TokenType> tokens = new HashSet<>();

    public PlayerData(UUID uuid, int lives) {
        this.uuid = uuid;
        this.lives = lives;
    }

    public UUID getUuid() { return uuid; }
    public int getLives() { return lives; }
    public void setLives(int lives) { this.lives = lives; }
    public Set<TokenType> getTokens() { return tokens; }
    public void addToken(TokenType token) { tokens.add(token); }
    public void removeToken(TokenType token) { tokens.remove(token); }
    public boolean hasToken(TokenType token) { return tokens.contains(token); }
}
