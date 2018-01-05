package net.minecraft.src;

public class DecoItemSpade extends ItemSpade
{
	public DecoItemSpade(int id, EnumToolMaterial toolMaterial)
	{
		super(id, toolMaterial);
	}
	
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
        if (!player.canPlayerEdit(x, y, z, par7, itemStack))
        	return false;
        
        int blockID = world.getBlockId(x, y, z);
        
        if (par7 == 0 && !world.isAirBlock(x, y + 1, z) || (blockID != Block.grass.blockID))
            return false;
        else
        {
        	if (world.isRemote)
                return true;
            else
            {
            	int metadata = world.getBlockMetadata(x, y, z);
            	if (metadata != 1)
            	{
            		Block pathBlock = DecoSubModuleExtendedDirtAndGrassBlocks.decoBlockGrassPath;
                    world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), 
                    		pathBlock.stepSound.getStepSound(), (pathBlock.stepSound.getVolume() + 1.0F) / 2.0F, pathBlock.stepSound.getPitch() * 0.8F);
                    
            		world.setBlockAndMetadataWithNotify(x, y, z, DecoSubModuleExtendedDirtAndGrassBlocks.decoBlockGrassPathID, metadata);
            		itemStack.damageItem(1, player);
            		return true;
            	}
            }
        }
        
        return false;
    }
}
