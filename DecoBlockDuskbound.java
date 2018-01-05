package net.minecraft.src;

import java.util.List;

public class DecoBlockDuskbound extends Block
{
	public static final String[] STONE_TYPES = new String[] {"normal", "chiseled", "smooth"};
	public static final String[] STONE_NAMES = new String[] {"", "Chiseled", "Smooth"};
	
	private Icon m_IconTop;
	private Icon m_IconBottom;
	private Icon m_IconChiseled;
	private Icon m_IconSmooth;
	
	public DecoBlockDuskbound(int id)
	{
		super(id, Material.rock);
		setUnlocalizedName("decoBlockDuskbound");
		setHardness(Block.obsidian.blockHardness / 2.0F);
        setResistance(Block.obsidian.blockResistance / 2.0F);
        setStepSound(Block.soundStoneFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
        
        DecoAddonManager.register(this, this.STONE_TYPES, this.STONE_NAMES, " Duskbound");
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
		this.blockIcon = register.registerIcon("decoBlockDuskbound");
		this.m_IconBottom = register.registerIcon("decoBlockDuskbound_bottom");
		this.m_IconTop = register.registerIcon("decoBlockDuskbound_top");
		this.m_IconChiseled = register.registerIcon("decoBlockDuskbound_chiseled");
		this.m_IconSmooth = register.registerIcon("decoBlockDuskbound_smooth");
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon(int side, int metadata)
	{
		switch (metadata)
        {
			case 1:
        	{
        		return this.m_IconChiseled;
        	}
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
    	for (int index = 0; index < this.STONE_TYPES.length; index++)
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
