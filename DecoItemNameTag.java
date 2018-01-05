package net.minecraft.src;

public class DecoItemNameTag extends Item
{
	public DecoItemNameTag(int id)
	{
		super(id);
		setUnlocalizedName("decoItemNameTag");
		SetBellowsBlowDistance(3);
		setCreativeTab(CreativeTabs.tabTools);
	}
	
	public boolean itemInteractionForEntity(ItemStack stack, EntityLiving entity)
	{
		if (!stack.hasDisplayName()) return false;

		entity.func_94058_c(stack.getDisplayName());
		entity.SetPersistent(true);
		--stack.stackSize;
		return true;
	}
}
