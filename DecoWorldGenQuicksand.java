package net.minecraft.src;

import java.util.Random;

public class DecoWorldGenQuicksand extends WorldGenerator 
{
	/** The maximum radius used when generating a patch of blocks. */
    private int m_Radius;

    public DecoWorldGenQuicksand(int radius)
    {
        this.m_Radius = radius;
    }

    public boolean generate(World world, Random random, int x, int y, int z)
    {
    	int randomRadius = random.nextInt(this.m_Radius - 2) + 2;
    	
    	for (int xIndex = x - randomRadius; xIndex <= x + randomRadius; ++xIndex)
        {
            for (int zIndex = z - randomRadius; zIndex <= z + randomRadius; ++zIndex)
            {
            	int var10 = xIndex - x;
            	int var11 = zIndex - z;
            	
            	if (var10 * var10 + var11 * var11 <= randomRadius * randomRadius)
            	{
            		int var13 = world.getBlockId(xIndex, y, zIndex);

            		if (var13 == Block.dirt.blockID || var13 == Block.sand.blockID || var13 == Block.grass.blockID) 
            			world.setBlock(xIndex, y, zIndex, DecoModuleWorld.decoBlockQuicksandID, 0, 2);
            	}
            }
        }

        return true;
    }
}