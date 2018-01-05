package net.minecraft.src;

public class DecoModuleEquipment 
{
	public static DecoSubModuleTools decoSubModuleTools;
	
	public DecoModuleEquipment()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading module: Equipment");
		this.decoSubModuleTools = new DecoSubModuleTools();
	}
	
	public void registerBlocks() {}
	
	public void addRecipes() {}
	
	public void changeVanillaItems() {}
	
	public void setupCustomToolProperties() {}
}
