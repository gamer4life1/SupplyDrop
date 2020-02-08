package com.leo.supplydrops;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import com.leo.supplydrops.Main;
import java.io.*;

public class GlobalItemGiver extends BukkitRunnable {
	
	private Main plugin;
	public int i = 0;
	private ArrayList<ItemStack> items = new ArrayList<ItemStack>();
	public GlobalItemGiver(Main plugin, ArrayList<ItemStack> items)
	{
		this.plugin = plugin;
		this.items = items;
		
	}
	
	@Override
	public void run() {
		//boolean isActive = false;
//        String cmd;
//        String OS = System.getProperty("os.name");
//        System.out.println(OS);
//        String tmpfolder = System.getProperty("java.io.tmpdir");
//        System.out.println(tmpfolder);
//
//        //iptmp.deleteOnExit();
//
//        String ipAddress = "localhost";
//		if (OS.equals("Linux") || OS.equals("Mac")) {
//            cmd = "ping " + ipAddress  + " -c 1";
//        } else {
//            cmd = "cmd /c ping " + ipAddress + " -n 1";
//        }
//        try {
//            String s = null;
//            Process p = Runtime.getRuntime().exec(cmd);
//            File iptmp = File.createTempFile("ipresult", ".txt", new File(tmpfolder));
//
//
//            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
//
//
//            
//            BufferedWriter writer = null;
//            try {
//                writer = new BufferedWriter(new FileWriter(iptmp));
//                while ((s = stdInput.readLine()) != null) {
//                    System.out.println(s);
//                    s = s.toString();
//                    writer.write(s);
//                }
//
//            } finally {
//                if (writer != null) {
//                    writer.close();
//                }
//            }
//
//
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage().toString());
//        }
		FileWriter fw = new FileWriter("plugins/pluginname/players.txt"); //remove the true
		BufferedWriter bw = new BufferedWriter(fw);
		FileReader freader = new FileReader("plugins/pluginname/players.txt");
		DataReader dreader = new DataReader(freader);
		BufferedReader reader = new BufferedReader(new InputStreamReader(reader));
		 
		ArrayList<String> list = new ArrayList<String>();
		String line = "";
		 
		while(line = reader.readLine() != null)
		{
		list.add(line);
		}
		 
		reader.close();
		 
		if (list.contains(player.getName())
		  list.remove(player.getName());
		 
		for (String s : list)
		{
		  bw.write(s);
		  bw.newLine();
		}
		 
		bw.close();
//		Bukkit.dispatchCommand(null, "/title @a {\"color\": \"red\", \"text\": \"Supply Drop Incoming!\"}");
		Bukkit.broadcastMessage(ChatColor.RED + "Supply Drop Incoming!");
		for(Player current: plugin.getServer().getOnlinePlayers()) {
			PlayerInventory pInv = current.getInventory();
			ItemStack item = items.get(i);
			pInv.addItem(item);
			current.sendMessage("Itemstack is " + item.getType().name().toLowerCase());
			current.sendMessage(ChatColor.GREEN + "Enjoy your supply drop with " + item.getType().name().toLowerCase() + "!");
			current.sendMessage(ChatColor.GOLD + "Next supply drop in " + plugin.intervalMins + " minutes.");
			
		}
		try{
    		
    	    	//create a temp file
    	    	File temp = File.createTempFile("tempfile", ".tmp"); 
    		
    	    	//write it
    	    	BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
    	    	bw.write(i);
    	    	bw.close();
    		
    	    	System.out.println("Done");
				
    	}catch(IOException e){
    		
    	    e.printStackTrace();
    		
    	}
		
	}
	
}
