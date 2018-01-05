package net.minecraft.src;

import java.util.Iterator;

public class DecoContainerCompostBin extends Container
{
	private final int m_NumMillStoneSlotRows = 1;
	private final int m_NumMillStoneSlotColumns = 3;
	private final int m_NumMillStoneSlots = 3;
	private DecoTileEntityCompostBin localTileEntityCompostBin;
	private int m_LastCompostBinDecomposeCounter;
	
	public DecoContainerCompostBin(IInventory inventory, DecoTileEntityCompostBin entity)
	{
	    this.localTileEntityCompostBin = entity;
	    this.m_LastCompostBinDecomposeCounter = 0;
	    int var3;
	    int var4;
	
	    for (var3 = 0; var3 < 1; ++var3)
	    {
	        for (var4 = 0; var4 < 3; ++var4)
	        {
	            this.addSlotToContainer(new Slot(entity, var4 + var3 * 3, 62 + var4 * 18, 43 + var3 * 18));
	        }
	    }
	
	    for (var3 = 0; var3 < 3; ++var3)
	    {
	        for (var4 = 0; var4 < 9; ++var4)
	        {
	            this.addSlotToContainer(new Slot(inventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 76 + var3 * 18));
	        }
	    }
	
	    for (var3 = 0; var3 < 9; ++var3)
	    {
	        this.addSlotToContainer(new Slot(inventory, var3, 8 + var3 * 18, 134));
	    }
	}
	
	public boolean canInteractWith(EntityPlayer var1)
	{
	    return this.localTileEntityCompostBin.isUseableByPlayer(var1);
	}
	
	/**
	 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	 */
	public ItemStack transferStackInSlot(EntityPlayer var1, int var2)
	{
	    ItemStack var3 = null;
	    Slot var4 = (Slot)this.inventorySlots.get(var2);
	
	    if (var4 != null && var4.getHasStack())
	    {
	        ItemStack var5 = var4.getStack();
	        var3 = var5.copy();
	
	        if (var2 < 3)
	        {
	            if (!this.mergeItemStack(var5, 3, this.inventorySlots.size(), true))
	            {
	                return null;
	            }
	        }
	        else if (!this.mergeItemStack(var5, 0, 3, false))
	        {
	            return null;
	        }
	
	        if (var5.stackSize == 0)
	        {
	            var4.putStack((ItemStack)null);
	        }
	        else
	        {
	            var4.onSlotChanged();
	        }
	    }
	
	    return var3;
	}
	
	public void addCraftingToCrafters(ICrafting var1)
	{
	    super.addCraftingToCrafters(var1);
	    var1.sendProgressBarUpdate(this, 0, this.localTileEntityCompostBin.m_CompostCounter);
	}
	
	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	public void detectAndSendChanges()
	{
	    super.detectAndSendChanges();
	    Iterator var1 = this.crafters.iterator();
	
	    while (var1.hasNext())
	    {
	        ICrafting var2 = (ICrafting)var1.next();
	
	        if (this.m_LastCompostBinDecomposeCounter != this.localTileEntityCompostBin.m_CompostCounter)
	        {
	            var2.sendProgressBarUpdate(this, 0, this.localTileEntityCompostBin.m_CompostCounter);
	        }
	    }
	
	    this.m_LastCompostBinDecomposeCounter = this.localTileEntityCompostBin.m_CompostCounter;
	}
	
	public void updateProgressBar(int var1, int var2)
	{
	    if (var1 == 0)
	    {
	        this.localTileEntityCompostBin.m_CompostCounter = var2;
	    }
	}
}
