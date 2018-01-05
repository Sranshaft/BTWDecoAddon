package net.minecraft.src;

public class DecoBlockRedSand extends FCBlockSand
{
	public DecoBlockRedSand(int id)
	{
		super(id);
		setUnlocalizedName("decoBlockRedSand");
		setHardness(Block.sand.blockHardness);
        setResistance(Block.sand.blockResistance);
        setStepSound(Block.soundSandFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
	}
	
	private void DropAsPiles(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        for (int var7 = 0; var7 < 3; ++var7)
        {
            if (var1.rand.nextFloat() <= var6)
            {
                ItemStack var8 = new ItemStack(DecoSubModuleRedSandstone.decoItemPileRedSand);
                this.dropBlockAsItem_do(var1, var2, var3, var4, var8);
            }
        }
    }
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
	{
		this.blockIcon = register.registerIcon("decoBlockRedSand");
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon(int side, int metadata)
	{
		return this.blockIcon;
	}
}
