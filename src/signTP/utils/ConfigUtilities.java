package signTP.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import signTP.MainClass;

public class ConfigUtilities extends JavaPlugin {
	static FileConfiguration config = MainClass.fc;
	static JavaPlugin jp;

	public static List<String> getStringList(String title) {
		return config.getStringList(title);
	}

	public static List<Integer> getIntegerList(String title) {
		return config.getIntegerList(title);
	}

	public static ArrayList<Integer> searchString(String title, String search) {
		int index = 0;
		List<String> stringList = config.getStringList(title);
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		for (String str : stringList) {
			if (str.equals(search))
				indexList.add(index);
			index++;
		}
		return indexList;
	}

	public static int getInteger(String title, int index) {
		List<Integer> integerList = config.getIntegerList(title);
		if (index >= integerList.size()) {
			jp.getLogger()
					.warning(
							"!!!INPUT INDEX OUT OF BOUND AT ConfigUtilities.getInteger!!!");
			return -1;
		}
		return integerList.get(index);
	}

	public static ArrayList<Integer> getInteger(String title,
			ArrayList<Integer> indexList) {
		List<Integer> integerList = config.getIntegerList(title);
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		for (int index : indexList) {
			if (index >= integerList.size()) {
				jp.getLogger()
						.warning(
								"!!!INPUT INDEX OUT OF BOUND AT ConfigUtilities.getInteger!!!");
				return new ArrayList<Integer>(); // then size() == 0;
			}
			resultList.add(integerList.get(index));

		}

		return resultList;

	}

	public static String getString(String title, int index) {
		List<String> stringList = config.getStringList(title);
		if (index >= stringList.size()) {
			jp.getLogger()
					.warning(
							"!!!INPUT INDEX OUT OF BOUND AT ConfigUtilities.getString!!!");
			return null;
		}
		return stringList.get(index);
	}

	public static ArrayList<String> getString(String title,
			ArrayList<Integer> indexList) {
		List<String> stringList = config.getStringList(title);
		ArrayList<String> resultList = new ArrayList<String>();
		for (int index : indexList) {
			if (index >= stringList.size()) {
				jp.getLogger()
						.warning(
								"!!!INPUT INDEX OUT OF BOUND AT ConfigUtilities.getString!!!");
				return new ArrayList<String>(); // then size() == 0;
			}
			resultList.add(stringList.get(index));

		}
		return resultList;
	}

	public static ArrayList<Integer> updateIndexList(
			ArrayList<Integer> origIndexList, List<Integer> searchIn,
			int equalsWhat) {
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		int index = 0;
		for (int content : searchIn) {
			if (content == equalsWhat)
				resultList.add(index);
			index++;

		}
		return resultList;

	}

	public static ArrayList<Integer> updateIndexList(
			ArrayList<Integer> origIndexList, List<String> searchIn,
			String equalsWhat) {
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		int index = 0;
		for (String content : searchIn) {
			if (content.equals(equalsWhat))
				resultList.add(index);
			index++;
		}
		return resultList;

	}
}
