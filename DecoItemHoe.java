package net.minecraft.src;

public class DecoItemHoe extends ItemHoe
{
	public DecoItemHoe(int id, EnumToolMaterial toolMaterial)
	{
		super(id, toolMaterial);
	}

	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if (!player.canPlayerEdit(x, y, z, side, itemStack))
			return false;

		int blockID = world.getBlockId(x, y, z);
		int blockMetadata = world.getBlockMetadata(x, y, z);

		if (side == 0 && !world.isAirBlock(x, y + 1, z) || (blockID != Block.grass.blockID && blockID != Block.dirt.blockID && blockID != FCBetterThanWolves.fcBlockAestheticOpaqueEarth.blockID))
			return false;
		else
		{
			if (!((FCBlockAestheticOpaqueEarth)FCBetterThanWolves.fcBlockAestheticOpaqueEarth).IsBlightFromMetadata(world.getBlockMetadata(x, y, z)))
				return false;

			if (world.isRemote)
				return true;
			else
			{
				if (blockMetadata != 1)
				{
					Block tilledBlock = DecoSubModuleExtendedDirtAndGrassBlocks.decoBlockFarmland;
					world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), 
							tilledBlock.stepSound.getStepSound(), (tilledBlock.stepSound.getVolume() + 1.0F) / 2.0F, tilledBlock.stepSound.getPitch() * 0.8F);
					world.setBlockAndMetadataWithNotify(x, y, z, tilledBlock.blockID, blockMetadata);
					itemStack.damageItem(1, player);

					this.CheckForSeedDropOnItemUse(itemStack, player, world, x, y, z);

					return true;
				}
			}
		}

		return false;
	}

	private void CheckForSeedDropOnItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z)
	{
		int checkBlockID = world.getBlockId(x, y, z);

		if (checkBlockID == Block.grass.blockID)
		{
			int chanceDrop = this.GetHempSeedDropPercentage(itemStack);

			if (world.rand.nextInt(100) < chanceDrop)
			{
				float motion = 0.7F;
				float spawnX = world.rand.nextFloat() * motion + (1.0F - motion) * 0.5F;
				float spawnY = 1.2F;
				float spawnZ = world.rand.nextFloat() * motion + (1.0F - motion) * 0.5F;

				EntityItem itemEntity = new EntityItem(world, (double)((float)x + spawnX), (double)((float)y + spawnY), (double)((float)z + spawnZ), 
						new ItemStack(FCBetterThanWolves.fcHempSeeds));
				itemEntity.delayBeforeCanPickup = 10;

				world.spawnEntityInWorld(itemEntity);
			}
		}
	}

	protected int GetHempSeedDropPercentage(ItemStack itemStack)
	{
		byte var2 = 0;
		int itemID = itemStack.itemID;

		if (itemID == Item.hoeWood.itemID)
			var2 = 1;
		else if (itemID == Item.hoeStone.itemID)
			var2 = 2;
		else if (itemID == Item.hoeIron.itemID)
			var2 = 4;
		else if (itemID == Item.hoeDiamond.itemID)
			var2 = 8;
		else if (itemID == Item.hoeGold.itemID)
			var2 = 16;

		return var2;
	}
}
