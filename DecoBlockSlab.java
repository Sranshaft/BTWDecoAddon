package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockSlab extends FCBlockSlab
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
	
	public boolean DoesBlockBreakSaw(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return Item.bone.itemID;
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public int GetCombinedBlockID(int var1)
    {
        return this.m_ParentBlock.blockID;
    }

    public int GetCombinedMetadata(int var1)
    {
        return this.m_Metadata;
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
        return this.m_ParentBlock.getBlockTextureFromSide(side);
    }
}