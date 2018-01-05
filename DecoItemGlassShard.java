package net.minecraft.src;

public class DecoItemGlassShard extends Item
{
	public DecoItemGlassShard(int id)
	{
		super(id);
		setUnlocalizedName("decoItemGlassShard");
		setCreativeTab(CreativeTabs.tabMaterials);
		SetBellowsBlowDistance(FCBetterThanWolves.fcCoalDust.GetBellowsBlowDistance(0));
	}
}
