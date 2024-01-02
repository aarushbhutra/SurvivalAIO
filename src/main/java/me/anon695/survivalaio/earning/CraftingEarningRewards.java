package me.anon695.survivalaio.earning;

import me.anon695.survivalaio.SurvivalAIO;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class CraftingEarningRewards implements Listener {

    private SurvivalAIO plugin;
    public CraftingEarningRewards(SurvivalAIO plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if(plugin.getConfig().contains("earning.crafting." + event.getRecipe().getResult().getType().toString())) {
            double amount = plugin.getConfig().getDouble("earning.crafting." + event.getRecipe().getResult().getType().toString());
            plugin.getEconomy().depositPlayer(event.getWhoClicked().getName(), amount);
            event.getWhoClicked().sendMessage("You have earned $" + amount + " for crafting " + event.getRecipe().getResult().getType().toString() + "!");
        }
    }

}
