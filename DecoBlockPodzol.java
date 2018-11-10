package net.minecraft.src;

public class DecoBlockPodzol extends FCBlockDirt
{
	public DecoBlockPodzol(int id)
	{
		super(id);
		
		this.setUnlocalizedName("decoBlockPodzol");
		this.setStepSound(Block.soundGravelFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
	}
}
