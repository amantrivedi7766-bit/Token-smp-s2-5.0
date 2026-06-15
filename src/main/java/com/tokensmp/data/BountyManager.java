package com.tokensmp.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BountyManager {
    private final Map<UUID, Integer> bounties = new HashMap<>();

    public void setBounty(UUID target, int amount) { bounties.put(target, amount); }
    public int getBounty(UUID target) { return bounties.getOrDefault(target, 0); }
    public void removeBounty(UUID target) { bounties.remove(target); }
    public boolean hasBounty(UUID target) { return bounties.containsKey(target); }
    public void claimBounty(UUID hunter, UUID target) {
        int amount = bounties.remove(target);
        // Add money to hunter (Vault integration optional) – here just message
        // In a real plugin, use Vault or economy hook.
    }
}
