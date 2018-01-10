package net.minecraft.src;

public class DecoBlockEbonstone extends Block
{
	public DecoBlockEbonstone(int id)
	{
		super(id, Material.rock);
		setUnlocalizedName("decoBlockEbonstone");
		setHardness(Block.whiteStone.blockHardness / 0.5F);
        setResistance(Block.whiteStone.blockResistance / 0.5F);
        setStepSound(Block.soundStoneFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
	}
}