package me.ktar.randomchest.storage;

import me.ktar.randomchest.RandomChest;
import me.ktar.randomchest.items.ChestType;
import me.ktar.randomchest.items.ChestWrapper;
import me.ktar.randomchest.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class Loader {

	private static final Map<Location, ChestWrapper> chests = new HashMap<>(); //Store the chests in a list, they have the location and reference the chest type
	private static final Map<String, String> messages = new HashMap<>();
    private static final Map<String, ChestType> types = new HashMap<>();


    public static ChestWrapper getChestWrapper(Block block){
        return chests.get(block.getLocation());
    }

    public static void removeChestWrapper(Location location){
        if(chests.containsKey(location))
            chests.remove(location);
    }

    public static void addChest(Block block, ChestType type){
        chests.put(block.getLocation(), new ChestWrapper(type, block.getLocation()));
    }

    public void load(){
        chests.clear();
        types.clear();
		messages.clear();
		loadChests();
    }

	private void loadChests(){
        Map<String, ChestType> types = loadChestTypes();
        FileConfiguration config = RandomChest.chests.getConfig();
        for(String type : config.getKeys(false)){
            for(String locationString : config.getStringList(type))
                chests.put(stringToLoc(locationString), new ChestWrapper(types.get(type.toUpperCase()), stringToLoc(locationString)));
        }
	}

	private Location stringToLoc(String input){
		String[] worldxyz = input.split(",");
		return new Location(Bukkit.getWorld(worldxyz[0]),
				Double.valueOf(worldxyz[1]),
				Double.valueOf(worldxyz[2]),
				Double.valueOf(worldxyz[3]));
	}

    private Map<String, ChestType> loadChestTypes(){
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

    private Map<String, ItemFactory> loadItems(){
		Map<String, ItemFactory> items = new HashMap<>();
		FileConfiguration config = RandomChest.items.getConfig();
		for(String name : config.getKeys(false)){
			ConfigurationSection itemSection = config.getConfigurationSection(name);
			items.put(name.toUpperCase(), new ItemFactory(Material.valueOf(itemSection.getString("material").toUpperCase()))
					.setDisplayName(itemSection.getString("title"))
					.setAmount(itemSection.getInt("amount"))
					.setDurability(itemSection.getInt("meta"))
					.setLore(itemSection.getStringList("lore")));
		}
		return items;
	}

	public static void unload(){
		for(String string : RandomChest.chests.getConfig().getKeys(false))
			RandomChest.chests.set(string, null);
	}
}
