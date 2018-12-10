package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockLeaves extends FCBlockLeaves
{
	public static final String[] LEAF_TYPES = new String[] { "ebonwood", "ironwood" };
	public static final String[] LEAF_NAMES = new String[] { "Ebonwood", "Ironwood" };
    public static final String[][] LEAF_TEXTURE_PATHS = new String[][] {{ "leaves_ebonwood", "leaves_ironwood" }, { "leaves_ebonwood_opaque", "leaves_ironwood_opaque" }};
    
    private int m_graphicsLevel;
    private Icon[][] m_IconByMetadataArray = new Icon[2][2];
    
    protected DecoBlockLeaves(int par1)
    {
        super(par1);
        
        this.setUnlocalizedName("decoBlockLeaves");
        this.SetFireProperties(60, 100);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setTickRandomly(true);
        
        DecoAddonManager.register(this, this.LEAF_TYPES, this.LEAF_NAMES, " Leaves");
    }
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.sapling.blockID;
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
        return metadata & 3;
    }
    
    /**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether or
	 * not to render the shared face of two adjacent blocks and also whether the
	 * player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube() 
	{
		this.m_graphicsLevel = Block.leaves.graphicsLevel ? 0 : 1;
		return !Block.leaves.graphicsLevel;
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
    	return this.m_IconByMetadataArray[this.m_graphicsLevel][metadata & 3];
    }
    
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
    	for (int var2 = 0; var2 < this.LEAF_TEXTURE_PATHS.length; ++var2)
        {
            this.m_IconByMetadataArray[var2] = new Icon[this.LEAF_TEXTURE_PATHS[var2].length];

            for (int var3 = 0; var3 < this.LEAF_TEXTURE_PATHS[var2].length; ++var3)
            {
                this.m_IconByMetadataArray[var2][var3] = register.registerIcon(this.LEAF_TEXTURE_PATHS[var2][var3]);
            }
        }
    }
    
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int blockID, CreativeTabs creativeTab, List list)
    {
    	for (int index = 0; index < this.m_IconByMetadataArray.length; index++)
        {
    		list.add(new ItemStack(blockID, 1, index));
        }
    }
    
    public int getBlockColor()
    {
    	double var1 = 0.5D;
        double var3 = 1.0D;
        return ColorizerFoliage.getFoliageColor(var1, var3);
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int metadata)
    {
        return (metadata & 3) == 0 ? DecoUtilsColorizer.getFoliageEbonwood() : DecoUtilsColorizer.getFoliageIronwood();  
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess bAccess, int x, int y, int z)
    {
    	int metadata = bAccess.getBlockMetadata(x, y, z);

        return (metadata & 3) == 0 ? DecoUtilsColorizer.getFoliageEbonwood() : DecoUtilsColorizer.getFoliageIronwood();
    }
    
    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int metadata)
    {
        return new ItemStack(this.blockID, 1, metadata & 3);
    }
}
