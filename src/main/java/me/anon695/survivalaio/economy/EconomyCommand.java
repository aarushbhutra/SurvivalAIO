package me.anon695.survivalaio.economy;

import me.anon695.survivalaio.SurvivalAIO;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EconomyCommand implements CommandExecutor {

    private final SurvivalAIO plugin;
    public EconomyCommand(SurvivalAIO plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(player.hasPermission("survivalaio.economy.admin")) {
                //Check if there are no arguments
                if(strings.length == 0) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lERROR! &cUsage: /economy <set|add|remove> <player> <amount>"));
                    return false;
                }else if(strings.length == 1) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.INVALID_PLAYER.getMessage()));
                    return false;
                }else if(strings.length == 2) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.INVALID_NUMBER.getMessage()));
                    return false;
                }else if(strings.length == 3) {
                    if(strings[0].equalsIgnoreCase("set")) {
                        OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(strings[1]);
                        //Check if the players uuid is in the data file
                        if(plugin.getEcoData().contains(offlinePlayer.getUniqueId().toString())) {
                            //Check if the 3rd argument is a number
                            try {
                                Integer.parseInt(strings[2]);
                            } catch (NumberFormatException e) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.INVALID_NUMBER.getMessage()));
                                return false;
                            }

                            //Set the player's balance
                            plugin.getEconomy().withdrawPlayer(offlinePlayer, plugin.getEconomy().getBalance(offlinePlayer));
                            plugin.getEconomy().depositPlayer(offlinePlayer, Integer.parseInt(strings[2]));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lSUCCESS! &aSet &e" + offlinePlayer.getName() + "'s &abalance to &e" + plugin.formatNumber(Integer.parseInt(strings[2]))));
                            return true;
                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.NO_ACCOUNT.getMessage()));
                            return false;
                        }
                    } else if(strings[0].equalsIgnoreCase("add")) {
                        OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(strings[1]);
                        //Check if the players uuid is in the data file
                        if(plugin.getEcoData().contains(offlinePlayer.getUniqueId().toString())) {
                            try {
                                Integer.parseInt(strings[2]);
                            } catch (NumberFormatException e) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.INVALID_NUMBER.getMessage()));
                                return false;
                            }

                            plugin.getEconomy().depositPlayer(offlinePlayer, Integer.parseInt(strings[2]));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lSUCCESS! &aAdded &e" + plugin.formatNumber(Integer.parseInt(strings[2])) + " &ato &e" + offlinePlayer.getName() + "'s &abalance! This player now has &e" + plugin.formatNumber(plugin.getEconomy().getBalance(offlinePlayer)) + " &ain their account!"));
                            return true;
                        }  else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.NO_ACCOUNT.getMessage()));
                            return false;
                        }
                    } else if(strings[0].equalsIgnoreCase("remove")) {
                        OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(strings[1]);
                        //Check if the players uuid is in the data file
                        if(plugin.getEcoData().contains(offlinePlayer.getUniqueId().toString())) {
                            try {
                                Integer.parseInt(strings[2]);
                            } catch (NumberFormatException e) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.INVALID_NUMBER.getMessage()));
                                return false;
                            }

                            if(plugin.getEconomy().has(offlinePlayer, Integer.parseInt(strings[2]))) {
                                plugin.getEconomy().withdrawPlayer(offlinePlayer, Integer.parseInt(strings[2]));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lSUCCESS! &aRemoved &e" + plugin.formatNumber(Integer.parseInt(strings[2])) + " &afrom &e" + offlinePlayer.getName() + "'s &abalance! This player now has &e" + plugin.formatNumber(plugin.getEconomy().getBalance(offlinePlayer)) + " &ain their account!"));
                                return true;
                            } else {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.NOT_ENOUGH_MONEY.getMessage()));
                                return false;
                            }
                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.NO_ACCOUNT.getMessage()));
                            return false;
                        }
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.INVALID_ARGUMENTS.getMessage()));
                        return false;
                    }
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', SurvivalAIO.Messages.NO_PERMISSION.getMessage()));
                return false;
            }
        } else {
            if(strings.length == 0) {
                commandSender.sendMessage("Usage: /economy <set|add|remove> <player> <amount>");
                return false;
            }else if(strings.length == 1) {
                commandSender.sendMessage("Usage: /economy <set|add|remove> <player> <amount>");
                return false;
            }else if(strings.length == 2) {
                commandSender.sendMessage("Usage: /economy <set|add|remove> <player> <amount>");
                return false;
            }else if(strings.length == 3) {
                if(strings[0].equalsIgnoreCase("set")) {
                    OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(strings[1]);
                    //Check if the 2nd argument is a player
                    if(plugin.getEcoData().contains(offlinePlayer.getUniqueId().toString())) {
                        //Check if the 3rd argument is a number
                        try {
                            Integer.parseInt(strings[2]);
                        } catch (NumberFormatException e) {
                            commandSender.sendMessage(SurvivalAIO.Messages.INVALID_NUMBER.getMessage());
                            return false;
                        }

                        //Set the player's balance
                        plugin.getEconomy().withdrawPlayer(offlinePlayer, plugin.getEconomy().getBalance(offlinePlayer));
                        plugin.getEconomy().depositPlayer(offlinePlayer, Integer.parseInt(strings[2]));
                        commandSender.sendMessage("Set " + offlinePlayer.getName() + "'s balance to " + plugin.formatNumber(Integer.parseInt(strings[2])));
                        return true;
                    } else {
                        commandSender.sendMessage(SurvivalAIO.Messages.NO_ACCOUNT.getMessage());
                        return false;
                    }
                } else if(strings[0].equalsIgnoreCase("add")) {
                    OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(strings[1]);
                    //Check if the 2nd argument is a player
                    if(plugin.getEcoData().contains(offlinePlayer.getUniqueId().toString())) {
                        try {
                            Integer.parseInt(strings[2]);
                        } catch (NumberFormatException e) {
                            commandSender.sendMessage(SurvivalAIO.Messages.INVALID_NUMBER.getMessage());
                            return false;
                        }

                        plugin.getEconomy().depositPlayer(offlinePlayer, Integer.parseInt(strings[2]));
                        commandSender.sendMessage("Added " + plugin.formatNumber(Integer.parseInt(strings[2])) + " to " + offlinePlayer.getName() + "'s balance! This player now has " + plugin.formatNumber(plugin.getEconomy().getBalance(offlinePlayer)) + " in their account!");
                        return true;
                    }  else {
                        commandSender.sendMessage(SurvivalAIO.Messages.NO_ACCOUNT.getMessage());
                        return false;
                    }
                } else if(strings[0].equalsIgnoreCase("remove")) {
                    OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(strings[1]);
                    //Check if the 2nd argument is a player
                    if(plugin.getEcoData().contains(offlinePlayer.getUniqueId().toString())) {
                        try {
                            Integer.parseInt(strings[2]);
                        } catch (NumberFormatException e) {
                            commandSender.sendMessage(SurvivalAIO.Messages.INVALID_NUMBER.getMessage());
                            return false;
                        }

                        if(plugin.getEconomy().has(offlinePlayer, Integer.parseInt(strings[2]))) {
                            plugin.getEconomy().withdrawPlayer(offlinePlayer, Integer.parseInt(strings[2]));
                            commandSender.sendMessage("Removed " + plugin.formatNumber(Integer.parseInt(strings[2])) + " from " + offlinePlayer.getName() + "'s balance! This player now has " + plugin.formatNumber(plugin.getEconomy().getBalance(offlinePlayer)) + " in their account!");
                            return true;
                        } else {
                            commandSender.sendMessage(SurvivalAIO.Messages.NOT_ENOUGH_MONEY.getMessage());
                            return false;
                        }
                    } else {
                        commandSender.sendMessage(SurvivalAIO.Messages.NO_ACCOUNT.getMessage());
                        return false;
                    }
                } else {
                    commandSender.sendMessage(SurvivalAIO.Messages.INVALID_ARGUMENTS.getMessage());
                    return false;
                }
            }
        }
        return false;
    }
}
