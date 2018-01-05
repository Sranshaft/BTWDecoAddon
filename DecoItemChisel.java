package net.minecraft.src;

public class DecoItemChisel extends Item
{
	private String m_Type;
	
	public DecoItemChisel(int id, EnumToolMaterial toolMaterial, String type) 
	{
		super(id);
		this.setUnlocalizedName("decoItemChisel." + type);
		this.setMaxStackSize(1);
		this.setMaxDamage(toolMaterial.getMaxUses());
        this.setCreativeTab(CreativeTabs.tabTools);
		
		this.m_Type = type;
	}
	
	public void registerIcons(IconRegister register)
    {
        this.itemIcon = register.registerIcon("decoItemChisel_" + this.m_Type);
    }
}
