package net.minecraft.src;

public class DecoSubModuleToolSling implements DecoISubModule 
{
	public static Item decoItemSling;
	
	public static final int decoItemSlingID = DecoAddonManager.getBlockID("decoItemSlingID");
	public static final int decoEntityRockID = 25;
	public static final int decoEntityRockVehicleSpawnType = 120;
	
	public DecoSubModuleToolSling()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Sling");
		
		this.decoItemSling = new DecoItemSling(this.decoItemSlingID);
		
		EntityList.addMapping(DecoEntityRock.class, "decoEntityRock", this.decoEntityRockID);
		RenderManager.AddEntityRenderer(DecoEntityRock.class, new RenderSnowball(FCBetterThanWolves.fcItemStone));
		
		this.registerBlocks();
		this.addRecipes();
	}

	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoItemSling, "Sling");
	}

	public void addRecipes() 
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemSling, 1), new Object[] {"S S", " X ", 'S', Item.silk, 'X', FCBetterThanWolves.fcItemLeatherCut});
	}

	public void changeVanillaItems() {}
	public void setupCustomToolProperties() {}
}