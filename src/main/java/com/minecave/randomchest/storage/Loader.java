package com.minecave.randomchest.storage;

import com.minecave.randomchest.RandomChest;
import com.minecave.randomchest.items.ChestType;
import com.minecave.randomchest.items.ChestWrapper;
import com.minecave.randomchest.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Loader {

	private static final Map<Location, ChestWrapper> chests = new HashMap<>(); //Store the chests in a list, they have the location and reference the chest type
	private static final Map<String, String> messages = new HashMap<>();
    private static final Map<String, ChestType> types = new HashMap<>();

    //---------------------------CHESTS STUFF----------------------------
    public static ChestWrapper getChestWrapper(Location location){
        return chests.get(location);
    }

    public static void removeChestWrapper(Location location){
        if(chests.containsKey(location)) {
            chests.remove(location);
            save();
        }
    }

    public static void addChest(Block block, ChestType type){
        if(!chests.containsKey(block.getLocation())) {
            chests.put(block.getLocation(), new ChestWrapper(type, block.getLocation()));
            String key = block.getWorld().getUID().toString()+"^"
                    +block.getX()+"^"
                    +block.getY()+"^"
                    +block.getZ();
            RandomChest.chests.set(key, chests.get(block.getLocation()).getType().getName(), true);
        }
    }

    public static ChestType getChestType(String name){
        return types.get(name.toUpperCase());
    }


    //---------------------------LOADERS AND UNLOADERS AND SAVERS----------------------------
    public static void load(){
        chests.clear();
        types.clear();
		messages.clear();
        RandomChest.chesttypes.reloadConfig();
        RandomChest.items.reloadConfig();
        RandomChest.chests.reloadConfig();
        RandomChest.messages.reloadConfig();
		loadChests();
    }

    public static void unload(){
        chests.values().forEach(ChestWrapper::unloadChest);
        save();
    }

    private static void save(){
        for(String string : RandomChest.chests.getConfig().getKeys(false)) {
            RandomChest.chests.set(string, null);
            RandomChest.chests.saveConfig();
        }
        for(Location location : chests.keySet()){
            String key = location.getWorld().getUID().toString()+"^"
                    +location.getBlockX()+"^"
                    +location.getBlockY()+"^"
                    +location.getBlockZ();
            RandomChest.chests.set(key, chests.get(location).getType().getName());
        }
        RandomChest.chests.saveConfig();
    }


    //---------------------------WRAPPER MAKERS----------------------------
	private static void loadChests(){
        Map<String, ChestType> types = loadChestTypes();
        FileConfiguration config = RandomChest.chests.getConfig();
            for(String locationString : config.getKeys(true)){
                Location loc = stringToLoc(locationString);
                chests.put(loc.getBlock().getLocation(), new ChestWrapper(types.get(config.getString(locationString).toUpperCase()), loc.getBlock().getLocation()));
        }
	}

    private static Map<String, ChestType> loadChestTypes(){
		Map<String, ItemFactory> items = loadItems();
		FileConfiguration config = RandomChest.chesttypes.getConfig();
		for(String name : config.getKeys(false)){
			ConfigurationSection chestSection = config.getConfigurationSection(name);
			ChestType type = new ChestType(chestSection.getInt("min"), chestSection.getInt("max"), name.toUpperCase());
			for(String itemName : chestSection.getConfigurationSection("items").getKeys(false))
				type.add(items.get(itemName.toUpperCase()), chestSection.getInt("items." + itemName));
			types.put(name.toUpperCase(), type);
		}
		return types;
	}

    private static Map<String, ItemFactory> loadItems(){
		Map<String, ItemFactory> items = new HashMap<>();
		FileConfiguration config = RandomChest.items.getConfig();
		for(String name : config.getKeys(false)){
			ConfigurationSection itemSection = config.getConfigurationSection(name);
			items.put(name.toUpperCase(), new ItemFactory(Material.valueOf(itemSection.getString("material", "DIRT").toUpperCase()))
					.setDisplayName(itemSection.getString("title", null))
					.setAmount(itemSection.getInt("amount", 1))
					.setDurability(itemSection.getInt("meta", 0))
					.setLore(itemSection.getStringList("lore")));
		}
		return items;
	}


    //---------------------------UTIL SHIT----------------------------
    private static Location stringToLoc(String input){
        String[] worldxyz = input.split("\\^");
        for(String s : worldxyz){
            System.out.println(s);
        }
        return new Location(Bukkit.getWorld(UUID.fromString(worldxyz[0])),
                Integer.valueOf(worldxyz[1]),
                Integer.valueOf(worldxyz[2]),
                Integer.valueOf(worldxyz[3]));
    }

}
