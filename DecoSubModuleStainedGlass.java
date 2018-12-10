package net.minecraft.src;

import java.util.Random;

public class DecoSubModuleStainedGlass implements DecoISubModule
{
	public static Block decoBlockStainedGlass;
	public static Block decoBlockPaneStainedGlass;
	
	public static final int decoBlockStainedGlassID = DecoAddonManager.getBlockID("decoBlockStainedGlassID");
	public static final int decoBlockPaneStainedGlassID = DecoAddonManager.getBlockID("decoBlockPaneStainedGlassID");
	
	public DecoSubModuleStainedGlass()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Stained Glass");
		
		this.decoBlockStainedGlass = new DecoBlockStainedGlass(this.decoBlockStainedGlassID);
		this.decoBlockPaneStainedGlass = new DecoBlockPaneStainedGlass(this.decoBlockPaneStainedGlassID);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
		
		FCAddOnHandler.LogMessage("[INFO]: Loaded submodule: Stained Glass");
	}
	
	public void registerBlocks() {}
	
	public void addRecipes()
	{
		if (DecoAddonManager.DEBUG_ADDON_LOAD) FCAddOnHandler.LogMessage("[INFO]: Adding recipes: Stained Glass");
		
		FCCraftingManagerCrucibleStoked.getInstance().RemoveRecipe(new ItemStack(Block.glass, 3), new ItemStack[] {new ItemStack(Block.thinGlass, 8)});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(Block.glass, 1), new ItemStack[] {new ItemStack(Block.thinGlass, 2)});

		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			FCRecipes.AddStokedCauldronRecipe(new ItemStack(this.decoBlockStainedGlass, 1, index),
					new ItemStack[] { new ItemStack(Block.glass), new ItemStack(Item.dyePowder, 1, index) });
			FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.glass), 
					new ItemStack[]{new ItemStack(FCBetterThanWolves.fcSoap), new ItemStack(this.decoBlockStainedGlass, 2, index) });
			
			FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockPaneStainedGlass, 16, index), new Object[] { "GGG", "GGG", 'G', new ItemStack(this.decoBlockStainedGlass, 1, index) });
		}
	}
	
	public void changeVanillaItems()
	{
		if (DecoAddonManager.DEBUG_ADDON_LOAD) FCAddOnHandler.LogMessage("[INFO]: Changing items: Stained Glass");
		
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.thinGlass, 16), new Object[] {"###", "###", '#', Block.glass});
		FCRecipes.AddVanillaRecipe(new ItemStack(Block.thinGlass, 12), new Object[] {"###", "###", '#', Block.glass});
	}
	
	public void setupCustomToolProperties()
	{
		if (DecoAddonManager.DEBUG_ADDON_LOAD) FCAddOnHandler.LogMessage("[INFO]: Setting tool properties: Stained Glass");
		
		this.decoBlockStainedGlass.SetPicksEffectiveOn();
		this.decoBlockPaneStainedGlass.SetPicksEffectiveOn();
	}
}
