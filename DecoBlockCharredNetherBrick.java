package net.minecraft.src;

public class DecoBlockCharredNetherBrick extends Block
{
	public DecoBlockCharredNetherBrick(int id)
	{
		super(id, Material.rock);
		setUnlocalizedName("decoBlockCharredNetherBrick");
		setHardness(Block.netherBrick.blockHardness);
        setResistance(Block.netherBrick.blockResistance);
        setStepSound(Block.soundStoneFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
	}
}
