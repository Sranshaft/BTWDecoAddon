package net.minecraft.src;

public class DecoItemChainRope extends Item
{
	public DecoItemChainRope(int id)
	{
		super(id);
		
		this.setUnlocalizedName("decoItemChainRope");
		this.setCreativeTab(CreativeTabs.tabTransport);
	}
	
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (itemStack.stackSize == 0)
        {
            return false;
        }
        else
        {
            int var11 = world.getBlockId(x, y, z);

            if (var11 == FCBetterThanWolves.fcAnchor.blockID && ((FCBlockAnchor)FCBetterThanWolves.fcAnchor).GetFacing(world, x, y, z) != 1 || var11 == DecoModuleDecoration.decoBlockChainRopeID)
            {
                for (int var12 = y - 1; var12 >= 0; --var12)
                {
                    int var13 = world.getBlockId(x, var12, z);

                    if (FCUtilsWorld.IsReplaceableBlock(world, x, var12, z))
                    {
                        int var14 = DecoModuleDecoration.decoBlockChainRope.onBlockPlaced(world, x, var12, z, side, 0.0F, 0.0F, 0.0F, 0);
                        var14 = DecoModuleDecoration.decoBlockChainRope.PreBlockPlacedBy(world, x, var12, z, var14, player);

                        if (world.setBlockAndMetadataWithNotify(x, var12, z, DecoModuleDecoration.decoBlockChainRopeID, var14))
                        {
                            FCBetterThanWolves.fcRopeBlock.onBlockPlacedBy(world, x, var12, z, player, itemStack);
                            FCBetterThanWolves.fcRopeBlock.onPostBlockPlaced(world, x, var12, z, var14);
                            
                            world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), 
                            		DecoModuleDecoration.decoBlockChainRope.stepSound.getStepSound(), 
                            		(DecoModuleDecoration.decoBlockChainRope.stepSound.getVolume() + 1.0F) / 2.0F, 
                            		DecoModuleDecoration.decoBlockChainRope.stepSound.getPitch() * 0.8F);
                            
                            --itemStack.stackSize;
                            return true;
                        }

                        return false;
                    }

                    if (var13 != DecoModuleDecoration.decoBlockChainRopeID)
                    {
                        return false;
                    }
                }
            }

            return false;
        }   
    }
}
