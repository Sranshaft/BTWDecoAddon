package net.minecraft.src;

import java.util.List;

public class DecoItemBlock extends ItemBlockWithMetadata
{
	private String[] m_SubTypes;
	
	public DecoItemBlock(Block parentBlock, String[] subTypes, String prefix, String[] localizedNames, String suffix)
	{
		super(parentBlock.blockID - 256, parentBlock);
		setUnlocalizedName(parentBlock.getUnlocalizedName());
		setMaxDamage(0);
		setHasSubtypes(true);
		
		this.m_SubTypes = subTypes;
		
		for (int index = 0; index < this.m_SubTypes.length; index++)
		{
			DecoAddonManager.register(new ItemStack(this, 1, index), prefix + localizedNames[index] + suffix);
		}
	}
	
	/**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int metadata)
    {
        return metadata;
    }
	
    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack reference)
    {
		return super.getUnlocalizedName() + "." + this.m_SubTypes[reference.getItemDamage()];
	}
	
	public void getSubItems(int var1, CreativeTabs var2, List var3)
	{
		for (int index = 0; index < this.m_SubTypes.length; index++)
			var3.add(new ItemStack(this, 1, index));
	}
}