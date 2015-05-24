package me.ktar.randomchest.armorstand;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

/**
 * Created by Carter on 5/23/2015.
 */
public class StandHandler {

    protected static void cycle(Material mat, ArmorStand stand){
        if(mat.isBlock()){
            if(stand.getItemInHand() != null) stand.setItemInHand(null);
            stand.setHelmet(new ItemStack(mat));
        }else {
            if(stand.getHelmet() != null) stand.setHelmet(null);
            stand.setItemInHand(new ItemStack(mat));
            /*if(stand.getPassenger() != null)
                stand.getPassenger().remove();
            Item item = stand.getLocation().getWorld().dropItem(stand.getLocation().add(0,0.75,0), new ItemStack(mat));
            stand.setPassenger(item);*/
        }
    }

    public static ArmorStand spawnStand(Location location){
        ArmorStand stand = location.getWorld().spawn(location.add(0.5, 0.5, 0.5), ArmorStand.class);
        stand.setArms(true);
        stand.setBasePlate(false);
        stand.setSmall(true);
        stand.setRightArmPose(new EulerAngle(Math.toRadians(0), Math.toRadians(250), Math.toRadians(270)));
        stand.setItemInHand(new ItemStack(Material.DIAMOND));
        stand.setCanPickupItems(false);
        stand.setGravity(false);
        //stand.setHelmet(new ItemStack(Material.BEDROCK));
        stand.setVisible(true);
        return stand;
    }

}
