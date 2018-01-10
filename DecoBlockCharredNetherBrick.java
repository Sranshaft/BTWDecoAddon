package net.minecraft.src;

public class DecoBlockCharredNetherBrick extends Block
{
	public DecoBlockCharredNetherBrick(int id)
	{
		super(id, Material.rock);
		
		this.setUnlocalizedName("decoBlockCharredNetherBrick");
		this.setHardness(Block.netherBrick.blockHardness);
        this.setResistance(Block.netherBrick.blockResistance);
        this.setStepSound(Block.soundStoneFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
	}
}
