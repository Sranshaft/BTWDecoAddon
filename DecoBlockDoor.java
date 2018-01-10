package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

public class DecoBlockDoor extends FCBlockDoorWood
{
	/** Used for pointing at icon names. */
	public static final String[] DOOR_TYPES = new String[] { "birch_upper", "birch_lower", "bloodwood_upper", "bloodwood_lower", "ebonwood_upper", "ebonwood_lower", 
			"ironwood_upper", "ironwood_lower", "jungle_upper", "jungle_lower", "spruce_upper", "spruce_lower" };
	
	private Icon[] iconArray;
    private int m_WoodType;
	
	public DecoBlockDoor(int id, Material material, String tag, int woodID)
	{
		super(id, material);
		setUnlocalizedName("decoBlockDoor." + tag);
		setStepSound(Block.soundWoodFootstep);
		
		float minOffset = 0.5F;
        float maxOffset = 1.0F;
        this.setBlockBounds(0.5F - minOffset, 0.0F, 0.5F - minOffset, 0.5F + minOffset, maxOffset, 0.5F + minOffset);
        
        setWoodType(woodID);
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
    	return this.iconArray[getWoodType()];
    }
    
    public Icon getBlockTexture(IBlockAccess bAccess, int x, int y, int z, int side)
	{
		if (side != 1 && side != 0)
		{
			int metadata = this.getFullMetadata(bAccess, x, y, z);
			int rotation = metadata & 3;
			boolean flag = (metadata & 4) != 0;
			boolean flag1 = false;
			boolean flag2 = (metadata & 8) != 0;

			if (flag)
			{
				if (rotation == 0 && side == 2)
					flag1 = !flag1;
				else if (rotation == 1 && side == 5)
					flag1 = !flag1;
				else if (rotation == 2 && side == 3)
					flag1 = !flag1;
				else if (rotation == 3 && side == 4)
					flag1 = !flag1;
			}
			else
			{
				if (rotation == 0 && side == 5)
					flag1 = !flag1;
				else if (rotation == 1 && side == 3)
					flag1 = !flag1;
				else if (rotation == 2 && side == 4)
					flag1 = !flag1;
				else if (rotation == 3 && side == 2)
					flag1 = !flag1;

				if ((metadata & 16) != 0)
					flag1 = !flag1;
			}

			return this.iconArray[getWoodType() + (flag1 ? this.DOOR_TYPES.length : 0) + (flag2 ? 1 : 0)];
		}
		else
		{
			return this.iconArray[getWoodType()];
		}
	}
    
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
	{
		this.iconArray = new Icon[this.DOOR_TYPES.length * 2];
		for(int index = 0; index < this.DOOR_TYPES.length; index++)
		{
			iconArray[index] = register.registerIcon("decoBlockDoor_" + this.DOOR_TYPES[index]);
			this.iconArray[index + this.DOOR_TYPES.length] = new IconFlipped(this.iconArray[index], true, false);
		}
	}
    
    public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean getBlocksMovement(IBlockAccess bAccess, int x, int y, int z)
	{
		int metadata = this.getFullMetadata(bAccess, x, y, z);
		return (metadata & 4) != 0;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public int getRenderType()
	{
		return 7;
	}
	
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z)
	{
		this.setDoorRotation(this.getFullMetadata(bAccess, x, y, z));
	}
	
	public int getDoorOrientation(IBlockAccess bAccess, int x, int y, int z)
	{
		return this.getFullMetadata(bAccess, x, y, z) & 3;
	}

	public boolean isDoorOpen(IBlockAccess bAccess, int x, int y, int z)
	{
		return (this.getFullMetadata(bAccess, x, y, z) & 4) != 0;
	}

	private void setDoorRotation(int metadata)
	{
		float var2 = 0.1875F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
		int var3 = metadata & 3;
		boolean var4 = (metadata & 4) != 0;
		boolean var5 = (metadata & 16) != 0;

		if (var3 == 0)
		{
			if (var4)
			{
				if (!var5)
					this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
				else
					this.setBlockBounds(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
			}
			else
			{
				this.setBlockBounds(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
			}
		}
		else if (var3 == 1)
		{
			if (var4)
			{
				if (!var5)
					this.setBlockBounds(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				else
					this.setBlockBounds(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
			}
			else
			{
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
			}
		}
		else if (var3 == 2)
		{
			if (var4)
			{
				if (!var5)
					this.setBlockBounds(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
				else
					this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
			}
			else
			{
				this.setBlockBounds(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			}
		}
		else if (var3 == 3)
		{
			if (var4)
			{
				if (!var5)
					this.setBlockBounds(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
				else
					this.setBlockBounds(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			}
			else
			{
				this.setBlockBounds(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
			}
		}
	}
	
	/**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {}


    /**
     * Called upon block activation (right click on the block.)
     */
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitZ)
	{
		if (this.blockMaterial == Material.iron)
			return false; //Allow items to interact with the door
		else
		{
			int var10 = this.getFullMetadata(world, x, y, z);
			int var11 = var10 & 7;
			var11 ^= 4;

			if ((var10 & 8) == 0)
			{
				world.setBlockMetadataWithNotify(x, y, z, var11, 3);
				world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			}
			else
			{
				world.setBlockMetadataWithNotify(x, y - 1, z, var11, 3);
				world.markBlockRangeForRenderUpdate(x, y - 1, z, x, y, z);
			}

			world.playAuxSFXAtEntity(player, 1003, x, y, z, 0);
			return true;
		}
	}
	public void onPoweredBlockChange(World par1World, int par2, int par3, int par4, boolean par5)
	{
		int var6 = this.getFullMetadata(par1World, par2, par3, par4);
		boolean var7 = (var6 & 4) != 0;

		if (var7 != par5)
		{
			int var8 = var6 & 7;
			var8 ^= 4;

			if ((var6 & 8) == 0)
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, var8, 3);
				par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
			}
			else
			{
				par1World.setBlockMetadataWithNotify(par2, par3 - 1, par4, var8, 3);
				par1World.markBlockRangeForRenderUpdate(par2, par3 - 1, par4, par2, par3, par4);
			}

			par1World.playAuxSFXAtEntity((EntityPlayer)null, 1003, par2, par3, par4, 0);
		}
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor Block
	 */
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockID)
	{
		int metadata = world.getBlockMetadata(x, y, z);

		if ((metadata & 8) == 0)
		{
			boolean var7 = false;

			if (world.getBlockId(x, y + 1, z) != this.blockID)
			{
				world.setBlockToAir(x, y, z);
				var7 = true;
			}

			if (!world.doesBlockHaveSolidTopSurface(x, y - 1, z))
			{
				world.setBlockToAir(x, y, z);
				var7 = true;

				if (world.getBlockId(x, y + 1, z) == this.blockID)
					world.setBlockToAir(x, y + 1, z);
			}

			if (var7)
			{
				if (!world.isRemote)
					this.dropBlockAsItem(world, x, y, z, metadata, 0);
			}
			else
			{
				boolean var8 = world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y + 1, z);
				if ((var8 || blockID > 0 && Block.blocksList[blockID].canProvidePower()) && blockID != this.blockID)
					this.onPoweredBlockChange(world, x, y, z, var8);
			}
		}
		else
		{
			if (world.getBlockId(x, y - 1, z) != this.blockID)
				world.setBlockToAir(x, y, z);

			if (blockID > 0 && blockID != this.blockID)
				this.onNeighborBlockChange(world, x, y - 1, z, blockID);
		}
	}

	/**
	 * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
	 * x, y, z, startVec, endVec
	 */
	public MovingObjectPosition collisionRayTrace(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3)
	{
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.collisionRayTrace(par1World, par2, par3, par4, par5Vec3, par6Vec3);
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return y >= 255 ? false : world.doesBlockHaveSolidTopSurface(x, y - 1, z) && super.canPlaceBlockAt(world, x, y, z) && super.canPlaceBlockAt(world, x, y + 1, z);
	}

	/**
	 * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
	 * and stop pistons
	 */
	@Override
	public int getMobilityFlag()
	{
		return 1;
	}

	/**
	 * Returns the full metadata value created by combining the metadata of both blocks the door takes up.
	 */
	public int getFullMetadata(IBlockAccess bAccess, int x, int y, int z)
	{
		int metadata = bAccess.getBlockMetadata(x, y, z);
		boolean var6 = (metadata & 8) != 0;
		int var7;
		int var8;

		if (var6)
		{
			var7 = bAccess.getBlockMetadata(x, y - 1, z);
			var8 = metadata;
		}
		else
		{
			var7 = metadata;
			var8 = bAccess.getBlockMetadata(x, y + 1, z);
		}

		boolean var9 = (var8 & 1) != 0;
		return var7 & 7 | (var6 ? 8 : 0) | (var9 ? 16 : 0);
	}

	/**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return this.blockMaterial == Material.iron ? Item.doorIron.itemID : Item.doorWood.itemID;
    }

    /**
     * Called when the block is attempted to be harvested
     */
    public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer)
    {
        if (par6EntityPlayer.capabilities.isCreativeMode && (par5 & 8) != 0 && par1World.getBlockId(par2, par3 - 1, par4) == this.blockID)
        {
            par1World.setBlockToAir(par2, par3 - 1, par4);
        }
    }

	public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
	{
		return true;
	}

	public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
	{
		return true;
	}
	
    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World world, int x, int y, int z)
    {
		return getWoodType() / 2;
    }

    public int getWoodType()
	{
		return this.m_WoodType;
	}

	public void setWoodType(int woodType)
	{
		this.m_WoodType = woodType;
	}
}