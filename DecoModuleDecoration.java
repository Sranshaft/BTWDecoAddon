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
	
	public static Block[] decoBlockStainedFlowerPot = new Block[16];
	
	public static Block decoBlockBamboo;
	public static Block decoBlockCarpetWool;
	public static Block decoBlockChainRope;
	public static Block decoBlockCrate;
	public static Block decoBlockLadderMetalIron;
	
	public static BlockPane decoBlockPanePaperWall;
	public static BlockPane decoBlockPaneSoulforgedSteelFence;
	public static BlockPane decoBlockPaneWattle;
	
	public static Item decoItemBottleHempOil;
	public static Item decoItemChainRope;
	
	public static Item[] decoItemStainedFlowerPot = new Item[16];
	
	public static final int decoBlockBambooID = DecoAddonManager.getBlockID("decoBlockBambooID");
	public static final int decoBlockCarpetWoolID = DecoAddonManager.getBlockID("decoBlockCarpetWoolID");
	public static final int decoBlockChainRopeID = DecoAddonManager.getBlockID("decoBlockChainRopeID");
	public static final int decoBlockCrateID = DecoAddonManager.getBlockID("decoBlockCrateID");
	public static final int decoBlockLadderIronID = DecoAddonManager.getBlockID("decoBlockLadderIronID");
	
	public static final int decoBlockStainedFlowerPotID = DecoAddonManager.getBlockID("decoBlockStainedFlowerPotID");
	
	public static final int decoBlockPanePaperWallID = DecoAddonManager.getBlockID("decoBlockPanePaperWallID");
	public static final int decoBlockPaneSoulforgedSteelFenceID = DecoAddonManager.getBlockID("decoBlockPaneSoulforgedSteelFenceID");
	public static final int decoBlockPaneWattleID = DecoAddonManager.getBlockID("decoBlockPaneWattleID");
	
	public static final int decoItemBottleHempOilID = DecoAddonManager.getBlockID("decoItemBottleHempOilID");
	public static final int decoItemChainRopeID = DecoAddonManager.getBlockID("decoItemChainRopeID");
	
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
			this.decoBlockCarpetWool = new DecoBlockWoolCarpet(this.decoBlockCarpetWoolID);
		
		this.decoBlockBamboo = new DecoBlockBamboo(this.decoBlockBambooID);
		this.decoBlockChainRope = new DecoBlockChainRope(this.decoBlockChainRopeID);
		
		TileEntity.addMapping(DecoTileEntityCrate.class, "Crate");
		this.decoBlockCrate = new DecoBlockCrate(this.decoBlockCrateID);
		
		this.decoBlockLadderMetalIron = new DecoBlockLadderMetal(this.decoBlockLadderIronID);
		
		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			this.decoBlockStainedFlowerPot[index] = new DecoBlockStainedFlowerPot(this.decoBlockStainedFlowerPotID + index, DecoUtilsStrings.COLOUR_TAGS[index]);
			this.decoItemStainedFlowerPot[index] = (new ItemReed(this.decoBlockStainedFlowerPotID + index, this.decoBlockStainedFlowerPot[index])).setUnlocalizedName("decoItemStainedFlowerPot_" + DecoUtilsStrings.COLOUR_TAGS[index]).setCreativeTab(CreativeTabs.tabDecorations);
		}
		
		this.decoBlockPanePaperWall = new DecoBlockPanePaperWall(this.decoBlockPanePaperWallID);
		this.decoBlockPaneSoulforgedSteelFence = new DecoBlockPaneSoulforgedSteelFence(this.decoBlockPaneSoulforgedSteelFenceID);
		this.decoBlockPaneWattle = new DecoBlockPaneWattle(this.decoBlockPaneWattleID);
		
		this.decoItemBottleHempOil = new DecoItemBottleHempOil(this.decoItemBottleHempOilID);
		this.decoItemChainRope = new DecoItemChainRope(this.decoItemChainRopeID);
		
		FCBetterThanWolves.fcAestheticNonOpaque = new DecoFixLightningRod(DecoAddonManager.replaceBlockID(FCBetterThanWolves.fcAestheticNonOpaque));
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockBamboo, "Bamboo");
		DecoAddonManager.register(this.decoBlockLadderMetalIron, "Iron Ladder");
		
		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			DecoAddonManager.register(this.decoItemStainedFlowerPot[index], DecoUtilsStrings.COLOUR_NAMES[index] + " Stained Flower Pot");
		}
		
		DecoAddonManager.register(this.decoBlockPanePaperWall, "Paper Wall");
		DecoAddonManager.register(this.decoBlockPaneSoulforgedSteelFence, "Soulforge Steel Fence");
		DecoAddonManager.register(this.decoBlockPaneWattle, "Wattle");
		
		DecoAddonManager.register(this.decoItemBottleHempOil, "Hemp Oil");
		DecoAddonManager.register(this.decoItemChainRope, "Iron Chain");
	}

	public void addRecipes() 
	{
		if (DecoAddonManager.getConfigOption("enableWoolCarpet"))
		{
			for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
			{
				FCRecipes.AddVanillaRecipe( new ItemStack(this.decoBlockCarpetWool, 4, index),
						new Object[] {"XX ", 'X', new ItemStack(Block.cloth, 1, index) });
			}
		}
		
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockPanePaperWall, 4), 
				new Object[] { "PPP", "PWP", "PPP", 'P', Item.paper, 'W', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockPaneSoulforgedSteelFence, 10), 
				new Object[] { "XXX", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcSteel) });
		
		/*FCRecipes.AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetSteel, 1), 
				new ItemStack[] { new ItemStack(this.decoBlockPaneSoulforgedSteelFence, 1) });*/
		
		// Hemp Oil
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(this.decoItemBottleHempOil), 
				new Object[] { Item.glassBottle, FCBetterThanWolves.fcHempSeeds });
	}

	public void changeVanillaItems() {}
		
	public void setupCustomToolProperties() 
	{
		if (DecoAddonManager.getConfigOption("enableWoolCarpet"))
			ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockCarpetWool);
		
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockPanePaperWall);
		
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockChainRope);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockPaneSoulforgedSteelFence);
	}
}