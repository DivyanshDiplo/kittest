package org.diploblastic.kitsTest.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.diploblastic.kitsTest.item.CustomItem;
import org.diploblastic.kitsTest.item.CustomItemRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomItemCommand implements CommandExecutor, TabCompleter {

    private final CustomItemRegistry registry;

    public CustomItemCommand(CustomItemRegistry registry) {
        this.registry = registry;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /customitem <" + String.join("|", registry.getIds()) + ">");
            return true;
        }

        CustomItem item = registry.getById(args[0].toLowerCase());
        if (item == null) {
            sender.sendMessage(ChatColor.RED + "Unknown item: " + args[0]);
            return true;
        }

        Player player = (Player) sender;
        player.getInventory().addItem(item.createItemStack());
        sender.sendMessage(ChatColor.GREEN + "You received a custom item.");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length != 1) {
            return Collections.emptyList();
        }

        String partial = args[0].toLowerCase();
        List<String> matches = new ArrayList<>();
        for (String id : registry.getIds()) {
            if (id.startsWith(partial)) {
                matches.add(id);
            }
        }
        return matches;
    }
}
