package net.minecraft.src;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.minecraft.client.Minecraft;

public class DecoModuleDecoration implements DecoIModule
{
	public static DecoSubModuleBell decoSubModuleBell;
	public static DecoSubModuleCarpetLeaf decoSubModuleCarpetLeaf;
	public static DecoSubModuleCarpetWool decoSubModuleCarpetWool;
	public static DecoSubModuleCrate decoSubModuleCrates;
	public static DecoSubModuleFlower decoSubModuleFlowers;
	public static DecoSubModuleFurniture decoSubModuleFurniture;
	public static DecoSubModuleHaybaleAndThatch decoSubModuleHaybaleAndThatch;
	public static DecoSubModuleHedge decoSubModuleHedge;
	public static DecoSubModuleLantern decoSubModuleLantern;
	public static DecoSubModuleSack decoSubModuleSack;
	public static DecoSubModuleStainedFlowerPot decoSubModuleStainedFlowerPot;
	public static DecoSubModuleStainedPlanter decoSubModuleStainedPlanter;

	public static Block decoBlockBamboo;
	public static Block decoBlockLadderMetalIron;

	public static Item decoItemBamboo;
	public static Item decoItemBottleHempOil;

	public static final int decoBlockBambooID = DecoAddonManager.getBlockID("decoBlockBambooID");
	public static final int decoBlockLadderIronID = DecoAddonManager.getBlockID("decoBlockLadderIronID");

	public static final int decoItemBambooID = DecoAddonManager.getBlockID("decoItemBambooID");
	public static final int decoItemBottleHempOilID = DecoAddonManager.getBlockID("decoItemBottleHempOilID");

	public DecoModuleDecoration()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading module: Decoration");

		if (DecoAddonManager.getConfigOption("enableBell"))
			this.decoSubModuleBell = new DecoSubModuleBell();
		
		if (DecoAddonManager.getConfigOption("enableCarpetLeaf"))
			this.decoSubModuleCarpetLeaf = new DecoSubModuleCarpetLeaf();
		
		if (DecoAddonManager.getConfigOption("enableCarpetWool"))
			this.decoSubModuleCarpetWool = new DecoSubModuleCarpetWool();

		if (DecoAddonManager.getConfigOption("enableCrates"))
			this.decoSubModuleCrates = new DecoSubModuleCrate();

		if (DecoAddonManager.getConfigOption("enableFlowers"))
			this.decoSubModuleFlowers = new DecoSubModuleFlower();

		if (DecoAddonManager.getConfigOption("enableWoodFurniture"))
			this.decoSubModuleFurniture = new DecoSubModuleFurniture();

		if (DecoAddonManager.getConfigOption("enableHaybaleAndThatch"))
			this.decoSubModuleHaybaleAndThatch = new DecoSubModuleHaybaleAndThatch();

		if (DecoAddonManager.getConfigOption("enableHedges"))
			this.decoSubModuleHedge = new DecoSubModuleHedge();

		if (DecoAddonManager.getConfigOption("enableLanterns"))
			this.decoSubModuleLantern = new DecoSubModuleLantern();
		
		if (DecoAddonManager.getConfigOption("enableSack"))
			this.decoSubModuleSack = new DecoSubModuleSack();

		if (DecoAddonManager.getConfigOption("enableStainedFlowerPot"))
			this.decoSubModuleStainedFlowerPot = new DecoSubModuleStainedFlowerPot();
		
		if (DecoAddonManager.getConfigOption("enableStainedPlanter"))
			this.decoSubModuleStainedPlanter = new DecoSubModuleStainedPlanter();

		this.decoBlockBamboo = new DecoBlockBamboo(this.decoBlockBambooID);
		this.decoBlockLadderMetalIron = new DecoBlockLadderMetal(this.decoBlockLadderIronID);

		this.decoItemBamboo = new DecoItemBamboo(this.decoItemBambooID, this.decoBlockBambooID);
		this.decoItemBottleHempOil = new DecoItemBottleHempOil(this.decoItemBottleHempOilID);

		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}

	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockLadderMetalIron, "Iron Ladder");

		DecoAddonManager.register(this.decoItemBamboo, "Bamboo");
		DecoAddonManager.register(this.decoItemBottleHempOil, "Hemp Oil");
	}

	public void addRecipes() 
	{
		// Hemp Oil
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(this.decoItemBottleHempOil), 
				new Object[] { Item.glassBottle, FCBetterThanWolves.fcHempSeeds });
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		this.decoBlockBamboo.SetAxesEffectiveOn();
		this.decoBlockLadderMetalIron.SetPicksEffectiveOn();
	}
}