package com.minecave.randomchest.utils;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.block.CraftBlock;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

public class ChestUtil {

	public static void changeChestState(Location loc, boolean open, Player p) {
        try {
            //ClassLoader loader = NbtFactory.class.getClassLoader();
            //String packageName = NbtFactory.getPackageName();
            //Class<?> craftPlayer = loader.loadClass(packageName + ".entity.CraftPlayer");

            Method method = CraftBlock.class.getDeclaredMethod("getNMSBlock");
            method.setAccessible(true);
            Block block = (Block) method.invoke(loc.getBlock());
            //((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutBlockAction(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), block, 1, 54));
        } catch (Exception ec) {
            ec.printStackTrace();
        }
		//p.playNote(loc, Instrument.BASS_DRUM, new Note(open ? 1 : 0));
	}

}
