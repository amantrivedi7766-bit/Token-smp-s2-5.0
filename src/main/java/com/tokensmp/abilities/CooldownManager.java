package com.tokensmp.abilities;
import java.util.*;
public class CooldownManager { private final Map<String,Long> cooldowns=new HashMap<>(); public boolean isReady(UUID uuid,String ability){return System.currentTimeMillis()>=cooldowns.getOrDefault(uuid+ability,0L);} public void set(UUID uuid,String ability,long seconds){cooldowns.put(uuid+ability,System.currentTimeMillis()+seconds*1000L);} }
