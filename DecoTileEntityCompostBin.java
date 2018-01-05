package net.minecraft.src;

import java.util.List;

public class DecoTileEntityCompostBin extends TileEntity implements IInventory 
{
	private final int m_InventorySize = 3;
    private final int m_StackSizeLimit = 64;
    private final double m_MaxPlayerInteractionDist = 64.0D;
    private final int m_TimeToCompost = 200;
    private int m_CurrentState = 0;
    private boolean m_ValidateContentsOnUpdate = true;
    private boolean m_ContainsIngredientsToCompost;
    private ItemStack[] m_InventoryContents = new ItemStack[3];
    public int m_CompostCounter = 0;
    
    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        NBTTagList var2 = var1.getTagList("Items");
        this.m_InventoryContents = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            int var5 = var4.getByte("Slot") & 255;

            if (var5 >= 0 && var5 < this.m_InventoryContents.length)
            {
                this.m_InventoryContents[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        if (var1.hasKey("decomposeCounter"))
            this.m_CompostCounter = var1.getInteger("decomposeCounter");
        
        if (var1.hasKey("currentState"))
        	this.m_CurrentState = var1.getInteger("currentState");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.m_InventoryContents.length; ++var3)
        {
            if (this.m_InventoryContents[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.m_InventoryContents[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        var1.setTag("Items", var2);
        var1.setInteger("decomposeCounter", this.m_CompostCounter);
        var1.setInteger("currentState", this.m_CurrentState);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        if (!this.worldObj.isRemote)
        {
            int var1 = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
            Block var2 = Block.blocksList[var1];

            if (var2 != null && var2 instanceof DecoBlockCompostBin)
            {
                DecoBlockCompostBin var3 = (DecoBlockCompostBin)var2;

                if (this.m_ValidateContentsOnUpdate)
                    this.ValidateContentsForCompost(var3);
                
                if (this.m_ContainsIngredientsToCompost && var3.IsInputtingMechanicalPower(this.worldObj, this.xCoord, this.yCoord, this.zCoord))
                {
                    ++this.m_CompostCounter;

                    if (this.m_CompostCounter >= 200)
                    {
                        this.DecomposeContents(var3);
                        this.m_CompostCounter = 0;
                        this.m_ValidateContentsOnUpdate = true;
                    }
                }
            }
        }
    }
    
    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 3;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int var1)
    {
        return this.m_InventoryContents[var1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int var1, int var2)
    {
        return FCUtilsInventory.DecrStackSize(this, var1, var2);
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int var1)
    {
        if (this.m_InventoryContents[var1] != null)
        {
            ItemStack var2 = this.m_InventoryContents[var1];
            this.m_InventoryContents[var1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int var1, ItemStack var2)
    {
        this.m_InventoryContents[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
        {
            var2.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "Compost Bin";
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
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
    public boolean isStackValidForSlot(int var1, ItemStack var2)
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

    public void EjectStack(ItemStack var1)
    {
        FCUtilsBlockPos var2 = new FCUtilsBlockPos(this.xCoord, this.yCoord, this.zCoord);
        int var3 = 2 + this.worldObj.rand.nextInt(4);
        var2.AddFacingAsOffset(var3);
        float var4 = this.worldObj.rand.nextFloat() * 0.7F + 0.15F;
        float var5 = this.worldObj.rand.nextFloat() * 0.2F + 0.1F;
        float var6 = this.worldObj.rand.nextFloat() * 0.7F + 0.15F;
        EntityItem var7 = new EntityItem(this.worldObj, (double)((float)var2.i + var4), (double)((float)var2.j + var5), (double)((float)var2.k + var6), var1);
        float var8 = 0.05F;
        var7.motionX = (double)((float)this.worldObj.rand.nextGaussian() * var8);
        var7.motionY = (double)((float)this.worldObj.rand.nextGaussian() * var8 + 0.2F);
        var7.motionZ = (double)((float)this.worldObj.rand.nextGaussian() * var8);

        switch (var3)
        {
            case 2:
                var7.motionZ = (double)(-var8);
                break;

            case 3:
                var7.motionZ = (double)var8;
                break;

            case 4:
                var7.motionX = (double)(-var8);
                break;

            case 5:
                var7.motionX = (double)var8;
        }

        var7.delayBeforeCanPickup = 10;
        this.worldObj.spawnEntityInWorld(var7);
    }

    public int getGrindProgressScaled(int var1)
    {
        return this.m_CompostCounter * var1 / 200;
    }

    public boolean IsDecomposing()
    {
        return this.m_CompostCounter > 0;
    }

    private boolean DecomposeContents(DecoBlockCompostBin var1)
    {
    	DecoCraftingManagerCompostBin var2 = DecoCraftingManagerCompostBin.getInstance();
        List var3 = DecoCraftingManagerCompostBin.getInstance().GetValidCraftingIngrediants(this);

        if (var3 == null)
            return false;
        else
        {
            List var7 = var2.ConsumeIngrediantsAndReturnResult(this);
            assert var7 != null && var7.size() > 0;

            for (int var8 = 0; var8 < var7.size(); ++var8)
            {
                ItemStack var9 = ((ItemStack)var7.get(var8)).copy();

                if (var9 != null)
                {
                    this.EjectStack(var9);
                }
            }

            return true;
        }
    }

    private void ValidateContentsForCompost(DecoBlockCompostBin block)
    {
        int var2 = block.GetCurrentCompostingType(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        byte var3 = 0;
        List var4 = DecoCraftingManagerCompostBin.getInstance().GetValidCraftingIngrediants(this);

        if (var4 != null)
        {
        	block.currentState = 1;
        	this.m_CurrentState = 1;
            this.m_ContainsIngredientsToCompost = true;
            var3 = 1;
        }
        else
        {
        	block.currentState = 0;
        	this.m_CurrentState = 0;
            this.m_CompostCounter = 0;
            this.m_ContainsIngredientsToCompost = false;
        }

        this.m_ValidateContentsOnUpdate = false;

        if (var2 != var3)
        {
            block.SetCurrentCompostingType(this.worldObj, this.xCoord, this.yCoord, this.zCoord, var3);
        }
    }
}

