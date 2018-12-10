package net.minecraft.src;

public class DecoItemChain extends FCItemRope
{
	public DecoItemChain(int id)
	{
		super(id);
		
		this.setUnlocalizedName("decoItemChain");
		this.setCreativeTab(CreativeTabs.tabTransport);
	}
	
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (itemStack.stackSize == 0) return false;
        else
        {
            int securedBlockID = world.getBlockId(x, y, z);

            if (securedBlockID == FCBetterThanWolves.fcAnchor.blockID && ((FCBlockAnchor)FCBetterThanWolves.fcAnchor).GetFacing(world, x, y, z) != 1 || securedBlockID == DecoModuleTransportation.decoBlockChainID)
            {
                for (int yIndex = y - 1; yIndex >= 0; --yIndex)
                {
                    int checkBlockID = world.getBlockId(x, yIndex, z);

                    if (FCUtilsWorld.IsReplaceableBlock(world, x, yIndex, z))
                    {
                        int var14 = DecoModuleTransportation.decoBlockChain.onBlockPlaced(world, x, yIndex, z, side, 0.0F, 0.0F, 0.0F, 0);
                        var14 = DecoModuleTransportation.decoBlockChain.PreBlockPlacedBy(world, x, yIndex, z, var14, player);

                        if (world.setBlockAndMetadataWithNotify(x, yIndex, z, DecoModuleTransportation.decoBlockChainID, var14))
                        {
                            FCBetterThanWolves.fcRopeBlock.onBlockPlacedBy(world, x, yIndex, z, player, itemStack);
                            FCBetterThanWolves.fcRopeBlock.onPostBlockPlaced(world, x, yIndex, z, var14);
                            
                            world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), 
                            		DecoModuleTransportation.decoBlockChain.stepSound.getStepSound(), 
                            		(DecoModuleTransportation.decoBlockChain.stepSound.getVolume() + 1.0F) / 2.0F, 
                            		DecoModuleTransportation.decoBlockChain.stepSound.getPitch() * 0.8F);
                            
                            --itemStack.stackSize;
                            return true;
                        }

                        return false;
                    }

                    if (checkBlockID != DecoModuleTransportation.decoBlockChainID) return false;
                }
            }

            return false;
        }   
    }
}

