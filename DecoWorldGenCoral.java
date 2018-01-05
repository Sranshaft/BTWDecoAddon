package net.minecraft.src;

import java.util.Random;

public class DecoWorldGenCoral extends WorldGenerator
{
	int type = 1;
	
	public boolean generate(World world, Random random, int x, int y, int z) 
	{
		int xRadius = random.nextInt(3) + 4;
		int zRadius = random.nextInt(3) + 4;
		
		if ((world.getBlockId(x, y, z) == Block.dirt.blockID || world.getBlockId(x, y, z) == Block.sand.blockID) && world.getBlockMaterial(x, y + 1, z) == Material.water)
		{
			for (int xIndex = -xRadius; xIndex <= xRadius; xIndex++)
			{
				for(int zIndex = -zRadius; zIndex <= zRadius; zIndex++)
				{
					if(random.nextInt(21) != 0 && !(Math.abs(x) == xRadius && Math.abs(z) == zRadius))
					{
						ItemStack block = getStoneType();
						world.setBlock(x + xIndex, y - 1, z + zIndex, block.itemID, block.getItemDamage(), 2);
					}
					else if(world.getBlockId(x + xIndex, y - 1, z + zIndex) == 0)
						world.setBlock(x + xIndex, y - 1, z + zIndex, Block.waterStill.blockID, 0, 2);
				}
			}
			
			for (int xIndex = -(xRadius - 1); xIndex <= xRadius - 1; xIndex++)
			{
				for(int zIndex = -(zRadius - 1); zIndex <= zRadius - 1; zIndex++)
				{
					if(random.nextInt(5) != 0)
					{
						ItemStack block = getStoneType();
						world.setBlock(x + xIndex, y - 2, z + zIndex, block.itemID, block.getItemDamage(), 2);
					}
					else if(world.getBlockId(x + xIndex, y - 2, z + zIndex) != Block.waterStill.blockID)
						world.setBlock(x + xIndex, y - 2, z + zIndex, Block.waterStill.blockID, 0, 2);
				}
			}
			
			for (int xIndex = -xRadius; xIndex <= xRadius; xIndex++)
			{
				for (int zIndex = -zRadius; zIndex <= zRadius; zIndex++)
				{
					if (random.nextInt(3) == 0)
					{
						ItemStack block = getStoneType();
						world.setBlock(x + xIndex, y, z + zIndex, block.itemID, block.getItemDamage(), 2);
					}
					else if (random.nextInt(4) != 0)
					{
						if (Block.blocksList[DecoModuleWorld.decoBlockCoralID].canPlaceBlockAt(world, x + xIndex, y, z + zIndex))
							world.setBlock(x + xIndex, y, z + zIndex, DecoModuleWorld.decoBlockCoralID, 0, 2);
						else if (world.getBlockId(x + xIndex, y, z + zIndex) != Block.waterStill.blockID)
							world.setBlock(x + xIndex, y, z + zIndex, Block.waterStill.blockID, 0, 2);
					}
				}
			}
			
			for (int xIndex = -xRadius; xIndex <= xRadius; xIndex++)
			{
				for (int zIndex = -zRadius; zIndex <= zRadius; zIndex++)
				{
					if (random.nextInt(4) != 0)
					{
						if (Block.blocksList[DecoModuleWorld.decoBlockCoralID].canPlaceBlockAt(world, x + xIndex, y + 1, z + zIndex))
							world.setBlock(x + xIndex, y + 1, z + zIndex, DecoModuleWorld.decoBlockCoralID, 0, 2);
						else if (world.getBlockId(x + xIndex, y + 1, z + zIndex) != Block.waterStill.blockID)
							world.setBlock(x + xIndex, y + 1, z + zIndex, Block.waterStill.blockID, 0, 2);
					}
					else if (world.getBlockId(x + xIndex, y + 1, z + zIndex) != Block.waterStill.blockID)
						world.setBlock(x + xIndex, y + 1, z + zIndex, Block.waterStill.blockID, 0, 2);
				}
			}
			
			for(int xIndex = -(xRadius - 1); xIndex <= xRadius -1; xIndex++)
			{
				for(int zIndex = -(zRadius - 1); zIndex <= zRadius - 1; zIndex++)
				{
					if (random.nextInt(4) == 0)
					{
						if (Block.blocksList[DecoModuleWorld.decoBlockCoralID].canPlaceBlockAt(world, x + xIndex, y + 2, z + zIndex))
							world.setBlock(x + xIndex, y + 2, z + zIndex, DecoModuleWorld.decoBlockCoralID, 0, 2);
						else if (world.getBlockId(x + xIndex, y + 2, z + zIndex) != Block.waterStill.blockID)
							world.setBlock(x + xIndex, y + 2, z + zIndex, Block.waterStill.blockID, 0, 2);
					}
					else if (world.getBlockId(x + xIndex, y + 2, z + zIndex) != Block.waterStill.blockID)
						world.setBlock(x + xIndex, y + 2, z + zIndex, Block.waterStill.blockID, 0, 2);
				}
			}
			
			for(int xIndex = -(xRadius - 1); xIndex <= (xRadius - 1); xIndex++)
			{
				for(int zIndex = -(zRadius - 1); zIndex <= (zRadius - 1); zIndex++)
				{
					if (world.getBlockId(x + xIndex, y + 3, z + zIndex) != Block.waterStill.blockID)
						world.setBlock(x + xIndex, y + 3, z + zIndex, Block.waterStill.blockID, 0, 2);
				}
			}
		}
		return false;
	}
	
	private ItemStack getStoneType()
	{
		switch(type)
		{
			case 1: return this.decideSandstone();
			default: return this.decideStone();
		}
	}
	
	private ItemStack decideStone()
	{
		Random rand = new Random();
		
		if(rand.nextInt(10) == 0)
			return new ItemStack(Block.stone);
		
		if(rand.nextInt(4)==0)
			return new ItemStack(Block.gravel);
		
		if(rand.nextInt(6)==0)
			return new ItemStack(Block.cobblestoneMossy);
		
		return new ItemStack(Block.cobblestone);
	}
	
	private ItemStack decideSandstone()
	{
		Random rand = new Random();
		
		if(rand.nextInt(10) == 0)
			return new ItemStack(Block.sandStone, 1, 2);
		
		if(rand.nextInt(4)==0)
			return new ItemStack(Block.sand);
		
		if(rand.nextInt(6)==0)
			return new ItemStack(Block.sandStone, 1, 1);

		return new ItemStack(Block.sandStone, 1, 0);
	}
}
