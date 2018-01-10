package net.minecraft.src;

public class DecoBlockBone extends Block implements DecoIBlock
{
	private Icon m_IconSide;
	private Icon m_IconTop;
	
	public DecoBlockBone(int id)
	{
		super(id, Material.rock);
		
		this.setUnlocalizedName("decoBlockBone");
		this.setHardness(2.0F);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public int idPicked(World world, int x, int y, int z) 
	{
		return world.getBlockId(x, y, z);
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
        return DecoEnumRenderType.LOG.getRenderType();
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
        int var3 = metadata & 12;
        int var4 = metadata & 3;
        return var3 == 0 && (side == 1 || side == 0) ? this.m_IconTop : (var3 == 4 && (side == 5 || side == 4) ? this.m_IconTop : (var3 == 8 && (side == 2 || side == 3) ? this.m_IconTop : this.m_IconSide));
    }
    
    /**
	 * When this method is called, your block should register all the icons it needs
	 * with the given IconRegister. This is the only chance you get to register
	 * icons.
	 */
	public void registerIcons(IconRegister register) 
	{
		this.m_IconSide = register.registerIcon("decoBlockBone_side");
		this.m_IconTop = register.registerIcon("decoBlockBone_top");
	}
	
	public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        this.setBlockBoundsBasedOnState(var1.blockAccess, var2, var3, var4);
        var1.setRenderBoundsFromBlock(this);

        return var1.renderBlockLog(this, var2, var3, var4);
    }
}
