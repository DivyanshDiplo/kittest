package org.diploblastic.kitsTest.item.impl;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.diploblastic.kitsTest.item.CustomItem;

public class Feather extends CustomItem {

    public static final String ID = "feather";
    private static final String DISPLAY_NAME = ChatColor.AQUA + "Feather of Swiftness";

    private static final int DURATION_TICKS = 200; // 10 seconds
    private static final int SPEED_AMPLIFIER = 0; // Speed I

    public Feather() {
        super(ID, DISPLAY_NAME, Material.FEATHER);
    }

    @Override
    public void onRightClick(Player player) {

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, DURATION_TICKS, SPEED_AMPLIFIER));
    }
}
