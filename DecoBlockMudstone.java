package net.minecraft.src;

public class DecoBlockMudstone extends Block
{
	public DecoBlockMudstone(int id)
	{
		super(id, Material.rock);
		setUnlocalizedName("decoBlockMudstone");
		setHardness(Block.stone.blockHardness / 0.5F);
        setResistance(Block.stone.blockResistance / 0.5F);
        setStepSound(Block.soundStoneFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
	}
}
