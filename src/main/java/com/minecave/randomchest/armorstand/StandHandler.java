package com.minecave.randomchest.armorstand;

import com.minecave.randomchest.RandomChest;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
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
        Location loc = location.add(0.5,0.5,0.5);
        //loc.setYaw(90);
        ArmorStand stand = location.getWorld().spawn(loc, ArmorStand.class);
        stand.setArms(true);
        stand.setBasePlate(false);
        stand.setSmall(true);
        stand.setRightArmPose(new EulerAngle(Math.toRadians(0), Math.toRadians(250), Math.toRadians(270)));
        stand.setCanPickupItems(false);
        stand.setGravity(false);
        //stand.setHelmet(new ItemStack(Material.BEDROCK));
        stand.setVisible(false);
        stand.setMetadata("ktarrandomchest", new FixedMetadataValue(RandomChest.getInstance(), true));
        return stand;
    }

}
