package com.tokensmp.data;
import java.util.*;
public class BountyManager { private final Map<UUID,Integer> bounties = new HashMap<>(); public int getBounty(UUID uuid){return bounties.getOrDefault(uuid,0);} public void setBounty(UUID uuid,int amount){if(amount<=0)bounties.remove(uuid);else bounties.put(uuid,amount);} public Map<UUID,Integer> getBounties(){return Collections.unmodifiableMap(bounties);} }
