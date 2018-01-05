package net.minecraft.src;

import java.util.List;

public class DecoBlockTulip extends BlockFlower
{
	public static final String[] FLOWER_TULIP_TYPES = new String[] { "red", "pink", "orange", "white", "blue"};
	public static final String[] FLOWER_NAMES = new String[] { "Red", "Pink", "Orange", "White", "Blue"};
	
	private Icon[] m_IconByMetadataArray;
	
	public DecoBlockTulip(int id)
	{
		super(id);
		setUnlocalizedName("decoBlockFlowerTulip");
		setStepSound(soundGrassFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
		
		this.m_IconByMetadataArray = new Icon[this.FLOWER_TULIP_TYPES.length];
		
		DecoAddonManager.register(this, this.FLOWER_TULIP_TYPES, this.FLOWER_NAMES, " Tulip");
	}
	
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return Block.plantRed.canBlockStay(world, x, y, z);
	}
	
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return Block.plantRed.canPlaceBlockAt(world, x, y, z);
	}
	
	public int damageDropped(int metadata)
	{
		return metadata;
	}
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
	{
		for (int index = 0; index < this.FLOWER_TULIP_TYPES.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon("decoBlockTulip_" + this.FLOWER_TULIP_TYPES[index]);
		}
	}

	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
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
    	for (int index = 0; index < this.FLOWER_TULIP_TYPES.length; index++)
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
