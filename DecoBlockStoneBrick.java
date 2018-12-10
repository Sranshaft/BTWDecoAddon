package net.minecraft.src;

import java.util.List;

public class DecoBlockStoneBrick extends FCBlockStoneBrick
{
	private Icon m_IconChiseled, m_IconCracked, m_IconMossy, m_IconPillarSide, m_IconPillarTop, m_IconPolished, m_IconTile, m_IconLarge;
	private String m_UnlocalizedName;
	
	public DecoBlockStoneBrick(int id, String unlocalizedName, String localizedName, float hardness, float resistance)
	{
		super(id);
		
		this.setUnlocalizedName(unlocalizedName);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		
		this.m_UnlocalizedName = unlocalizedName;
		
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this);
		
		DecoAddonManager.register(this, DecoUtilsStrings.STONE_BRICK_TAGS, DecoUtilsStrings.STONE_BRICK_NAMES, " " + localizedName);
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
		return metadata;
	}
	
    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
    
    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
    	if (!player.capabilities.isCreativeMode && DecoUtilsInventory.getItemInIventory(player, FCBetterThanWolves.fcItemChiselIron.itemID) == null) return;
    	if (player.getCurrentEquippedItem().itemID != DecoModuleEquipment.decoItemStonecuttersHammerIron.itemID) return;
    	
    	if (metadata == 0) world.setBlockAndMetadataWithNotify(x, y, z, this.blockID, 5);
    }

	//CLIENT ONLY
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
	{
		super.registerIcons(register);
		
		this.blockIcon = register.registerIcon(this.m_UnlocalizedName);
		this.m_IconChiseled = register.registerIcon(this.m_UnlocalizedName + "_chiseled");
		this.m_IconCracked = register.registerIcon(this.m_UnlocalizedName + "_cracked");
		this.m_IconMossy = register.registerIcon(this.m_UnlocalizedName + "_mossy");
		this.m_IconPillarSide = register.registerIcon(this.m_UnlocalizedName + "_pillar_side");
		this.m_IconPillarTop = register.registerIcon(this.m_UnlocalizedName + "_pillar_top");
		this.m_IconPolished = register.registerIcon(this.m_UnlocalizedName + "_polished");
		this.m_IconTile = register.registerIcon(this.m_UnlocalizedName + "_tiles");
		this.m_IconLarge = register.registerIcon(this.m_UnlocalizedName + "_large");
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon(int side, int metadata)
	{
		switch (metadata)
        {
			case 0: return this.blockIcon;
        	case 1: return this.m_IconChiseled;
        	case 2: return this.m_IconCracked;
        	case 3: return this.m_IconMossy;
        	case 4: return side < 2 ? this.m_IconPillarTop : this.m_IconPillarSide;
        	case 5: return this.m_IconPolished;
        	case 6: return this.m_IconTile;
        	case 7: return side < 2 ? this.m_IconPolished : this.m_IconLarge;
        	
        	default: return this.blockIcon;
        }
	}
	
	 /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
    	for (int index = 0; index < DecoUtilsStrings.STONE_BRICK_TAGS.length; index++)
    	{
    		var3.add(new ItemStack(var1, 1, index));
    	}
    }
}