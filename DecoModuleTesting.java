package net.minecraft.src;

public class DecoModuleTesting implements DecoIModule 
{
	public static Block decoBlockDebug;
	public static Item decoItemDebugStick;
	
	public static final int decoBlockDebugID = DecoAddonManager.getBlockID("decoBlockDebugID");
	public static final int decoItemDebugStickID = DecoAddonManager.getBlockID("decoItemDebugStickID");
	
	public DecoModuleTesting()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading module: Debug");
		
		this.decoBlockDebug = new DecoBlockDebug(this.decoBlockDebugID);
		this.decoItemDebugStick = new DecoItemDebugStick(this.decoItemDebugStickID);
		
		this.registerBlocks();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockDebug, "Debug Block");
		DecoAddonManager.register(this.decoItemDebugStick, "Debug Stick");
	}

	public void addRecipes() {}
	public void changeVanillaItems() {}
	public void setupCustomToolProperties() {}
}
