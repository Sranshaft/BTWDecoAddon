package net.minecraft.src;

public class DecoBlockFlowerPot extends FCBlockFlowerPot
{
	public DecoBlockFlowerPot(int id)
	{
		super(id);
		
		this.setUnlocalizedName("flowerPot");
		this.setHardness(0.1F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		
		this.setBlockBoundsForItemRender();
	}
	
	/**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        ItemStack currentItem = player.inventory.getCurrentItem();

        if (currentItem == null && world.getBlockMetadata(x, y, z) != 0)
        {
        	int var10 = world.getBlockMetadata(x, y, z);
        	player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)this.getPlantForMeta(var10));
        	world.setBlockMetadataWithNotify(x, y, z, 0);
        	
        	return true;
        }
        else if (currentItem != null && world.getBlockMetadata(x, y, z) == 0)
        {
            int var11 = this.GetMetadataForItemStack(currentItem);

            if (var11 > 0)
            {
                world.setBlockMetadataWithNotify(x, y, z, var11, 2);

                if (!player.capabilities.isCreativeMode)
                {
                    --currentItem.stackSize;

                    if (currentItem.stackSize <= 0)
                    {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                    }
                }

                return true;
            }
        }
        
        return false;
    }
    
    private ItemStack GetPlantStackForMetadata(int metadata)
    {
        return metadata == 7 ? new ItemStack(FCBetterThanWolves.fcItemMushroomRed) : (metadata == 8 ? new ItemStack(FCBetterThanWolves.fcItemMushroomBrown) : this.getPlantForMeta(metadata));
    }

    private int GetMetadataForItemStack(ItemStack itemStack)
    {
        int itemID = itemStack.getItem().itemID;
        return itemID == FCBetterThanWolves.fcItemMushroomRed.itemID ? 7 : (itemID == FCBetterThanWolves.fcItemMushroomBrown.itemID ? 8 : this.getMetaForPlant(itemStack));
    }
    
    /**
     * Return the flower pot metadata value associated with the specified item.
     */
    public static int getMetaForPlant(ItemStack itemStack)
    {
        int itemID = itemStack.getItem().itemID;

        if      (itemID == Block.plantRed.blockID) return 1;
        else if (itemID == Block.plantYellow.blockID) return 2;
        else if (itemID == Block.mushroomRed.blockID) return 7;
        else if (itemID == Block.mushroomBrown.blockID) return 8;
        else if (itemID == Block.cactus.blockID) return 9;
        else if (itemID == Block.deadBush.blockID) return 10;
        else if (itemID == DecoModuleDecoration.decoBlockBambooID) return 12;
        else
        {
            if (itemID == Block.sapling.blockID)
            {
                switch (itemStack.getItemDamage())
                {
                    case 0 : return 3;
                    case 1 : return 4;
                    case 2 : return 5;
                    case 3 : return 6;
                }
            }

            if (itemID == Block.tallGrass.blockID)
            {
                switch (itemStack.getItemDamage())
                {
                    case 2 : return 11;
                }
            }
            
            if (itemID == Block.leaves.blockID)
            {
            	switch (itemStack.getItemDamage())
            	{
            		case 0 : return 13;
            		case 2 : return 14;
            		case 3 : return 15;
            	}
            }

            return 0;
        }
    }
    
    public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
    {
        this.setBlockBoundsBasedOnState(render.blockAccess, x, y, z);
        render.setRenderBoundsFromBlock(this);
        return this.renderBlockFlowerpot(render, this, x, y, z);
    }
    
    private boolean renderBlockFlowerpot(RenderBlocks render, BlockFlowerPot blockFlowerPot, int x, int y, int z)
    {
        render.renderStandardBlock(blockFlowerPot, x, y, z);
        Tessellator var5 = Tessellator.instance;
        var5.setBrightness(blockFlowerPot.getMixedBrightnessForBlock(render.blockAccess, x, y, z));
        float var6 = 1.0F;
        int var7 = blockFlowerPot.colorMultiplier(render.blockAccess, x, y, z);
        Icon var8 = render.getBlockIconFromSide(blockFlowerPot, 0);
        float var9 = (float)(var7 >> 16 & 255) / 255.0F;
        float var10 = (float)(var7 >> 8 & 255) / 255.0F;
        float var11 = (float)(var7 & 255) / 255.0F;
        var5.setColorOpaque_F(var6 * var9, var6 * var10, var6 * var11);
        float var12 = 0.1865F;
        render.renderFaceXPos(blockFlowerPot, (double)((float)x - 0.5F + var12), (double)y, (double)z, var8);
        render.renderFaceXNeg(blockFlowerPot, (double)((float)x + 0.5F - var12), (double)y, (double)z, var8);
        render.renderFaceZPos(blockFlowerPot, (double)x, (double)y, (double)((float)z - 0.5F + var12), var8);
        render.renderFaceZNeg(blockFlowerPot, (double)x, (double)y, (double)((float)z + 0.5F - var12), var8);
        render.renderFaceYPos(blockFlowerPot, (double)x, (double)((float)y - 0.5F + var12 + 0.1875F), (double)z, 
        		render.getBlockIcon(DecoModuleTweaks.decoSubModuleExtendedDirtAndGrassBlocks.decoBlockDirt));
        
        int metadata = render.blockAccess.getBlockMetadata(x, y, z);

        if (metadata != 0)
        {
            float var13 = 0.0F;
            float var15 = 4.0F;
            float var16 = 0.0F;
            BlockFlower var17 = null;

            switch (metadata)
            {
                case 1:
                    var17 = Block.plantRed;
                    break;

                case 2:
                    var17 = Block.plantYellow;

                case 3:
                case 4:
                case 5:
                case 6:
                default:
                    break;

                case 7:
                    var17 = Block.mushroomRed;
                    break;

                case 8:
                    var17 = Block.mushroomBrown;
            }

            var5.addTranslation(var13 / 16.0F, var15 / 16.0F, var16 / 16.0F);

            if (var17 != null)
            {
            	render.renderBlockByRenderType(var17, x, y, z);
            }
            else if (metadata == 3)
            {
            	render.drawCrossedSquares(Block.sapling, 0, (double)x, (double)y, (double)z, 0.75F);
            }
            else if (metadata == 4)
            {
            	render.drawCrossedSquares(Block.sapling, 1, (double)x, (double)y, (double)z, 0.75F);
            }
            else if (metadata == 5)
            {
            	render.drawCrossedSquares(Block.sapling, 2, (double)x, (double)y, (double)z, 0.75F);
            }
            else if (metadata == 6)
            {
            	render.drawCrossedSquares(Block.sapling, 3, (double)x, (double)y, (double)z, 0.75F);
            }
            else if (metadata == 9)
            {
            	float var18 = 0.125F;
                render.setRenderBounds((double)(0.5F - var18), 0.0D, (double)(0.5F - var18), (double)(0.5F + var18), 0.25D, (double)(0.5F + var18));
                render.renderStandardBlock(Block.cactus, x, y, z);
                render.setRenderBounds((double)(0.5F - var18), 0.25D, (double)(0.5F - var18), (double)(0.5F + var18), 0.5D, (double)(0.5F + var18));
                render.renderStandardBlock(Block.cactus, x, y, z);
                render.setRenderBounds((double)(0.5F - var18), 0.5D, (double)(0.5F - var18), (double)(0.5F + var18), 0.75D, (double)(0.5F + var18));
                render.renderStandardBlock(Block.cactus, x, y, z);
                render.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            }
            else if (metadata == 10)
            {
            	render.drawCrossedSquares(Block.deadBush, 2, (double)x, (double)y, (double)z, 0.75F);
            }
            else if (metadata == 11)
            {
                var7 = Block.tallGrass.colorMultiplier(render.blockAccess, x, y, z);
                var9 = (float)(var7 >> 16 & 255) / 255.0F;
                var10 = (float)(var7 >> 8 & 255) / 255.0F;
                var11 = (float)(var7 & 255) / 255.0F;
                var5.setColorOpaque_F(var6 * var9, var6 * var10, var6 * var11);
                render.drawCrossedSquares(Block.tallGrass, 2, (double)x, (double)y, (double)z, 0.75F);
            }
            else if (metadata == 12)
            {
            	float var18 = 0.125F;
                render.setRenderBounds((double)(0.5F - var18), 0.0D, (double)(0.5F - var18), (double)(0.5F + var18), 0.25D, (double)(0.5F + var18));
                render.renderStandardBlock(DecoModuleDecoration.decoBlockBamboo, x, y, z);
                render.setRenderBounds((double)(0.5F - var18), 0.25D, (double)(0.5F - var18), (double)(0.5F + var18), 0.5D, (double)(0.5F + var18));
                render.renderStandardBlock(DecoModuleDecoration.decoBlockBamboo, x, y, z);
                render.setRenderBounds((double)(0.5F - var18), 0.5D, (double)(0.5F - var18), (double)(0.5F + var18), 0.75D, (double)(0.5F + var18));
                render.renderStandardBlock(DecoModuleDecoration.decoBlockBamboo, x, y, z);
                render.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            }
            else if (metadata == 13)
            {
            	float var18 = 0.125F;
                render.setRenderBounds((double)(0.5F - var18), 0.0D, (double)(0.5F - var18), (double)(0.5F + var18), 0.25D, (double)(0.5F + var18));
                render.renderStandardBlock(Block.leaves, x, y, z);
                render.setRenderBounds((double)(0.5F - var18), 0.25D, (double)(0.5F - var18), (double)(0.5F + var18), 0.5D, (double)(0.5F + var18));
                render.renderStandardBlock(Block.leaves, x, y, z);
                render.setRenderBounds((double)(0.5F - var18), 0.5D, (double)(0.5F - var18), (double)(0.5F + var18), 0.75D, (double)(0.5F + var18));
                render.renderStandardBlock(Block.leaves, x, y, z);
                render.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            }
            
            var5.addTranslation(-var13 / 16.0F, -var15 / 16.0F, -var16 / 16.0F);
        }

        return true;
    }
}
