package net.minecraft.src;

public class DecoUtilsRecipes 
{	
	public static void addBlockRecipe(Block subItem, int subItemMetadata, Block container, int containerMetadata, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted, containerMetadata), new Object[] { "XX", "XX", 'X', new ItemStack(subItem, subItemMetadata) });
	}
	
	public static void addBlockRecipe(Block subItem, Block container, int containerMetadata, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted, containerMetadata), new Object[] { "XX", "XX", 'X', subItem });
	}
	
	public static void addBlockRecipe(Block subItem, Block container, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted), new Object[] { "XX", "XX", 'X', subItem });
	}
	
	public static void addBlockRecipe(Item subItem, Block container, int containerMetadata, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted, containerMetadata), new Object[] { "XX", "XX", 'X', subItem });
	}
	
	public static void addBlockRecipe(Item subItem, Block container, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted), new Object[] { "XX", "XX", 'X', subItem });
	}
	
	public static void addChiseledRecipe(Block subItem, int subItemMetadata, Block container, int containerMetadata, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted, containerMetadata), new Object[] { "XXX", "X X", "XXX", 'X', new ItemStack(subItem, subItemMetadata) });
	}
	
	public static void addChiseledRecipe(Block subItem, int subItemMetadata, Block container, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted), new Object[] { "XXX", "X X", "XXX", 'X', new ItemStack(subItem, subItemMetadata) });
	}
	
	public static void addChiseledRecipe(Block subItem, Block container, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted), new Object[] { "XXX", "X X", "XXX", 'X', new ItemStack(subItem) });
	}
	
	public static void addChiseledRecipe(Block subItem, Block container, int containerMetadata, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted, containerMetadata), new Object[] { "XXX", "X X", "XXX", 'X', new ItemStack(subItem) });
	}
	
	public static void addPillarRecipe(Block subItem, int subItemMetadata, Block container, int containerMetadata, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted, containerMetadata), new Object[] { "X", "X", 'X', new ItemStack(subItem, subItemMetadata) });
	}
	
	public static void addPillarRecipe(Block subItem, int subItemMetadata, Block container, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted), new Object[] { "X", "X", 'X', new ItemStack(subItem, subItemMetadata) });
	}
	
	public static void addPillarRecipe(Block subItem, Block container, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted), new Object[] { "X", "X", 'X', new ItemStack(subItem) });
	}
	
	public static void addPillarRecipe(Block subItem, Block container, int containerMetadata, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted, containerMetadata), new Object[] { "X", "X", 'X', new ItemStack(subItem) });
	}
	
	public static void addSlabRecipe(Block subItem, int subItemMetadata, Item container, int containerMetadata, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted, containerMetadata), new Object[] { "XXX", 'X', new ItemStack(subItem, subItemMetadata) });
		FCRecipes.AddVanillaRecipe(new ItemStack(subItem), new Object[] { "X", "X", 'X', container });
	}
	
	public static void addSlabRecipe(Block subItem, int subItemMetadata, Block container, int containerMetadata, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted, containerMetadata), new Object[] { "XXX", 'X', new ItemStack(subItem, subItemMetadata) });
		FCRecipes.AddVanillaRecipe(new ItemStack(subItem), new Object[] { "X", "X", 'X', container });
	}
	
	public static void addSlabRecipe(Block subItem, Block container, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted), new Object[] { "XXX", 'X', subItem });
		FCRecipes.AddVanillaRecipe(new ItemStack(subItem), new Object[] { "XX ", "XX ", 'X', container });
	}
	
	public static void addSlabRecipe(Block subItem, Item container, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted), new Object[] { "XXX", 'X', subItem });
		FCRecipes.AddVanillaRecipe(new ItemStack(subItem), new Object[] { "XX ", "XX ", 'X', container });
	}
	
	public static void addStairsRecipe(Block subItem, Block container, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted), new Object[] { "X  ", "XX ", "XXX", 'X', subItem });
	}
	
	public static void addStairsRecipe(ItemStack subItem, Block container, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted), new Object[] { "X  ", "XX ", "XXX", 'X', subItem });
	}
	
	public static void addStorageRecipe(Object subItem, ItemStack container)
	{
		FCRecipes.AddVanillaRecipe(container, new Object [] { "XXX", "XXX", "XXX", 'X', subItem });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack((Item)subItem, 9), new ItemStack[] { container });
	}
	
	public static void addStorageRecipe(Item subItem, Block container)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container), new Object[] { "XXX", "XXX", "XXX", 'X', subItem });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(subItem, 9), new ItemStack[] { new ItemStack(container) });
	}
	
	public static void addStorageRecipe(Item subItem, Item container)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container), new Object[] { "XXX", "XXX", "XXX", 'X', subItem });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(subItem, 9), new ItemStack[] { new ItemStack(container) });
	}
	
	public static void addStorageRecipe(ItemStack subItem, ItemStack Container)
	{
		FCRecipes.AddVanillaRecipe(Container, new Object[] { "XXX", "XXX", "XXX", 'X', subItem });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(subItem.itemID, 9, subItem.getItemDamage()), new ItemStack[] { Container } );
	}
	
	public static void addWallRecipe(Item subItem, Block container, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted), new Object[] { "XXX", "XXX", 'X', subItem });
	}
	
	public static void addWallRecipe(ItemStack subItem, Block container, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted), new Object[] { "XXX", "XXX", 'X', subItem });
	}
	
	public static void addWallRecipe(ItemStack subItem, ItemStack container, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(container, new Object[] { "XXX", "XXX", 'X', subItem });
	}
	
	public static void addWallRecipe(Block subItem, Block container, int amountCrafted)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(container, amountCrafted), new Object[] { "XXX", "XXX", 'X', subItem });
	}
	
	public static void addCompostBinRecipe(ItemStack subItem, ItemStack container)
    {
        DecoCraftingManagerCompostBin.getInstance().AddRecipe(subItem, container);
    }
}
