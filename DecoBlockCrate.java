package net.minecraft.src;

import java.util.List;

public class DecoBlockCrate extends BlockContainer 
{
	private Icon[] m_IconByMetadataArray;
	
	protected DecoBlockCrate(int id) 
	{
		super(id, Material.wood);
		
		this.setUnlocalizedName("decoBlockCrate");
		this.setHardness(1.0F);
        this.setStepSound(soundWoodFootstep);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        
        DecoAddonManager.register(this, DecoUtilsStrings.WOOD_PLANK_TYPES, DecoUtilsStrings.WOOD_PLANK_NAMES, " Crate");
	}
	
	/**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack itemStack)
    {
    	if (!world.isRemote)
		{
    		DecoTileEntityCrate crate = (DecoTileEntityCrate)world.getBlockTileEntity(x, y, z);
    		
    		if (itemStack.hasTagCompound()) crate.readInventoryFromNBT(itemStack.getTagCompound());
		}
    }
	
	/**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
        	DecoTileEntityCrate entity = (DecoTileEntityCrate)world.getBlockTileEntity(x, y, z);
        	
            if (player instanceof EntityPlayerMP)
            {
            	DecoContainerCrate container = new DecoContainerCrate(player.inventory, entity);
                DecoUtilsPacketHandler.ServerOpenCustomInterface((EntityPlayerMP)player, container, DecoModuleDecoration.decoCrateContainerID);
            }
        }

        return true;
    }
    
    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) { }
    
    /**
     * Called when the block is attempted to be harvested
     */
    public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player) 
    {
		if (!world.isRemote)
		{
			DecoTileEntityCrate entity = (DecoTileEntityCrate)world.getBlockTileEntity(x, y, z);

			if (player instanceof EntityPlayerMP)
			{
				ItemStack itemStack = new ItemStack(this, 1, metadata);
				NBTTagCompound tagCompound = new NBTTagCompound();
				entity.writeInventoryToNBT(tagCompound);
				 
				itemStack.setTagCompound(tagCompound);
				
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, itemStack));
			}
		}
    }
    
    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) 
    {
    	if (!world.isRemote)
        {
			DecoTileEntityCrate entity = (DecoTileEntityCrate)world.getBlockTileEntity(x, y, z);
			
			ItemStack itemStack = new ItemStack(this, 1, world.getBlockMetadata(x, y, z));
			NBTTagCompound tagCompound = new NBTTagCompound();
			entity.writeToNBT(tagCompound);
			           	 
			if (!tagCompound.hasNoTags()) itemStack.setTagCompound(tagCompound);
			
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, itemStack));
        }
    }

	public TileEntity createNewTileEntity(World world) 
	{
		return new DecoTileEntityCrate();
	}

	/**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int metadata)
    {
        return metadata;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
        return this.m_IconByMetadataArray[metadata];
    }
    
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
        this.m_IconByMetadataArray = new Icon[DecoUtilsStrings.WOOD_PLANK_TYPES.length];
        
        for (int index = 0; index < DecoUtilsStrings.WOOD_PLANK_TYPES.length; index++)
        {
        	this.m_IconByMetadataArray[index] = register.registerIcon("decoBlockCrate_" + DecoUtilsStrings.WOOD_PLANK_TYPES[index]);
        }
    }
    
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for (int index = 0; index < DecoUtilsStrings.WOOD_PLANK_TYPES.length; index++)
        {
    		par3List.add(new ItemStack(par1, 1, index));
        }
    }
}
