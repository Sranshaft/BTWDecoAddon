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
	
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int var7, float var8, float var9, float var10)
	{
		return player != null && !player.canPlayerEdit(x, y, z, var7, itemStack) ? false : DecoUtilsCrops.hasAppliedBonemeal(itemStack, player, world, x, y, z, var7, var8, var9, var10);
	}
}