package me.xxastaspastaxx.dimensions.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

public class DimensionsSettings {
	
	private static File settings;
	private static YamlConfiguration portalSettings;
	
	private static int debugLevel;
	private static boolean generateWorlds;
	private static int maxRadius;
	private static World defaultWorld;
	private static boolean enableParticles;
	private static boolean enableMobs;
	private static boolean enableEntities;
	private static int teleportDelay;
	private static int searchRadius;
	private static int spotSearchRadius;
	private static boolean consumeItems;
	private static boolean netherPortalEffect;
	private static boolean showPortalContent;
	private static HashMap<String, ArrayList<String>> pathRules = new HashMap<String, ArrayList<String>>();
	private static DimensionsMode dimensionsMode;
	private static long unlinkAfter;
	private static ReplacePortalRuins replacePortalRuins;
	private static float replacePortalRuinsAt = 0.9f;
	
	public static boolean reloadSettings() {
		
		settings = new File("plugins/Dimensions/Settings.yml");
		portalSettings = YamlConfiguration.loadConfiguration(settings);

  	  	debugLevel = portalSettings.getInt("Debug.Level");
  	  	generateWorlds = portalSettings.getBoolean("GenerateNewWorlds");
  	  	maxRadius = portalSettings.getInt("MaxRadius");
  	  	defaultWorld = Bukkit.getWorld(portalSettings.getString("DefaultWorld"));
  	  	enableParticles = portalSettings.getBoolean("EnableParticles");
  	  	enableMobs = portalSettings.getBoolean("EnableMobsTeleportation");
  	  	enableEntities = portalSettings.getBoolean("EnableNonLivingEntitiesTeleportation");
  	  	teleportDelay = portalSettings.getInt("TeleportDelay");
  	  	searchRadius = portalSettings.getInt("SearchRadius");
  	  	spotSearchRadius = portalSettings.getInt("SafeSpotSearchRadius");
  	  	consumeItems = portalSettings.getBoolean("ConsumeItems");
  	  	netherPortalEffect = portalSettings.getBoolean("NetherPortalEffect");
  	  	showPortalContent = portalSettings.getBoolean("ShowPortalContent");
  	  	
  	  	String[] rules = {"forceReturnWorld", "ignoreIrrelevantWorld"};
  	  	for (String str : rules) {
  	  		pathRules.put(str, new ArrayList<String>());
  	  	}
  	  	
  	  	for (String str : portalSettings.getStringList("PathRules")) {
  	  		if (!str.contains(";")) continue;
  	  		String[] spl = str.split(";");
  	  		if (!pathRules.containsKey(spl[0])) continue;
  	  		pathRules.get(spl[0]).add(spl[1]);
  	  	}
  	  	
  	  	dimensionsMode = DimensionsMode.valueOf(portalSettings.getString("DimensionsMode").toUpperCase());
  	  	unlinkAfter = portalSettings.getLong("UnlinkPortalsAfter");
  	  	
  	  	if (DimensionsUtils.isFloat(portalSettings.getString("ReplacePortalRuins"))) {
  	  		replacePortalRuinsAt = Float.parseFloat(portalSettings.getString("ReplacePortalRuins"));
  	  		if (replacePortalRuinsAt==1)
  	  			replacePortalRuins = ReplacePortalRuins.FULL;
  	  		else 
  	  	  		replacePortalRuins = ReplacePortalRuins.ANY;
  	  	} else {
  	  	  	replacePortalRuins = ReplacePortalRuins.valueOf(portalSettings.getString("ReplacePortalRuins").toUpperCase());
  	  	}
		
		return true;
	}

	public static YamlConfiguration getConf() {
		return portalSettings;
	}
	
	public static int getDebugLevel() {
		return debugLevel;
	}

	public static boolean isGenerateWorlds() {
		return generateWorlds;
	}

	public static int getMaxRadius() {
		return maxRadius;
	}

	public static World getDefaultWorld() {
		return defaultWorld;
	}

	public static boolean isEnableParticles() {
		return enableParticles;
	}

	public static boolean enableMobsTeleportation() {
		return enableMobs;
	}

	public static boolean enableEntitiesTeleportation() {
		return  enableMobs && enableEntities;
	}

	public static int getTeleportDelay() {
		return teleportDelay;
	}

	public static int getSearchRadius() {
		return searchRadius;
	}

	public static int getSpotSearchRadius() {
		return spotSearchRadius;
	}

	public static boolean consumeItems() {
		return consumeItems;
	}

	public static boolean enableNetherPortalEffect() {
		return netherPortalEffect;
	}
	
	public static boolean showPortalContent() {
		return showPortalContent;
	}
	
	public static HashMap<String, ArrayList<String>> getPathRules() {
		return pathRules;
	}
	
	public static DimensionsMode getDimensionsMode() {
		return dimensionsMode;
	}
	
	public static long unlinkAfter() {
		return unlinkAfter;
	}
	
	public static ReplacePortalRuins replacePortalRuinsWhen() {
		return replacePortalRuins;
	}
	
	public static float replacePortalRuinsAt() {
		return replacePortalRuinsAt;
	}
	
}
