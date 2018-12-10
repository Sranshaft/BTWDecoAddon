package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class DecoBlockAestheticNonOpaque extends FCBlockAestheticNonOpaque 
{
	private static ArrayList m_BlocksThatCanBeHeld = new ArrayList();
	
	private Icon m_IconGrateSide, m_IconSlatsSide, m_IconWickerSide;
	private Icon m_IconLightningRod;
	
	public DecoBlockAestheticNonOpaque(int id)
	{
		super(id);
		
		this.setUnlocalizedName("fcBlockAestheticNonOpaque");
	}
	
	public static void AddHoldableBlock(Block block)
	{
		AddHoldableBlockID(block.blockID);
	}
	
	public static void AddHoldableBlockID(int id)
	{
		m_BlocksThatCanBeHeld.add(id);
	}
	
	/**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
    	int metadata = world.getBlockMetadata(x, y, z);
    	
    	if (metadata != 6 && metadata != 7 && metadata != 8) return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    	else return AxisAlignedBB.getAABBPool().getAABB((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, 
    												    (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
    }
    
    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity)
    {
        int metadata = world.getBlockMetadata(x, y, z);

        if (metadata != 6 && metadata != 7 && metadata != 8) super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        else
        {
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
    }
	
	public void SetBlockBoundsForPane(IBlockAccess bAccess, int x, int y, int z, int metadata)
    {
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
	
	public void SetBlockBoundsBasedOnSubType(int metadata)
	{
		if (metadata != 6 && metadata != 7 && metadata != 8) super.SetBlockBoundsBasedOnSubType(metadata);
	}
	
	private boolean canConnectPaneToBlock(IBlockAccess bAccess, int x, int y, int z, int metadata)
    {
        int blockID = bAccess.getBlockId(x, y, z);
        if (blockID == this.blockID)
        {
            int blockMetadata = bAccess.getBlockMetadata(x, y, z);
            if (blockMetadata == metadata) return true;
        }
        
        if (bAccess.isBlockOpaqueCube(x, y, z)) return true;
        
        return false;
    }
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
    {
        super.registerIcons(register);
        
        this.m_IconGrateSide = register.registerIcon("fcBlockGrate_side");
        this.m_IconSlatsSide = register.registerIcon("fcBlockSlats_side");
        this.m_IconWickerSide = register.registerIcon("fcBlockWicker_side");
    }
	
	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public Icon getIcon(int side, int metadata)
	{
		if ((side == 0 || side == 1) && metadata == 6) return this.m_IconGrateSide;
		else if ((side == 0 || side == 1) && metadata == 7) return this.m_IconSlatsSide;
		else if ((side == 0 || side == 1) && metadata == 8) return this.m_IconWickerSide;
		else return super.getIcon(side, metadata);
	}
	
	public boolean RenderLightningRod(RenderBlocks render, IBlockAccess blockAccess, int x, int y, int z, Block block)
	{
		render.setRenderBounds(0.46875F, 0.0F, 0.46875F, 0.53125F, 1.0F, 0.53125F);
		FCClientUtilsRender.RenderStandardBlockWithTexture(render, block, x, y, z, this.m_IconLightningRod);

		if (blockAccess.getBlockId(x, y - 1, z) != FCBetterThanWolves.fcAestheticNonOpaque.blockID || blockAccess.getBlockMetadata(x, y - 1, z) != 12)
		{
			render.setRenderBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.125F, 0.625F);
			FCClientUtilsRender.RenderStandardBlockWithTexture(render, block, x, y, z, this.m_IconLightningRod);
		}

		int var8 = blockAccess.getBlockId(x, y + 1, z);
		int var9 = blockAccess.getBlockMetadata(x, y + 1, z);

		if (var8 != FCBetterThanWolves.fcAestheticNonOpaque.blockID || var9 != 12)
		{
			if (var8 == FCBetterThanWolves.fcBlockCandle.blockID || m_BlocksThatCanBeHeld.contains(var8))
				render.setRenderBounds(0.375F, 0.9375F, 0.375F, 0.625F, 1.0F, 0.625F);
			else
				render.setRenderBounds(0.40625F, 0.625F, 0.40625F, 0.59375F, 0.8125F, 0.59375F);
			
			FCClientUtilsRender.RenderStandardBlockWithTexture(render, block, x, y, z, this.m_IconLightningRod);
		}

		return true;
	}
	
	public boolean RenderPane(RenderBlocks render, IBlockAccess bAccess, int x, int y, int z, Block block, int metadata)
	{
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
		
		if (isConnectedWestOrEast)
		{
			render.SetUvRotateTop(1);
			render.SetUvRotateBottom(1);
			render.setRenderBounds(minX, 0.0F, 0.4375F, maxX, maxY, 0.5625F);
			render.renderStandardBlock(this, x, y, z);
		}
		
		if (isConnectedSouthOrNorth)
		{
			render.SetUvRotateTop(0);
			render.SetUvRotateBottom(0);
			render.setRenderBounds(0.4375F, 0.0F, minZ, 0.5625F, maxY, maxZ);
			render.renderStandardBlock(this, x, y, z);
		}

		this.setBlockBoundsBasedOnState(bAccess, x, y, z);
		
		return true;
	}
}
