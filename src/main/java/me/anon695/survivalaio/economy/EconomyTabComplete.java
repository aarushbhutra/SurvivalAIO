package me.anon695.survivalaio.economy;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EconomyTabComplete implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.asList("set", "add","remove"), new ArrayList<>());
        } else if  (args.length == 2){
            List<String> names = new ArrayList<>();
            for(Player player : Bukkit.getOnlinePlayers()) {
                names.add(player.getName());
            }
            return StringUtil.copyPartialMatches(args[1], names, new ArrayList<>());
        }

        return new ArrayList<>();
    }
}
