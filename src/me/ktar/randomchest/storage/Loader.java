package me.ktar.randomchest.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.ktar.randomchest.items.ChestType;
import me.ktar.randomchest.items.ChestWrapper;

public class Loader {

	private List<ChestWrapper> chests = new ArrayList<>(); //Store the chests in a list, they have the location and reference the chest type
	//private List<ChestType> chestTypes = new ArrayList<>(); //Store the types of chests for reference by the chest wrapperrrr..
	private Map<String, String> messages = new HashMap<>();

}
