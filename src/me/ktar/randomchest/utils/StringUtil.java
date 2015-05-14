package me.ktar.randomchest.utils;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;

public class StringUtil {

	/**
	 * Quickly color a string
	 */
	public static String colorString(String input) {
		return ChatColor.translateAlternateColorCodes('&', input);
	}

	/**
	 * Quickly remove color from a string
	 */
	public static String removeColor(String input) {
		return ChatColor.stripColor(input);
	}

	/**
	 * Color a list..
	 */
	public static List<String> colorList(List<String> inputs) {
		inputs.stream().forEachOrdered(StringUtil::colorString);
		return inputs;
	}

	/**
	 * Color us some strings matey!
	 */
	public static String[] colorArray(String[] inputs) {
		Arrays.stream(inputs).forEachOrdered(StringUtil::colorString);
		return inputs;
	}

	/**
	 * Limits a string to an upper bound for its length
	 *
	 * @param string The string to limit
	 * @param limit The upper bound value length (char count)
	 * @return Returns the chopped up string if it goes over the limite, otherwise returns string
	 */
	public static String limitString(String string, int limit) {
		return string.substring(0, Math.min(string.length(), limit));
	}
}
