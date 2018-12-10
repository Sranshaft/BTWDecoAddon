package net.minecraft.src;

public class DecoModuleBuilding implements DecoIModule 
{
	public static DecoSubModuleAestheticMetal decoSubModuleAestheticMetal;
	public static DecoSubModuleAestheticWood decoSubModuleAestheticWood;
	public static DecoSubModuleBark decoSubModuleBark;
	public static DecoSubModuleBlackStoneBrick decoSubModuleBlackStoneBrick;
	public static DecoSubModuleBrimstone decoSubModuleBrimstone;
	public static DecoSubModuleCharredNetherBrick decoSubModuleCharredNetherBrick;
	public static DecoSubModuleDuskbound decoSubModuleDuskbound;
	public static DecoSubModuleEbonstoneAndBrick decoSubModuleEbonstoneAndBrick;
	public static DecoSubModuleEndstoneBrick decoSubModuleEndstoneBrick;
	public static DecoSubModuleHardenedClay decoSubModuleHardenedClay;
	public static DecoSubModuleMudstoneAndBrick decoSubModuleMudstoneAndBrick;
	public static DecoSubModuleNetherWroughtStone decoSubModuleNetherWroughtStone;
	public static DecoSubModulePrismarineAndBrick decoSubModulePrismarineAndBrick;
	public static DecoSubModuleRedSandstone decoSubModuleRedSandstone;
	public static DecoSubModuleRedSandstoneBrick decoSubModuleRedSandstoneBrick;
	public static DecoSubModuleSandstoneBrick decoSubModuleSandstoneBrick;
	public static DecoSubModuleSandyBrick decoSubModuleSandyBrick;
	public static DecoSubModuleStainedClay decoSubModuleStainedClay;
	public static DecoSubModuleStainedGlass decoSubModuleStainedGlass;
	public static DecoSubModuleWhiteStoneBrick decoSubModuleWhiteStoneBrick;
	
	public DecoModuleBuilding()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading module: Building");
		
		if (DecoAddonManager.getConfigOption("enableAestheticMetal"))
			this.decoSubModuleAestheticMetal = new DecoSubModuleAestheticMetal();
		
		if (DecoAddonManager.getConfigOption("enableAestheticWood"))
			this.decoSubModuleAestheticWood = new DecoSubModuleAestheticWood();
		
		if (DecoAddonManager.getConfigOption("enableBarkBlock"))
			this.decoSubModuleBark = new DecoSubModuleBark();
		
		if (DecoAddonManager.getConfigOption("enableBlackStoneBricks"))
			this.decoSubModuleBlackStoneBrick = new DecoSubModuleBlackStoneBrick();
		
		if (DecoAddonManager.getConfigOption("enableBrimstone"))
			this.decoSubModuleBrimstone = new DecoSubModuleBrimstone();
		
		if (DecoAddonManager.getConfigOption("enableCharredNetherBricks"))
			this.decoSubModuleCharredNetherBrick = new DecoSubModuleCharredNetherBrick();
		
		if (DecoAddonManager.getConfigOption("enableDuskbound"))
			this.decoSubModuleDuskbound = new DecoSubModuleDuskbound();
		
		if (DecoAddonManager.getConfigOption("enableEbonstoneAndBricks"))
			this.decoSubModuleEbonstoneAndBrick = new DecoSubModuleEbonstoneAndBrick();
		
		if (DecoAddonManager.getConfigOption("enableEndstoneBricks"))
			this.decoSubModuleEndstoneBrick = new DecoSubModuleEndstoneBrick();
		
		if (DecoAddonManager.getConfigOption("enableHardenedClay"))
			this.decoSubModuleHardenedClay = new DecoSubModuleHardenedClay();
		
		if (DecoAddonManager.getConfigOption("enableMudstoneAndBricks"))
			this.decoSubModuleMudstoneAndBrick = new DecoSubModuleMudstoneAndBrick();
		
		if (DecoAddonManager.getConfigOption("enableNetherWroughtStone"))
			this.decoSubModuleNetherWroughtStone = new DecoSubModuleNetherWroughtStone();
		
		if (DecoAddonManager.getConfigOption("enablePrismarineAndBricks"))
			this.decoSubModulePrismarineAndBrick = new DecoSubModulePrismarineAndBrick();
			
		if (DecoAddonManager.getConfigOption("enableRedSandstone"))
			this.decoSubModuleRedSandstone = new DecoSubModuleRedSandstone();
		
		if (DecoAddonManager.getConfigOption("enableRedSandstoneBricks"))
			this.decoSubModuleRedSandstoneBrick = new DecoSubModuleRedSandstoneBrick();
			
		if (DecoAddonManager.getConfigOption("enableSandstoneBricks"))
			this.decoSubModuleSandstoneBrick = new DecoSubModuleSandstoneBrick();
		
		if (DecoAddonManager.getConfigOption("enableSandyBricks"))
			this.decoSubModuleSandyBrick = new DecoSubModuleSandyBrick();
		
		if (DecoAddonManager.getConfigOption("enableStainedClay") && DecoAddonManager.getConfigOption("enableHardenedClay"))
			this.decoSubModuleStainedClay = new DecoSubModuleStainedClay();
		
		if (DecoAddonManager.getConfigOption("enableStainedGlass"))
			this.decoSubModuleStainedGlass = new DecoSubModuleStainedGlass();
		
		if (DecoAddonManager.getConfigOption("enableWhiteStoneBricks"))
			this.decoSubModuleWhiteStoneBrick = new DecoSubModuleWhiteStoneBrick();
	}
	
	public void registerBlocks() {}
	public void addRecipes() {}
	public void changeVanillaItems() {}
	public void setupCustomToolProperties() {}
}