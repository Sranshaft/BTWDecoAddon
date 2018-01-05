package net.minecraft.src;

public class DecoBlockStair  extends FCBlockStairs
{
	private Block m_ParentBlock;
	private int m_Metadata;
	
	public DecoBlockStair(int id, Block parentBlock, String subType)
	{
		this(id, parentBlock, subType, 0);
	}
	
	public DecoBlockStair(int id, Block parentBlock, int metadata)
	{
		this(id, parentBlock, "", 0);
	}
	
	public DecoBlockStair(int id, Block parentBlock, String subType, int metadata)
	{
		super(id, parentBlock, metadata);
		
		if (subType.isEmpty())
			this.setUnlocalizedName(parentBlock.getUnlocalizedName2() + ".stairs");
		else
			this.setUnlocalizedName(parentBlock.getUnlocalizedName2() + "." + subType + ".stairs");
		
		this.setHardness(parentBlock.blockHardness);
        this.setResistance(parentBlock.blockResistance / 3.0F);
        this.setStepSound(parentBlock.stepSound);
        this.setCreativeTab(CreativeTabs.tabBlock);
        
        Block.useNeighborBrightness[id] = true;
        
        this.m_ParentBlock = parentBlock;
        this.m_Metadata = metadata;
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
    	return this.m_ParentBlock.getIcon(side, this.m_Metadata);
    }
}
