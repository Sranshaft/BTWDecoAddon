package net.minecraft.src;

import java.util.List;

public class DecoBlockChest extends FCBlockChest 
{
	private Icon[] m_IconByMetadataArray;
	
	public DecoBlockChest(int id)
	{
		super(id);
		
		this.setUnlocalizedName("chest");
		this.SetBlockMaterial(FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(1.5F);
        this.SetAxesEffectiveOn();
        this.SetBuoyant();
        this.setStepSound(soundWoodFootstep);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        
        DecoAddonManager.register(this, DecoUtilsStrings.WOOD_TAGS, DecoUtilsStrings.WOOD_NAMES, " Crate");
	}

    public int idPicked(World world, int x, int y, int z) 
    {
        return world.getBlockId(x, y, z);
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
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
        return this.m_IconByMetadataArray[metadata];
    }
    
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
        this.m_IconByMetadataArray = new Icon[DecoUtilsStrings.WOOD_TAGS.length];
        
        for (int index = 0; index < DecoUtilsStrings.WOOD_TAGS.length; index++)
        {
        	this.m_IconByMetadataArray[index] = register.registerIcon("decoBlockCrate_" + DecoUtilsStrings.WOOD_TAGS[index]);
        }
    }
    
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for (int index = 0; index < DecoUtilsStrings.WOOD_TAGS.length; index++)
        {
    		par3List.add(new ItemStack(par1, 1, index));
        }
    }
}
