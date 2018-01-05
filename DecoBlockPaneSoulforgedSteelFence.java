package net.minecraft.src;

public class DecoBlockPaneSoulforgedSteelFence extends FCBlockPane
{
	public DecoBlockPaneSoulforgedSteelFence(int ID)
	{
		super(ID, "decoBlockPaneSoulforgedSteelFence_face", "decoBlockPaneSoulforgedSteelFence_side", Material.iron, true);
		setHardness(5.0F);
		setResistance(20.0F);
		setStepSound(Block.soundMetalFootstep);
		setUnlocalizedName("decoBlockPaneSoulforgedSteelFence");
		setCreativeTab(CreativeTabs.tabDecorations);
	}
}