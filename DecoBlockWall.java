package net.minecraft.src;

import java.util.List;

public class DecoBlockWall extends BlockWall 
{
	private Block m_ParentBlock;
	
	private String[] m_SubTypes, m_SubNames, m_TexturePaths;
	private String  m_TexturePrefix;
	private int m_Metadata;
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
		this(id, parentBlock, subTypes, subNames, texturePaths, "", "");
	}
	
	public DecoBlockWall(int id, Block parentBlock, String[] subTypes, String[] subNames, String[] texturePaths, String texturePrefix)
	{
		this(id, parentBlock, subTypes, subNames, texturePaths, texturePrefix, "");
	}
	
	public DecoBlockWall(int id, Block parentBlock, String[] subTypes, String[] subNames, String[] texturePaths, String texturePrefix, String name)
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
		this.m_TexturePrefix = texturePrefix;
		
		this.m_ParentBlock = parentBlock;
		
		DecoAddonManager.register(this, this.m_SubTypes, this.m_SubNames, name + " Wall");
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
				this.m_IconByMetadataArray[index] = register.registerIcon(this.m_TexturePrefix + this.m_TexturePaths[index]);
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
		boolean isBlockAbove = bAccess.isBlockOpaqueCube(x, y + 1, z);
		boolean isWallAbove = !isBlockAbove && this.canConnectWallTo(render.blockAccess, x, y + 1, z);

		boolean isConnectedWest = this.canConnectWallTo(bAccess, x - 1, y, z);
		boolean isConnectedEast = this.canConnectWallTo(bAccess, x + 1, y, z);
		boolean isConnectedSouth = this.canConnectWallTo(bAccess, x, y, z - 1);
		boolean isConnectedNorth = this.canConnectWallTo(bAccess, x, y, z + 1);
		boolean isConnectedWestEast = (isConnectedWest && isConnectedEast && !isConnectedSouth && !isConnectedNorth);
		boolean isConnectedSouthNorth = (!isConnectedWest && !isConnectedEast && isConnectedSouth && isConnectedNorth);
		
		float minY = 0.0F;
		float maxY = 0.8125F;
		if (isBlockAbove || isWallAbove) maxY = 1.0F;
		
		float minX = isConnectedWest ? 0.0F : 0.3125F;
		float maxX = isConnectedEast ? 1.0F : 0.6875F;
		float minZ = isConnectedSouth ? 0.0F : 0.3125F;
		float maxZ = isConnectedNorth ? 1.0F : 0.6875F;
		
		if ((isConnectedWestEast || isConnectedSouthNorth) && !isBlockAbove)
		{
			if (isConnectedWest || isConnectedEast)
			{
				render.setRenderBounds(minX, 0.0F, 0.3125F, maxX, maxY, 0.6875F);
				render.renderStandardBlock(this, x, y, z);
			}
			
			if (isConnectedSouth || isConnectedNorth)
			{
				render.setRenderBounds(0.3125F, 0.0F, minZ, 0.6875F, maxY, maxZ);
				render.renderStandardBlock(this, x, y, z);
			}
		}
		else
		{
			render.setRenderBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
			render.renderStandardBlock(this, x, y, z);
			
			if (isConnectedWest || isConnectedEast)
			{
				render.setRenderBounds(minX, 0.0F, 0.3125F, maxX, maxY, 0.6875F);
				render.renderStandardBlock(this, x, y, z);
			}
			
			if (isConnectedSouth || isConnectedNorth)
			{
				render.setRenderBounds(0.3125F, 0.0F, minZ, 0.6875F, maxY, maxZ);
				render.renderStandardBlock(this, x, y, z);
			}
		}
		
		this.setBlockBoundsBasedOnState(bAccess, x, y, z);
		
		return true;
	}
}
