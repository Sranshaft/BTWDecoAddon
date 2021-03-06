package net.minecraft.src;

import java.util.Random;

public class DecoUtilsCrops 
{
	private static final int m_FlowerChance = 30;
	private static final int m_FoliageChance = 40;
	private static final int m_TallGrassChance = 75;
	
	/**
	 * returns true if the block below the crop is suitable for planting
	 */
	public static boolean isBlockSuitable(World world, int x, int y, int z)
	{
		int blockID = world.getBlockId(x, y, z);
		int blockMetadata = world.getBlockMetadata(x, y, z);
		
		return blockID == Block.tilledField.blockID || blockID == FCBetterThanWolves.fcPlanter.blockID && blockMetadata == 1 || blockID == Block.grass.blockID;
	}
	
	/**
	 * returns true if item can be used as bonemeal
	 */
	public static boolean isBonemeal(ItemStack itemStack)
	{
		if (itemStack.itemID == FCBetterThanWolves.fcPotash.itemID 
				|| itemStack.itemID == DecoModuleTweaks.decoItemFertilizer.itemID
				|| (itemStack.itemID == Item.dyePowder.itemID && itemStack.getItemDamage() == 15))
	    	 return true;
		else 
			return false;
	}
	
	/**
	 * returns true if the item is a plantable seed
	 */
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
	
	/**
	 * returns true if there is at least one cropblock nearby (x-1 to x+1, y+1, z-1 to z+1)
	 */
	public static boolean isCropsNearby(World world, int x, int y, int z)
	{
		byte var5 = 0;
	
	    for (int indexX = x - var5; indexX <= x + var5; ++indexX)
	    {
	        for (int indexY = z - var5; indexY <= z + var5; ++indexY)
	        {
	            int blockID = world.getBlockId(indexX, y + 1, indexY);
	
	            if (blockID == Block.crops.blockID || blockID == Block.melonStem.blockID || blockID == Block.pumpkinStem.blockID || blockID == Block.potato.blockID || blockID == Block.carrot.blockID)
	                return true;
	        }
	    }
	
	    return false;
	}
	
	/**
	 * returns true if there's water nearby (x-4 to x+4, y to y+1, k-4 to k+4)
	 */
	public static boolean isWaterNearby(World world, int x, int y, int z)
	{
		for (int indexX = x - 4; indexX <= x + 4; indexX++)
	    {
	        for (int indexY = y; indexY <= y + 1; indexY++)
	        {
	            for (int indexZ = z - 4; indexZ <= z + 4; indexZ++)
	            {
	                if (world.getBlockMaterial(indexX, indexY, indexZ) == Material.water)
	                    return true;
	            }
	        }
	    }
	
	    return false;
	}
	
	/**
	 * returns the crop ID from the supplied seed item's id
	 */
	public static int getCropIDFromSeedItem(ItemStack itemStack)
	{
		int cropID = itemStack.itemID == Item.seeds.itemID ? Block.crops.blockID
				   : itemStack.itemID == Item.pumpkinSeeds.itemID ? Block.pumpkinStem.blockID
				   : itemStack.itemID == Item.melonSeeds.itemID ? Block.melonStem.blockID
				   : itemStack.itemID == FCBetterThanWolves.fcHempSeeds.itemID ? FCBetterThanWolves.fcHempCrop.blockID
				   : FCBetterThanWolves.fcHempCrop.blockID;
		
		return cropID;
	}
	
	/**
	 * returns true if bonemeal has been applied to the block below the crop
	 */
	public static boolean hasAppliedBonemeal(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int var7, float var8, float var9, float var10)
	{
		int blockID = world.getBlockId(x, y, z);
		
		if (blockID == Block.tallGrass.blockID)
		{
			int metadata = world.getBlockMetadata(x, y, z);
			
			if (metadata == 1)
			{
				world.setBlockAndMetadataWithNotify(x, y, z, DecoModuleWorld.decoBlockFoliageID, 5);
				world.setBlockAndMetadataWithNotify(x, y + 1, z, DecoModuleWorld.decoBlockFoliageID, 6);
			}
			else if (metadata == 2)
			{
				world.setBlockAndMetadataWithNotify(x, y, z, DecoModuleWorld.decoBlockFoliageID, 7);
				world.setBlockAndMetadataWithNotify(x, y + 1, z, DecoModuleWorld.decoBlockFoliageID, 8);
			}
			
			return true;
		}
		
		if (!isBlockSuitable(world, x, y, z))
			y--;
		
		if (isBlockSuitable(world, x, y, z))
		{
			blockID = world.getBlockId(x, y, z);
			
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
	
	public static void growTallGrassAndFlowers(World World, int x, int y, int z)
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
					if (Item.itemRand.nextInt(100) <= m_FlowerChance && Block.plantYellow.canBlockStay(World, newX, newY, newZ))
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
								World.setBlockAndMetadataWithNotify(newX, newY, newZ, DecoSubModuleFlower.decoBlockFlowerExtended.blockID, rnd);
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
								World.setBlockAndMetadataWithNotify(newX, newY, newZ, DecoSubModuleFlower.decoBlockFlowerTulip.blockID, rnd - 17);
								break;
						}
					}
					else if (Item.itemRand.nextInt(100) <= m_TallGrassChance && Block.tallGrass.canBlockStay(World, newX, newY, newZ))
					{
						World.setBlockAndMetadataWithNotify(newX, newY, newZ, Block.tallGrass.blockID, 1);
						break;
					}
					else if (Item.itemRand.nextInt(100) <= m_FoliageChance && DecoModuleWorld.decoBlockFoliage.canBlockStay(World, newX, newY, newZ))
					{
						int tempRandomMetadata = Item.itemRand.nextInt(3) + 1;
						World.setBlockAndMetadataWithNotify(newX, newY, newZ, DecoModuleWorld.decoBlockFoliageID, tempRandomMetadata);
					}
				}
				
				++index;
				break;
			}
		}
	}
}
