package net.minecraft.src;

import java.util.Random;

public class DecoWorldGenWaterPlant extends WorldGenerator
{
    /** Stores ID for WorldGenTallGrass */
    private int m_waterPlantID;

    public DecoWorldGenWaterPlant(int id)
    {
        this.m_waterPlantID = id;
    }

    public boolean generate(World world, Random random, int x, int y, int z)
    {
		if (world.getBlockMaterial(x, y, z) != Material.water)
			return false;
		else
		{
			for (int index = 0; index < 10; index++)
		    {
		        int randomXPos = x + random.nextInt(8) - random.nextInt(8);
		        int randomYPos = y + random.nextInt(4) - random.nextInt(4);
		        int randomZPos = z + random.nextInt(8) - random.nextInt(8);
		        
		        if (Block.blocksList[this.m_waterPlantID].canPlaceBlockAt(world, randomXPos, randomYPos, randomZPos))
	        	{
	        		world.setBlock(randomXPos, randomYPos, randomZPos, Block.waterStill.blockID, 0, 2);
	        		
	        		if (world.getBiomeGenForCoords(randomXPos, randomZPos).biomeName.equalsIgnoreCase(BiomeGenBase.swampland.biomeName))
	        			world.setBlockAndMetadataWithNotify(randomXPos, randomYPos + 1, randomZPos, this.m_waterPlantID, 0);
	        		else if (world.getBiomeGenForCoords(randomXPos, randomZPos).biomeName.equalsIgnoreCase(BiomeGenBase.beach.biomeName) 
	        				|| world.getBiomeGenForCoords(randomXPos, randomZPos).biomeName.equalsIgnoreCase(BiomeGenBase.ocean.biomeName))
	        			world.setBlockAndMetadataWithNotify(randomXPos, randomYPos + 1, randomZPos, this.m_waterPlantID, 1);
	        		else
	        			world.setBlockAndMetadataWithNotify(randomXPos, randomYPos + 1, randomZPos, this.m_waterPlantID, 2);
	        		
	        		return true;
	        	}
		    }
			
			return false;
		}
    }
}
