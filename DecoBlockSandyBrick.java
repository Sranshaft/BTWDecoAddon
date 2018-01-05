package net.minecraft.src;

public class DecoBlockSandyBrick extends Block 
{
	public DecoBlockSandyBrick(int id)
	{
		super(id, Block.brick.blockMaterial);
		setUnlocalizedName("decoBlockSandyBrick");
		setHardness(Block.brick.blockHardness);
		setResistance(Block.brick.blockResistance);
		setStepSound(Block.soundStoneFootstep);
		setCreativeTab(CreativeTabs.tabBlock);
	}
}
