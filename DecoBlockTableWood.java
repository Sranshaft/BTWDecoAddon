package net.minecraft.src;

import java.util.List;

public class DecoBlockTableWood extends Block implements FCIBlockSolidTop 
{
	private Icon[] m_IconByMetadataArray;

	public DecoBlockTableWood(int id)
	{
		super(id, Material.wood);
		
		this.setUnlocalizedName("decoBlockTableWood");
		this.setHardness(Block.wood.blockHardness);
		this.setResistance(Block.wood.blockResistance);
		this.setStepSound(Block.soundWoodFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		
		Block.useNeighborBrightness[id] = true;
		Block.lightOpacity[id] = 255;
		
		DecoAddonManager.register(this, DecoUtilsStrings.WOOD_TAGS, DecoUtilsStrings.WOOD_NAMES, " Table");
	}
	
	//CLIENT ONLY

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World world, int x, int y, int z)
    {
        return world.getBlockId(x, y, z);
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
    
    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int metadata)
    {
        return metadata;
    }

	public boolean DoesBlockHaveSolidTop(IBlockAccess bAccess, int x, int y, int z) 
	{
		return true;
	}
	
	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public Icon getIcon(int side, int metadata)
	{
		return this.m_IconByMetadataArray[metadata];
	}
	
	/**
	 * When this method is called, your block should register all the icons it needs
	 * with the given IconRegister. This is the only chance you get to register
	 * icons.
	 */
	public void registerIcons(IconRegister register) 
	{
		this.m_IconByMetadataArray = new Icon[DecoUtilsStrings.WOOD_TAGS.length];
		
		for (int index = 0; index < DecoUtilsStrings.WOOD_TAGS.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon("decoBlockFurniture_" + DecoUtilsStrings.WOOD_TAGS[index]);
		}
	}
	
	/**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
    	for (int index = 0; index < DecoUtilsStrings.WOOD_TAGS.length; index++)
    	{
    		var3.add(new ItemStack(var1, 1, index));
    	}
    }

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether or
	 * not to render the shared face of two adjacent blocks and also whether the
	 * player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube() 
	{
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False
	 * (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock() 
	{
		return false;
	}
	
	public boolean shouldSideBeRendered(IBlockAccess bAccess, int x, int y, int z, int side)
	{
		return true;
	}
	
	/**
     * Return whether an adjacent block can connect to a wall.
     */
    public boolean canConnectTo(IBlockAccess bAccess, int x, int y, int z)
    {
        int var5 = bAccess.getBlockId(x, y, z);
        
        return var5 != this.blockID ? false : true;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return AxisAlignedBB.getAABBPool().getAABB((double) ((float) x), (double) ((float) y), (double) ((float) z), 
												   (double) ((float) x + 1.0F), (double) ((float) y + 1.0F), (double) ((float) z + 1.0F));
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z)
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
	{
		boolean isBlockWest = this.canConnectTo(render.blockAccess, x - 1, y, z);
		boolean isBlockEast = this.canConnectTo(render.blockAccess, x + 1, y, z);
		boolean isBlockNorth = this.canConnectTo(render.blockAccess, x, y, z - 1);
		boolean isBlockSouth = this.canConnectTo(render.blockAccess, x, y, z + 1);
		boolean isBlockNorthAndSouth = isBlockNorth && isBlockSouth;
		boolean isBlockEastAndWest = isBlockWest && isBlockEast;
		
		if (isBlockNorthAndSouth || isBlockEastAndWest)
		{
			if (isBlockNorthAndSouth && !isBlockEastAndWest)
			{
				//E Side
				render.setRenderBounds(0.9F, 0.6F, 0.0F, 0.95F, 0.85F, 1.0F);
				render.renderStandardBlock(this, x, y, z);
				
				//W Side
				render.setRenderBounds(0.05F, 0.6F, 0.0F, 0.1F, 0.85F, 1.0F);
				render.renderStandardBlock(this, x, y, z);
			}

			if (isBlockEastAndWest && !isBlockNorthAndSouth)
			{
				//N Side
				render.setRenderBounds(0.0F, 0.6F, 0.05F, 1.0F, 0.85F, 0.1F);
				render.renderStandardBlock(this, x, y, z);
				
				//S Side
				render.setRenderBounds(0.0F, 0.6F, 0.9F, 1.0F, 0.85F, 0.95F);
				render.renderStandardBlock(this, x, y, z);
			}	
		}
		else if ((isBlockWest || isBlockEast || isBlockNorth || isBlockSouth) && !isBlockNorthAndSouth && !isBlockEastAndWest)
		{
			if (isBlockWest && !(isBlockEast || isBlockNorth || isBlockSouth))
			{
				//NE LEG
				render.setRenderBounds(0.85F, 0.0F, 0.025F, 0.975F, 0.85F, 0.15F);
				render.renderStandardBlock(this, x, y, z);
				
				//SE LEG
				render.setRenderBounds(0.85F, 0F, 0.85F, 0.975F, 0.85F, 0.975F);
				render.renderStandardBlock(this, x, y, z);
				
				//N Side
				render.setRenderBounds(0.0F, 0.6F, 0.05F, 0.95F, 0.85F, 0.1F);
				render.renderStandardBlock(this, x, y, z);
				
				//S Side
				render.setRenderBounds(0.0F, 0.6F, 0.9F, 0.95F, 0.85F, 0.95F);
				render.renderStandardBlock(this, x, y, z);
				
				//E Side
				render.setRenderBounds(0.9F, 0.6F, 0.05F, 0.95F, 0.85F, 0.95F);
				render.renderStandardBlock(this, x, y, z);
			}
			
			if (isBlockEast && !(isBlockWest || isBlockNorth || isBlockSouth))
			{
				//NW Leg
				render.setRenderBounds(0.025F, 0.0F, 0.025F, 0.15F, 0.85F, 0.15F);
				render.renderStandardBlock(this, x, y, z);
				
				//SW Leg
				render.setRenderBounds(0.025F, 0.0F, 0.85F, 0.15F, 0.85F, 0.975F);
				render.renderStandardBlock(this, x, y, z);
				
				//N Side
				render.setRenderBounds(0.05F, 0.6F, 0.05F, 1.0F, 0.85F, 0.1F);
				render.renderStandardBlock(this, x, y, z);
				
				//S Side
				render.setRenderBounds(0.05F, 0.6F, 0.9F, 1.0F, 0.85F, 0.95F);
				render.renderStandardBlock(this, x, y, z);
				
				//W Side
				render.setRenderBounds(0.05F, 0.6F, 0.05F, 0.1F, 0.85F, 0.95F);
				render.renderStandardBlock(this, x, y, z);
			}
			
			if (isBlockNorth)
			{
				//SW Leg
				render.setRenderBounds(0.025F, 0.0F, 0.85F, 0.15F, 0.85F, 0.975F);
				render.renderStandardBlock(this, x, y, z);
				
				//SE LEG
				render.setRenderBounds(0.85F, 0F, 0.85F, 0.975F, 0.85F, 0.975F);
				render.renderStandardBlock(this, x, y, z);
				
				//S Side
				render.setRenderBounds(0.05F, 0.6F, 0.9F, 0.95F, 0.85F, 0.95F);
				render.renderStandardBlock(this, x, y, z);
				
				//E Side
				render.setRenderBounds(0.9F, 0.6F, 0.0F, 0.95F, 0.85F, 0.95F);
				render.renderStandardBlock(this, x, y, z);
				
				//W Side
				render.setRenderBounds(0.05F, 0.6F, 0.0F, 0.1F, 0.85F, 0.95F);
				render.renderStandardBlock(this, x, y, z);
			}
			
			if (isBlockSouth)
			{
				//NW Leg
				render.setRenderBounds(0.025F, 0.0F, 0.025F, 0.15F, 0.85F, 0.15F);
				render.renderStandardBlock(this, x, y, z);
				
				//NE LEG
				render.setRenderBounds(0.85F, 0.0F, 0.025F, 0.975F, 0.85F, 0.15F);
				render.renderStandardBlock(this, x, y, z);
				
				//N Side
				render.setRenderBounds(0.05F, 0.6F, 0.05F, 0.95F, 0.85F, 0.1F);
				render.renderStandardBlock(this, x, y, z);
				
				//E Side
				render.setRenderBounds(0.9F, 0.6F, 0.05F, 0.95F, 0.85F, 1.0F);
				render.renderStandardBlock(this, x, y, z);
				
				//W Side
				render.setRenderBounds(0.05F, 0.6F, 0.05F, 0.1F, 0.85F, 1.0F);
				render.renderStandardBlock(this, x, y, z);
			}
		}
		else if (!isBlockNorthAndSouth && !isBlockEastAndWest)
		{
			//NW Leg
			render.setRenderBounds(0.025F, 0.0F, 0.025F, 0.15F, 0.85F, 0.15F);
			render.renderStandardBlock(this, x, y, z);
			
			//SW Leg
			render.setRenderBounds(0.025F, 0.0F, 0.85F, 0.15F, 0.85F, 0.975F);
			render.renderStandardBlock(this, x, y, z);
			
			//NE LEG
			render.setRenderBounds(0.85F, 0.0F, 0.025F, 0.975F, 0.85F, 0.15F);
			render.renderStandardBlock(this, x, y, z);
			
			//SE LEG
			render.setRenderBounds(0.85F, 0F, 0.85F, 0.975F, 0.85F, 0.975F);
			render.renderStandardBlock(this, x, y, z);
			
			//N Side
			render.setRenderBounds(0.05F, 0.6F, 0.05F, 0.95F, 0.85F, 0.1F);
			render.renderStandardBlock(this, x, y, z);
			
			//S Side
			render.setRenderBounds(0.05F, 0.6F, 0.9F, 0.95F, 0.85F, 0.95F);
			render.renderStandardBlock(this, x, y, z);
			
			//E Side
			render.setRenderBounds(0.9F, 0.6F, 0.05F, 0.95F, 0.85F, 0.95F);
			render.renderStandardBlock(this, x, y, z);
			
			//W Side
			render.setRenderBounds(0.05F, 0.6F, 0.05F, 0.1F, 0.85F, 0.95F);
			render.renderStandardBlock(this, x, y, z);
		}
		
		//Top
		render.setRenderBounds(0.0F, 0.85F, 0.0F, 1.0F, 1.0F, 1.0F);
		render.renderStandardBlock(this, x, y, z);
		
		return true;
	}
	
	public void RenderBlockAsItem(RenderBlocks render, int var2, float var3)
	{
		//Legs
		render.setRenderBounds(0.025F, 0.0F, 0.025F, 0.15F, 0.85F, 0.15F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
		render.setRenderBounds(0.025F, 0.0F, 0.85F, 0.15F, 0.85F, 0.975F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
		render.setRenderBounds(0.85F, 0.0F, 0.025F, 0.975F, 0.85F, 0.15F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
		render.setRenderBounds(0.85F, 0F, 0.85F, 0.975F, 0.85F, 0.975F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
		
		//Sides
		render.setRenderBounds(0.05F, 0.6F, 0.05F, 0.95F, 0.85F, 0.1F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
		render.setRenderBounds(0.05F, 0.6F, 0.05F, 0.1F, 0.85F, 0.95F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
		render.setRenderBounds(0.05F, 0.6F, 0.9F, 0.1F, 0.85F, 0.1F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
		render.setRenderBounds(0.05F, 0.6F, 0.9F, 0.95F, 0.85F, 0.95F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
		
		//Back
		render.setRenderBounds(0.0F, 0.85F, 0.0F, 1.0F, 1.0F, 1.0F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
	}
}