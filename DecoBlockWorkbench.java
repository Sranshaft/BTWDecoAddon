package net.minecraft.src;

import java.util.List;

public class DecoBlockWorkbench extends FCBlockWorkbench
{
	private static String[] m_BookshelfFrontPaths = new String[] { "workbench_front", "workbench_spruce_front", "workbench_birch_front", "workbench_jungle_front", "workbench_bloodwood_front", "workbench_ebonwood_front", "workbench_ironwood_front" };
	private static String[] m_BookshelfSidePaths = new String[] { "workbench_side", "workbench_spruce_side", "workbench_birch_side", "workbench_jungle_side", "workbench_bloodwood_side", "workbench_ebonwood_side", "workbench_ironwood_side" };
	private static String[] m_BookshelfTopPaths = new String[] { "workbench_top", "workbench_spruce_top", "workbench_birch_top", "workbench_jungle_top", "workbench_bloodwood_top", "workbench_ebonwood_top", "workbench_ironwood_top" };
	
	private Icon[] m_IconFronts, m_IconSides, m_IconTops;
	
	public DecoBlockWorkbench(int id)
	{
		super(id);
		this.setUnlocalizedName("workbench");
		this.setHardness(Block.planks.blockHardness);
		this.setResistance(Block.planks.blockResistance);
		this.setStepSound(Block.soundWoodFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		
		DecoAddonManager.register(this, DecoUtilsStrings.WOOD_TAGS, DecoUtilsStrings.WOOD_NAMES, " Workbench");
	}
	
	/**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int metadata)
    {
        return metadata;
    }
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
        switch (side)
        {
        	case 0: return DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockWood.getIcon(side, metadata);
        	case 1: return this.m_IconTops[metadata];
        	case 2:
        	case 3: return this.m_IconSides[metadata];
        	default: return this.m_IconFronts[metadata];
        }
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
    	this.m_IconFronts = new Icon[this.m_BookshelfFrontPaths.length];
    	this.m_IconSides = new Icon[this.m_BookshelfSidePaths.length];
    	this.m_IconTops = new Icon[this.m_BookshelfTopPaths.length];
    	
    	for (int index = 0; index < this.m_BookshelfFrontPaths.length; index++)
        {
    		this.m_IconFronts[index] = register.registerIcon(this.m_BookshelfFrontPaths[index]);
    		this.m_IconSides[index] = register.registerIcon(this.m_BookshelfSidePaths[index]);
        	this.m_IconTops[index] = register.registerIcon(this.m_BookshelfTopPaths[index]);
        }
    }
    
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for (int index = 0; index < this.m_BookshelfFrontPaths.length; index++)
        {
    		par3List.add(new ItemStack(par1, 1, index));
        }
    }
}
