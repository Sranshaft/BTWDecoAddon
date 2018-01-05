package net.minecraft.src;

public class DecoBlockPaneWattle extends FCBlockPane
{
	public DecoBlockPaneWattle(int id)
	{
		super(id, "decoBlockPaneWattle_face", "decoBlockPaneWattle_side", Material.wood, true);
		setUnlocalizedName("decoBlockPaneWattle");
		setHardness(0.3F);
		setResistance(1.0F);
		setStepSound(Block.soundWoodFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
}
