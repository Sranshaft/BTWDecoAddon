package net.minecraft.src;

import java.util.Random;

public class DecoWorldGenBoulder extends WorldGenerator
{
	private final int m_StoneChance = 85;
	private final int m_CobbleChance = 20;
	private final int m_MossyChance = 10;
	
	private final int m_GravelChance = 10;
	private final int m_CoarseDirtChance = 50;
	private final int m_PackedEarth = 50;
	
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		/** GENERATES SMALL ROCKS */
		for (int index = 0; index < 32; index++)
		{
			int xRandom = x + (random.nextInt(8) - random.nextInt(8));
			int yRandom = y + (random.nextInt(4) - random.nextInt(4));
			int zRandom = z + (random.nextInt(8) - random.nextInt(8));
			
			if (world.getBlockId(xRandom, yRandom, zRandom) == Block.grass.blockID)
				changeSurroundingGrass(world, xRandom, yRandom, zRandom);
			
			if (world.isBlockNormalCube(xRandom, yRandom - 1, zRandom))
				addStoneToBoulder(world, xRandom, yRandom, zRandom);
		}
		
		return true;
	}
	
	private void changeSurroundingGrass(World world, int x, int y, int z)
	{
		if (world.rand.nextInt(100) <= this.m_CoarseDirtChance)
			world.setBlock(x, y, z, Block.dirt.blockID, 1, 2);
		
		if (world.rand.nextInt(100) <= this.m_GravelChance)
			world.setBlock(x, y, z, Block.gravel.blockID, 0, 2);
		
		if (world.rand.nextInt(100) <= this.m_PackedEarth)
			world.setBlock(x, y, z, FCBetterThanWolves.fcBlockAestheticOpaqueEarth.blockID, 6, 2);
	}
	
	private void addStoneToBoulder(World world, int x, int y, int z)
	{
		if (world.rand.nextInt(100) <= this.m_StoneChance)
			world.setBlock(x, y, z, Block.stone.blockID, 0, 2);
		
		if (world.rand.nextInt(100) <= this.m_CobbleChance)
			world.setBlock(x, y, z, Block.cobblestone.blockID, 0, 2);
		
		if (world.rand.nextInt(100) <= this.m_MossyChance)
			world.setBlock(x, y, z, Block.cobblestoneMossy.blockID, 0, 2);
	}
}