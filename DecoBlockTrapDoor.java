package net.minecraft.src;

public class DecoBlockTrapDoor extends FCBlockTrapDoor 
{
	private Block m_ParentBlock;
	private String m_Tag;
	
	public DecoBlockTrapDoor(int id, Block parentBlock, String tag)
	{
		super(id, parentBlock.blockMaterial);
		setUnlocalizedName("decoBlockTrapDoor." + tag);
		setHardness(parentBlock.blockHardness);
		setResistance(parentBlock.blockResistance);
		
		this.m_ParentBlock = parentBlock;
		this.m_Tag = tag;
	}
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    { 
    	this.blockIcon = register.registerIcon("decoBlockTrapDoor_" + this.m_Tag);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
    	return this.blockIcon;
    }
    
    public void onPoweredBlockChange(World world, int x, int y, int z, boolean par5)
    {
	    int metadata = world.getBlockMetadata(x, y, z);
        boolean var7 = (metadata & 4) > 0;

        if (var7 != par5)
        {
            world.setBlockMetadataWithNotify(x, y, z, metadata ^ 4, 2);
            world.playAuxSFXAtEntity((EntityPlayer)null, 1003, x, y, z, 0);
        }
    }
    
    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourBlockID)
    {
        if (!world.isRemote)
        {
            boolean var9 = world.isBlockIndirectlyGettingPowered(x, y, z);

            if (var9 || neighbourBlockID > 0 && Block.blocksList[neighbourBlockID].canProvidePower())
            {
                this.onPoweredBlockChange(world, x, y, z, var9);
            }
        }
    }
}
