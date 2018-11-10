package net.minecraft.src;

public class DecoItemFertilizer extends Item
{
	public DecoItemFertilizer(int id)
	{
		super(id);
		
		this.setUnlocalizedName("decoItemFertilizer");
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.SetBellowsBlowDistance(FCBetterThanWolves.fcCoalDust.GetBellowsBlowDistance(0));
	}
	
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		return player != null && !player.canPlayerEdit(x, y, z, side, itemStack) ? false : DecoUtilsCrops.hasAppliedBonemeal(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}
}