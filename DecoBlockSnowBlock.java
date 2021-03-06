package net.minecraft.src;

import java.util.Random;

public class DecoBlockSnowBlock extends BlockSnowBlock
{
	public DecoBlockSnowBlock(int id)
	{
		super(id);
		this.setUnlocalizedName("snow");
		this.setHardness(0.2F);
		this.setLightOpacity(11);
		this.setStepSound(Block.soundSnowFootstep);
	    
		ItemSpade.SetAllShovelsToBeEffectiveVsBlock(this);
	}
	
	public void onBlockAdded(World world, int x, int y, int z)
	{
		if (world.provider.isHellWorld)
		{
			world.setBlockWithNotify(x, y, z, 0);
			world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

			for (int i = 0; i < 8; ++i)
			{
				world.spawnParticle("largesmoke", (double)x + Math.random(), (double)y + Math.random(), (double)z + Math.random(), 0.0D, 0.0D, 0.0D);
			}
		} 
		else 
			checkMelting(world, x, y, z);
	}
	
	public void onNeighborBlockChange(World world, int x, int y, int z, int id)
	{
		world.scheduleBlockUpdate(x, y, z, this.blockID, this.tickRate(world));
	}
	
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		checkMelting(world, x, y, z);
	}

	private void checkMelting(World world, int x, int y, int z)
	{
		int aboveBlockID = world.getBlockId(x, y + 1, z);

        if (!FCUtilsMisc.IsIKInColdBiome(world, x, z) && (world.canBlockSeeTheSky(x, y + 1, z) || world.getBlockLightValue(x, y, z) > 7) && aboveBlockID != this.blockID)
        {
        	world.setBlockWithNotify(x, y, z, 0);
            FCUtilsMisc.PlaceNonPersistantWater(world, x, y, z);
        }
	}
}
