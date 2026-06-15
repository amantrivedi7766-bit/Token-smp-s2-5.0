package com.tokensmp.commands;
import com.tokensmp.spin.SpinManager; import org.bukkit.command.*; import org.bukkit.entity.Player;
public class SpinCommand implements CommandExecutor { private final SpinManager spinManager; public SpinCommand(SpinManager spinManager){this.spinManager=spinManager;} @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args){ if(!(sender instanceof Player player)){sender.sendMessage("Only players can use this command."); return true;} spinManager.spin(player); return true;} }
