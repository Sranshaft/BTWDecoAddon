package net.minecraft.src;

import java.util.Random;

public class DecoBlockChainRope extends FCBlockRope
{
	public static final float fRopeWidth = 0.125F;
	
	public DecoBlockChainRope(int id)
	{
		super(id);
		
		setUnlocalizedName("decoBlockChainRope");
		setHardness(Block.blockIron.blockHardness);
		setStepSound(Block.soundMetalFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	/**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return DecoModuleDecoration.decoItemChainRopeID;
    }
    
    public void BreakRope(World world, int x, int y, int z)
    {
        FCUtilsItem.EjectSingleItemWithRandomOffset(world, x, y, z, DecoModuleDecoration.decoItemChainRopeID, 0);
        
        world.playAuxSFX(2001, x, y, z, this.blockID);
        world.setBlockWithNotify(x, y, z, 0);
    }
}


