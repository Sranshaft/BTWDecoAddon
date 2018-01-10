package net.minecraft.src;

import java.util.List;
import java.util.Random;

public interface DecoIBlock 
{
	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	public int idPicked(World world, int x, int y, int z);

	/**
	 * Get the block's damage value (for use with pick block).
	 */
	public int getDamageValue(World world, int x, int y, int z);

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int itemID, Random random, int amount);

	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
	public int damageDropped(int metadata);

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random random);

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether or
	 * not to render the shared face of two adjacent blocks and also whether the
	 * player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube();

	/**
	 * If this block doesn't render as an ordinary block it will return False
	 * (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock();
	
	public boolean canBlockStay(World world, int x, int y, int z);
	
	/**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
	public boolean canPlaceBlockAt(World world, int x, int y, int z);
	
	/**
     * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
     */
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack);

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List);

	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public Icon getIcon(int side, int metadata);

	/**
	 * When this method is called, your block should register all the icons it needs
	 * with the given IconRegister. This is the only chance you get to register
	 * icons.
	 */
	public void registerIcons(IconRegister register);
}
