package net.minecraft.src;

import java.util.Random;

public class DecoWorldGenWildgrass extends WorldGenerator
{
    /** Stores ID for WorldGenTallGrass */
    private int m_WildgrassID;
    private int m_FoliageID;
    
    private static final int m_WildGrassChance = 30;
    private static final int m_ShortGrassChance = 10;
    private static final int m_MediumGrassChance = 10;
    private static final int m_TallGrassChance = 10;
    private static final int m_PoisonIvyChance = 5;
    private static final int m_BushChance = 30;

    public DecoWorldGenWildgrass()
    {
        this.m_WildgrassID = DecoModuleWorld.decoBlockWildgrassID;
        this.m_FoliageID = DecoModuleWorld.decoBlockFoliageID;
    }

    public boolean generate(World world, Random rnd, int x, int y, int z)
    {
        int var11;

        for (boolean var6 = false; ((var11 = world.getBlockId(x, y, z)) == 0 || var11 == Block.leaves.blockID) && y > 0; --y)
        {
            ;
        }

        for (int index = 0; index < 128; ++index)
        {
            int xPos = x + rnd.nextInt(8) - rnd.nextInt(8);
            int yPos = y + rnd.nextInt(4) - rnd.nextInt(4);
            int zPos = z + rnd.nextInt(8) - rnd.nextInt(8);
            
            addFoliageToGround(world, xPos, yPos, zPos);
        }

        return false;
    }
    
    private void addFoliageToGround(World world, int x, int y, int z)
    {
    	if (world.rand.nextInt(100) <= this.m_WildGrassChance)
    	{
    		if (world.isAirBlock(x, y, z) && Block.blocksList[this.m_WildgrassID].canPlaceBlockAt(world, x, y, z)) 
    			world.setBlock(x, y, z, this.m_WildgrassID, 0, 2);
    		
    		return;
    	}
    	
    	if (world.rand.nextInt(100) <= this.m_ShortGrassChance)
    	{
    		if (world.isAirBlock(x, y, z) && Block.blocksList[this.m_FoliageID].canPlaceBlockAt(world, x, y, z)) 
    			world.setBlock(x, y, z, this.m_FoliageID, 1, 2);
    		
    		return;
    	}
    	
    	if (world.rand.nextInt(100) <= this.m_MediumGrassChance)
    	{
    		if (world.isAirBlock(x, y, z) && Block.blocksList[this.m_FoliageID].canPlaceBlockAt(world, x, y, z)) 
    			world.setBlock(x, y, z, this.m_FoliageID, 2, 2);
    		
    		return;
    	}
    	
    	if (world.rand.nextInt(100) <= this.m_BushChance && (world.getBiomeGenForCoords(x, z).biomeName.equals(BiomeGenBase.forest.biomeName) 
    													  || world.getBiomeGenForCoords(x, z).biomeName.equals(BiomeGenBase.forestHills.biomeName)
    													  || world.getBiomeGenForCoords(x, z).biomeName.equals(BiomeGenBase.taiga.biomeName)
    													  || world.getBiomeGenForCoords(x, z).biomeName.equals(BiomeGenBase.taigaHills.biomeName)))
		{
			if (world.isAirBlock(x, y, z) && Block.blocksList[this.m_FoliageID].canPlaceBlockAt(world, x, y, z)) world.setBlock(x, y, z, this.m_FoliageID, 3, 2);
			return;
		}
		
		if (world.rand.nextInt(100) <= this.m_PoisonIvyChance && (world.getBiomeGenForCoords(x, z).biomeName.equals(BiomeGenBase.forest.biomeName) 
														  	   || world.getBiomeGenForCoords(x, z).biomeName.equals(BiomeGenBase.forestHills.biomeName)))
		{
			if (world.isAirBlock(x, y, z) && Block.blocksList[this.m_FoliageID].canPlaceBlockAt(world, x, y, z)) world.setBlock(x, y, z, this.m_FoliageID, 4, 2);
			return;
		}
    	
    	if (world.rand.nextInt(100) <= this.m_TallGrassChance)
    	{
    		if (world.isAirBlock(x, y, z) && Block.blocksList[this.m_FoliageID].canPlaceBlockAt(world, x, y, z))
    		{
    			world.setBlock(x, y, z, this.m_FoliageID, 5, 2);
    			world.setBlock(x, y + 1, z, this.m_FoliageID, 6, 2);
    		}
    		
    		return;
    	}
    }
}
