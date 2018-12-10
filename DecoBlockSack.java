package net.minecraft.src;

import java.util.List;

public class DecoBlockSack extends BlockContainer
{
	private Icon m_IconTop;
	
	protected DecoBlockSack(int id) 
	{
		super(id, Material.cloth);

		this.setUnlocalizedName("decoBlockSack");
		this.setHardness(1.0F);
		this.setStepSound(soundClothFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	public TileEntity createNewTileEntity(World world) 
	{
		return new DecoTileEntitySack();
	}

	/**
	 * Called when the block is placed in the world.
	 */
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack itemStack)
	{
		if (!world.isRemote)
		{
			DecoTileEntitySack sack = (DecoTileEntitySack)world.getBlockTileEntity(x, y, z);

			if (itemStack.hasTagCompound()) sack.readInventoryFromNBT(itemStack.getTagCompound());
		}
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			DecoTileEntitySack entity = (DecoTileEntitySack)world.getBlockTileEntity(x, y, z);

			if (player instanceof EntityPlayerMP)
			{
				DecoContainerSack container = new DecoContainerSack(player.inventory, entity);
				DecoUtilsPacketHandler.ServerOpenCustomInterface((EntityPlayerMP)player, container, DecoModuleDecoration.decoSubModuleSack.decoContainerSackID);
			}
		}

		return true;
	}

	/**
	 * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
	 * block and l is the block's subtype/damage.
	 */
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) { }
	
	/**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World world, int x, int y, int z, int neighbourID, int flags)
    {
        FCUtilsInventory.EjectInventoryContents(world, x, y, z, (IInventory)world.getBlockTileEntity(x, y, z));
        super.breakBlock(world, x, y, z, neighbourID, flags);
    }

	/**
	 * Called when the block is attempted to be harvested
	 */
	public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player) 
	{
		if (!world.isRemote) FCUtilsInventory.EjectInventoryContents(world, x, y, z, (IInventory)world.getBlockTileEntity(x, y, z));
	}

	/**
	 * Called upon the block being destroyed by an explosion
	 */
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) 
	{
		if (!world.isRemote) FCUtilsInventory.EjectInventoryContents(world, x, y, z, (IInventory)world.getBlockTileEntity(x, y, z));
	}

	/**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
    	return AxisAlignedBB.getAABBPool().getAABB((double)x + 0.0625D, (double)y, (double)z + 0.0625D,
    											   (double)x + 0.9375D, (double)y + 1, (double)z + 0.9375D);
    }
	
	/**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess bAccess, int x, int y, int z, int side)
    {
        return true;
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z)
	{
    	this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, 0.9375F);
	}
	
	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public Icon getIcon(int side, int metadata)
	{
		if (side == 1) return this.m_IconTop; else return this.blockIcon; 
	}

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerIcons(IconRegister register)
	{
		this.blockIcon = register.registerIcon("decoBlockSack");
		this.m_IconTop = register.registerIcon("decoBlockSack_top");
	}
	
	public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
    {
		// BOTTOM
        render.setRenderBounds(0.125D, 0.0D, 0.125D, 0.875D, 0.125D, 0.875D);
        render.renderStandardBlock(this, x, y, z);
        
        // BODY
        render.setRenderBounds(0.0625D, 0.125D, 0.0625D, 0.9375D, 0.825D, 0.9375D);
        render.renderStandardBlock(this, x, y, z);
        
        // INDENT
        render.setRenderBounds(0.125D, 0.825D, 0.125D, 0.875D, 0.85D, 0.875D);
        render.renderStandardBlock(this, x, y, z);
        
        // TOP : NORTH
        render.setRenderBounds(0.0625D, 0.85D, 0.0625D, 0.9375D, 1.0D, 0.125D);
        render.renderStandardBlock(this, x, y, z);
        
        // TOP : WEST
        render.setRenderBounds(0.0625D, 0.85D, 0.125D, 0.125D, 1.0D, 0.875D);
        render.renderStandardBlock(this, x, y, z);
        
        // TOP : EAST
        render.setRenderBounds(0.875D, 0.85D, 0.125D, 0.9375D, 1.0D, 0.875D);
        render.renderStandardBlock(this, x, y, z);
        
        // TOP : SOUTH
        render.setRenderBounds(0.0625D, 0.85D, 0.875D, 0.9375D, 1.0D, 0.9375D);
        render.renderStandardBlock(this, x, y, z);
        
        // INSERT
        render.setRenderBounds(0.065D, 0.85D, 0.065D, 0.935D, 0.95D, 0.935D);
        render.renderStandardBlock(this, x, y, z);
        
        return true;
    }
	
	public void RenderBlockAsItem(RenderBlocks render, int var2, float var3)
    {
    	render.setRenderBounds(0.125D, 0.0D, 0.125D, 0.875D, 0.125D, 0.875D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
        render.setRenderBounds(0.0625D, 0.125D, 0.0625D, 0.9375D, 0.825D, 0.9375D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
        render.setRenderBounds(0.125D, 0.825D, 0.125D, 0.875D, 0.85D, 0.875D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
        render.setRenderBounds(0.0625D, 0.85D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
    }
}
