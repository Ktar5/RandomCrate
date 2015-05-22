package me.ktar.randomchest.listeners;

import me.ktar.randomchest.items.ChestType;
import me.ktar.randomchest.items.ChestWrapper;
import me.ktar.randomchest.keys.KeyHandler;
import me.ktar.randomchest.storage.Loader;
import me.ktar.randomchest.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * Created by Carter on 5/19/2015.
 */
public class CommandListener implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("rc")) {
                Player player = (Player) sender;
                switch (args.length) {
                    case 0:
                        if (!player.hasPermission("randomchest.help")) {
                            player.sendMessage("You don't have permission for this!");
                            return false;
                        }
                        displayHelp(player);
                        break;
                    case 1:
                        switch (args[0].toLowerCase()) {
                            case "delete":
                            case "d":
                                if (!player.hasPermission("randomchest.delete")) {
                                    player.sendMessage("You don't have permission for this!");
                                    return false;
                                }
                                if (!deleteChest(player))
                                    player.sendMessage("You cannot delete this, because it isn't a randomchest :(");
                                else
                                    player.sendMessage("..deleted");
                                break;
                            case "fuck":
                            case "force":
                            case "use":
                                if (!player.hasPermission("randomchest.force")) {
                                    player.sendMessage("You don't have permission for this!");
                                    return false;
                                }
                                if (!forceUseChestKey(player))
                                    player.sendMessage("You cannot force use that chest/block :(");
                                else
                                    player.sendMessage("..used");
                                break;
                            case "reload":
                            case "r":
                                if (!player.hasPermission("randomchest.reload")) {
                                    player.sendMessage("You don't have permission for this!");
                                    return false;
                                }
                                Loader.load();
                                player.sendMessage("Successfully reloaded");
                                break;
                            default:
                                player.sendMessage("That command does not exist :(");
                        }
                        break;
                    case 2:
                        switch (args[0].toLowerCase()) {
                            case "create":
                            case "c":
                                if (!player.hasPermission("randomchest.create")) {
                                    player.sendMessage("You don't have permission for this!");
                                    return false;
                                }
                                if (Loader.getChestType(args[1].toUpperCase()) != null)
                                    if (!createChest(player, Loader.getChestType(args[1].toUpperCase())))
                                        player.sendMessage("That isn't a chest!! Silly goose.");
                                    else
                                        player.sendMessage("..created");
                                break;
                            default:
                                player.sendMessage("That command does not exist :(");
                                break;
                        }
                        break;
                    case 3:
                        switch (args[0].toLowerCase()) {
                            case "give":
                            case "g":
                                if (!player.hasPermission("randomchest.give")) {
                                    player.sendMessage("You don't have permission for this!");
                                    return false;
                                }
                                if (Loader.getChestType(args[2].toUpperCase()) != null) {
                                    Player given = Bukkit.getServer().getPlayer(args[1]);
                                    player.sendMessage("Giving..");
                                    if (given == null) {
                                        player.sendMessage("Player isn't online :(");
                                        return false;
                                    } else if (!KeyHandler.giveKey(given, Loader.getChestType(args[2])))
                                        player.sendMessage("something.. went... wrong? :(");
                                    else
                                        player.sendMessage("..given");
                                } else {
                                    player.sendMessage("That type of chest doesn't exist :(");
                                }
                                break;
                            default:
                                player.sendMessage("That command does not exist :(");
                                break;
                        }
                    default:
                        player.sendMessage("Too many arguments :(");
                        break;
                }
            }
        }
        return false;
    }

    public void displayHelp(Player player) {
        player.sendMessage(StringUtil.colorArray(new String[]{
                "",
                "",
                "=-=-=-=-=-=-[Random Chest]=-=-=-=-=-=-",
                "",
                "&7/rc &3(&fcreate &7| &fc&3) &3{type} &7- turns a chest you're looking at into a chest of type {type}",
                "&7/rc &3(&fdelete &7| &fd&3) &7- removes the randomchest that you're looking at, if any",
                "&7/rc &3(&ffuck &7| &fforce &7| &fuse&3) &7- (ForceUseChestKey) forces a chest to operate as if you had the key, force use",
                "&7/rc &3(&fgive &7| &fg&3) &3{player} {type} &7- give {player} a key of type {type}",
                "&7/rc &3(&freload &7| &fr&3) &7- reloads from the config",
                "",
                "randomchest.create | delete | force | reload | give | help - permission nodes &r",
                "",
                "=-=-=-=-=-=-[Random Chest]=-=-=-=-=-=-"
        }));
    }

    public boolean createChest(Player player, ChestType type){
        if(isLookingAtChest(player)){
            player.sendMessage("Creating..");
            Loader.addChest(getLookingAt(player), type);
            return true;
        }
        return false;
    }

    public boolean deleteChest(Player player){
        ChestWrapper wrapper = isLookingAtRandomChest(player);
        if(wrapper != null){
            player.sendMessage("Deleting..");
            Loader.removeChestWrapper(wrapper.getLocation());
            return true;
        }
        return false;
    }

    public boolean forceUseChestKey(Player player){
        ChestWrapper wrapper = isLookingAtRandomChest(player);
        if(wrapper != null){
            player.sendMessage("Using..");
            wrapper.forceStopUse();
            wrapper.use(player);
            return true;
        }
        return false;
    }

    public ChestWrapper isLookingAtRandomChest(Player player){
        if(Loader.getChestWrapper(getLookingAt(player)) != null) {
            return Loader.getChestWrapper(getLookingAt(player));
        }else{
            return null;
        }
    }

    public boolean isLookingAtChest(Player player){
        return getLookingAt(player).getType().equals(Material.CHEST);
    }

    public Block getLookingAt(Player player){
        return player.getTargetBlock((Set<Material>) null, 10);
    }

}
