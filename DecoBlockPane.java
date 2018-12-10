package net.minecraft.src;

import java.util.List;

public class DecoBlockPane extends Block
{
	public Icon[] m_IconByMetadataArrayFaces, m_IconByMetadataArraySides;
	public boolean canOverrideConnection = false;
	
	public DecoBlockPane(int id, Material material)
	{
		super(id, material);
		
		//Block.useNeighborBrightness[id] = true;
		//Block.lightOpacity[id] = 255;
	}
	
	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	public int idPicked(World world, int x, int y, int z)
	{
		return world.getBlockId(x, y, z);
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
	public int damageDropped(int metadata)
	{
		return metadata;
	}

	/**
	 * Get the block's damage value (for use with pick block).
	 */
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}
	
	/**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return AxisAlignedBB.getAABBPool().getAABB((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, 
        										   (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
    }
    
    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity)
    {
        int metadata = world.getBlockMetadata(x, y, z);

        boolean isConnectedSouth = this.canConnectPaneToBlock(world, x, y, z - 1, metadata);
        boolean isConnectedNorth = this.canConnectPaneToBlock(world, x, y, z + 1, metadata);
        boolean isConnectedWest = this.canConnectPaneToBlock(world, x - 1, y, z, metadata);
        boolean isConnectedEast = this.canConnectPaneToBlock(world, x + 1, y, z, metadata);
        
        if (!isConnectedSouth && !isConnectedNorth && !isConnectedWest && !isConnectedEast)
        {
        	this.setBlockBounds(0.4375F, 0.0F, 0.4375F, 0.5625F, 1.0F, 0.5625F);
            super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
            
            return;
        }

        if ((!isConnectedWest || !isConnectedEast) && (isConnectedWest || isConnectedEast || isConnectedSouth || isConnectedNorth))
        {
            if (isConnectedWest && !isConnectedEast)
            {
                this.setBlockBounds(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
                super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
            }
            else if (!isConnectedWest && isConnectedEast)
            {
                this.setBlockBounds(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
                super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
            }
        }
        else
        {
            this.setBlockBounds(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
            super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        }

        if ((!isConnectedSouth || !isConnectedNorth) && (isConnectedWest || isConnectedEast || isConnectedSouth || isConnectedNorth))
        {
            if (isConnectedSouth && !isConnectedNorth)
            {
                this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
                super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
            }
            else if (!isConnectedSouth && isConnectedNorth)
            {
                this.setBlockBounds(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
                super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
            }
        }
        else
        {
            this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
            super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        }
    }
	
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z)
    {
		int metadata = bAccess.getBlockMetadata(x, y, z);
		
        float minX = 0.4375F;
        float maxX = 0.5625F;
        float minZ = 0.4375F;
        float maxZ = 0.5625F;
        
        boolean isConnectedSouth = this.canConnectPaneToBlock(bAccess, x, y, z - 1, metadata);
        boolean isConnectedNorth = this.canConnectPaneToBlock(bAccess, x, y, z + 1, metadata);
        boolean isConnectedWest = this.canConnectPaneToBlock(bAccess, x - 1, y, z, metadata);
        boolean isConnectedEast = this.canConnectPaneToBlock(bAccess, x + 1, y, z, metadata);

        if ((!isConnectedWest || !isConnectedEast) && (isConnectedWest || isConnectedEast || isConnectedSouth || isConnectedNorth))
        {
            if (isConnectedWest && !isConnectedEast) minX = 0.0F;
            else if (!isConnectedWest && isConnectedEast) maxX = 1.0F;
        }
        else
        {
            minX = 0.0F;
            maxX = 1.0F;
        }

        if ((!isConnectedSouth || !isConnectedNorth) && (isConnectedWest || isConnectedEast || isConnectedSouth || isConnectedNorth))
        {
            if (isConnectedSouth && !isConnectedNorth) minZ = 0.0F;
            else if (!isConnectedSouth && isConnectedNorth) maxZ = 1.0F;
        }
        else
        {
            minZ = 0.0F;
            maxZ = 1.0F;
        }
        
        if (!isConnectedSouth && !isConnectedNorth && !isConnectedWest && !isConnectedEast)
        {
        	minX = 0.4375F;
        	maxX = 0.5625F;
            minZ = 0.4375F;
            maxZ = 0.5625F;
        }

        this.setBlockBounds(minX, 0.0F, minZ, maxX, 1.0F, maxZ);
    }
	
	private boolean canConnectPaneToBlock(IBlockAccess bAccess, int x, int y, int z, int metadata)
    {
        int blockID = bAccess.getBlockId(x, y, z);
        if (blockID == this.blockID)
        {
            int blockMetadata = bAccess.getBlockMetadata(x, y, z);
            if (blockMetadata == metadata || this.canOverrideConnection) return true;
        }
        
        if (bAccess.isBlockOpaqueCube(x, y, z)) return true;
        
        return false;
    }
	
	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates.  Args: blockAccess, x, y, z, side
	 */
	public boolean shouldSideBeRendered(IBlockAccess bAccess, int x, int y, int z, int side)
	{
		/// COME BACK TO FIX LATER
		/*int metadata = bAccess.getBlockMetadata(x, y, z);
		
		switch (side)
		{
			case 0 : return bAccess.isAirBlock(x, y - 1, z) || !(bAccess.getBlockId(x, y - 1, z) == this.blockID && bAccess.getBlockMetadata(x, y - 1, z) == metadata);
			case 1 : return bAccess.isAirBlock(x, y + 1, z) || !(bAccess.getBlockId(x, y + 1, z) == this.blockID && bAccess.getBlockMetadata(x, y + 1, z) == metadata);
			case 2 : return !this.canConnectPaneToBlock(bAccess, x, y, z - 1, metadata);
			case 3 : return !this.canConnectPaneToBlock(bAccess, x, y, z + 1, metadata);
			case 4 : return !this.canConnectPaneToBlock(bAccess, x - 1, y, z, metadata);
			case 5 : return !this.canConnectPaneToBlock(bAccess, x + 1, y, z, metadata);
		}*/
		
		return true;
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
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
    {
        super.registerIcons(register);
        
        this.m_IconByMetadataArrayFaces = new Icon[1];
		this.m_IconByMetadataArraySides = new Icon[1];
		
        this.m_IconByMetadataArrayFaces[0] = register.registerIcon(this.getUnlocalizedName2());
        this.m_IconByMetadataArraySides[0] = register.registerIcon(this.getUnlocalizedName2() + "_side");
    }
	
	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public Icon getIcon(int side, int metadata)
	{
		if (side < 2) return this.m_IconByMetadataArraySides[metadata];
		else return this.m_IconByMetadataArrayFaces[metadata];
	}
	
	public boolean DoesItemRenderAsBlock(int var1)
    {
        return false;
    }
	
	public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
    {
		IBlockAccess bAccess = render.blockAccess;
		return this.RenderPane(render, bAccess, x, y, z);
    }
	
	public boolean RenderPane(RenderBlocks render, IBlockAccess bAccess, int x, int y, int z)
	{
		Tessellator tess = Tessellator.instance;
        tess.setBrightness(this.getMixedBrightnessForBlock(bAccess, x, y, z));
        
        int colorMultiplier = this.colorMultiplier(bAccess, x, y, z);
        float colorBase = 1.0F;
        float colorR = (float)(colorMultiplier >> 16 & 255) / 255.0F;
        float colorG = (float)(colorMultiplier >> 8 & 255) / 255.0F;
        float colorB = (float)(colorMultiplier & 255) / 255.0F;
        
        tess.setColorOpaque_F(colorBase * colorR, colorBase * colorG, colorBase * colorB);
		
		int metadata = bAccess.getBlockMetadata(x, y, z);
		
		boolean isConnectedWest = this.canConnectPaneToBlock(bAccess, x - 1, y, z, metadata);
		boolean isConnectedEast = this.canConnectPaneToBlock(bAccess, x + 1, y, z, metadata);
		boolean isConnectedSouth = this.canConnectPaneToBlock(bAccess, x, y, z - 1, metadata);
		boolean isConnectedNorth = this.canConnectPaneToBlock(bAccess, x, y, z + 1, metadata);
		boolean isConnectedWestOrEast = false;
		boolean isConnectedSouthOrNorth = false;
		
		if (isConnectedWest || isConnectedEast) isConnectedWestOrEast = true;
		if (isConnectedSouth || isConnectedNorth) isConnectedSouthOrNorth = true;
		
		if (!isConnectedWestOrEast && !isConnectedSouthOrNorth) isConnectedWestOrEast = true;
		
		float minY = 0.0F;
		float maxY = 1.0F;
		
		float minX = isConnectedWest ? 0.0F : 0.4375F;
		float maxX = isConnectedEast ? 1.0F : 0.5625F;
		float minZ = isConnectedSouth ? 0.0F : 0.4375F;
		float maxZ = isConnectedNorth ? 1.0F : 0.5625F;
		
		render.SetRenderAllFaces(false);
		
		if (isConnectedWestOrEast)
		{
			render.SetUvRotateTop(1);
			render.SetUvRotateBottom(1);
			render.setRenderBounds(minX, 0.0F, 0.4375F, maxX, maxY, 0.5625F);
			
			render.renderFaceYNeg(this, x, y, z, this.m_IconByMetadataArraySides[metadata]);
			render.renderFaceYPos(this, x, y, z, this.m_IconByMetadataArraySides[metadata]);
			
			render.renderFaceZNeg(this, x, y, z, this.m_IconByMetadataArrayFaces[metadata]);
			render.renderFaceZPos(this, x, y, z, this.m_IconByMetadataArrayFaces[metadata]);
			
			if (!isConnectedWest && !isConnectedSouthOrNorth) render.renderFaceXNeg(this, x, y, z, this.m_IconByMetadataArrayFaces[metadata]);
			if (!isConnectedEast && !isConnectedSouthOrNorth) render.renderFaceXPos(this, x, y, z, this.m_IconByMetadataArrayFaces[metadata]);
		}
		
		if (isConnectedSouthOrNorth)
		{
			render.SetUvRotateTop(0);
			render.SetUvRotateBottom(0);
			render.setRenderBounds(0.4375F, 0.0F, minZ, 0.5625F, maxY, maxZ);
			
			render.renderFaceYNeg(this, x, y, z, this.m_IconByMetadataArraySides[metadata]);
			render.renderFaceYPos(this, x, y, z, this.m_IconByMetadataArraySides[metadata]);
			
			render.renderFaceXNeg(this, x, y, z, this.m_IconByMetadataArrayFaces[metadata]);
			render.renderFaceXPos(this, x, y, z, this.m_IconByMetadataArrayFaces[metadata]);
			
			if (!isConnectedSouth && !isConnectedWestOrEast) render.renderFaceZNeg(this, x, y, z, this.m_IconByMetadataArrayFaces[metadata]);
			if (!isConnectedNorth && !isConnectedWestOrEast) render.renderFaceZPos(this, x, y, z, this.m_IconByMetadataArrayFaces[metadata]);
		}
		
		render.SetRenderAllFaces(true);

		this.setBlockBoundsBasedOnState(bAccess, x, y, z);
		
		return true;
	}
}
