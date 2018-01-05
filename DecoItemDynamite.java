package net.minecraft.src;

public class DecoItemDynamite extends FCItemDynamite
{
	public DecoItemDynamite(int id)
	{
		super(id);
		setUnlocalizedName("fcItemDynamite");
		SetBuoyancy(1.0F);
		setCreativeTab(CreativeTabs.tabTools);
	}
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		int flintSlot = -1;
		for (int index = 0; index < player.inventory.mainInventory.length; index++)
		{
			if (player.inventory.mainInventory[index] != null && player.inventory.mainInventory[index].itemID == Item.flintAndSteel.itemID)
			{
				flintSlot = index;
				break;
			}
		}
		
		if (flintSlot >= 0)
		{
			ItemStack flintItem = player.inventory.getStackInSlot(flintSlot);
			flintItem.damageItem(1, player);
			
			if (flintItem.stackSize <= 0)
				player.inventory.mainInventory[flintSlot] = null;
			
			world.playSoundAtEntity(player, "random.fuse", 1.0F, 1.0F);
			player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
		}
		else if (!world.isRemote)
		{
			itemStack.stackSize--;
			FCEntityDynamite dynamite = new FCEntityDynamite(world, player, this.itemID, false);
			world.spawnEntityInWorld(dynamite);
			world.playSoundAtEntity(dynamite, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		}
		
		return itemStack;
	}
	
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int inUseCount)
	{
		itemStack.stackSize--;
		
		if (itemStack.stackSize <= 0)
			player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
		
		if (!world.isRemote)
		{
			float strength = 0.5F + 1.5F * (float)(Math.min(100, 120 - inUseCount)) / 100.0F;
			FCEntityDynamite dynamite = new FCEntityDynamite(world, player, this.itemID, true);
			dynamite.m_iFuse = inUseCount;
			dynamite.motionX *= strength;
			dynamite.motionY *= strength;
			dynamite.motionZ *= strength;
			world.spawnEntityInWorld(dynamite);
			world.playSoundAtEntity(dynamite, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		}
	}
	
	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			itemStack.stackSize--;
			world.createExplosion((Entity)null, player.posX, player.posY, player.posZ, 1.5F, true);
		}
		
		return itemStack;
	}
	
	public int getMaxItemUseDuration(ItemStack itemStack)
	{
		return 100;
	}
	
	public EnumAction getItemUseAction(ItemStack itemStack)
	{
		return EnumAction.bow;
	}
}
