package net.minecraft.src;

public class DecoItemCoal extends ItemCoal
{
	private Icon m_IconCharcoal;
	
	public DecoItemCoal(int id)
	{
		super(id);
		setUnlocalizedName("coal");
		setCreativeTab(CreativeTabs.tabMaterials);
		SetDefaultFurnaceBurnTime(1600);
	}
	
	public Icon getIconFromDamage(int metadata)
	{
		return metadata == 1 ? this.m_IconCharcoal : super.getIconFromDamage(metadata);
	}
	
	public void registerIcons(IconRegister register)
	{
		super.registerIcons(register);
		this.m_IconCharcoal = register.registerIcon("charcoal");
	}
	
	public boolean IsPistonPackable(ItemStack itemStack)
	{
		return true;
	}
	
	public int GetRequiredItemCountToPistonPack(ItemStack itemStack)
	{
		return 9;
	}
	
	public int GetResultingBlockMetadataOnPistonPack(ItemStack itemStack)
	{
		return itemStack.getItemDamage();
	}
}
