package com.tokensmp.spin;

import com.tokensmp.TokenSMPPlugin;
import com.tokensmp.token.TokenType;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SpinManager {
    private final List<TokenType> normalTokens = new ArrayList<>();

    public SpinManager() {
        for (TokenType t : TokenType.values()) {
            if (!t.isAdminToken()) normalTokens.add(t);
        }
    }

    public void startSpin(Player player) {
        SpinAnimation.playSpinEffect(player, () -> {
            TokenType won = normalTokens.get(ThreadLocalRandom.current().nextInt(normalTokens.size()));
            TokenSMPPlugin.getInstance().getDataManager().addToken(player.getUniqueId(), won);
            player.showTitle(Title.title(
                net.kyori.adventure.text.Component.text("§6✦ Token Won! ✦"),
                net.kyori.adventure.text.Component.text("§a" + won.getDisplayName()),
                Title.Times.times(Duration.ofMillis(500), Duration.ofMillis(2000), Duration.ofMillis(500))
            ));
            player.playSound(player.getLocation(), org.bukkit.Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
            // Win effect particles
            for (int i = 0; i < 50; i++) {
                double x = (Math.random() - 0.5) * 2;
                double z = (Math.random() - 0.5) * 2;
                player.getWorld().spawnParticle(org.bukkit.Particle.FIREWORK, player.getLocation().clone().add(x, 1, z), 0, 0, 0, 0, 1);
            }
            TokenSMPPlugin.getInstance().getTokenManager().giveTokenToPlayer(player, won);
            player.sendMessage("§aYou received the " + won.getDisplayName() + " token!");
        });
    }
}
