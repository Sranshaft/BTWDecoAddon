package net.minecraft.src;

import java.util.Random;

public class DecoUtilsCrops 
{
	public static boolean isPlantableSeed(ItemStack itemStack)
	{
		if (itemStack.itemID == Item.seeds.itemID 
				|| itemStack.itemID == Item.pumpkinSeeds.itemID
				|| itemStack.itemID == Item.melonSeeds.itemID
				|| itemStack.itemID == FCBetterThanWolves.fcHempSeeds.itemID)
			return true;
		else
			return false;
	}
	
	public static int getCropIDFromSeedItem(ItemStack itemStack)
	{
		int cropID = itemStack.itemID == Item.seeds.itemID ? Block.crops.blockID
				   : itemStack.itemID == Item.pumpkinSeeds.itemID ? Block.pumpkinStem.blockID
				   : itemStack.itemID == Item.melonSeeds.itemID ? Block.melonStem.blockID
				   : itemStack.itemID == FCBetterThanWolves.fcHempSeeds.itemID ? FCBetterThanWolves.fcHempCrop.blockID
				   : FCBetterThanWolves.fcHempCrop.blockID;
		
		return cropID;
	}
	
	public static boolean isBonemeal(ItemStack itemStack)
	{
		if ((itemStack.itemID == Item.dyePowder.itemID && itemStack.getItemDamage() == 15) 
				|| itemStack.itemID == FCBetterThanWolves.fcPotash.itemID 
				|| itemStack.itemID == DecoSubModuleFlowers.decoItemFertilizerID)
	    	 return true;
		else 
			return false;
	}
	
	public static boolean hasAppliedBonemeal(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int var7, float var8, float var9, float var10)
	{
		if (!isBlockSuitable(world, x, y, z))
			y--;
		
		if (isBlockSuitable(world, x, y, z))
		{
			int blockID = world.getBlockId(x, y, z);
			if (blockID == Block.tilledField.blockID)
			{
				int metadata = world.getBlockMetadata(x, y, z);
				world.setBlockAndMetadataWithNotify(x, y, z, FCBetterThanWolves.fcBlockFarmlandFertilized.blockID, metadata);
			}
			else if (blockID == FCBetterThanWolves.fcPlanter.blockID)
			{
				((FCBlockPlanter) ((FCBlockPlanter) FCBetterThanWolves.fcPlanter)).SetPlanterType(world, x, y, z, 2);
			}
			else if (blockID == Block.grass.blockID)
			{
				if (world.provider.dimensionId == 1)
					return false;
				
				if (!world.isRemote)
					growTallGrassAndFlowers(world, x, y, z);
			}
			
			itemStack.stackSize--;
			
			return true;
		}
		else 
			return false;
	}
	
	private static boolean isBlockSuitable(World world, int x, int y, int z)
	{
		int blockID = world.getBlockId(x, y, z);
		int blockMetadata = world.getBlockMetadata(x, y, z);
		
		return blockID == Block.tilledField.blockID || blockID == FCBetterThanWolves.fcPlanter.blockID && blockMetadata == 1 || blockID == Block.grass.blockID;
	}
	
	private static void growTallGrassAndFlowers(World World, int x, int y, int z)
	{
		int index = 0;
		while (index < 128)
		{
			int newX = x;
			int newY = y + 1;
			int newZ = z;
			int subIndex = 0;
			boolean isGrassBlock = true;
			
			while (true)
			{
				if (subIndex < index / 16)
				{
					newX += Item.itemRand.nextInt(3) - 1;
					newY += (Item.itemRand.nextInt(3) - 1) * Item.itemRand.nextInt(3) / 2;
					newZ += Item.itemRand.nextInt(3) - 1;
					
					if (World.getBlockId(newX, newY - 1, newZ) == Block.grass.blockID && !World.isBlockNormalCube(newX, newY, newZ))
					{
						++subIndex;
						continue;
					}
						isGrassBlock = false;
				}
				
				if (isGrassBlock && World.getBlockId(newX, newY, newZ) == 0)
				{
					if (Item.itemRand.nextInt(100) <= 40 && Block.tallGrass.canBlockStay(World, newX, newY, newZ))
						World.setBlockAndMetadataWithNotify(newX, newY, newZ, Block.tallGrass.blockID, 1);
					else if (Item.itemRand.nextInt(100) <= 50 && DecoModuleWorld.decoBlockFoliage.canBlockStay(World, newX, newY, newZ))
					{
						int tempRandomMetadata = Item.itemRand.nextInt(3) + 1;
						World.setBlockAndMetadataWithNotify(newX, newY, newZ, DecoModuleWorld.decoBlockFoliageID, tempRandomMetadata);
					}
					else if (Item.itemRand.nextInt(100) <= 10 && DecoModuleWorld.decoBlockWildgrass.canBlockStay(World, newX, newY, newZ) && DecoAddonManager.getConfigOption("generateWildgrassAndFoliage"))
						World.setBlockAndMetadataWithNotify(newX, newY, newZ, DecoModuleWorld.decoBlockWildgrass.blockID, 0);
					else if (Item.itemRand.nextInt(100) <= 30 && Block.plantYellow.canBlockStay(World, newX, newY, newZ))
					{
						int rnd = Item.itemRand.nextInt(21);
						switch (rnd)
						{
							case 0:
							case 1:
							case 2:
							case 3:
							case 4:
							case 5:
							case 6:
							case 7:
							case 8:
							case 9:
							case 10:
							case 11:
							case 12:
							case 13:
							case 14:
								World.setBlockAndMetadataWithNotify(newX, newY, newZ, DecoSubModuleFlowers.decoBlockFlowerExtended.blockID, rnd);
								break;
							case 15:
								World.setBlockWithNotify(newX, newY, newZ, Block.plantYellow.blockID);
								break;
							case 16:
								World.setBlockWithNotify(newX, newY, newZ, Block.plantRed.blockID);
								break;
							case 17:
							case 18:
							case 19:
							case 20:
								World.setBlockAndMetadataWithNotify(newX, newY, newZ, DecoSubModuleFlowers.decoBlockFlowerTulip.blockID, rnd - 17);
								break;
						}
					}
				}
				
				++index;
				break;
			}
		}
	}
}
