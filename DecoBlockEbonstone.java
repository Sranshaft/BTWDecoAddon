package net.minecraft.src;

public class DecoBlockEbonstone extends Block
{
	public DecoBlockEbonstone(int id)
	{
		super(id, Material.rock);

		this.setUnlocalizedName("decoBlockEbonstone");
		this.setHardness(Block.whiteStone.blockHardness / 0.5F);
		this.setResistance(Block.whiteStone.blockResistance / 0.5F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
}