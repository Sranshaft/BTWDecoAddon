package net.minecraft.src;

public interface DecoITileEntity 
{
	public void readFromNBT(NBTTagCompound tagCompound);
	public void writeToNBT(NBTTagCompound tagCompound);
}
