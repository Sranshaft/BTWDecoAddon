package net.minecraft.src;

public class DecoBlockMudstoneBrick extends Block
{
	public DecoBlockMudstoneBrick(int id)
	{
		super(id, Material.rock);
		setUnlocalizedName("decoBlockMudstoneBrick");
		setHardness(Block.stoneBrick.blockHardness / 0.5F);
        setResistance(Block.stoneBrick.blockResistance / 0.5F);
        setStepSound(Block.soundStoneFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
	}
}
