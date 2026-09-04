package org.diploblastic.kitsTest.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomItemRegistry {

    private final Map<String, CustomItem> itemsById = new LinkedHashMap<>();

    public void register(CustomItem item) {
        itemsById.put(item.getId(), item);
    }

    public CustomItem getById(String id) {
        return itemsById.get(id);
    }

    /** All registered ids, in registration order — used for command usage/tab-complete. */
    public Collection<String> getIds() {
        return itemsById.keySet();
    }

    public CustomItem match(ItemStack stack) {
        if (stack == null) {
            return null;
        }
        ItemMeta meta = stack.getItemMeta();
        if (meta == null || !meta.hasDisplayName()) {
            return null;
        }
        String displayName = meta.getDisplayName();
        for (CustomItem item : itemsById.values()) {
            if (item.getDisplayName().equals(displayName)) {
                return item;
            }
        }
        return null;
    }
}
