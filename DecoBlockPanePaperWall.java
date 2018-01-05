package net.minecraft.src;

public class DecoBlockPanePaperWall extends FCBlockPane
{
	public DecoBlockPanePaperWall(int id)
	{
		super(id, "decoBlockPanePaperWall_face", "decoBlockPanePaperWall_side", Material.wood, true);
		setUnlocalizedName("decoBlockPanePaperWall");
		setHardness(0.3F);
		setResistance(1.0F);
		setStepSound(Block.soundWoodFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
}