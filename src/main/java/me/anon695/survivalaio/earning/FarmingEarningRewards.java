package me.anon695.survivalaio.earning;

import me.anon695.survivalaio.SurvivalAIO;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class FarmingEarningRewards implements Listener {

    private SurvivalAIO plugin;
    public FarmingEarningRewards(SurvivalAIO plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCropBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        //Check if the user is holding a hoe or is empty handed
        if(player.getInventory().getItemInMainHand().getType().toString().contains("HOE") || player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            if(plugin.getConfig().contains("earning.farming." + block.getType().toString())) {
                if(block.getBlockData() instanceof Ageable) {
                    Ageable ageable = (Ageable) block.getBlockData();
                    if(ageable.getAge() == ageable.getMaximumAge()) {
                        double amount = plugin.getConfig().getDouble("earning.farming." + block.getType().toString());
                        plugin.getEconomy().depositPlayer(player, amount);
                        player.sendMessage("You have earned $" + amount + " for farming " + block.getType().toString() + "!");
                    }
                } else {
                    double amount = plugin.getConfig().getDouble("earning.farming." + block.getType().toString());
                    plugin.getEconomy().depositPlayer(player, amount);
                    player.sendMessage("You have earned $" + amount + " for farming " + block.getType().toString() + "!");
                }
            }
        }
    }

}
