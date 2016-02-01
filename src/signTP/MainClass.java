package signTP;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import signTP.listeners.SignTPListener;

public class MainClass extends JavaPlugin {
	public static FileConfiguration fc;
	public static boolean coordMode = false;

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		fc = this.getConfig();
		registerListeners();
	}

	@Override
	public void onDisable() {

	}

	public void registerListeners() {

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new SignTPListener(this), this);

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (cmd.getName().equalsIgnoreCase("stp") && sender instanceof Player) {

			Player player = (Player) sender;
			if (args.length == 0)
				return false;
			// receiving parameter reload, reload the plugin
			else if (args[0].equalsIgnoreCase("reload")) {
				this.reloadConfig();
				PluginManager pm = getServer().getPluginManager();
				pm.disablePlugin(this);
				pm.enablePlugin(this);
				player.sendMessage(ChatColor.GREEN + "Reloaded signTP!");
				return true;
			}

			else if (args[0].equalsIgnoreCase("co")) {

				if (!MainClass.coordMode) {
					MainClass.coordMode = true;
					player.sendMessage(ChatColor.GREEN
							+ "Switched to Coordinate mode!");
				} else {
					MainClass.coordMode = false;
					player.sendMessage(ChatColor.GREEN
							+ "Switched to normal mode!");
				}
			}

			return true;

		}

		return false;

	}

}