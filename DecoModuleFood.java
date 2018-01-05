package net.minecraft.src;

public class DecoModuleFood
{
	DecoSubModuleFruit decoSubModuleFruit;
	
	public DecoModuleFood()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading module: Food");
		
		if (DecoAddonManager.getConfigOption("enableFruit"))
			this.decoSubModuleFruit = new DecoSubModuleFruit();
	}
}
