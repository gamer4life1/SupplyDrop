package com.leo.supplydrops;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
//import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import com.leo.supplydrops.GlobalItemGiver;

public class Main extends JavaPlugin {
	public int intervalMins;
	public int i = 0;
	private Logger log = Bukkit.getLogger();
	private BukkitTask giver;
//	public int i = 0;
	//private Random RNG;
	private ArrayList<ItemStack> itemsToGive = new ArrayList<ItemStack>();
	@Override
	public void onEnable() {
		itemsToGive.add(new ItemStack(Material.WOOD, 16));
		itemsToGive.add(new ItemStack(Material.GRILLED_PORK, 8));
		itemsToGive.add(new ItemStack(Material.IRON_PICKAXE, 1));
		itemsToGive.add(new ItemStack(Material.LEATHER_LEGGINGS, 1));
		itemsToGive.add(new ItemStack(Material.IRON_INGOT, 8));
		itemsToGive.add(new ItemStack(Material.IRON_CHESTPLATE, 1));
		itemsToGive.add(new ItemStack(Material.DIAMOND_SPADE, 1));
		itemsToGive.add(new ItemStack(Material.IRON_BOOTS, 1));
		itemsToGive.add(new ItemStack(Material.DIAMOND, 3));
		itemsToGive.add(new ItemStack(Material.DIAMOND, 5));
		
		ItemStack testEnchant = itemsToGive.get(7);
        ItemMeta testEnchantMeta = testEnchant.getItemMeta();
        testEnchantMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
        testEnchant.setItemMeta(testEnchantMeta);
        itemsToGive.set(7, testEnchant);
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender,
            Command command,
            String label,
            String[] args) {
        String cname = command.getName();
        if (cname.equalsIgnoreCase("startsupplydrops") || cname.equalsIgnoreCase("startsd")) {
        		if (args.length > 0 && args != null) {
        			intervalMins = Integer.parseInt(args[0]);
        		} else {
        			Bukkit.broadcastMessage(ChatColor.RED + "Usage: /startsupplydrops <interval>");
        			return true;
        		}
//        	supplydrops(intervalMins);
        		giver = new GlobalItemGiver(this, itemsToGive).runTaskTimer(this, 0, intervalMins*1200);
        	
        		return true;
        } else if (cname.equalsIgnoreCase("stopsupplydrops") || cname.equalsIgnoreCase("stopsd")) {
        	if (giver != null) {
        		if (sender instanceof Player) {
        			Player p = (Player)sender;
        			p.sendMessage("You have not called /startsupplydrops!");
        			return true;
        		} else {
        			log.log(Level.WARNING, "Console has not called /startsupplydrops!");
        			return true;
        		}
        	}
        	giver.cancel();
        	GlobalItemGiver g = (GlobalItemGiver) giver;
        	g.i = 0;
        	return true;
        }
        
        return false;
    }
	
//	private void supplydrops(int interval) {
//		for(int i=0;i<itemsToGive.length;i++) {
//			Collection<? extends Player> ps = Bukkit.getOnlinePlayers();
//			for (Iterator<? extends Player> iterator = ps.iterator(); iterator.hasNext();) {
//		        Player p = (Player)iterator.next();
//		    }
//			
//		}
//	}
}
