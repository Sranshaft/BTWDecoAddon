package net.minecraft.src;

import java.util.Random;

public class DecoSubModuleStainedGlass
{
	public static Block decoBlockStainedGlass;
	public static BlockPane decoBlockPaneStainedGlass;
	
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
	}
	
	private void registerBlocks() {}
	
	private void addRecipes()
	{
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
	
	private void changeVanillaItems()
	{
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.thinGlass, 16), new Object[] {"###", "###", '#', Block.glass});
		FCRecipes.AddVanillaRecipe(new ItemStack(Block.thinGlass, 12), new Object[] {"###", "###", '#', Block.glass});
	}
	
	private void setupCustomToolProperties()
	{
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockStainedGlass);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockPaneStainedGlass);
	}
}
