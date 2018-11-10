package net.minecraft.src;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class DecoSubModuleFlowers implements DecoISubModule
{
	public static DecoBlockFlower decoBlockFlowerExtended;
	public static DecoBlockTulip decoBlockFlowerTulip;
	
	public static final int decoBlockFlowerExtendedID = DecoAddonManager.getBlockID("decoBlockFlowerExtendedID");
	public static final int decoBlockFlowerTulipID = DecoAddonManager.getBlockID("decoBlockFlowerTulipID");
	
	public static final int decoItemFertilizerID = DecoAddonManager.getBlockID("decoItemFertilizerID");
	
	public DecoSubModuleFlowers()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Flowers");
		
		Item.m_bSuppressConflictWarnings=true;
		Item.dyePowder = new DecoItemDyeExtended(Item.dyePowder.itemID - 256);
		Item.m_bSuppressConflictWarnings=false;

		FCBetterThanWolves.fcPlanter = new DecoBlockPlanter(DecoAddonManager.replaceBlockID(FCBetterThanWolves.fcPlanter));
		
		this.decoBlockFlowerExtended = new DecoBlockFlower(this.decoBlockFlowerExtendedID);
		this.decoBlockFlowerTulip = new DecoBlockTulip(this.decoBlockFlowerTulipID);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() {}
	
	public void addRecipes()
	{
		for (int Index = 0; Index < 16; ++Index)
			FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Block.cloth, 1, BlockCloth.getDyeFromBlock(Index)), 
				new Object[] { new ItemStack(Item.dyePowder, 1, Index + 16), new ItemStack(Item.itemsList[Block.cloth.blockID], 1, 0)});
		
		//New millstone recipes, flowers
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 1, 31), new ItemStack(Item.dyePowder, 1, 11)}, 
				new ItemStack[] { new ItemStack(this.decoBlockFlowerExtended, 1, 6)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 1, 31), new ItemStack(Item.dyePowder, 1, 11)}, 
				new ItemStack[] { new ItemStack(this.decoBlockFlowerExtended, 1, 10)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 1, 31), new ItemStack(Item.dyePowder, 1, 11)}, 
				new ItemStack[] { new ItemStack(this.decoBlockFlowerExtended, 1, 11)});
		
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 2, 10)},	
				new ItemStack[] { new ItemStack(this.decoBlockFlowerExtended, 1, 0) });
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 2, 12)},	
				new ItemStack[] { new ItemStack(this.decoBlockFlowerExtended, 1, 1) });
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 2, 14)},	
				new ItemStack[] { new ItemStack(this.decoBlockFlowerExtended, 1, 2) });
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 2, 9) },	
				new ItemStack[] { new ItemStack(this.decoBlockFlowerExtended, 1, 3) });
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 2, 20)},	
				new ItemStack[] { new ItemStack(this.decoBlockFlowerExtended, 1, 4) });
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 2, 5) },	
				new ItemStack[] { new ItemStack(this.decoBlockFlowerExtended, 1, 5) });
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 2, 13)},	
				new ItemStack[] { new ItemStack(this.decoBlockFlowerExtended, 1, 7) });
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 2, 12)},	
				new ItemStack[] { new ItemStack(this.decoBlockFlowerExtended, 1, 8) });
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 2, 1) },	
				new ItemStack[] { new ItemStack(this.decoBlockFlowerExtended, 1, 9) });
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 2, 13)},	
				new ItemStack[] { new ItemStack(this.decoBlockFlowerExtended, 1, 12) });
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 2, 13)},	
				new ItemStack[] { new ItemStack(this.decoBlockFlowerExtended, 1, 13) });
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 3, 1)},	
				new ItemStack[] { new ItemStack(this.decoBlockFlowerExtended, 1, 14) });
		
		//tulips
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 1, 14), new ItemStack(Item.dyePowder, 1, 1)}, 
				new ItemStack[] { new ItemStack(this.decoBlockFlowerTulip, 1, 0)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 2, 9)}, 
				new ItemStack[] { new ItemStack(this.decoBlockFlowerTulip, 1, 1) });
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 2, 14)},
				new ItemStack[] { new ItemStack(this.decoBlockFlowerTulip, 1, 2) });
		FCRecipes.AddMillStoneRecipe(new ItemStack[] { new ItemStack(Item.dyePowder, 2, 31)},
				new ItemStack[] { new ItemStack(this.decoBlockFlowerTulip, 1, 3) });
		
		//Cooking with dyes
		for (int Index = 0; Index < 16; Index++)
		{
			FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Block.cloth, 1, BlockCloth.getDyeFromBlock(Index)),
				new Object[] { new ItemStack(Item.dyePowder, 1, Index + 16), new ItemStack(Item.itemsList[Block.cloth.blockID], 1, 0) });
		}
		
		//Mixing dyes
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 10), 
				new Object[] { new ItemStack(Item.dyePowder, 1, 2), new ItemStack(Item.dyePowder, 1, 31) });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 8), 
				new Object[] { new ItemStack(Item.dyePowder, 1, 0), new ItemStack(Item.dyePowder, 1, 31) });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 7), 
				new Object[] { new ItemStack(Item.dyePowder, 1, 8), new ItemStack(Item.dyePowder, 1, 31) });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 9), 
				new Object[] { new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 31) });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 6), 
				new Object[] { new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 2) });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 5), 
				new Object[] { new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 1) });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 3, 13), 
				new Object[] { new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 9) });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 12), 
				new Object[] { new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 15) });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 12), 
				new Object[] { new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 31) });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 12), 
				new Object[] { new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.dyePowder, 1, 15) });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 3, 7), 
				new Object[] { new ItemStack(Item.dyePowder, 1, 0), new ItemStack(Item.dyePowder, 1, 31), new ItemStack(Item.dyePowder, 1, 15) });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 3, 7), 
				new Object[] { new ItemStack(Item.dyePowder, 1, 0), new ItemStack(Item.dyePowder, 1, 31), new ItemStack(Item.dyePowder, 1, 31) });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 4, 13), 
				new Object[] { new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 15) });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 4, 13), 
				new Object[] { new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 31) });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 4, 13), 
				new Object[] { new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 31) });
	}

	public void changeVanillaItems() 
	{
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Item.flowerPot, 1),
				new Object[] {"# #", " # ", '#', Item.brick});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.flowerPot, 1), 
				new ItemStack[]{new ItemStack(FCBetterThanWolves.fcItemPileDirt), new ItemStack(FCBetterThanWolves.fcUrn)});	
	}

	public void setupCustomToolProperties() {}
}
