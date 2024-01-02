package me.anon695.survivalaio.earning;

import me.anon695.survivalaio.SurvivalAIO;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class EarningListenerGUI implements Listener {

    private SurvivalAIO plugin;
    public EarningListenerGUI(SurvivalAIO plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if(player.hasMetadata("OpenMenu")) {
            player.removeMetadata("OpenMenu", plugin);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(player.hasMetadata("OpenMenu")) {
            if(player.getMetadata("OpenMenu").get(0).asString().equals("EarningGUI")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(player.hasMetadata("OpenMenu")) {
            if(player.getMetadata("OpenMenu").get(0).asString().equals("EarningGUI")) {
                event.setCancelled(true);
            }
        }
    }

}
