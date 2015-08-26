package org.walterweight.wastewood;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

import java.util.Random;

public class BlockBreakEventHandler
{

	Random random = new Random();

	@SubscribeEvent
	public void onBlockDropItems(BlockEvent.HarvestDropsEvent event)
	{
		Block block = event.block;
		World world = event.world;
		int x = event.x;
		int y = event.y;
		int z = event.z;

		if (block instanceof BlockGravel)
		{
			if (spawnSticks(WasteWood.chanceOfFindingSticksInGravel, WasteWood.maxSticksFromGravel, world, x, y, z))
				cancelDrop(event);
		}

		if (block instanceof BlockSand)
		{
			if (spawnSticks(WasteWood.chanceOfFindingSticksInSand, WasteWood.maxSticksFromSand, world, x, y, z))
				cancelDrop(event);
		}

		if (block instanceof BlockDirt)
		{
			if (spawnSticks(WasteWood.chanceOfFindingSticksInDirt, WasteWood.maxSticksFromDirt, world, x, y, z))
				cancelDrop(event);
		}

		if (block instanceof BlockGrass)
		{
			if (spawnSticks(WasteWood.chanceOfFindingSticksInGrass, WasteWood.maxSticksFromGrass, world, x, y, z))
				cancelDrop(event);
		}

	}
	private void cancelDrop(BlockEvent.HarvestDropsEvent event)
	{
		if (WasteWood.loseBlockWhenSticksDropped)
			event.dropChance = 0F;
	}

	private boolean spawnSticks(int chanceOfFindingStick, int maxSticks, World world, int x, int y, int z)
	{
		if (random.nextInt(100) >= chanceOfFindingStick)
			return false;

		ItemStack stickStack = new ItemStack(Items.stick, random.nextInt(maxSticks)+1);
		world.spawnEntityInWorld(new EntityItem(world, x, y, z, stickStack));
		return true;
	}
}
