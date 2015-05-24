package me.ktar.randomchest.armorstand;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Carter on 5/23/2015.
 */
public class StandHandler {

    public static void cycle(Material mat, ArmorStand stand){
        if(stand.getItemInHand() != null)
            stand.setItemInHand(null);

        if(stand.getHelmet() != null)
            stand.setHelmet(null);

        if(mat.isBlock()){
            stand.setHelmet(new ItemStack(mat));
        }else {
            stand.setItemInHand(new ItemStack(mat));
        }
    }

    public static ArmorStand spawnStand(Location location){
        ArmorStand stand = location.getWorld().spawn(location, ArmorStand.class);
        stand.setArms(true);
        stand.setBasePlate(false);
        stand.setSmall(false);
        stand.setCanPickupItems(false);
        stand.setGravity(false);
        stand.setHelmet(new ItemStack(Material.BEDROCK));
        return stand;
    }

}
