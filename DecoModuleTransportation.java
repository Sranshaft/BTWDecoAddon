package net.minecraft.src;

public class DecoModuleTransportation implements DecoIModule 
{
	public static Block decoBlockAnchor;
	public static Block decoBlockChain;
	
	public static Item decoItemChainRing;
	public static Item decoItemChain;
	
	public static final int decoBlockChainID = DecoAddonManager.getBlockID("decoBlockChainID");
	
	public static final int decoItemChainRingID = DecoAddonManager.getBlockID("decoItemChainRingID");
	public static final int decoItemChainID = DecoAddonManager.getBlockID("decoItemChainID");
	
	public DecoModuleTransportation()
	{
		this.decoBlockAnchor = new DecoBlockAnchor(DecoAddonManager.replaceBlockID(FCBetterThanWolves.fcAnchor));
		this.decoBlockChain = new DecoBlockChain(this.decoBlockChainID);
		
		this.decoItemChain = new DecoItemChain(this.decoItemChainID);
		this.decoItemChainRing = new DecoItemChainRing(this.decoItemChainRingID);
		
		this.registerBlocks();
		this.addRecipes();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks()
	{
		DecoAddonManager.register(this.decoBlockAnchor, "Anchor");
		DecoAddonManager.register(this.decoBlockChain, "Iron Chain Block");
		
		DecoAddonManager.register(this.decoItemChain, "Iron Chain");
		DecoAddonManager.register(this.decoItemChainRing, "Iron Chain Ring");
	}
	
	public void addRecipes() 
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemChainRing, 1, 0), new Object[] { " # ", "# #", " # ", '#', FCBetterThanWolves.fcItemNuggetIron });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemChain, 1, 0), new Object[] { "#", "#", "#", '#', this.decoItemChainRing });
	}
	
	public void changeVanillaItems() {}
	
	public void setupCustomToolProperties()
	{
		this.decoBlockAnchor.SetPicksEffectiveOn(true);
		this.decoBlockChain.SetPicksEffectiveOn(true);
	}
}
