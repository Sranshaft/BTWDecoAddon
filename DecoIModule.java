package net.minecraft.src;

public interface DecoIModule 
{
	/**
	 * Registers blocks and items with minecraft
	 */
	void registerBlocks();

	/**
	 * Adds the recipe of blocks and items to minecraft
	 */
	void addRecipes();

	/**
	 * Used to remove, change properties of vanilla items and blocks
	 */
	void changeVanillaItems();

	/**
	 * Used to add tool properties to blocks
	 */
	void setupCustomToolProperties();
}
