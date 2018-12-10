package net.minecraft.src;

public class DecoBlockPrismarine extends Block implements DecoIBlock 
{
	public DecoBlockPrismarine(int id)
	{
		super(id, Material.rock);
		
		this.setUnlocalizedName("decoBlockPrismarine");
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public boolean isOpaqueCube() 
	{
		return true;
	}

	public boolean renderAsNormalBlock() 
	{	
		return true;
	}

	public void registerIcons(IconRegister register) 
	{
		this.blockIcon = register.registerIcon("decoBlockPrismarine");
	}

	public Icon getIcon(int side, int metadata) 
	{
		return this.blockIcon;
	}

}
