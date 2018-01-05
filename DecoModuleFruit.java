package net.minecraft.src;

public class DecoModuleFruit
{
	public static Item decoItemFruitBanana;
	public static Item decoItemFruitGrape;
	public static Item decoItemFruitOrange;
	
	public static final int decoItemFruitBananaID = DecoAddonManager.getBlockID("decoItemFruitBananaID");
	public static final int decoItemFruitGrapeID = DecoAddonManager.getBlockID("decoItemFruitGrapeID");
	public static final int decoItemFruitOrangeID = DecoAddonManager.getBlockID("decoItemFruitOrangeID");
	
	public DecoModuleFruit()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading module: Fruit");
		
		this.decoItemFruitBanana = new DecoItemFruit(this.decoItemFruitBananaID, "banana", 1, 0.5F, Potion.poison.id, 25);
		this.decoItemFruitGrape = new DecoItemFruit(this.decoItemFruitGrapeID, "grape", 1, 0.0F);
		this.decoItemFruitOrange = new DecoItemFruit(this.decoItemFruitOrangeID, "orange", 1, 0.0F);
		
		this.registerBlock();
	}
	
	private void registerBlock()
	{
		DecoAddonManager.register(this.decoItemFruitBanana, "Banana");
		DecoAddonManager.register(this.decoItemFruitGrape, "Grape");
		DecoAddonManager.register(this.decoItemFruitOrange, "Orange");
	}
}
