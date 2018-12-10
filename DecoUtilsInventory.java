package net.minecraft.src;

public class DecoUtilsInventory 
{
	public static ItemStack getItemInIventory(EntityPlayer player, int itemID)
	{
		for (int i = 0; i < 9; ++i)
		{
			ItemStack stack = player.inventory.getStackInSlot(i);
			
			if (stack != null && stack.itemID == itemID) return stack;
		}
		
		return null;
	}
	
	public static Item getItemInIventory(EntityLiving entity, int itemID)
	{
		for (int i = 0; i < 9; ++i)
		{
			Item stack = entity.getArmorItemForSlot(0, i);
			
			if (stack != null && stack.itemID == itemID) return stack;
		}
		
		return null;
	}
}
