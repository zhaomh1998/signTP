package signTP.handlers;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class TPHandler {
	public static boolean tpRandom(Player p, int xRange, int yValue, int zRange) {
		Random rand = new Random();
		Location to = new Location(p.getWorld(), p.getLocation().getX()
				- xRange + rand.nextInt(2 * xRange), yValue, p.getLocation()
				.getZ() - zRange + rand.nextInt(2 * zRange));
		try {
			p.teleport(to);
			return true;
		} catch (Exception e) {
			p.sendMessage(ChatColor.RED
					+ ("Error Occured! Please contact OP for support!"));
			e.printStackTrace();
			return false;
		}
	}

	public static boolean tpToWorldSpawn(Player p, String worldName) {
		World toGo = p.getServer().getWorld(worldName);

		Location tp = new Location(toGo, toGo.getSpawnLocation().getX(), toGo
				.getSpawnLocation().getY(), toGo.getSpawnLocation().getZ());
		try {
			p.teleport(tp);
			return true;
		} catch (Exception e) {
			p.sendMessage(ChatColor.RED
					+ ("Error Occured! Please contact OP for support!"));
			e.printStackTrace();
			return false;
		}
	}

	public static boolean tp(Player p, String worldName, double x, double y,
			double z) {
		World w = p.getServer().getWorld(worldName);
		Location tp = new Location(w, x, y, z);
		try {
			p.teleport(tp);
			return true;
		} catch (Exception e) {
			p.sendMessage(ChatColor.RED
					+ ("Error Occured! Please contact OP for support!"));
			e.printStackTrace();
			return false;
		}

	}
}
