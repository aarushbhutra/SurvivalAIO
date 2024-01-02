package me.anon695.survivalaio.earning;

import me.anon695.survivalaio.SurvivalAIO;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class FishingEarningRewards implements Listener {

    private SurvivalAIO plugin;
    public FishingEarningRewards(SurvivalAIO plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        //Check if a player catches a fish
        if(event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            //Check if the user is holding a fishing rod
            if(player.getInventory().getItemInMainHand().getType().toString().contains("FISHING_ROD")) {
                Entity item = event.getCaught();
                if(item instanceof Item) {
                    ItemStack itemStack = ((Item) item).getItemStack();
                    if(plugin.getConfig().contains("earning.fishing." + itemStack.getType().toString())) {
                        double amount = plugin.getConfig().getDouble("earning.fishing." + itemStack.getType().toString());
                        plugin.getEconomy().depositPlayer(player, amount);
                        player.sendMessage("You have earned $" + amount + " for fishing " + itemStack.getType().toString() + "!");
                    }
                }
            }
        }
    }

}
