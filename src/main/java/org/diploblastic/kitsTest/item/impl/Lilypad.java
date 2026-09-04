package org.diploblastic.kitsTest.item.impl;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.diploblastic.kitsTest.item.CustomItem;

public class Lilypad extends CustomItem {

    public static final String ID = "lilypad";
    private static final String DISPLAY_NAME = ChatColor.GREEN + "Bouncy Lilypad";

    private static final int DURATION_TICKS = 200; // 10 seconds
    private static final int JUMP_AMPLIFIER = 2; // Jump Boost III

    public Lilypad() {
        super(ID, DISPLAY_NAME, Material.WATER_LILY);
    }

    @Override
    public void onRightClick(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, DURATION_TICKS, JUMP_AMPLIFIER));
    }

    @Override
    public void onLeftClick(Player player){

    }
}
