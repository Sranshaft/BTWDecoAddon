package net.minecraft.src;

import java.util.List;

public class DecoBlockWall extends BlockWall 
{
	private Block m_ParentBlock;
	private int m_Metadata = 0;
	private String[] m_TexturePaths;
	private String[] m_SubTypes;
	private String[] m_SubNames;
	private Icon[] m_IconByMetadataArray;
	
	public DecoBlockWall(int id, Block parentBlock)
	{
		super(id, parentBlock);
		
		this.setUnlocalizedName(parentBlock.getUnlocalizedName2() + ".wall");
        this.setHardness(parentBlock.blockHardness);
        this.setResistance(parentBlock.blockResistance / 3.0F);
        this.setStepSound(parentBlock.stepSound);
        this.setCreativeTab(CreativeTabs.tabBlock);
        
        this.m_ParentBlock = parentBlock;
	}
	
	public DecoBlockWall(int id, Block parentBlock, String[] subTypes, String[] subNames, String[] texturePaths)
	{
		super(id, parentBlock);
		
		this.setUnlocalizedName(parentBlock.getUnlocalizedName2() + ".wall");
        this.setHardness(parentBlock.blockHardness);
        this.setResistance(parentBlock.blockResistance / 3.0F);
        this.setStepSound(parentBlock.stepSound);
        this.setCreativeTab(CreativeTabs.tabBlock);
        
        this.m_IconByMetadataArray = new Icon[subTypes.length];
		this.m_SubTypes = subTypes;
		this.m_SubNames = subNames;
		this.m_TexturePaths = texturePaths;
		
		this.m_ParentBlock = parentBlock;
		
		DecoAddonManager.register(this, this.m_SubTypes, this.m_SubNames, " Wall");
	}
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
	{
		if (this.m_SubTypes != null)
		{
			for (int index = 0; index < this.m_TexturePaths.length; index++)
			{
				this.m_IconByMetadataArray[index] = register.registerIcon(this.m_TexturePaths[index]);
			}
		}
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
    	if (this.m_SubTypes == null)
    		return this.m_ParentBlock.getBlockTextureFromSide(side);
    	else
    		return this.m_IconByMetadataArray[metadata];
    }
    
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	if (this.m_SubTypes != null)
		{
			for (int index = 0; index < this.m_SubTypes.length; index++)
			{
				par3List.add(new ItemStack(par1, 1, index));
			}
		}
    	else
    		par3List.add(new ItemStack(par1, 1, 0));
    }
    
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
    {
		return true;
	}
    
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        this.maxY = 1.0D;
		
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

	public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
	{
		IBlockAccess bAccess = render.blockAccess;
		int metadata = bAccess.getBlockMetadata(x, y, z);

		return RenderWall(render, bAccess, x, y, z, this);
	}
	
	public boolean RenderWall(RenderBlocks render, IBlockAccess bAccess, int x, int y, int z, Block block)
	{
		boolean isBlockWest = canConnectWallTo(bAccess, x - 1, y, z);
		boolean isBlockEast = canConnectWallTo(bAccess, x + 1, y, z);
		boolean isBlockNorth = canConnectWallTo(bAccess, x, y, z - 1);
		boolean isBlockSouth = canConnectWallTo(bAccess, x, y, z + 1);
		boolean var9 = isBlockNorth && isBlockSouth && !isBlockWest && !isBlockEast;
		boolean var10 = !isBlockNorth && !isBlockSouth && isBlockWest && isBlockEast;
		boolean isBlockAbove = bAccess.isAirBlock(x, y + 1, z);
		boolean isWallAbove = !isBlockAbove && canConnectWallTo(bAccess, x, y + 1, z);
		
		if ((var9 || var10) && isBlockAbove)
		{
			if (var9)
			{
				render.setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, 0.8125D, 1.0D);
				render.renderStandardBlock(block, x, y, z);
			}
			else
			{
				render.setRenderBounds(0.0D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
				render.renderStandardBlock(block, x, y, z);
			}
		}
		else if (((isBlockWest && isBlockEast) || (isBlockNorth && isBlockSouth)) && isWallAbove)
		{
			if (isBlockWest && isBlockEast)
			{
				render.setRenderBounds(0.0D, 0.0D, 0.3125D, 1.0D, 1.0D, 0.6875D);
				render.renderStandardBlock(block, x, y, z);
			}

			if (isBlockNorth && isBlockSouth)
			{
				render.setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, 1.0D, 1.0D);
				render.renderStandardBlock(block, x, y, z);
			}
		}
		else
		{
			render.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
			render.renderStandardBlock(block, x, y, z);
			double height = (isWallAbove?1.0D:0.8125D);

			if (isBlockWest)
			{
				render.setRenderBounds(0.0D, 0.0D, 0.3125D, 0.25D, height, 0.6875D);
				render.renderStandardBlock(block, x, y, z);
			}

			if (isBlockEast)
			{
				render.setRenderBounds(0.75D, 0.0D, 0.3125D, 1.0D, height, 0.6875D);
				render.renderStandardBlock(block, x, y, z);
			}

			if (isBlockNorth)
			{
				render.setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, height, 0.25D);
				render.renderStandardBlock(block, x, y, z);
			}

			if (isBlockSouth)
			{
				render.setRenderBounds(0.3125D, 0.0D, 0.75D, 0.6875D, height, 1.0D);
				render.renderStandardBlock(block, x, y, z);
			}
		}
		
		block.setBlockBoundsBasedOnState(render.blockAccess, x, y, z);
		
		return true;
	}
}
