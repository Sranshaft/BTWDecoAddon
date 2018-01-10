package net.minecraft.src;

public class DecoItemHoe extends FCItemHoe
{
	public DecoItemHoe(int id, EnumToolMaterial toolMaterial)
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
        
        int checkBlockID = world.getBlockId(x, y, z);
        
        if (par7 == 0 && !world.isAirBlock(x, y + 1, z) || (checkBlockID != Block.grass.blockID && checkBlockID != Block.dirt.blockID && checkBlockID != FCBetterThanWolves.fcBlockAestheticOpaqueEarth.blockID))
            return false;
        else
        {
        	if (!((FCBlockAestheticOpaqueEarth)FCBetterThanWolves.fcBlockAestheticOpaqueEarth).IsBlightFromMetadata(world.getBlockMetadata(x, y, z)))
        		return false;
        	
            Block tilledBlock = Block.tilledField;
            world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), tilledBlock.stepSound.getStepSound(), (tilledBlock.stepSound.getVolume() + 1.0F) / 2.0F, tilledBlock.stepSound.getPitch() * 0.8F);

            if (world.isRemote)
                return true;
            else
            {
            	int metadata = world.getBlockMetadata(x, y, z);
            	
            	if (metadata != 1)
            	{
            		this.CheckForSeedDropOnItemUse(itemStack, player, world, x, y, z);
            		world.setBlockAndMetadataWithNotify(x, y, z, tilledBlock.blockID, metadata);
            		itemStack.damageItem(1, player);
            		return true;
            	}
            }
        }
        
        return false;
    }
    
    private void CheckForSeedDropOnItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z)
    {
        int checkBlockID = world.getBlockId(x, y, z);

        if (checkBlockID == Block.grass.blockID)
        {
            int chanceDrop = this.GetHempSeedDropPercentage(itemStack);

            if (world.rand.nextInt(100) < chanceDrop)
            {
                float motion = 0.7F;
                float spawnX = world.rand.nextFloat() * motion + (1.0F - motion) * 0.5F;
                float spawnY = 1.2F;
                float spawnZ = world.rand.nextFloat() * motion + (1.0F - motion) * 0.5F;
                
                EntityItem itemEntity = new EntityItem(world, (double)((float)x + spawnX), (double)((float)y + spawnY), (double)((float)z + spawnZ), 
                		new ItemStack(FCBetterThanWolves.fcHempSeeds));
                itemEntity.delayBeforeCanPickup = 10;
                
                world.spawnEntityInWorld(itemEntity);
            }
        }
    }

    protected int GetHempSeedDropPercentage(ItemStack itemStack)
    {
        byte var2 = 0;
        int itemID = itemStack.itemID;

        if (itemID == Item.hoeWood.itemID)
            var2 = 1;
        else if (itemID == Item.hoeStone.itemID)
            var2 = 2;
        else if (itemID == Item.hoeIron.itemID)
            var2 = 4;
        else if (itemID == Item.hoeDiamond.itemID)
            var2 = 8;
        else if (itemID == Item.hoeGold.itemID)
            var2 = 16;
        
        return var2;
    }
}
