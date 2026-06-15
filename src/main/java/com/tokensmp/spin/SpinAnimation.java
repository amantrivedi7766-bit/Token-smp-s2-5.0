package com.tokensmp.spin;
import com.tokensmp.token.TokenType; import org.bukkit.entity.Player;
public class SpinAnimation { public void play(Player player, TokenType result){player.sendMessage("You spun: " + result.name());} }
