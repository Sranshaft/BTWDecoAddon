package net.minecraft.src;

public class DecoTileEntityCrate extends TileEntity implements IInventory
{
	private final int m_InventorySize = 9;
    private final int m_StackSizeLimit = 64;
    private final double m_MaxPlayerInteractionDist = 64.0D;
    private boolean m_ValidateContentsOnUpdate = true;
    private ItemStack[] m_InventoryContents = new ItemStack[9];
    
    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        super.readFromNBT(tagCompound);
        readInventoryFromNBT(tagCompound);
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        writeInventoryToNBT(tagCompound);
    }
    
    public void readInventoryFromNBT(NBTTagCompound tagCompound)
    {
    	NBTTagList tagList = tagCompound.getTagList("Items");
        
        this.m_InventoryContents = new ItemStack[this.getSizeInventory()];

        for (int slotIndex = 0; slotIndex < tagList.tagCount(); ++slotIndex)
        {
            NBTTagCompound itemTag = (NBTTagCompound)tagList.tagAt(slotIndex);
            int var5 = itemTag.getByte("Slot") & 255;

            if (var5 >= 0 && var5 < this.m_InventoryContents.length)
            {
                this.m_InventoryContents[var5] = ItemStack.loadItemStackFromNBT(itemTag);
            }
        }
    }
    
    public void writeInventoryToNBT(NBTTagCompound tagCompound)
    {
    	NBTTagList tagList = new NBTTagList();
        boolean hasContent = false;

        for (int slotIndex = 0; slotIndex < this.m_InventoryContents.length; slotIndex++)
        {
            if (this.m_InventoryContents[slotIndex] != null)
            {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setByte("Slot", (byte)slotIndex);
                this.m_InventoryContents[slotIndex].writeToNBT(itemTag);
                tagList.appendTag(itemTag);
                
                hasContent = true;
            }
        }

        if (hasContent) tagCompound.setTag("Items", tagList);
    }
    
    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.m_InventorySize;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int slot)
    {
    	if (slot < this.m_InventorySize) return this.m_InventoryContents[slot];
    	else return null;
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int slot, int amount)
    {
        return FCUtilsInventory.DecrStackSize(this, slot, amount);
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (this.m_InventoryContents[slot] != null)
        {
            ItemStack itemStack = this.m_InventoryContents[slot];
            this.m_InventoryContents[slot] = null;
            return itemStack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        this.m_InventoryContents[slot] = itemStack;

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "Crate";
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return this.m_StackSizeLimit;
    }

    /**
     * Called when an the contents of an Inventory change, usually
     */
    public void onInventoryChanged()
    {
        super.onInventoryChanged();

        if (this.worldObj != null && !this.worldObj.isRemote)
        	this.m_ValidateContentsOnUpdate = true;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isStackValidForSlot(int slot, ItemStack itemStack)
    {
        return true;
    }

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    public boolean isInvNameLocalized()
    {
        return true;
    }
}
