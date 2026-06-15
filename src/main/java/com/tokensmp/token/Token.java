package com.tokensmp.token;
import java.util.List;
public record Token(TokenType type, String displayName, List<Ability> abilities) {}
