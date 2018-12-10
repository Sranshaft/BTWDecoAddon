package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockStainedGlass extends DecoBlockGlass
{
	private Icon[] m_IconByMetadataArray = new Icon[16];

	public DecoBlockStainedGlass(int id)
	{
		super(id);

		this.setUnlocalizedName("decoBlockStainedGlass");
		this.setHardness(0.3F);
		this.setResistance(0.5F);
		this.setStepSound(Block.soundGlassFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);

		DecoAddonManager.register(this, DecoUtilsStrings.COLOUR_TAGS, DecoUtilsStrings.COLOUR_NAMES, " Stained Glass");
	}

	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
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

	/**
	 * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
	 */
	public int getRenderBlockPass()
	{
		return 1;
	}

	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates.  Args: blockAccess, x, y, z, side
	 */
	public boolean shouldSideBeRendered(IBlockAccess bAccess, int x, int y, int z, int side)
	{
		int checkBlockID = bAccess.getBlockId(x, y, z);
		return checkBlockID == this.blockID ? false : super.shouldSideBeRendered(bAccess, x, y, z, 1 - side);
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture.
	 * Args: side, metadata
	 */
	public Icon getIcon(int side, int metadata) 
	{
		if (metadata > this.m_IconByMetadataArray.length) return this.m_IconByMetadataArray[0]; else return this.m_IconByMetadataArray[metadata];
	}

	/**
	 * When this method is called, your block should register all the icons it needs
	 * with the given IconRegister. This is the only chance you get to register
	 * icons.
	 */
	public void registerIcons(IconRegister register) 
	{
		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon("decoBlockStainedGlass_" + DecoUtilsStrings.COLOUR_TAGS[index]);
		}
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	public void getSubBlocks(int var1, CreativeTabs var2, List var3)
	{
		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			var3.add(new ItemStack(var1, 1, index));
		}
	}
}
