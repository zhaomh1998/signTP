package signTP.listeners;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

import signTP.MainClass;
import signTP.handlers.TPHandler;
import signTP.utils.ConfigUtilities;

public class SignTPListener implements Listener {
	public SignTPListener(MainClass plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	
	
	@EventHandler
	public void onClickBlockEvent(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Block block = e.getClickedBlock();
		if (e.hasBlock()) {
			if (MainClass.coordMode){
				String world = block.getWorld().getName();
			
			String type = block.getType().toString();
			int x = block.getX();
			int y = block.getY();
			int z = block.getZ();
			player.sendMessage("You just clicked a " + type + " block at X="
					+ x + " Y=" + y + " Z=" + z + " in " + world);
			}
			else if (signTP(e)) {
				player.sendMessage(ChatColor.GREEN + "Successfully teleported!");
			}

		}
	}

	public boolean signTP(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		ArrayList<Integer> approvedWorldIndexList = ConfigUtilities
				.searchString("worldName", player.getWorld().getName());
		// list(The
		// list
		// contains
		// all
		// index
		// of
		// world
		// that
		// is
		// found
		// to
		// be
		// the
		// same)
		if (approvedWorldIndexList.size() == 0) {
			return false;
		}
		ArrayList<Integer> approvedXIndexList = ConfigUtilities
				.updateIndexList(approvedWorldIndexList,
						ConfigUtilities.getIntegerList("signX"), block.getX());
		if (approvedXIndexList.size() == 0)
			return false;

		ArrayList<Integer> approvedYIndexList = ConfigUtilities
				.updateIndexList(approvedXIndexList,
						ConfigUtilities.getIntegerList("signY"), block.getY());
		if (approvedYIndexList.size() == 0)
			return false;

		ArrayList<Integer> approvedZIndexList = ConfigUtilities
				.updateIndexList(approvedYIndexList,
						ConfigUtilities.getIntegerList("signZ"), block.getZ());
		if (approvedZIndexList.size() != 1)
			return false; // Not found or paired coordinate more than one
		int signIndex = approvedZIndexList.get(0);
		// player.sendMessage("SignIndex : " + signIndex);
		// player.sendMessage(" "+ ConfigUtilities.getInteger("tpX", signIndex)
		// );
		// Debug use--------^^^^^^^
		if (ConfigUtilities.getInteger("tpY", signIndex) < 1)
			TPHandler.tpToWorldSpawn(player,
					ConfigUtilities.getString("tpWorldName", signIndex));
		else {
			TPHandler.tp(event.getPlayer(),
					ConfigUtilities.getString("tpWorld", signIndex),
					ConfigUtilities.getInteger("tpX", signIndex) * 1.0,
					ConfigUtilities.getInteger("tpY", signIndex) * 1.0,
					ConfigUtilities.getInteger("tpZ", signIndex) * 1.0);
		}
		return true;

	}
}
