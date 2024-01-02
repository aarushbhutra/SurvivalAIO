package me.anon695.survivalaio.earning;

import me.anon695.survivalaio.SurvivalAIO;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobEarningEvent implements Listener {

    private SurvivalAIO plugin;
    public MobEarningEvent(SurvivalAIO plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobKill(EntityDeathEvent event) {
        if(event.getEntity().getKiller() != null) {
            Player player = event.getEntity().getKiller();
            if(plugin.getConfig().contains("earning.mobs." + event.getEntity().getType().toString())) {
                double amount = plugin.getConfig().getDouble("earning.mobs." + event.getEntity().getType().toString());
                plugin.getEconomy().depositPlayer(player, amount);
                player.sendMessage("You have earned $" + amount + " for killing " + event.getEntity().getType().toString() + "!");
            }
        }
    }

}
