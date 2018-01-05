package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class DecoItemSpyglass extends Item
{
	private float m_CurrentFOV;
	private boolean m_IsZooming = false;
	private final float m_ZoomFOV = -10F;
	
	public DecoItemSpyglass(int id)
	{
		super(id);
		setUnlocalizedName("decoItemSpyglass");
		setMaxStackSize(1);
		setMaxDamage(256);
		setCreativeTab(CreativeTabs.tabTools);
	}
	
	/**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int itemCount)
    {
    	itemStack.damageItem(16, player);
    	player.isZooming = false;
    }
    
    /**
     * How long it takes to use or consume an item
     */
    public int getItemInUseDuration(ItemStack itemStack)
    {
        return 7200;
    }
    
    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
    	player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
    	player.isZooming = true;
    	
    	return itemStack;
    }
}