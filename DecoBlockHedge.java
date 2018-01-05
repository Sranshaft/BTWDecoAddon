package net.minecraft.src;

public class DecoBlockHedge extends Block implements FCIBlock
{
	private String m_Tag;
	
	public DecoBlockHedge(int id, String tag)
	{
		super(id, Material.leaves);
		setUnlocalizedName("decoBlockHedge." + tag);
        setHardness(Block.leaves.blockHardness);
        setResistance(Block.leaves.blockResistance / 3.0F);
        setStepSound(Block.leaves.stepSound);
        setBlockBounds(0.25F, 0.0F, 0.0F, 0.75F, 0.75F, 1.0F);
        setCreativeTab(CreativeTabs.tabDecorations);
        
        this.m_Tag = tag;
	}
	
	public int GetFacing(IBlockAccess bAccess, int x, int y, int z)
	{
		return bAccess.getBlockMetadata(x, y, z);
	}
	
	public void SetFacing(World world, int x, int y, int z, int direction)
	{
		world.setBlockMetadataWithNotify(x, y, z, direction);
	}
	
	public int GetFacingFromMetadata(int metadata)
	{
		return metadata;
	}
	
	public int SetFacingInMetadata(int direction, int metadata)
	{
		return metadata;
	}
	
	public boolean CanRotateOnTurntable(IBlockAccess bAccess, int x, int y, int z)
	{
		return true;
	}
	
	public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess bAccess, int x, int y, int z)
	{
		return false;
	}
	
	public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess bAccess, int x, int y, int z)
	{
		return false;
	}
	
	public void RotateAroundJAxis(World world, int x, int y, int z, boolean var5)
	{
		FCUtilsMisc.StandardRotateAroundJ(this, world, x, y, z, var5);
	}
	
	public int RotateMetadataAroundJAxis(int metadata, boolean var2)
	{
		return FCUtilsMisc.StandardRotateMetadataAroundJ(this, metadata, var2);
	}
	
	public boolean ToggleFacing(World world, int x, int y, int z, boolean var5)
	{
		this.RotateAroundJAxis(world, x, y, z, var5);
		return true;
	}
	
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		if (side < 2)
			side = 2;
		else
			side = FCUtilsMisc.GetOppositeFacing(side);

		return SetFacingInMetadata(metadata, side);
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack itemStack)
	{
		int var7 = FCUtilsMisc.ConvertPlacingEntityOrientationToBlockFacing(entity);
		this.SetFacing(world, x, y, z, var7);
		
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		int direction = this.GetFacing(world, x, y, z);
		
		return direction != 2 && direction != 3 
				? AxisAlignedBB.getAABBPool().getAABB((double) ((float) x), (double) ((float) y), (double) ((float) z + 0.5F - 0.25F), 
													  (double) ((float) x + 1.0F), (double) ((float) y + 0.75F), (double) ((float) z + 0.5F + 0.25F)) 
				: AxisAlignedBB.getAABBPool().getAABB((double) ((float) x + 0.5F - 0.25F), (double) ((float) y), (double) ((float) z), 
											    	  (double) ((float) x + 0.5F + 0.25F), (double) ((float) y + 0.75F), (double) ((float) z + 1.0F));
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z)
	{
		int direction = GetFacing(bAccess, x, y, z);
		
		float rotatedMinX;
		float rotatedMinZ;
		float rotatedMaxX;
		float rotatedMaxZ;
		
		if (direction == 4)
		{
			rotatedMinX = 1.0F - 0.85F;
			rotatedMinZ = 1.0F - 1.0F;
			rotatedMaxX = 1.0F - 0.15F;
			rotatedMaxZ = 1.0F - 0.0F;
		} 
		else if (direction == 3)
		{
			rotatedMinX = 0.0F;
			rotatedMinZ = 0.15F;
			rotatedMaxX = 1.0F;
			rotatedMaxZ = 0.85F;
		} 
		else if (direction == 2)
		{
			rotatedMinX = 1.0F - 1.0F;
			rotatedMinZ = 1.0F - 0.85F;
			rotatedMaxX = 1.0F - 0.0F;
			rotatedMaxZ = 1.0F - 0.15F;
		} 
		else
		{
			rotatedMinX = 0.15F;
			rotatedMinZ = 0.0F;
			rotatedMaxX = 0.85F;
			rotatedMaxZ = 1.0F;
		}
		
		setBlockBounds(rotatedMinX, 0.0F, rotatedMinZ, rotatedMaxX, 0.75F, rotatedMaxZ);
	}
	
	public boolean shouldSideBeRendered(IBlockAccess bAccess, int x, int y, int z, int side)
	{
		return true;
	}
	
	/**
	 * When this method is called, your block should register all the icons it needs
	 * with the given IconRegister. This is the only chance you get to register
	 * icons.
	 */
	public void registerIcons(IconRegister register) 
	{
		this.blockIcon = register.registerIcon("decoBlockHedge_" + this.m_Tag);
	}
	
	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
	public int damageDropped(int metadata) 
	{
		return metadata;
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
	
	public void SetRenderBoundsRotatedAboutJToFacing(RenderBlocks render, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int direction)
	{
		float rotatedMinX;
		float rotatedMinZ;
		float rotatedMaxX;
		float rotatedMaxZ;
		
		if (direction == 4)
		{
			rotatedMinX = 1.0F - maxX;
			rotatedMinZ = 1.0F - maxZ;
			rotatedMaxX = 1.0F - minX;
			rotatedMaxZ = 1.0F - minZ;
		} 
		else if (direction == 3)
		{
			rotatedMinX = minZ;
			rotatedMinZ = minX;
			rotatedMaxX = maxZ;
			rotatedMaxZ = maxX;
		} 
		else if (direction == 2)
		{
			rotatedMinX = 1.0F - maxZ;
			rotatedMinZ = 1.0F - maxX;
			rotatedMaxX = 1.0F - minZ;
			rotatedMaxZ = 1.0F - minX;
		} 
		else
		{
			rotatedMinX = 0.15F;
			rotatedMinZ = 0.0F;
			rotatedMaxX = 0.85F;
			rotatedMaxZ = 1.0F;
		}
		
		render.setRenderBounds((double) rotatedMinX, (double) minY, (double) rotatedMinZ, (double) rotatedMaxX, (double) maxY, (double) rotatedMaxZ);
	}
	
	public int getBlockColor()
    {
        double temp = 0.5D;
        double humidity = 1.0D;
        
        if (this.m_Tag == "birch")
        	return ColorizerFoliage.getFoliageColorBirch();
        else if (this.m_Tag == "spruce")
        	return ColorizerFoliage.getFoliageColorPine();
        else
        	return ColorizerFoliage.getFoliageColor(temp, humidity);
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int par1)
    {
        return this.getBlockColor();
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess bAccess, int x, int y, int z)
    {
        int var5 = 0;
        int var6 = 0;
        int var7 = 0;

        for (int index = -1; index <= 1; ++index)
        {
            for (int var9 = -1; var9 <= 1; ++var9)
            {
                int var10 = bAccess.getBiomeGenForCoords(x + var9, z + index).getBiomeFoliageColor();
                var5 += (var10 & 16711680) >> 16;
                var6 += (var10 & 65280) >> 8;
                var7 += var10 & 255;
            }
        }

        return (var5 / 9 & 255) << 16 | (var6 / 9 & 255) << 8 | var7 / 9 & 255;
    }
    
    /**
     * Return whether an adjacent block can connect to a wall.
     */
    public boolean canConnectHedgeTo(IBlockAccess bAccess, int x, int y, int z)
    {
        int blockID = bAccess.getBlockId(x, y, z);

        if (blockID != this.blockID)
        {
            Block block = Block.blocksList[blockID];
            return block != null && block.blockMaterial.isOpaque() && block.renderAsNormalBlock() ? block.blockMaterial != Material.pumpkin : false;
        }
        else
            return true;
    }

	public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
	{
		int direction = GetFacing(render.blockAccess, x, y, z);
		
		boolean isBlockAbove = render.blockAccess.isAirBlock(x, y + 1, z);
		boolean isHedgeAbove = !isBlockAbove && canConnectHedgeTo(render.blockAccess, x, y + 1, z);
		
		if (isHedgeAbove)
			SetRenderBoundsRotatedAboutJToFacing(render, 0.15F, 0.0F, 0.0F, 0.85F, 1.0F, 1.0F, direction);
		else
			SetRenderBoundsRotatedAboutJToFacing(render, 0.15F, 0.0F, 0.0F, 0.85F, 0.75F, 1.0F, direction);
		
		render.renderStandardBlock(this, x, y, z);
		
		return true;
	}
}