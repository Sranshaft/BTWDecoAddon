package net.minecraft.src;

import java.util.List;

public class DecoBlockFlower extends BlockFlower
{
	public static final String[] FLOWER_EXTENDED_TYPES = new String[]{ "yucca", "hyacinth", "birdsParadise", "azalea", "cornFlower", "lavender", "honeysuckle","allium", "orchidBlue", "poppy", "azureBluet", "daisy", "peony","lilac","roseBush", "blueRoseBush"};
	public static final String[] FLOWER_EXTENDED_NAMES = new String[]{ "Yucca", "Hyacinth", "Birds of Paradise", "Azaleas", "Cornflower", "Lavender", "Honeysuckle", "Allium","Blue Orchid", "Poppy", "Azure Bluet", "Daisy", "Peony", "Lilac", "Rose Bush", "Blue Rose Bush"};
	
	private Icon[] m_IconByMetadataArray = new Icon[16];
	
	public DecoBlockFlower(int id)
	{
		super(id);
		setUnlocalizedName("decoBlockFlowerExtended");
		setStepSound(soundGrassFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
		
		this.m_IconByMetadataArray = new Icon[this.FLOWER_EXTENDED_TYPES.length];
		
		DecoAddonManager.register(this, this.FLOWER_EXTENDED_TYPES, this.FLOWER_EXTENDED_NAMES);
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
		for (int index = 0; index < this.FLOWER_EXTENDED_TYPES.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon("decoBlockFlower_" + this.FLOWER_EXTENDED_TYPES[index]);
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
    	for (int index = 0; index < this.FLOWER_EXTENDED_TYPES.length; index++)
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
