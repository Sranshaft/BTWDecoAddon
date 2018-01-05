package net.minecraft.src;

public class DecoBlockFroststone extends Block
{
	public DecoBlockFroststone(int id)
	{
		super(id, Material.rock);
		setUnlocalizedName("decoBlockFroststone");
		setHardness(Block.stone.blockHardness);
        setResistance(Block.stone.blockResistance);
        setStepSound(Block.soundStoneFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
	}
}
