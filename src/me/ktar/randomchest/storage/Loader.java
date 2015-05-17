package me.ktar.randomchest.storage;

import me.ktar.randomchest.RandomChest;
import me.ktar.randomchest.items.ChestType;
import me.ktar.randomchest.items.ChestWrapper;
import me.ktar.randomchest.utils.ItemFactory;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loader {

	private List<ChestWrapper> chests = new ArrayList<>(); //Store the chests in a list, they have the location and reference the chest type
	//private List<ChestType> chestTypes = new ArrayList<>(); //Store the types of chests for reference by the chest wrapperrrr..
	private Map<String, String> messages = new HashMap<>();

	public void loadChests(){

	}

	public List<ChestType> loadChestTypes(){

	}

	public Map<String, ItemFactory> loadItems(){
		Map<String, ItemFactory> items = new HashMap<>();
		FileConfiguration config = RandomChest.items.getConfig();
		for(String name : config.getKeys(false)){
			ConfigurationSection itemSection = config.getConfigurationSection(name);
			Material itemMaterial = Material.valueOf(itemSection.getString("material").toUpperCase());
			items.put(name.toUpperCase(), new ItemFactory(itemMaterial)
					.setDisplayName(itemSection.))
		}
	}

}
