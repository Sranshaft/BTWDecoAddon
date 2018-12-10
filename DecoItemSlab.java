package net.minecraft.src;

public class DecoItemSlab extends ItemBlock
{
	private final DecoBlockSlab m_HalfSlab, m_DoubleSlab;
	private final boolean m_IsFullBlock;
	
	public DecoItemSlab(int id, DecoBlockSlab halfSlab, DecoBlockSlab doubleSlab, boolean isFullBlock)
	{
		super(id);
		
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		
		this.m_HalfSlab = halfSlab;
		this.m_DoubleSlab = doubleSlab;
		this.m_IsFullBlock = isFullBlock;
	}
	
	/**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int metadata)
    {
        return this.m_HalfSlab.getIcon(2, metadata);
    }
    
    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int metadata)
    {
        return metadata;
    }
    
    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return this.m_HalfSlab.getFullSlabName(itemStack.getItemDamage());
    }
	
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (this.m_IsFullBlock) return super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
        else if (itemStack.stackSize == 0) return false;
        else if (!player.canPlayerEdit(x, y, z, side, itemStack)) return false;
        else
        {
            int checkBlockID = world.getBlockId(x, y, z);
            int checkBlockMetadata = world.getBlockMetadata(x, y, z);
            int var13 = checkBlockMetadata & 7;
            boolean isTopSlab = (checkBlockMetadata & 8) != 0;

            if ((side == 1 && !isTopSlab || side == 0 && isTopSlab) && checkBlockID == this.m_HalfSlab.blockID && var13 == itemStack.getItemDamage())
            {
                if (world.checkNoEntityCollision(this.m_DoubleSlab.getCollisionBoundingBoxFromPool(world, x, y, z)) && world.setBlock(x, y, z, this.m_DoubleSlab.blockID, var13, 3))
                {
                    world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.m_DoubleSlab.stepSound.getPlaceSound(), (this.m_DoubleSlab.stepSound.getVolume() + 1.0F) / 2.0F, this.m_DoubleSlab.stepSound.getPitch() * 0.8F);
                    --itemStack.stackSize;
                }

                return true;
            }
            else if (side < 2)
            {
            	return this.canCombineBlock(itemStack, player, world, x, y, z, side) ? true : super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
            }
            else 
        	{
            	return super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
        	}
        }
    }
    
    /**
     * Returns true if the given ItemBlock can be placed on the given side of the given block position.
     */
    public boolean canPlaceItemBlockOnSide(World world, int x, int y, int z, int side, EntityPlayer player, ItemStack itemStack)
    {
        int offsetX = x;
        int offsetY = y;
        int offsetZ = z;
        int checkBlockID = world.getBlockId(x, y, z);
        int checkBlockMetadata = world.getBlockMetadata(x, y, z);
        int var13 = checkBlockMetadata & 7;
        boolean isTopSlab = (checkBlockMetadata & 8) != 0;

        if ((side == 1 && !isTopSlab || side == 0 && isTopSlab) && checkBlockID == this.m_HalfSlab.blockID && var13 == itemStack.getItemDamage()) return true;
        else
        {
        	switch(side)
            {
            	case 0 : --y;
            	case 1 : ++y;
            	case 2 : --z;
            	case 3 : ++z;
            	case 4 : --x;
            	case 5 : ++x;
            }

            checkBlockID = world.getBlockId(x, y, z);
            checkBlockMetadata = world.getBlockMetadata(x, y, z);
            var13 = checkBlockMetadata & 7;
            isTopSlab = (checkBlockMetadata & 8) != 0;
            return checkBlockID == this.m_HalfSlab.blockID && var13 == itemStack.getItemDamage() ? true : super.canPlaceItemBlockOnSide(world, offsetX, offsetY, offsetZ, side, player, itemStack);
        }
    }
    
    private boolean canCombineBlock(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side)
    {
    	switch(side)
        {
        	case 0 : --y;
        	case 1 : ++y;
        	case 2 : --z;
        	case 3 : ++z;
        	case 4 : --x;
        	case 5 : ++x;
        }

        int checkBlockID = world.getBlockId(x, y, z);
        int checkBlockMetadata = world.getBlockMetadata(x, y, z);
        int var10 = checkBlockMetadata & 7;

        if (checkBlockID == this.m_HalfSlab.blockID && var10 == itemStack.getItemDamage())
        {
            if (world.checkNoEntityCollision(this.m_DoubleSlab.getCollisionBoundingBoxFromPool(world, x, y, z)) && world.setBlock(x, y, z, this.m_DoubleSlab.blockID, var10, 3))
            {
                world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.m_DoubleSlab.stepSound.getPlaceSound(), (this.m_DoubleSlab.stepSound.getVolume() + 1.0F) / 2.0F, this.m_DoubleSlab.stepSound.getPitch() * 0.8F);
                --itemStack.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}
