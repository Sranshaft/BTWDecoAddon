package net.minecraft.src;

public class DecoItemBamboo extends FCItemPlacesAsBlock
{
	public DecoItemBamboo(int id, int blockID)
	{
		super(id, blockID);

		this.setUnlocalizedName("decoItemBamboo");
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		int checkBlockID = world.getBlockId(x, y, z);

		if ((checkBlockID == Block.grass.blockID || checkBlockID == Block.dirt.blockID || checkBlockID == Block.sand.blockID 
				|| checkBlockID == FCBetterThanWolves.fcPlanter.blockID || checkBlockID == DecoModuleDecoration.decoBlockBambooID) 
				&& world.isAirBlock(x, y + 1, z))
		{
			world.setBlock(x, y + 1, z, DecoModuleDecoration.decoBlockBamboo.blockID);
			return true;
		}
		else return false;
	}
}
