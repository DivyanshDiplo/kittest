package org.diploblastic.kitsTest.item;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class CustomItem {

    private final String id;
    private final String displayName;
    private final Material material;

    protected CustomItem(String id, String displayName, Material material) {
        this.id = id;
        this.displayName = displayName;
        this.material = material;
    }

    /** Plain, typeable key (e.g. "feather") used for commands/tab-complete. */
    public String getId() {
        return id;
    }

    /** Colored name shown on the item and used to recognise it on click. */
    public String getDisplayName() {
        return displayName;
    }

    public ItemStack createItemStack() {
        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(displayName);
        stack.setItemMeta(meta);
        return stack;
    }

    public abstract void onRightClick(Player player);

    public void onLeftClick(Player player) {
        // no-op by default
    }
}
