package net.minecraft.src;

public class DecoItemHoe extends FCItemHoe
{
	public DecoItemHoe(int id, EnumToolMaterial material, String tag)
	{
		super(id, material);
		
		this.setUnlocalizedName("hoe" + tag);
	}
	
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (player.canPlayerEdit(x, y, z, side, itemStack))
        {
            int var11 = world.getBlockId(x, y, z);
            int var14 = world.getBlockMetadata(x, y, z);
            
            boolean var12 = false;
            Block var13 = Block.tilledField;
            
            if (var14 != 0) return false;
            
            if (side != 0 && world.isAirBlock(x, y + 1, z))
            {
                if (var11 != Block.grass.blockID && var11 != Block.dirt.blockID &&
            	   (var11 != FCBetterThanWolves.fcBlockAestheticOpaqueEarth.blockID || 
            	   !((FCBlockAestheticOpaqueEarth)FCBetterThanWolves.fcBlockAestheticOpaqueEarth).IsBlightFromMetadata(var14)))
            	{
            		if (var11 == FCBetterThanWolves.fcBlockDirtLoose.blockID)
                	{
                    	var12 = true;
                    	var13 = Block.tilledField;
                	}
            	}
            	else
            	{
                	var12 = true;
                	var13 = FCBetterThanWolves.fcBlockDirtLoose;
                }
            }

            if (var12)
            {
                if (!world.isRemote)
                {
                    this.CheckForSeedDropOnItemUse(itemStack, player, world, x, y, z);
                    world.setBlockWithNotify(x, y, z, var13.blockID);
                    itemStack.damageItem(1, player);
                }
                else
                {
                    world.playSound((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, var13.stepSound.getStepSound(), (var13.stepSound.getVolume() + 1.0F) / 2.0F, var13.stepSound.getPitch() * 0.8F);
                }

                return true;
            }
        }

        return false;
    }
    
    private void CheckForSeedDropOnItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        int var7 = var3.getBlockId(var4, var5, var6);

        if (var7 == Block.grass.blockID)
        {
            int var8 = this.GetHempSeedDropPercentage(var1);

            if (var3.rand.nextInt(100) < var8)
            {
                float var9 = 0.7F;
                float var10 = var3.rand.nextFloat() * var9 + (1.0F - var9) * 0.5F;
                float var11 = 1.2F;
                float var12 = var3.rand.nextFloat() * var9 + (1.0F - var9) * 0.5F;
                EntityItem var13 = new EntityItem(var3, (double)((float)var4 + var10), (double)((float)var5 + var11), (double)((float)var6 + var12), new ItemStack(FCBetterThanWolves.fcHempSeeds));
                var13.delayBeforeCanPickup = 10;
                var3.spawnEntityInWorld(var13);
            }
        }
    }
}
