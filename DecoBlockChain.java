package net.minecraft.src;

import java.util.Random;

public class DecoBlockChain extends FCBlockRope
{
	public static final float fRopeWidth = 0.125F;

	public DecoBlockChain(int id)
	{
		super(id);

		setUnlocalizedName("decoBlockChain");
		setHardness(Block.blockIron.blockHardness);
		setStepSound(Block.soundMetalFootstep);
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int var1, Random var2, int var3)
	{
		return DecoModuleTransportation.decoItemChain.itemID;
	}
}
