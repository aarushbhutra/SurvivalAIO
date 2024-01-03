package me.anon695.survivalaio.economy;

import me.anon695.survivalaio.SurvivalAIO;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BalanceCommand implements CommandExecutor {

    private final SurvivalAIO plugin;
    public BalanceCommand(SurvivalAIO plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            //Check if there are no arguments
            if(strings.length == 0) {
                if(player.hasPermission("survivalaio.balance")) {
                    double playerBalance = plugin.getEconomy().getBalance(player);
                    String formattedBalance = plugin.formatNumber(playerBalance);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.balance-me").replace("%balance%", formattedBalance)));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.NO_PERMISSION.getMessage()));
                }
            } else if (strings.length == 1) {
                if(player.hasPermission("survivalaio.balance.others")) {
                    OfflinePlayer target = plugin.getServer().getOfflinePlayer(strings[0]);
                    if(plugin.getEcoData().contains(target.getUniqueId().toString())) {
                        double targetBalance = plugin.getEconomy().getBalance(target);
                        String formattedBalance = plugin.formatNumber(targetBalance);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.balance-other").replace("%player%", target.getName()).replace("%balance%", formattedBalance)));
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.NO_ACCOUNT.getMessage()));
                    }
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.INVALID_ARGUMENTS.getMessage()));
                return false;
            }
        } else {
            if(strings.length == 0) {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lERROR! &cMust provide a player!"));
                return false;
            } else if (strings.length == 1) {
                OfflinePlayer target = plugin.getServer().getOfflinePlayer(strings[0]);
                if(plugin.getEcoData().contains(target.getUniqueId().toString())) {
                    double targetBalance = plugin.getEconomy().getBalance(target);
                    String formattedBalance = plugin.formatNumber(targetBalance);
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.balance-other").replace("%player%", target.getName()).replace("%balance%", formattedBalance))));
                } else {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.NO_ACCOUNT.getMessage()));
                }
            } else {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.INVALID_ARGUMENTS.getMessage()));
                return false;
            }
        }


        return false;
    }
}
