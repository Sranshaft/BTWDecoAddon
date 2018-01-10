package net.minecraft.src;

public class DecoBlockDiamondium extends Block
{
	public DecoBlockDiamondium(int id, Material material)
	{
		super(id, material);
		
		this.setUnlocalizedName("decoBlockDiamondium");
		this.setHardness(10.0F);
		this.setResistance(2000.0F);
		this.setStepSound(Block.soundMetalFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
}
