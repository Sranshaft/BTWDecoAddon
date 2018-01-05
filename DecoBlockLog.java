package net.minecraft.src;

public class DecoBlockLog extends FCBlockLog
{
	private Icon[] m_IconTreeSideArray;
    private Icon[] m_IconTreeTopArray;
	private Icon[] m_IconTrunkSideArray;
    private Icon[] m_IconTrunkTopArray;
    
	public DecoBlockLog(int id)
	{
		super(id);
		this.setUnlocalizedName("log");
		this.setStepSound(soundWoodFootstep);
	    this.SetCanBeCookedByKiln(true);
	    this.SetItemIndexDroppedWhenCookedByKiln(Item.coal.itemID);
	    this.SetItemDamageDroppedWhenCookedByKiln(1);
	    
	    ItemAxe.SetAllAxesToBeEffectiveVsBlock(this);
	}
	
	public void OnDestroyedByFire(World world, int x, int y, int z)
	{
		dropBlockAsItem_do(world, x, y, z, new ItemStack(DecoModuleTweaks.decoItemAsh.itemID, 1, 0));
	}
	
	public float getBlockHardness(World world, int x, int y, int z)
	{
		float multiplier = 1;
		int metadata = world.getBlockMetadata(x, y, z);
		
		if ((metadata & 12) == 12) multiplier = 3;
		
		if ((metadata & 3) == 1) return 1.3F * multiplier;
		if ((metadata & 3) == 3) return 1.0F * multiplier;
		
		return 1.5F * multiplier;
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
    	int pos = metadata & 12;
    	int type = metadata & 3;
        
    	if (pos == 12)
    	{
    		if (side <= 1) return this.m_IconTrunkTopArray[type];
    			return this.m_IconTrunkSideArray[type];
    	}
        
    	return ((pos == 0 && (side == 1 || side == 0)) || (pos == 4 && (side == 5 || side == 4)) || (pos == 8 && (side == 2 || side == 3))) ? this.m_IconTrunkTopArray[type] : this.m_IconTreeSideArray[type];
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
        this.m_IconTreeSideArray = new Icon[DecoUtilsStrings.TREE_SIDE_TEXTURE_PATHS.length];
        this.m_IconTreeTopArray = new Icon[DecoUtilsStrings.TREE_TOP_TEXTURE_PATHS.length];
        this.m_IconTrunkSideArray = new Icon[DecoUtilsStrings.TRUNK_SIDE_TEXTURE_PATHS.length];
        this.m_IconTrunkTopArray = new Icon[DecoUtilsStrings.TRUNK_TOP_TEXTURE_PATHS.length];
        
        for (int index = 0; index < this.m_IconTreeSideArray.length; index++)
        {
        	this.m_IconTreeSideArray[index] = register.registerIcon(DecoUtilsStrings.TREE_SIDE_TEXTURE_PATHS[index]);
        	this.m_IconTreeTopArray[index] = register.registerIcon(DecoUtilsStrings.TREE_TOP_TEXTURE_PATHS[index]);
            this.m_IconTrunkSideArray[index] = register.registerIcon(DecoUtilsStrings.TRUNK_SIDE_TEXTURE_PATHS[index]);
            this.m_IconTrunkTopArray[index] = register.registerIcon(DecoUtilsStrings.TRUNK_TOP_TEXTURE_PATHS[index]);
        }

        super.registerIcons(register);
    }
}
