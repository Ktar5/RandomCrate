package me.ktar.randomchest.storage;

import me.ktar.randomchest.RandomChest;
import me.ktar.randomchest.items.ChestType;
import me.ktar.randomchest.items.ChestWrapper;
import me.ktar.randomchest.utils.ItemFactory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loader {

	private final Map<Location, ChestWrapper> chests = new HashMap<>(); //Store the chests in a list, they have the location and reference the chest type
	private final Map<String, String> messages = new HashMap<>();

	public void loadChests(){

	}

	public Map<String, ChestType> loadChestTypes(){
		Map<String, ItemFactory> items = loadItems();
		Map<String, ChestType> types = new HashMap<>();

		FileConfiguration config = RandomChest.chesttypes.getConfig();
		for(String name : config.getKeys(false)){
			ConfigurationSection chestSection = config.getConfigurationSection(name);

			for(String itemName : chestSection.getConfigurationSection().getKeys(false)){
				ChestTypenew ChestType();
			}
		}
	}

	public Map<String, ItemFactory> loadItems(){
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
