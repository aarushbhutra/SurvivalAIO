package me.anon695.survivalaio.earning;

import me.anon695.survivalaio.SurvivalAIO;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EarningCommandGUI implements CommandExecutor {

    private SurvivalAIO plugin;
    public EarningCommandGUI(SurvivalAIO plugin) {
        this.plugin = plugin;
    }

    public Inventory earningGUI = Bukkit.createInventory(null,27, ChatColor.translateAlternateColorCodes('&', "&e&lEARNING METHODS"));

    public Map<String, Double> getEarningValues(String path) {
        Map<String, Double> earningValues = new HashMap<>();
        for (String key : plugin.getConfig().getConfigurationSection(path).getKeys(false)) {
            earningValues.put(key, plugin.getConfig().getDouble(path + "." + key));
        }
        return earningValues;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(player.hasPermission("survivalaio.earning")) {
                ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                ItemMeta fillerMeta = filler.getItemMeta();
                fillerMeta.setDisplayName(" ");
                filler.setItemMeta(fillerMeta);

                //For all the slots in the inventory, set the item to the filler item
                for(int i = 0; i < earningGUI.getSize(); i++) {
                    earningGUI.setItem(i, filler);
                }

                ItemStack mining = new ItemStack(Material.DIAMOND_PICKAXE);
                ItemMeta miningMeta = mining.getItemMeta();
                miningMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lMINING REWARDS"));
                Map<String, Double> earningMining = getEarningValues("earning.mining");
                List<String> lore = new ArrayList<>();
                for (Map.Entry<String, Double> entry : earningMining.entrySet()) {
                    String block = entry.getKey().toLowerCase().replace("_", " ");
                    String formattedBlock = WordUtils.capitalizeFully(block); // Requires Apache Commons Lang
                    double amount = entry.getValue();

                    String loreLine = ChatColor.translateAlternateColorCodes('&', "&c" + formattedBlock + "&7: &c$" + amount);
                    lore.add(loreLine);
                }
                miningMeta.setLore(lore);
                mining.setItemMeta(miningMeta);
                earningGUI.setItem(10, mining);

                ItemStack farming = new ItemStack(Material.DIAMOND_HOE);
                ItemMeta farmingMeta = farming.getItemMeta();
                farmingMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lFARMING REWARDS"));
                Map<String, Double> earningFarming = getEarningValues("earning.farming");
                List<String> farmingLore = new ArrayList<>();
                for (Map.Entry<String, Double> entry : earningFarming.entrySet()) {
                    String block = entry.getKey().toLowerCase().replace("_", " ");
                    String formattedBlock = WordUtils.capitalizeFully(block); // Requires Apache Commons Lang
                    double amount = entry.getValue();

                    String loreLine = ChatColor.translateAlternateColorCodes('&', "&a" + formattedBlock + "&7: &a$" + amount);
                    farmingLore.add(loreLine);
                }
                farmingMeta.setLore(farmingLore);
                farming.setItemMeta(farmingMeta);
                earningGUI.setItem(11, farming);

                ItemStack fishing = new ItemStack(Material.FISHING_ROD);
                ItemMeta fishingMeta = fishing.getItemMeta();
                fishingMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lFISHING REWARDS"));
                Map<String, Double> earningFishing = getEarningValues("earning.fishing");
                List<String> fishingLore = new ArrayList<>();
                for (Map.Entry<String, Double> entry : earningFishing.entrySet()) {
                    String block = entry.getKey().toLowerCase().replace("_", " ");
                    String formattedBlock = WordUtils.capitalizeFully(block); // Requires Apache Commons Lang
                    double amount = entry.getValue();

                    String loreLine = ChatColor.translateAlternateColorCodes('&', "&b" + formattedBlock + "&7: &b$" + amount);
                    fishingLore.add(loreLine);
                }
                fishingMeta.setLore(fishingLore);
                fishing.setItemMeta(fishingMeta);
                earningGUI.setItem(12, fishing);

                ItemStack mobs = new ItemStack(Material.ZOMBIE_HEAD);
                ItemMeta mobsMeta = mobs.getItemMeta();
                mobsMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lMOB REWARDS"));
                Map<String, Double> earningMobs = getEarningValues("earning.mobs");
                List<String> mobsLore = new ArrayList<>();
                for (Map.Entry<String, Double> entry : earningMobs.entrySet()) {
                    String block = entry.getKey().toLowerCase().replace("_", " ");
                    String formattedBlock = WordUtils.capitalizeFully(block); // Requires Apache Commons Lang
                    double amount = entry.getValue();

                    String loreLine = ChatColor.translateAlternateColorCodes('&', "&e" + formattedBlock + "&7: &e$" + amount);
                    mobsLore.add(loreLine);
                }
                mobsMeta.setLore(mobsLore);
                mobs.setItemMeta(mobsMeta);
                earningGUI.setItem(14, mobs);

                ItemStack woodcutting = new ItemStack(Material.DIAMOND_AXE);
                ItemMeta woodcuttingMeta = woodcutting.getItemMeta();
                woodcuttingMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lWOODCUTTING REWARDS"));
                Map<String, Double> earningWoodcutting = getEarningValues("earning.woodcutting");
                List<String> woodcuttingLore = new ArrayList<>();
                for (Map.Entry<String, Double> entry : earningWoodcutting.entrySet()) {
                    String block = entry.getKey().toLowerCase().replace("_", " ");
                    String formattedBlock = WordUtils.capitalizeFully(block); // Requires Apache Commons Lang
                    double amount = entry.getValue();

                    String loreLine = ChatColor.translateAlternateColorCodes('&', "&6" + formattedBlock + "&7: &6$" + amount);
                    woodcuttingLore.add(loreLine);
                }
                woodcuttingMeta.setLore(woodcuttingLore);
                woodcutting.setItemMeta(woodcuttingMeta);
                earningGUI.setItem(15, woodcutting);

                ItemStack crafting = new ItemStack(Material.CRAFTING_TABLE);
                ItemMeta craftingMeta = crafting.getItemMeta();
                craftingMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&d&lCRAFTING REWARDS"));
                Map<String, Double> earningCrafting = getEarningValues("earning.crafting");
                List<String> craftingLore = new ArrayList<>();
                for (Map.Entry<String, Double> entry : earningCrafting.entrySet()) {
                    String block = entry.getKey().toLowerCase().replace("_", " ");
                    String formattedBlock = WordUtils.capitalizeFully(block); // Requires Apache Commons Lang
                    double amount = entry.getValue();

                    String loreLine = ChatColor.translateAlternateColorCodes('&', "&d" + formattedBlock + "&7: &d$" + amount);
                    craftingLore.add(loreLine);
                }

                craftingMeta.setLore(craftingLore);
                crafting.setItemMeta(craftingMeta);
                earningGUI.setItem(16, crafting);

                player.openInventory(earningGUI);
                player.setMetadata("OpenMenu", new FixedMetadataValue(plugin, "EarningGUI"));
            } else {
                player.sendMessage(SurvivalAIO.Messages.NO_PERMISSION.getMessage());
            }
        } else {
            commandSender.sendMessage("You must be a player to use this command!");
        }


        return false;
    }
}
