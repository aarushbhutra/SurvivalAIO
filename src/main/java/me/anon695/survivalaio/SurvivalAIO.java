package me.anon695.survivalaio;

import me.anon695.survivalaio.earning.*;
import me.anon695.survivalaio.economy.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public final class SurvivalAIO extends JavaPlugin {

    private static Economy econ = null;

    private final File configFolder = new File(getDataFolder(), "data");

    private final File econFile = new File(configFolder, "balance.yml");
    private YamlConfiguration econConfig = null;


    /**
     * Better way to send money messages (toggle? per minute?)
     * Bank System
     */

    @Override
    public void onEnable() {
        //Check if vault is installed
        if(Bukkit.getPluginManager().getPlugin("Vault") == null) {
            throw new RuntimeException("Vault is not installed!");
        } else {
            if(getConfig().getBoolean("useCustomEconomy")) {
                econ = new SurvivalAIOEconomy(this);
                Bukkit.getServicesManager().register(Economy.class, econ, this, ServicePriority.Highest);
            } else {
                RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
                if (rsp == null) {
                    throw new RuntimeException("No economy plugin found!");
                }
                econ = rsp.getProvider();
            }

        }

        registerEvents();
        registerCommands();

        Messages.initMessages(getConfig());

        //load config
        getConfig().options().copyDefaults(true);
        saveConfig();

        if(!configFolder.exists()) {
            configFolder.mkdirs();
        }

        if(!econFile.exists()) {
            try {
                econFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



    public Economy getEconomy() {
        return econ;
    }


    //Create a method to get the file as a YamlConfiguration and can be accessed from anywhere
    public YamlConfiguration getEcoData() {
        if (econConfig == null) {
            econConfig = YamlConfiguration.loadConfiguration(getEcoFile());
        }
        return econConfig;
    }

    //Create a method to save the file
    public File getEcoFile() {
        return econFile;
    }


    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new UserDataCreator(this), this);
        getServer().getPluginManager().registerEvents(new MiningEarningRewards(this), this);
        getServer().getPluginManager().registerEvents(new FarmingEarningRewards(this), this);
        getServer().getPluginManager().registerEvents(new FishingEarningRewards(this), this);
        getServer().getPluginManager().registerEvents(new WoodCuttingEarningRewards(this), this);
        getServer().getPluginManager().registerEvents(new MobEarningEvent(this), this);
        getServer().getPluginManager().registerEvents(new CraftingEarningRewards(this), this);
        getServer().getPluginManager().registerEvents(new EarningListenerGUI(this), this);
    }

    public void registerCommands() {
        getCommand("economy").setExecutor(new EconomyCommand(this));
        getCommand("economy").setTabCompleter(new EconomyTabComplete());
        getCommand("balance").setExecutor(new BalanceCommand(this));
        getCommand("balance").setTabCompleter(new BalanceTabComplete());
        getCommand("earning").setExecutor(new EarningCommandGUI(this));
        getCommand("pay").setExecutor(new PayCommand(this));
        getCommand("pay").setTabCompleter(new PayTabComplete());
    }



    public enum Messages {
        NO_PERMISSION("no-permission"),
        INVALID_ARGUMENTS("invalid-arguments"),
        INVALID_PLAYER("invalid-player"),
        INVALID_NUMBER("invalid-number"),
        NO_ACCOUNT("no-account"),
        NOT_ENOUGH_MONEY("not-enough-money");

        public String message;
        public String path;
        Messages(String path) {
            this.path = path;
        }
        public String getMessage() {
            return message;
        }

        public static void initMessages(FileConfiguration config) {
            for (Messages messageEnum : values()) {
                messageEnum.message = config.getString("message." + messageEnum.path);
            }
        }
    }

    public String formatNumber(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(number);
    }
}
