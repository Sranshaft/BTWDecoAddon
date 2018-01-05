package net.minecraft.src;

import java.util.List;

public class DecoBlockPaneStainedGlass extends FCBlockPane
{
	public static final String[] COLOUR_TYPES = new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "grey", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white" };
	public static final String[] COLOUR_NAMES = new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Silver", "Grey", "Pink", "Lime", "Yellow", "Light Blue", "Magenta", "Orange", "White" };
	
	private Icon[] m_IconByMetadataArray = new Icon[16];
	
	public DecoBlockPaneStainedGlass(int id)
	{
		super(id, "decoBlockPaneStainedGlass_face", "decoBlockPaneStainedGlass_side", Material.glass, false);
		setUnlocalizedName("decoBlockPaneStainedGlass");
		setHardness(0.3F);
		setResistance(0.5F);
		setStepSound(soundGlassFootstep);
		
		DecoAddonManager.register(this, this.COLOUR_TYPES, this.COLOUR_NAMES, " Stained Glass Pane");
	}
	
	public int damageDropped(int metadata)
	{
		return metadata;
	}
	
	public int getRenderBlockPass()
	{
		return 1;
	}
	
	//CLIENT ONLY
	
	/**
	 * When this method is called, your block should register all the icons it needs
	 * with the given IconRegister. This is the only chance you get to register
	 * icons.
	 */
	public void registerIcons(IconRegister register) 
	{
		super.registerIcons(register);
		
		for (int index = 0; index < this.COLOUR_TYPES.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon("decoBlockStainedGlass_" + this.COLOUR_TYPES[index]);
		}
		
		if (this.COLOUR_TYPES.length < 16)
		{
			for (int index = this.COLOUR_TYPES.length; index < 16; index++)
				this.m_IconByMetadataArray[index] = this.blockIcon;
		}
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture.
	 * Args: side, metadata
	 */
	public Icon getIcon(int side, int metadata)
	{
		return this.m_IconByMetadataArray[metadata];
	}
	
	 /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
    	for (int index = 0; index < this.COLOUR_TYPES.length; index++)
    	{
    		var3.add(new ItemStack(var1, 1, index));
    	}
    }
	
	/**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World World, int x, int y, int z)
    {
        return World.getBlockId(x, y, z);
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World World, int x, int y, int z)
    {
        return World.getBlockMetadata(x, y, z);
    }
}
