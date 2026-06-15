package com.tokensmp.gui;
import org.bukkit.Bukkit; import org.bukkit.inventory.Inventory;
public class PlayerTokensGUI { public Inventory create(){return Bukkit.createInventory(null, 27, "Your Tokens");} }
