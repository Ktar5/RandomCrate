package me.ktar.randomchest.storage;

import me.ktar.randomchest.RandomChest;
import me.ktar.randomchest.items.ChestType;
import me.ktar.randomchest.items.ChestWrapper;
import me.ktar.randomchest.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class Loader {

	private final Map<Location, ChestWrapper> chests = new HashMap<>(); //Store the chests in a list, they have the location and reference the chest type
	private final Map<String, String> messages = new HashMap<>();


    public void load(){
        loadChests();
        //loadMessages();
    }

	private void loadChests(){
        Map<String, ChestType> types = loadChestTypes();
        FileConfiguration config = RandomChest.chests.getConfig();
        for(String type : config.getKeys(false)){
            for(String locationString : config.getStringList(type)){
                chests.put(stringToLoc(locationString), new ChestWrapper(types.get(type.toUpperCase())));
            }
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
		Map<String, ChestType> types = new HashMap<>();

		FileConfiguration config = RandomChest.chesttypes.getConfig();
		for(String name : config.getKeys(false)){
			ConfigurationSection chestSection = config.getConfigurationSection(name);
			ChestType type = new ChestType(chestSection.getInt("min"), chestSection.getInt("max"));
			for(String itemName : chestSection.getConfigurationSection("items").getKeys(false)){
				type.add(items.get(itemName.toUpperCase()), chestSection.getInt("items." + itemName));
			}
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


}
