package me.anon695.survivalaio.economy;

import me.anon695.survivalaio.SurvivalAIO;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PayCommand implements CommandExecutor {

    private final SurvivalAIO plugin;
    public PayCommand(SurvivalAIO plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(player.hasPermission("survivalaio.pay")) {
                if(strings.length == 0) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lERROR! &cUsage: /pay <player> <amount>"));
                } else if (strings.length == 1) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.INVALID_ARGUMENTS.getMessage()));
                } else if (strings.length == 2) {
                    //Check if the first argument is a player on the server
                    Player target = plugin.getServer().getPlayer(strings[0]);
                    if(target != null) {
                        //Make sure the player is not paying themselves
                        if(!target.getName().equals(player.getName())) {
                            double amount;
                            try {
                                amount = Double.parseDouble(strings[1]);
                            } catch (NumberFormatException e) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.INVALID_NUMBER.getMessage()));
                                return false;
                            }

                            if(plugin.getEconomy().has(player,amount)) {
                                //Check if the amount is greater than 0
                                if(amount > 0) {
                                    if(plugin.getConfig().getBoolean("tax.enabled")) {
                                        double taxPercent = (Math.log(amount)/Math.log(plugin.getConfig().getDouble("tax.base")))/100;
                                        double taxAmount = amount * taxPercent;
                                        double amountAfterTax = amount - taxAmount;
                                        plugin.getEconomy().withdrawPlayer(player, amount);
                                        plugin.getEconomy().depositPlayer(target, amountAfterTax);
                                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.pay-receive").replace("%player%", target.getName()).replace("%amount%", plugin.formatNumber(amountAfterTax))));
                                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.pay-send").replace("%player%", player.getName()).replace("%amount%", plugin.formatNumber(amount))));
                                    } else {
                                        if(plugin.getEconomy().has(player, amount)) {
                                            plugin.getEconomy().withdrawPlayer(player, amount);
                                            plugin.getEconomy().depositPlayer(target, amount);
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.pay-receive").replace("%player%", target.getName()).replace("%amount%", plugin.formatNumber(amount))));
                                            target.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.pay-send").replace("%player%", player.getName()).replace("%amount%", plugin.formatNumber(amount))));
                                        } else {
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.NOT_ENOUGH_MONEY.getMessage()));
                                        }
                                    }

                                } else {
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.INVALID_NUMBER.getMessage()));
                                }
                            } else {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.NOT_ENOUGH_MONEY.getMessage()));
                            }
                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.INVALID_PLAYER.getMessage()));
                        }
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.INVALID_PLAYER.getMessage()));
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.INVALID_ARGUMENTS.getMessage()));
                }
            }
        } else {
            commandSender.sendMessage("You must be a player to use this command!");
        }

        return false;
    }
}
