package net.minecraft.src;

public interface DecoIPlant 
{
	/**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata);
    
    public boolean isBlockReplaceable(World world, int x, int y, int z);
    
    public boolean isShearable(ItemStack item, World world, int x, int y, int z);
    
    public ItemStack onSheared(ItemStack item, World world, int x, int y, int z, int fortune);
    
    public boolean isBlockFoliage(World world, int x, int y, int z);
}
