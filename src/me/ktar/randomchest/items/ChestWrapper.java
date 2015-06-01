package me.ktar.randomchest.items;

import me.ktar.randomchest.RandomChest;
import me.ktar.randomchest.armorstand.Cycler;
import me.ktar.randomchest.armorstand.StandHandler;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class ChestWrapper {

	private ChestType type;
	public Player inUse;
    private Location location;
    private ArmorStand stand;

	public ChestWrapper(ChestType type, Location location){
		this.type = type;
        this.location = location.getBlock().getLocation();
        stand = StandHandler.spawnStand(location);
        inUse = null;
        location.getBlock().setMetadata("ktarrandomchest", new FixedMetadataValue(RandomChest.getInstance(), true));
	}

    public ChestType getType(){
        return type;
    }

	public boolean getInUse(){
		return inUse != null;
	}

    public void unloadChest(){
        location.getBlock().removeMetadata("ktarrandomchest", RandomChest.getInstance());
        stand.removeMetadata("ktarrandomchest", RandomChest.getInstance());
        stand.remove();
    }

	public Player getUser(){
		return inUse;
	}

	public void forceStopUse(){
		this.inUse = null;
	}

    public void use(Player player){
        this.inUse = player; //make the chest become in use

        //ChestUtil.changeChestState(getLocation(), true, player); //open the chest animation

        cycleItems(); //make fancy display to the player
    }

    public Location getLocation(){
        return this.location.getBlock().getLocation();
    }

    public ArmorStand getStand(){
        return this.stand;
    }

    private void cycleItems(){
        new Cycler(RandomChest.getInstance(), this).start();
    }


}
