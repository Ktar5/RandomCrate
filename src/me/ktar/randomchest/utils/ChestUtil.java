package me.ktar.randomchest.utils;

import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.entity.Player;

public class ChestUtil {

	public static void changeChestState(Location loc, boolean open, Player p) {
		p.playNote(loc, Instrument.BASS_DRUM, new Note(open ? 1 : 0));
	}

}
