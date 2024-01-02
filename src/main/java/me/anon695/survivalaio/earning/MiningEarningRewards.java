package me.anon695.survivalaio.earning;

import me.anon695.survivalaio.SurvivalAIO;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class MiningEarningRewards implements Listener {

    private SurvivalAIO plugin;
    public MiningEarningRewards(SurvivalAIO plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        //Check if the user is holding a pickaxe
        if(player.getInventory().getItemInMainHand().getType().toString().contains("PICKAXE")) {
            if(plugin.getConfig().contains("earning.mining." + block.getType().toString())) {
                double amount = plugin.getConfig().getDouble("earning.mining." + block.getType().toString());
                plugin.getEconomy().depositPlayer(player, amount);
                player.sendMessage("You have earned $" + amount + " for mining " + block.getType().toString() + "!");
            }
        }
    }


}
