package net.minecraft.src;

public class DecoItemDebugStick extends Item
{
	public DecoItemDebugStick(int id)
	{
		super(id);
		
		this.setUnlocalizedName("decoItemDebugStick");
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setMaxStackSize(1);
	}
	
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
    	int blockID = world.getBlockId(x, y, z);
        int blockMetadata = world.getBlockMetadata(x, y, z);
        
        player.addChatMessage("======================================");
        player.addChatMessage("Block ID: " + blockID);
        player.addChatMessage("Metadata: " + blockMetadata);
        player.addChatMessage("Side: " + side);
        player.addChatMessage("Position: " + x + ", " + y + ", " + z);
        player.addChatMessage("======================================");
		
		return true;
    }
}
