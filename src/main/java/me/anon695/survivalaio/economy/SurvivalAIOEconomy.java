package me.anon695.survivalaio.economy;

import me.anon695.survivalaio.SurvivalAIO;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.List;

public class SurvivalAIOEconomy implements Economy {

    private final SurvivalAIO plugin;
    public SurvivalAIOEconomy(SurvivalAIO plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getName() {
        return "SurvivalAIO";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double amount) {
        return null;
    }

    @Override
    public String currencyNamePlural() {
        return "Coins";
    }

    @Override
    public String currencyNameSingular() {
        return "Coin";
    }

    @Override
    public boolean hasAccount(String playerName) {
        return false;
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        if(player != null) {
            return plugin.getData().contains(player.getUniqueId().toString());
        }
        return false;
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return false;
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return false;
    }

    @Override
    public double getBalance(String playerName) {
        return 0;
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        if(player != null) {
            return plugin.getData().getDouble(player.getUniqueId().toString());
        }
        return 0;
    }

    @Override
    public double getBalance(String playerName, String world) {
        return 0;
    }

    @Override
    public double getBalance(OfflinePlayer player, String world) {
        return 0;
    }

    @Override
    public boolean has(String playerName, double amount) {
        return false;
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        if(player != null) {
            return plugin.getData().getDouble(player.getUniqueId().toString()) >= amount;
        }
        return false;
    }


    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return false;
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        return false;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        if(player != null) {
            double balance = plugin.getData().getDouble(player.getUniqueId().toString());
            if(balance >= amount) {
                YamlConfiguration configuration = plugin.getData();
                configuration.set(player.getUniqueId().toString(), balance - amount);
                try {
                    configuration.save(plugin.getDataFile());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return new EconomyResponse(amount, balance - amount, EconomyResponse.ResponseType.SUCCESS, "");
            } else {
                return new EconomyResponse(amount, balance, EconomyResponse.ResponseType.FAILURE, ChatColor.RED + "You do not have enough money!");
            }
        }
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        YamlConfiguration configuration = plugin.getData();
        if(player != null) {
            double balance = plugin.getData().getDouble(player.getUniqueId().toString());
            plugin.getData().set(player.getUniqueId().toString(), balance + amount);
            try {
                configuration.save(plugin.getDataFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return new EconomyResponse(amount, balance + amount, EconomyResponse.ResponseType.SUCCESS, "");
        }
        return null;
    }



    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return false;
    }
}
