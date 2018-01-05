package net.minecraft.src;

public class DecoBlockLadder extends FCBlockLadder
{
	public DecoBlockLadder(int id)
	{
		super(id);
		
		this.setUnlocalizedName("ladder");
		this.setHardness(0.4F);
		this.setStepSound(Block.soundLadderFootstep);
		
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this);
	}
	
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		if (FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x, y, z + 1, 2)) return true;
		if (FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x, y, z - 1, 3)) return true;
		if (FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x + 1, y, z, 4)) return true;
		if (FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x - 1, y, z, 5)) return true;
		
		return false;
	}

	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		int checkSide = metadata;
		
		if ((checkSide == 0 || side == 2) && FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x, y, z + 1, 2)) checkSide = 2;
		if ((checkSide == 0 || side == 3) && FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x, y, z - 1, 3)) checkSide = 3;
		if ((checkSide == 0 || side == 4) && FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x + 1, y, z, 4)) checkSide = 4;
		if ((checkSide == 0 || side == 5) && FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x - 1, y, z, 5)) checkSide = 5;
		
		return checkSide;
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, int id)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		boolean canStay = false;

		if (metadata == 2 && FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x, y, z + 1, 2)) canStay = true;
		if (metadata == 3 && FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x, y, z - 1, 3)) canStay = true;
		if (metadata == 4 && FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x + 1, y, z, 4)) canStay = true;
		if (metadata == 5 && FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x - 1, y, z, 5)) canStay = true;

		if (!canStay)
		{
			this.dropBlockAsItem(world, x, y, z, metadata, 0);
			world.setBlockToAir(x, y, z);
		}
	}
}
