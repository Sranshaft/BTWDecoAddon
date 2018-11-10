package net.minecraft.src;

import java.util.List;

public class DecoBlockBrimstone extends Block implements DecoIBlock
{
	public static final String[] BRIMSTONE_TYPES = new String[] {"normal", "chiseled", "smooth"};
	public static final String[] BRIMSTONE_NAMES = new String[] {"", "Chiseled", "Smooth"};
	
	private Icon m_IconChiseled;
	private Icon m_IconSmooth;
	
	public DecoBlockBrimstone(int id)
	{
		super(id, Material.rock);

		this.setUnlocalizedName("decoBlockBrimstone");
		this.setHardness(Block.stone.blockHardness);
		this.setResistance(Block.stone.blockResistance);
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		
		DecoAddonManager.register(this, this.BRIMSTONE_TYPES, this.BRIMSTONE_NAMES, " Brimstone");
	}

	/**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
	public int damageDropped(int metadata)
	{
		return metadata;
	}

	//CLIENT ONLY
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
	{
		super.registerIcons(register);
		
		this.blockIcon = register.registerIcon("decoBlockBrimstone");
		this.m_IconChiseled = register.registerIcon("decoBlockBrimstone_chiseled");
		this.m_IconSmooth = register.registerIcon("decoBlockBrimstone_smooth");
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon(int side, int metadata)
	{
		switch (metadata)
        {
			case 0:
				return this.blockIcon;
			case 1:
        		return this.m_IconChiseled;
        	case 2:
        		return this.m_IconSmooth;
        	default:
        		return this.blockIcon;
        }
	}
	
	 /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
    	for (int index = 0; index < this.BRIMSTONE_TYPES.length; index++)
    	{
    		var3.add(new ItemStack(var1, 1, index));
    	}
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
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
}
