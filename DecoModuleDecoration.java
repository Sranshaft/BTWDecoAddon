package net.minecraft.src;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.minecraft.client.Minecraft;

public class DecoModuleDecoration implements DecoIModule
{
	public static DecoSubModuleFlowers decoSubModuleFlowers;
	public static DecoSubModuleFurniture decoSubModuleFurniture;
	public static DecoSubModuleHaybale decoSubModuleHaybale;
	public static DecoSubModuleHedge decoSubModuleHedge;
	public static DecoSubModuleLantern decoSubModuleLantern;
	
	public static Block decoBlockBamboo;
	public static Block decoBlockCrate;
	public static Block decoBlockPaperWall;
	public static Block decoBlockWoolCarpet;
	
	public static BlockPane decoBlockPanePaperWall;
	public static BlockPane decoBlockPaneSoulforgedSteelFence;
	public static BlockPane decoBlockPaneWattle;
	
	public static Item decoItemBottleHempOil;
	
	public static final int decoBlockBambooID = DecoAddonManager.getBlockID("decoBlockBambooID");
	public static final int decoBlockCrateID = DecoAddonManager.getBlockID("decoBlockCrateID");
	public static final int decoBlockPaperWallID = DecoAddonManager.getBlockID("decoBlockPaperWallID");
	public static final int decoBlockWoolCarpetID = DecoAddonManager.getBlockID("decoBlockWoolCarpetID");
	
	public static final int decoBlockPanePaperWallID = DecoAddonManager.getBlockID("decoBlockPanePaperWallID");
	public static final int decoBlockPaneSoulforgedSteelFenceID = DecoAddonManager.getBlockID("decoBlockPaneSoulforgedSteelFenceID");
	public static final int decoBlockPaneWattleID = DecoAddonManager.getBlockID("decoBlockPaneWattleID");
	
	public static final int decoItemBottleHempOilID = DecoAddonManager.getBlockID("decoItemBottleHempOilID");
	
	public static final int decoCrateContainerID = 301;
	
	public DecoModuleDecoration()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading module: Decoration");
		
		if (DecoAddonManager.getConfigOption("enableFlowers"))
			this.decoSubModuleFlowers = new DecoSubModuleFlowers();
			
		if (DecoAddonManager.getConfigOption("enableFurniture"))
			this.decoSubModuleFurniture = new DecoSubModuleFurniture();
		
		if (DecoAddonManager.getConfigOption("enableHaybalesAndThatch"))
			this.decoSubModuleHaybale = new DecoSubModuleHaybale();
		
		if (DecoAddonManager.getConfigOption("enableHedges"))
			this.decoSubModuleHedge = new DecoSubModuleHedge();
		
		if (DecoAddonManager.getConfigOption("enableLanterns"))
			this.decoSubModuleLantern = new DecoSubModuleLantern();
		
		if (DecoAddonManager.getConfigOption("enableWoolCarpet"))
			this.decoBlockWoolCarpet = new DecoBlockWoolCarpet(this.decoBlockWoolCarpetID);
		
		this.decoBlockBamboo = new DecoBlockBamboo(this.decoBlockBambooID);
		
		TileEntity.addMapping(DecoTileEntityCrate.class, "Crate");
		this.decoBlockCrate = new DecoBlockCrate(this.decoBlockCrateID);

		this.decoBlockPaperWall = new DecoBlockPaperWall(this.decoBlockPaperWallID);
		this.decoBlockPanePaperWall = new DecoBlockPanePaperWall(this.decoBlockPanePaperWallID);
		this.decoBlockPaneSoulforgedSteelFence = new DecoBlockPaneSoulforgedSteelFence(this.decoBlockPaneSoulforgedSteelFenceID);
		this.decoBlockPaneWattle = new DecoBlockPaneWattle(this.decoBlockPaneWattleID);
		
		this.decoItemBottleHempOil = new DecoItemBottleHempOil(this.decoItemBottleHempOilID);
		
		FCBetterThanWolves.fcAestheticNonOpaque = new DecoFixLightningRod(DecoAddonManager.replaceBlockID(FCBetterThanWolves.fcAestheticNonOpaque));
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockBamboo, "Bamboo");
		DecoAddonManager.register(this.decoBlockPaperWall, "Paper Wall");
		DecoAddonManager.register(this.decoBlockPanePaperWall, "Paper Wall");
		DecoAddonManager.register(this.decoBlockPaneSoulforgedSteelFence, "Soulforge Steel Fence");
		DecoAddonManager.register(this.decoBlockPaneWattle, "Wattle");
		
		DecoAddonManager.register(this.decoItemBottleHempOil, "Hemp Oil");
	}

	public void addRecipes() 
	{
		if (DecoAddonManager.getConfigOption("enableWoolCarpet"))
		{
			for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
			{
				FCRecipes.AddVanillaRecipe( new ItemStack(this.decoBlockWoolCarpet, 4, index),
						new Object[] {"XX ", 'X', new ItemStack(Block.cloth, 1, index) });
			}
		}
		
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockPanePaperWall, 4), 
				new Object[] { "PPP", "PWP", "PPP", 'P', Item.paper, 'W', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockPaneSoulforgedSteelFence, 10), 
				new Object[] { "XXX", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcSteel) });
		
		/*FCRecipes.AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetSteel, 1), 
				new ItemStack[] { new ItemStack(this.decoBlockPaneSoulforgedSteelFence, 1) });*/
		
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(this.decoItemBottleHempOil), 
				new Object[] { Item.glassBottle, FCBetterThanWolves.fcHempSeeds });
	}

	public void changeVanillaItems() {}
		
	public void setupCustomToolProperties() 
	{
		if (DecoAddonManager.getConfigOption("enableWoolCarpet"))
			ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockWoolCarpet);
		
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockPaperWall);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockPanePaperWall);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockPaneSoulforgedSteelFence);
	}
}