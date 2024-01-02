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
import java.util.List;

public class BalanceTabComplete implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(commandSender.hasPermission("survivalaio.balance.others")) {
            if(args.length == 1) {
                List<String> names = new ArrayList<>();
                for(Player player : Bukkit.getOnlinePlayers()) {
                    names.add(player.getName());
                }
                return StringUtil.copyPartialMatches(args[0], names, new ArrayList<>());
            }
        }

        return new ArrayList<>();
    }
}
