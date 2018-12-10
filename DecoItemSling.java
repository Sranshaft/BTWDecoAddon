package net.minecraft.src;

public class DecoItemSling extends Item
{
	public DecoItemSling(int id)
	{
		super(id);
		
		this.setUnlocalizedName("decoItemSling");
		this.maxStackSize = 1;
		this.setMaxDamage(128);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int inUseCount)
	{
		if (!player.capabilities.isCreativeMode && DecoUtilsInventory.getItemInIventory(player, FCBetterThanWolves.fcItemStone.itemID) == null) return;

		int useTime = this.getMaxItemUseDuration(stack) - inUseCount;
		float tension = (float)useTime / 20.0F;
		if (tension < 0.2F) return;
		if (tension > 1.0F) tension = 1.0F;

		DecoEntityRock rock = new DecoEntityRock(world, player, tension * 0.75F);

		stack.damageItem(1, player);
		world.playSoundAtEntity(player, "random.bow", 0.5F, 0.3F + itemRand.nextFloat() * 0.1F + tension * 0.1F);

		player.addExhaustion(0.25F + 0.5F * tension);
		player.inventory.consumeInventoryItem(FCBetterThanWolves.fcItemStone.itemID);

		if (!world.isRemote) world.spawnEntityInWorld(rock);
	}

	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
	{
		return stack;
	}

	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 72000;
	}

	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.bow;
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (player.getFoodStats().getFoodLevel() <= 18)
		{
			if (world.isRemote) player.addChatMessage("You\'re too exhausted for throwing rocks.");
			return itemStack;
		}
		
		if (player.capabilities.isCreativeMode || DecoUtilsInventory.getItemInIventory(player, FCBetterThanWolves.fcItemStone.itemID) != null) 
			player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
		
		return itemStack;
	}

	public int getItemEnchantability()
	{
		return 0;
	}
}
