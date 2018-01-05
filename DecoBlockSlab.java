package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockSlab extends Block
{
	private Block m_ParentBlock;
	private int m_Metadata, m_BottomSlabID, m_TopSlabID;
	private Boolean m_IsDoubleSlab;
	
	private String[] m_TexturePaths;
	private String[] m_SubTypes;
	private String[] m_SubNames;
	private String m_TexturePrefix;
	private Icon[] m_IconByMetadataArray;
	
	public DecoBlockSlab(int id, Block parentBlock, boolean isDoubleSlab, int bottomSlabID, int topSlabID)
	{
		this(id, parentBlock, isDoubleSlab, bottomSlabID, topSlabID, 0, null, null, null, null);
	}
	
	public DecoBlockSlab(int id, Block parentBlock, boolean isDoubleSlab, int bottomSlabID, int topSlabID, int metadata)
	{
		this(id, parentBlock, isDoubleSlab, bottomSlabID, topSlabID, metadata, null, null, null, null);
	}
	
	public DecoBlockSlab(int id, Block parentBlock, boolean isDoubleSlab, int bottomSlabID, int topSlabID, String[] subTypes, String[] subNames, String[] texturePaths)
	{
		this(id, parentBlock, isDoubleSlab, bottomSlabID, topSlabID, 0, subTypes, subNames, null, texturePaths);
	}
	
	public DecoBlockSlab(int id, Block parentBlock, boolean isDoubleSlab, int bottomSlabID, int topSlabID, String[] subTypes, String[] subNames, String texturePrefix, String[] texturePaths)
	{
		this(id, parentBlock, isDoubleSlab, bottomSlabID, topSlabID, 0, subTypes, subNames, texturePrefix, texturePaths);
	}
	
	public DecoBlockSlab(int id, Block parentBlock, boolean isDoubleSlab, int bottomSlabID, int topSlabID, int metadata, String[] subTypes, String[] subNames, String texturePrefix, String[] texturePaths)
	{
		super(id, parentBlock.blockMaterial);
		this.setUnlocalizedName(parentBlock.getUnlocalizedName2() + ".slab");
		this.setHardness(parentBlock.blockHardness);
		this.setResistance(parentBlock.blockResistance / 3.0F);
		this.setStepSound(parentBlock.stepSound);
		this.setCreativeTab(CreativeTabs.tabBlock);
        
        Block.useNeighborBrightness[id] = true;
        
        this.m_ParentBlock = parentBlock;
        this.m_Metadata = metadata;
        this.m_IsDoubleSlab = isDoubleSlab;
        this.m_BottomSlabID = bottomSlabID;
        this.m_TopSlabID = topSlabID;
        
        if (subTypes != null && subNames != null)
        {
        	this.m_IconByMetadataArray = new Icon[subTypes.length];
    		this.m_SubTypes = subTypes;
    		this.m_SubNames = subNames;
    		this.m_TexturePaths = texturePaths;
    		this.m_TexturePrefix = texturePrefix;
    	
    		DecoAddonManager.register(this, this.m_SubTypes, this.m_SubNames, " " + parentBlock.getLocalizedName() + " Slab");
        }
	}
	
	/**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z)
    {
        if (this.m_IsDoubleSlab)
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        else
        {
            boolean metatdata = (bAccess.getBlockMetadata(x, y, z) & 8) != 0;

            if (metatdata)
                this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            else
            {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            }
        }
    }
    
    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender()
    {
        if (this.m_IsDoubleSlab)
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        else
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity)
    {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
    }
    
    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
    	return this.m_IsDoubleSlab ? metadata : (side != 0 && (side == 1 || (double) hitY <= 0.5D) ? metadata : metadata | 8);
    }
    
	/**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int id, Random random, int amount)
    {
    	return this.m_IsDoubleSlab ? this.m_BottomSlabID : this.blockID;
    }
    
    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int metadata)
    {
        return metadata;
    }
    
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World world, int x, int y, int z)
    {
        return this.idDropped(world.getBlockMetadata(x, y, z), world.rand, 0);
    }
    
    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return this.m_IsDoubleSlab ? 2 : 1;
    }
    
    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
    	if (this.m_SubTypes == null)
    		return this.m_ParentBlock.getIcon(side, this.m_Metadata);
    	else
    		return this.m_IconByMetadataArray[metadata];
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    { 
    	if (this.m_SubTypes != null)
		{
			for (int index = 0; index < this.m_SubTypes.length; index++)
			{
				this.m_IconByMetadataArray[index] = register.registerIcon(this.m_TexturePrefix + this.m_TexturePaths[index]);
			}
		}
    	else this.blockIcon = this.m_ParentBlock.getIcon(0, this.m_Metadata);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	if (!this.m_IsDoubleSlab && this.m_SubTypes != null)
		{
			for (int index = 0; index < this.m_SubTypes.length; index++)
			{
				par3List.add(new ItemStack(par1, 1, index));
			}
		}
    	else if (!this.m_IsDoubleSlab && this.m_SubTypes == null)
    		par3List.add(new ItemStack(par1, 1, 0));
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess bAccess, int x, int y, int z, int side)
    {
        if (!this.m_IsDoubleSlab)
        {
            boolean blockMetadata = bAccess.getBlockMetadata(x, y, z) != 0;

            if (side == 0 && !blockMetadata)
                return true;
        }

        return super.HasCenterHardPointToFacing(bAccess, x, y, z, side);
    }

    public float SnowRestingOnVisualOffset(IBlockAccess bAccess, int x, int y, int z)
    {
        return !this.m_IsDoubleSlab && (bAccess.getBlockMetadata(x, y, z) & 8) == 0 ? -0.5F : 0.0F;
    }

    public boolean CanSnowOnBlock(World world, int x, int y, int z)
    {
        return true;
    }

    public boolean CanContainPistonPackingToFacing(World world, int x, int y, int z, int side, int metadata)
    {
        if (!this.m_IsDoubleSlab)
        {
            boolean blockMetadata = world.getBlockMetadata(x, y, z) != 0;

            if (blockMetadata) if (side != 1) return false;
            else if (side != 0) return false;
        }

        return true;
    }
}