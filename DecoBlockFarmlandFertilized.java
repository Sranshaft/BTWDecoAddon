package net.minecraft.src;

import java.util.Random;

public class DecoBlockFarmlandFertilized extends FCBlockFarmland implements FCISoil
{
	public static final String[] DIRT_TEXTURE_PATHS = new String[] { "dirt", "decoBlockDirt_coarse", "decoBlockDirt_dried", "decoBlockDirt_rich" };

	private Icon[] m_IconByMetadataArray = new Icon[this.DIRT_TEXTURE_PATHS.length];
	private Icon m_IconFarmlandWet;
	private Icon m_IconFarmlandDry;

	private boolean m_IsFarmlandHydrated = false;

	public DecoBlockFarmlandFertilized(int id)
	{
		super(id);
		
		this.setUnlocalizedName("FCBlockFarmlandFertilized");
		this.setHardness(0.6F);
		this.setStepSound(soundGravelFootstep);
		this.setTickRandomly(true);

		Block.useNeighborBrightness[id] = true;
		Block.lightOpacity[id] = 255;
		
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
	}
	
	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	public int idPicked(World world, int x, int y, int z)
	{
		return Block.dirt.blockID;
	}

	/**
	 * Get the block's damage value (for use with pick block).
	 */
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}
	
	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		int metadata = world.getBlockMetadata(x, y, z);

		if (!DecoUtilsCrops.isWaterNearby(world, x, y, z) && !world.canLightningStrikeAt(x, y + 1, z))
		{
			if (!DecoUtilsCrops.isCropsNearby(world, x, y, z) && random.nextInt(100) <= 50)
				world.setBlockAndMetadataWithNotify(x, y, z, Block.dirt.blockID, this.getMetadata(metadata));
		}
		else
		{
			if (!this.isFarmlandHydrated(metadata)) world.setBlockMetadataWithNotify(x, y, z, this.getMetadata(metadata), 2);
		}
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor blockID
	 */
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourBlockID)
	{
		super.onNeighborBlockChange(world, x, y, z, neighbourBlockID);
		Material blockMaterial = world.getBlockMaterial(x, y + 1, z);

		if (blockMaterial.isSolid())
		{
			int metadata = world.getBlockMetadata(x, y, z);
			world.setBlockAndMetadataWithNotify(x, y, z, Block.dirt.blockID, this.getMetadata(metadata));
		}
	}
	
	/**
	 * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
	 */
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (!world.isRemote && !entity.isDead)
		{
			if (entity instanceof EntityItem)
			{
				ItemStack itemStack = ((EntityItem)entity).getEntityItem();

				if (DecoUtilsCrops.isPlantableSeed(itemStack))
				{
					if (((EntityItem)entity).age >= 40 && world.isAirBlock(x, y + 1, z))
					{
						world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "random.pop", 0.25F, world.rand.nextFloat() * 1.5F + 2.0F);
						world.setBlock(x, y + 1, z, DecoUtilsCrops.getCropIDFromSeedItem(itemStack), 0, 2);
						itemStack.stackSize--;

						if (itemStack.stackSize <= 0) entity.setDead();
					}
				}
			}
		}
	}

	/**
	 * Block's chance to react to an entity falling on it.
	 */
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float distance)
	{
		if (!world.isRemote && world.rand.nextFloat() < distance - 0.5F)
		{
			if (!(entity instanceof EntityPlayer) && !world.getGameRules().getGameRuleBooleanValue("mobGriefing"))
				return;

			int metadata = world.getBlockMetadata(x, y, z);
			world.setBlockAndMetadataWithNotify(x, y, z, Block.dirt.blockID, this.getMetadata(metadata));
		}
	}
	
	public boolean CanPlantGrowOnBlock(World world, int x, int y, int z, Block block) 
	{
		return block.blockID == Block.crops.blockID || block.blockID == FCBetterThanWolves.fcHempCrop.blockID || block.blockID == Block.pumpkinStem.blockID || block.blockID == Block.melonStem.blockID 
				|| block.blockID == Block.pumpkin.blockID || block.blockID == Block.melon.blockID || block.blockID == Block.carrot.blockID || block.blockID == Block.potato.blockID;
	}
	
	public void NotifyOfPlantGrowth(World world, int x, int y, int z, Block block) 
	{
		int metadata = world.getBlockMetadata(x, y, z);
		world.setBlockAndMetadataWithNotify(x, y, z, Block.tilledField.blockID, metadata);
	}

	public boolean IsPlantGrowthMaximizedOnBlock(World world, int x, int y, int z, Block block)
	{
		return true;
	}

	public boolean IsBlockHydrated(World world, int x, int y, int z) 
	{
		return DecoUtilsCrops.isWaterNearby(world, x, y, z);
	}

	public boolean IsBlockConsideredNeighbouringWater(World world, int x, int y, int z) 
	{
		return false;
	}

	public float GetGrowthMultiplier(World world, int x, int y, int z, Block block) 
	{
		int metadata = world.getBlockMetadata(x, y, z);
		return getMetadata(metadata) == 3 ? 5.0F : getMetadata(metadata) == 2 ? 0.5F : 2.0F;
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public Icon getIcon(int side, int metadata)
	{
		return side == 1 ? (this.isFarmlandHydrated(metadata) ? this.m_IconFarmlandWet : this.m_IconFarmlandDry) : this.m_IconByMetadataArray[this.getMetadata(metadata)];
	}

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerIcons(IconRegister register)
	{
		for (int index = 0; index < this.DIRT_TEXTURE_PATHS.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon(this.DIRT_TEXTURE_PATHS[index]);
		}

		this.m_IconFarmlandWet = register.registerIcon("FCBlockFarmlandFertilized_wet");
		this.m_IconFarmlandDry = register.registerIcon("FCBlockFarmlandFertilized_dry");
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	private boolean isFarmlandHydrated(int metadata)
	{
		return metadata > 3;
	}

	private int getMetadata(int metadata)
	{
		int result = metadata > 3 ? metadata - 8 : metadata;
		return result < 0 || result > 16 ? 0 : result;		
	}
}
