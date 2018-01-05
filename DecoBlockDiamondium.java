package net.minecraft.src;

public class DecoBlockDiamondium extends Block
{
	public DecoBlockDiamondium(int id, Material material)
	{
		super(id, material);
		setUnlocalizedName("decoBlockDiamondium");
		setHardness(10.0F);
		setResistance(2000.0F);
		setStepSound(Block.soundMetalFootstep);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
    {
        this.blockIcon = register.registerIcon("decoBlockDiamondium");
    }

	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon(int side, int metadata)
    {
        return this.blockIcon;
    }
}
