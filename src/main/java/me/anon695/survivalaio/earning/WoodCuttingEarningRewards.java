package me.anon695.survivalaio.earning;

import me.anon695.survivalaio.SurvivalAIO;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class WoodCuttingEarningRewards implements Listener {

    private SurvivalAIO plugin;
    public WoodCuttingEarningRewards(SurvivalAIO plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLogBreak(BlockBreakEvent event) {
        //Check if the user is holding an axe or is empty handed
        if(event.getPlayer().getInventory().getItemInMainHand().getType().toString().contains("AXE") || event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            if(plugin.getConfig().contains("earning.woodcutting." + event.getBlock().getType().toString())) {
                double amount = plugin.getConfig().getDouble("earning.woodcutting." + event.getBlock().getType().toString());
                plugin.getEconomy().depositPlayer(event.getPlayer(), amount);
                event.getPlayer().sendMessage("You have earned $" + amount + " for woodcutting " + event.getBlock().getType().toString() + "!");
            }
        }
    }

}
