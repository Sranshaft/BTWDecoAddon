package net.minecraft.src;

import java.util.Random;

public class DecoBlockLadderMetal extends FCBlockLadderBase
{
	public DecoBlockLadderMetal(int id)
	{
		super(id);
		
		this.setUnlocalizedName("decoBlockIronLadder");
		this.setHardness(Block.blockIron.blockHardness);
		this.setResistance(Block.blockIron.blockResistance);
		this.SetBlockMaterial(Material.anvil);
		this.setStepSound(Block.soundMetalFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this);
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
    	return this.blockIcon;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
    	this.blockIcon = register.registerIcon("decoBlockIronLadder");
    }
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
    	return DecoModuleDecoration.decoBlockLadderIronID;
    }
	
	/**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (player.getCurrentEquippedItem() != null)
        {
	    	ItemStack currentItem = player.getCurrentEquippedItem();
	
	        if (currentItem.itemID == new ItemStack(DecoModuleDecoration.decoBlockLadderMetalIron).itemID)
	        {
	        	for (int yIndex = y; yIndex > 0; yIndex--)
	        	{
	        		if (world.isAirBlock(x, yIndex, z))
	        		{
	        			FCUtilsMisc.PlayPlaceSoundForBlock(world, x, y, z);
	        			world.setBlockAndMetadataWithNotify(x, yIndex, z, DecoModuleDecoration.decoBlockLadderIronID, world.getBlockMetadata(x, yIndex + 1, z));
	        			
	        			currentItem.stackSize--;

	                    if (currentItem.stackSize <= 0)
	                        player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
	        			
	        			return true;
	        		}
	        	}
	        }
        }
        
        return false;
    }
    
    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
    	if (world.getBlockId(x, y + 1, z) == DecoModuleDecoration.decoBlockLadderIronID) return world.getBlockMetadata(x, y + 1, z);
    	
    	if (this.CanAttachToFacing(world, x, y, z, FCUtilsMisc.GetOppositeFacing(side)))
        {
            metadata = this.SetFacing(metadata, side);
        }
        else
        {
            for (int var10 = 2; var10 <= 5; ++var10)
            {
                if (this.CanAttachToFacing(world, x, y, z, var10))
                {
                    metadata = this.SetFacing(metadata, FCUtilsMisc.GetOppositeFacing(var10));
                    break;
                }
            }
        }

        return metadata;
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
    	if (world.getBlockId(x, y + 1, z) == DecoModuleDecoration.decoBlockLadderIronID) return true;
    	
        for (int side = 2; side <= 5; ++side)
        {
            if (this.CanAttachToFacing(world, x, y, z, FCUtilsMisc.GetOppositeFacing(side)))
            {
                return true;
            }
        }

        return false;
    }
	
	/**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, int side)
    {
        int var6 = world.getBlockMetadata(x, y, z);

        if (!this.CanAttachToFacing(world, x, y, z, FCUtilsMisc.GetOppositeFacing(this.GetFacing(var6))) && world.getBlockId(x, y + 1, z) != this.blockID)
        {
            this.dropBlockAsItem(world, x, y, z, var6, 0);
            world.setBlockToAir(x, y, z);
        }
    }
	
	protected boolean CanAttachToFacing(World world, int x, int y, int z, int side)
    {
		if (side >= 2)
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(x, y, z, side);
            return FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, var6.i, var6.j, var6.k, FCUtilsMisc.GetOppositeFacing(side));
        }
        
        return false;
    }
	
	/**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess bAccess, int x, int y, int z, int side)
    {
        return true;
    }
    
    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        this.setBlockBoundsBasedOnState(var1.blockAccess, var2, var3, var4);
        var1.setRenderBoundsFromBlock(this);
        return this.renderBlockLadder(var1, var2, var3, var4);
    }

    public boolean renderBlockLadder(RenderBlocks var1, int var2, int var3, int var4)
    {
    	IBlockAccess var5 = var1.blockAccess;
    	
    	Tessellator var7 = Tessellator.instance;
    	var7.setBrightness(this.getMixedBrightnessForBlock(var5, var2, var3, var4));
    	float var8 = 1.0F;
    	var7.setColorOpaque_F(var8, var8, var8);
    	
        Icon var9 = this.blockIcon;

        if (var1.hasOverrideBlockTexture())
        {
            var9 = var1.GetOverrideTexture();
        }

        double var12 = (double)var9.getMinU();
        double var14 = (double)var9.getMinV();
        double var16 = (double)var9.getMaxU();
        double var18 = (double)var9.getMaxV();
        double var20 = 0.05000000074505806D;
        
        int var6 = this.GetFacing(var5, var2, var3, var4);
        if (var6 == 5)
        {
        	var7.addVertexWithUV((double)var2 + var20, (double)(var3 + 1), (double)(var4 + 1), var12, var14);
        	var7.addVertexWithUV((double)var2 + var20, (double)(var3 + 0), (double)(var4 + 1), var12, var18);
        	var7.addVertexWithUV((double)var2 + var20, (double)(var3 + 0), (double)(var4 + 0), var16, var18);
        	var7.addVertexWithUV((double)var2 + var20, (double)(var3 + 1), (double)(var4 + 0), var16, var14);
        	var7.addVertexWithUV((double)var2 + var20, (double)(var3 + 1), (double)(var4 + 0), var16, var14);
        	var7.addVertexWithUV((double)var2 + var20, (double)(var3 + 0), (double)(var4 + 0), var16, var18);
        	var7.addVertexWithUV((double)var2 + var20, (double)(var3 + 0), (double)(var4 + 1), var12, var18);
        	var7.addVertexWithUV((double)var2 + var20, (double)(var3 + 1), (double)(var4 + 1), var12, var14);
        }
        else if (var6 == 4)
        {
        	var7.addVertexWithUV((double)(var2 + 1) - var20, (double)(var3 + 0), (double)(var4 + 1), var16, var18);
        	var7.addVertexWithUV((double)(var2 + 1) - var20, (double)(var3 + 1), (double)(var4 + 1), var16, var14);
        	var7.addVertexWithUV((double)(var2 + 1) - var20, (double)(var3 + 1), (double)(var4 + 0), var12, var14);
        	var7.addVertexWithUV((double)(var2 + 1) - var20, (double)(var3 + 0), (double)(var4 + 0), var12, var18);
        	var7.addVertexWithUV((double)(var2 + 1) - var20, (double)(var3 + 0), (double)(var4 + 0), var12, var18);
        	var7.addVertexWithUV((double)(var2 + 1) - var20, (double)(var3 + 1), (double)(var4 + 0), var12, var14);
        	var7.addVertexWithUV((double)(var2 + 1) - var20, (double)(var3 + 1), (double)(var4 + 1), var16, var14);
        	var7.addVertexWithUV((double)(var2 + 1) - var20, (double)(var3 + 0), (double)(var4 + 1), var16, var18);
        }
        else if (var6 == 3)
        {
        	var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)var4 + var20, var16, var18);
        	var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 1), (double)var4 + var20, var16, var14);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 1), (double)var4 + var20, var12, var14);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)var4 + var20, var12, var18);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)var4 + var20, var12, var18);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 1), (double)var4 + var20, var12, var14);
            var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 1), (double)var4 + var20, var16, var14);
            var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)var4 + var20, var16, var18);
        }
        else if (var6 == 2)
        {
        	var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 1), (double)(var4 + 1) - var20, var12, var14);
            var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)(var4 + 1) - var20, var12, var18);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)(var4 + 1) - var20, var16, var18);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 1), (double)(var4 + 1) - var20, var16, var14);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 1), (double)(var4 + 1) - var20, var16, var14);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)(var4 + 1) - var20, var16, var18);
            var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)(var4 + 1) - var20, var12, var18);
            var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 1), (double)(var4 + 1) - var20, var12, var14);
        }

        return true;
    }
}
