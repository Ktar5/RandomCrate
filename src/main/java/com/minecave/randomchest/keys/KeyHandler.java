package com.minecave.randomchest.keys;

import com.minecave.randomchest.items.ChestType;
import com.minecave.randomchest.utils.ItemFactory;
import com.minecave.randomchest.utils.InventoryUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Created by Carter on 5/19/2015.
 */
public class KeyHandler {

    public static final UUID ID = UUID.fromString("b1a9409d-f668-4443-ab56-9c79b538f397");
    public static final ItemStack key = new ItemFactory(Material.TRIPWIRE_HOOK).setAmount(1).setDisplayName("Key To Your Chest").getItemStack();

    public static boolean giveKey(Player player, ChestType type){
        if(InventoryUtil.howManyFreeSpaces(player) > 0){
            AttributeStorage storage = AttributeStorage.newTarget(key.clone(), ID);
            storage.setData(type.getName());
            player.getInventory().addItem(storage.getTarget());
            return true;
        }
        return false;
    }

    public static boolean isKey(ItemStack stack, ChestType type){
        if(stack != null){
            AttributeStorage storage = AttributeStorage.newTarget(stack, ID);
            String name = storage.getData(null);
            if(name != null) {
                return name.equalsIgnoreCase(type.getName());
            }
        }
        return false;
    }

}
