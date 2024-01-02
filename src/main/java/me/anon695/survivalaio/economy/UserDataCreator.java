package me.anon695.survivalaio.economy;

import me.anon695.survivalaio.SurvivalAIO;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class UserDataCreator implements Listener {

    private final SurvivalAIO plugin;
    public UserDataCreator(SurvivalAIO plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();
        YamlConfiguration configuration = plugin.getData();
        if(!plugin.getData().contains(player.getUniqueId().toString())) {
            configuration.set(player.getUniqueId().toString(), 0);
            configuration.save(plugin.getDataFile());
        };
    }

}
