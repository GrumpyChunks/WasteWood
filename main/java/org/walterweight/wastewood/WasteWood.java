package org.walterweight.wastewood;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import org.walterweight.wastewood.proxies.Common;

import java.util.MissingFormatArgumentException;


@Mod(modid = WasteWood.MODID, name = WasteWood.NAME, version = WasteWood.VERSION)
public class WasteWood
{
	public static final String NAME = "WasteWood";
	public static final String MODID = "wastewood";
	public static final String VERSION = "0.1.0";
	public BlockBreakEventHandler blockBreakEventHandler = new BlockBreakEventHandler();
	public static int chanceOfFindingSticksInGravel;
	public static int chanceOfFindingSticksInDirt;
	public static int chanceOfFindingSticksInGrass;
	public static int chanceOfFindingSticksInSand;
	public static boolean loseBlockWhenSticksDropped;
	public static int maxSticksFromGravel;
	public static int maxSticksFromDirt;
	public static int maxSticksFromSand;
	public static int maxSticksFromGrass;

	@Mod.Instance("wastewood")
	public static WasteWood instance;

	@SidedProxy(clientSide="org.walterweight.wastewood.proxies.Client", serverSide="org.walterweight.wastewood.proxies.Common")
	public static Common proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();

		chanceOfFindingSticksInGravel = config.getInt("chanceOfFindingSticksInGravel", Configuration.CATEGORY_GENERAL,
				12, 0, 100, "The likelihood of finding sticks in gravel as a percentage");
		chanceOfFindingSticksInSand = config.getInt("chanceOfFindingSticksInSand", Configuration.CATEGORY_GENERAL,
				10, 0, 100, "The likelihood of finding sticks in sand as a percentage");
		chanceOfFindingSticksInDirt = config.getInt("chanceOfFindingSticksInDirt", Configuration.CATEGORY_GENERAL,
				8, 0, 100, "The likelihood of finding sticks in dirt as a percentage");
		chanceOfFindingSticksInGrass = config.getInt("chanceOfFindingSticksInGrass", Configuration.CATEGORY_GENERAL,
				3, 0, 100, "The likelihood of finding sticks in grass as a percentage");
		loseBlockWhenSticksDropped = config.getBoolean("loseBlockWhenSticksDropped", Configuration.CATEGORY_GENERAL,
				true, "If true, the block will be lost if it drops sticks");
		maxSticksFromDirt = config.getInt("maxSticksFromDirt", Configuration.CATEGORY_GENERAL,
				1, 0, 3, "The maximum number of sticks you can expect to drop when breaking a dirt block");
		maxSticksFromGravel = config.getInt("maxSticksFromGravel", Configuration.CATEGORY_GENERAL,
				1, 0, 3, "The maximum number of sticks you can expect to drop when breaking a gravel block");
		maxSticksFromSand = config.getInt("maxSticksFromSand", Configuration.CATEGORY_GENERAL,
				1, 0, 3, "The maximum number of sticks you can expect to drop when breaking a sand block");
		maxSticksFromGrass = config.getInt("maxSticksFromGrass", Configuration.CATEGORY_GENERAL,
				1, 0, 3, "The maximum number of sticks you can expect to drop when breaking a grass block");

		config.save();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(blockBreakEventHandler);
	}

}
