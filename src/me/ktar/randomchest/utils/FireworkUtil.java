package me.ktar.randomchest.utils;

import me.ktar.randomchest.RandomChest;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Carter on 5/23/2015.
 */
public class FireworkUtil {
    /**
     * Plays a firework effect :)
     *
     * @param location location to play the firework at
     * @param effect the firework effect to play
     */
    public static void playFirework(Location location, FireworkEffect effect) {
        Firework firework = location.getWorld().spawn(location, Firework.class);
        FireworkMeta fMeta = firework.getFireworkMeta();
        fMeta.addEffect(effect);
        firework.setFireworkMeta(fMeta);
        new BukkitRunnable() {
            @Override
            public void run() {
                firework.detonate();
            }
        }.runTaskLater(RandomChest.getInstance(), 1);
    }
}
