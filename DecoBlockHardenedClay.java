package net.minecraft.src;

public class DecoBlockHardenedClay extends Block
{
	public DecoBlockHardenedClay(int id, Material material)
	{
		super(id, material);
		setUnlocalizedName("decoBlockHardenedClay");
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(Block.soundStoneFootstep);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
    {
        this.blockIcon = register.registerIcon("decoBlockHardenedClay");
    }

	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon()
    {
        return this.blockIcon;
    }
}
