package org.diploblastic.kitsTest;

import org.bukkit.plugin.java.JavaPlugin;

// Custom item base imports
import org.diploblastic.kitsTest.commands.CustomItemCommand;
import org.diploblastic.kitsTest.item.CustomItemRegistry;
import org.diploblastic.kitsTest.listeners.CustomItemListener;

// Custom item implementation imports
import org.diploblastic.kitsTest.item.impl.Feather;
import org.diploblastic.kitsTest.item.impl.Lilypad;

public final class KitsTest extends JavaPlugin {

    @Override
    public void onEnable() {
        CustomItemRegistry registry = new CustomItemRegistry();
        registry.register(new Feather());
        registry.register(new Lilypad());
        //can register more custom items here

        getServer().getPluginManager().registerEvents(new CustomItemListener(registry), this);

        CustomItemCommand customItemCommand = new CustomItemCommand(registry);
        getCommand("customitem").setExecutor(customItemCommand);
        getCommand("customitem").setTabCompleter(customItemCommand);
    }

    @Override
    public void onDisable() {
        getLogger().info("Signing out");
    }
}
