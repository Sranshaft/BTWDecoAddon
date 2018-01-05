package net.minecraft.src;

import java.util.Random;

public class DecoWorldGenMud extends WorldGenerator 
{
	private int m_CoarseDirtChance = 20;
	private int m_DirtChance = 30;
	private int m_PackedEarthChance = 5;
	
	public DecoWorldGenMud() {}

    public boolean generate(World world, Random random, int x, int y, int z)
    {
    	for (int xIndex = x; xIndex < x + 32; xIndex++)
        {
            for (int zIndex = z; zIndex < z + 32; zIndex++)
            {
            	if (world.getBiomeGenForCoords(xIndex, zIndex).biomeName.equals(BiomeGenBase.swampland.biomeName))
            	{
	            	for (int yIndex = 0; yIndex < y + 16; yIndex++)
	            	{
		            	if (world.getBlockId(xIndex, yIndex, zIndex) == Block.sand.blockID)
		            		changeSand(world, xIndex, yIndex, zIndex);
	            	}
            	}
            	
            	if (world.getBiomeGenForCoords(xIndex, zIndex).biomeName.equals(BiomeGenBase.ocean.biomeName))
            	{
	            	for (int yIndex = 0; yIndex < 60; yIndex++)
	            	{
		            	if (world.getBlockId(xIndex, yIndex, zIndex) == Block.sand.blockID)
		            		changeSand(world, xIndex, yIndex, zIndex);
	            	}
            	}
            }
        }

        return true;
    }
    
    private void changeSand(World world, int x, int y, int z)
    {
    	world.setBlock(x, y, z, DecoModuleWorld.decoBlockMudID, 0, 2);
    	
    	if (world.rand.nextInt(100) <= this.m_DirtChance)
    		world.setBlock(x, y, z, Block.dirt.blockID, 0, 2);
    	
    	if (world.rand.nextInt(100) <= this.m_CoarseDirtChance)
    		world.setBlock(x, y, z, Block.dirt.blockID, 1, 2);
    	
    	if (world.rand.nextInt(100) <= this.m_PackedEarthChance)
    		world.setBlock(x, y, z, FCBetterThanWolves.fcBlockAestheticOpaqueEarth.blockID, 6, 2);
    }
    
}