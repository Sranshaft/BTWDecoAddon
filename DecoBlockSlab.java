package net.minecraft.src;

import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;

public class DecoBlockSlab extends BlockHalfSlab
{
	private Block m_ParentBlock, m_SingleSlab, m_DoubleSlab;
	
	private String[] m_SubTypes, m_SubNames, m_TexturePaths;
	private String  m_TexturePrefix;
	private int m_Metadata;
	private Icon[] m_IconByMetadataArray;
	
	private boolean m_IsDoubleSlab;
	
	public DecoBlockSlab(int id, Block parentBlock, boolean isDoubleSlab, Block singleSlab, Block doubleSlab)
	{
		this(id, parentBlock, isDoubleSlab, singleSlab, doubleSlab, 0, null, null, null, "", "");
	}
	
	public DecoBlockSlab(int id, Block parentBlock, boolean isDoubleSlab, Block singleSlab, Block doubleSlab, int metadata)
	{
		this(id, parentBlock, isDoubleSlab, singleSlab, doubleSlab, metadata, null, null, null, "", "");
	}
	
	public DecoBlockSlab(int id, Block parentBlock, boolean isDoubleSlab, Block singleSlab, Block doubleSlab, String[] subTypes, String[] subNames, String[] texturePaths, String name)
	{
		this(id, parentBlock, isDoubleSlab, singleSlab, doubleSlab, 0, subTypes, subNames, texturePaths, "", name);
	}
	
	public DecoBlockSlab(int id, Block parentBlock, boolean isDoubleSlab, Block singleSlab, Block doubleSlab, String[] subTypes, String[] subNames, String[] texturePaths, String texturePrefix, String name)
	{
		this(id, parentBlock, isDoubleSlab, singleSlab, doubleSlab, 0, subTypes, subNames, texturePaths, texturePrefix, name);
	}
	
	public DecoBlockSlab(int id, Block parentBlock, boolean isDoubleSlab, Block singleSlab, Block doubleSlab, int metadata, String[] subTypes, String[] subNames, String[] texturePaths, String texturePrefix, String name)
	{
		super(id, isDoubleSlab, parentBlock.blockMaterial);
		this.setUnlocalizedName(parentBlock.getUnlocalizedName2() + ".slab");
		this.setHardness(parentBlock.blockHardness);
		this.setResistance(parentBlock.blockResistance / 3.0F);
		this.setStepSound(parentBlock.stepSound);
		this.setCreativeTab(parentBlock.getCreativeTabToDisplayOn());
		this.setLightOpacity(255);
		
        Block.useNeighborBrightness[id] = true;
        
        this.m_ParentBlock = parentBlock;
        this.m_Metadata = metadata;
        this.m_SingleSlab = singleSlab;
        this.m_DoubleSlab = doubleSlab;
        this.m_IsDoubleSlab = isDoubleSlab;
        
        if (isDoubleSlab) Block.opaqueCubeLookup[id] = true;
        else this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        
        if (subTypes != null && subNames != null)
        {
        	this.m_IconByMetadataArray = new Icon[subTypes.length];
    		this.m_SubTypes = subTypes;
    		this.m_SubNames = subNames;
    		this.m_TexturePaths = texturePaths;
    		this.m_TexturePrefix = texturePrefix;
    	
    		DecoAddonManager.register(this, this.m_SubTypes, this.m_SubNames, name + " Slab");
        }
	}
	
	/**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
        return this.isDoubleSlab ? metadata : (side != 0 && (side == 1 || (double)hitY <= 0.5D) ? metadata : metadata | 8);
    }
    
    /**
     * Returns the slab block name with step type.
     */
    public String getFullSlabName(int par1)
    {
    	return this.m_ParentBlock.getUnlocalizedName() + ".slab";
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
    	else this.blockIcon = this.m_ParentBlock.getIcon(2, this.m_Metadata);
    }
    
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
    	if (this.m_SubTypes == null)
    		return this.m_ParentBlock.getIcon(side, metadata & 7);
    	else
    		return this.m_IconByMetadataArray[metadata & 7];
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return !this.isDoubleSlab ? this.blockID : this.m_SingleSlab.blockID;
    }
    
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World world, int x, int y, int z)
    {
    	return !this.isDoubleSlab ? this.blockID : this.m_SingleSlab.blockID;
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
    
    public void overrideUnlocalizedName(String overrideName)
    {
    	this.setUnlocalizedName(overrideName);
    }
    
    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int metadata)
    {
        return new ItemStack(this.m_SingleSlab.blockID, 2, metadata & 7);
    }
    
    /**
     * Takes a block ID, returns true if it's the same as the ID for a stone or wooden single slab.
     */
    private static boolean isBlockSingleSlab(int id)
    {
        return false;
    }
}