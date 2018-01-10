package net.minecraft.src;

public class DecoBlockCoral extends Block
{
	public DecoBlockCoral(int id)
	{
		super(id, Material.coral);
		setUnlocalizedName("decoBlockCoral");
		setHardness(2.0F);
        setResistance(1.0F);
        setLightValue(0.4F);
        setStepSound(Block.soundPowderFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
	}
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
    {
        this.blockIcon = register.registerIcon("decoBlockCoral");
    }

	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon()
    {
        return this.blockIcon;
    }
}
