package net.minecraft.src;

public class DecoBlockMud extends Block
{
	private final float m_SinkRate = -0.025F;
	
	public DecoBlockMud(int id)
	{
		super(id, Material.clay);
		setUnlocalizedName("decoBlockMud");
        setHardness(1.0F);
        setResistance(1.0F);
        setStepSound(FCBetterThanWolves.fcSoundSquishFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
	}
	
	/**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
    
    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
    	entity.setInWeb();
        entity.motionY *= this.m_SinkRate;
    }
    
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
    {
        this.blockIcon = register.registerIcon("decoBlockMud");
    }

	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon()
    {
        return this.blockIcon;
    }
}
