package net.minecraft.src;

public class DecoContainerCrate extends Container implements DecoIContainer
{
	private final DecoTileEntityCrate m_LocalTileEntityCrate;
	private final int m_NumSlots = 27;
	private int m_NumRows;
	
	public DecoContainerCrate(IInventory inventory, DecoTileEntityCrate entity)
	{
	    this.m_LocalTileEntityCrate = entity;
	    
	    this.m_NumRows = entity.getSizeInventory() / 9;
        int rowHeight = (this.m_NumRows - 4) * 18;
        int rowIndex;
        int columnIndex;

        for (rowIndex = 0; rowIndex < this.m_NumRows; ++rowIndex)
        {
            for (columnIndex = 0; columnIndex < 9; ++columnIndex)
            {
                this.addSlotToContainer(new Slot(entity, columnIndex + rowIndex * 9, 8 + columnIndex * 18, 18 + rowIndex * 18));
            }
        }

        for (rowIndex = 0; rowIndex < 3; ++rowIndex)
        {
            for (columnIndex = 0; columnIndex < 9; ++columnIndex)
            {
                this.addSlotToContainer(new Slot(inventory, columnIndex + rowIndex * 9 + 9, 8 + columnIndex * 18, 103 + rowIndex * 18 + rowHeight));
            }
        }

        for (rowIndex = 0; rowIndex < 9; ++rowIndex)
        {
            this.addSlotToContainer(new Slot(inventory, rowIndex, 8 + rowIndex * 18, 161 + rowHeight));
        }
	}

	public boolean canInteractWith(EntityPlayer player)
	{
	    return this.m_LocalTileEntityCrate.isUseableByPlayer(player);
	}
	
	/**
	 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	 */
	public ItemStack transferStackInSlot(EntityPlayer player, int slot)
	{
	    ItemStack itemStack = null;
	    Slot slotChecked = (Slot)this.inventorySlots.get(slot);
	
	    if (slotChecked != null && slotChecked.getHasStack())
	    {
	        ItemStack itemStackChecked = slotChecked.getStack();
	        itemStack = itemStackChecked.copy();
	
	        if (slot < this.m_NumSlots)
	        {
	            if (!this.mergeItemStack(itemStackChecked, this.m_NumSlots, this.inventorySlots.size(), true))
	            {
	                return null;
	            }
	        }
	        else if (!this.mergeItemStack(itemStackChecked, 0, this.m_NumSlots, false))
	        {
	            return null;
	        }
	
	        if (itemStackChecked.stackSize == 0) slotChecked.putStack((ItemStack)null);
	        else slotChecked.onSlotChanged();
	    }
	
	    return itemStack;
	}	
}
