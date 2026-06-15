package com.tokensmp.data;
import com.tokensmp.token.TokenType; import java.util.UUID;
public class PlayerData { private final UUID uuid; private TokenType tokenType; private int lives; public PlayerData(UUID uuid, int lives){this.uuid=uuid;this.lives=lives;} public UUID getUuid(){return uuid;} public TokenType getTokenType(){return tokenType;} public void setTokenType(TokenType tokenType){this.tokenType=tokenType;} public int getLives(){return lives;} public void setLives(int lives){this.lives=lives;} public void addLife(){lives++;} public void removeLife(){lives=Math.max(0,lives-1);} }
