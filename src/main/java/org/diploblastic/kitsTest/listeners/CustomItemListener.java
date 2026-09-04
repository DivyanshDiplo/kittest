package org.diploblastic.kitsTest.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.diploblastic.kitsTest.item.CustomItem;
import org.diploblastic.kitsTest.item.CustomItemRegistry;

public class CustomItemListener implements Listener {

    private final CustomItemRegistry registry;

    public CustomItemListener(CustomItemRegistry registry) {
        this.registry = registry;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null) {
            return;
        }

        CustomItem customItem = registry.match(item);
        if (customItem == null) {
            return;
        }

        Player player = event.getPlayer();
        Action action = event.getAction();

        switch (action) {
            case LEFT_CLICK_AIR:
            case LEFT_CLICK_BLOCK:
                customItem.onLeftClick(player);
                break;
            case RIGHT_CLICK_AIR:
            case RIGHT_CLICK_BLOCK:
                customItem.onRightClick(player);
                break;
            default:
                break;
        }
    }
}
