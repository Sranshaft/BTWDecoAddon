package net.minecraft.src;

public class DecoBlockCoral extends Block
{
	public DecoBlockCoral(int id)
	{
		super(id, Material.coral);
		
        this.setUnlocalizedName("decoBlockCoral");
		this.setHardness(2.0F);
        this.setResistance(1.0F);
        this.setLightValue(0.4F);
        this.setStepSound(Block.soundPowderFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
	}

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon()
    {
        return this.blockIcon;
    }
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
    {
        this.blockIcon = register.registerIcon("decoBlockCoral");
    }
}
