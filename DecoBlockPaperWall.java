package net.minecraft.src;

public class DecoBlockPaperWall extends Block
{
	public DecoBlockPaperWall(int id)
	{
		super(id, Material.wood);
		setUnlocalizedName("decoBlockPaperWall");
		setHardness(1.0F);
        setResistance(1.0F);
        setStepSound(Block.soundWoodFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
	}
}
