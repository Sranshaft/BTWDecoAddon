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
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        float var5 = 0.625F;
        return AxisAlignedBB.getAABBPool().getAABB((double)x, (double)y, (double)z, (double)(x + 1), (double)((float)(y + 1) - var5), (double)(z + 1));
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
