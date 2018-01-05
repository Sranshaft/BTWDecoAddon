package net.minecraft.src;

public interface DecoIContainer 
{
	public boolean canInteractWith(EntityPlayer player);
	public ItemStack transferStackInSlot(EntityPlayer player, int slot);
}
