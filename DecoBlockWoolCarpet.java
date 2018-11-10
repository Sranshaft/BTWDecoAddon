package net.minecraft.src;

import java.util.List;

public class DecoBlockWoolCarpet extends Block 
{
	public static final String[] COLOUR_TYPES = new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "grey", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white" };
	public static final String[] COLOUR_NAMES = new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Silver", "Grey", "Pink", "Lime", "Yellow", "Light Blue", "Magenta", "Orange", "White" };

	private Icon[] m_IconByMetadataArray = new Icon[16];

	public DecoBlockWoolCarpet(int id) 
	{
		super(id, Material.cloth);
		
		this.setUnlocalizedName("decoBlockWoolCarpet");
		this.setHardness(0.3F);
		this.setResistance(0.5F);
		this.setStepSound(Block.soundClothFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);

		DecoAddonManager.register(this, this.COLOUR_TYPES, this.COLOUR_NAMES, " Carpet");
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether or
	 * not to render the shared face of two adjacent blocks and also whether the
	 * player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube() 
	{
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False
	 * (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock() 
	{
		return false;
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
	public int damageDropped(int metadata) 
	{
		return metadata;
	}
	
	/**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
    	return AxisAlignedBB.getAABBPool().getAABB((double)x, (double)y, (double)z, (double)((float)x + 1.0F), (double)((float)y + 0.125F), (double)((float)z + 1.0F));
    }
    
    // CLIENT ONLY

	/**
	 * When this method is called, your block should register all the icons it needs
	 * with the given IconRegister. This is the only chance you get to register
	 * icons.
	 */
	public void registerIcons(IconRegister register) 
	{
		for (int index = 0; index < this.COLOUR_TYPES.length; index++) 
		{
			this.m_IconByMetadataArray[index] = register.registerIcon("decoBlockWoolCarpet_" + this.COLOUR_TYPES[index]);
		}

		if (this.COLOUR_TYPES.length < 16) 
		{
			for (int index = this.COLOUR_TYPES.length; index < 16; index++)
				this.m_IconByMetadataArray[index] = this.blockIcon;
		}
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture.
	 * Args: side, metadata
	 */
	public Icon getIcon(int side, int metadata) 
	{
		return this.m_IconByMetadataArray[metadata];
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood
	 * returns 4 blocks)
	 */
	public void getSubBlocks(int var1, CreativeTabs var2, List var3) 
	{
		for (int index = 0; index < this.COLOUR_TYPES.length; index++) 
		{
			var3.add(new ItemStack(var1, 1, index));
		}
	}
	
	/**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess bAccess, int x, int y, int z, int side)
    {
    	return true;
    }

	/**
	 * only called by clickMiddleMouseButton , and passed to
	 * inventory.setCurrentItem (along with isCreative)
	 */
	public int idPicked(World world, int x, int y, int z) 
	{
		return world.getBlockId(x, y, z);
	}

	/**
	 * Get the block's damage value (for use with pick block).
	 */
	public int getDamageValue(World world, int x, int y, int z) 
	{
		return world.getBlockMetadata(x, y, z);
	}
	
	public void RenderBlockDamageEffect(RenderBlocks render, int var2, int var3, int var4, Icon var5)
	{
		render.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125D, 1.0F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, var5);
	}
}
