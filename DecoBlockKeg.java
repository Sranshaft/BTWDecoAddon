package net.minecraft.src;

public class DecoBlockKeg extends Block
{
	private Icon m_IconSide;
	private Icon m_IconTop;
	
	public DecoBlockKeg(int id) 
	{
		super(id, Material.wood);
		
		this.setUnlocalizedName("decoBlockKeg.small");
		this.setHardness(Block.wood.blockHardness);
		this.setResistance(Block.wood.blockResistance);
		this.setStepSound(Block.soundWoodFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	/**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
        int var10 = metadata & 3;
        byte var11 = 0;

        switch (side)
        {
            case 0:
            case 1:
                var11 = 0;
                break;

            case 2:
            case 3:
                var11 = 8;
                break;

            case 4:
            case 5:
                var11 = 4;
        }

        return var10 | var11;
    }
	
	/**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 31;
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
    		if (side <= 1) return this.m_IconTop;
    			return this.m_IconSide;
    	}
        
    	return ((pos == 0 && (side == 1 || side == 0)) || (pos == 4 && (side == 5 || side == 4)) || (pos == 8 && (side == 2 || side == 3))) ? this.m_IconTop : this.m_IconSide;
    }
	
	/**
	 * When this method is called, your block should register all the icons it needs
	 * with the given IconRegister. This is the only chance you get to register
	 * icons.
	 */
	public void registerIcons(IconRegister register) 
	{
		this.m_IconSide = register.registerIcon("decoBlockKeg_side");
		this.m_IconTop = register.registerIcon("decoBlockKeg_top");
	}
	
	public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        this.setBlockBoundsBasedOnState(var1.blockAccess, var2, var3, var4);
        var1.setRenderBoundsFromBlock(this);
        return var1.renderBlockLog(this, var2, var3, var4);
    }
}
