package net.minecraft.src;

public class DecoItemDoor extends ItemDoor
{
	private int m_WoodType;
	private String m_Type;
	
	public DecoItemDoor(int id, String type, int woodID)
	{
		super(id, Material.wood);
		setUnlocalizedName("decoItemDoor." + type);
		maxStackSize = 1;
		setCreativeTab(CreativeTabs.tabRedstone);
		
		this.m_Type = type;
		this.m_WoodType = 0; //woodID;
	}
	
	public void registerIcons(IconRegister register)
    {
        this.itemIcon = register.registerIcon("decoItemDoor_" + this.m_Type);
    }
	
	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if (side != 1)
			return false;
		else
		{
			DecoBlockDoor doorBlock = (DecoBlockDoor)DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockDoors[this.m_WoodType];
			y++;

			if (player.canPlayerEdit(x, y, z, side, itemStack) && player.canPlayerEdit(x, y + 1, z, side, itemStack))
			{
				if (!doorBlock.canPlaceBlockAt(world, x, y, z))
					return false;
				else
				{
					int rotation = MathHelper.floor_double((player.rotationYaw + 180.0F) * 4.0F / 360.0F - 0.5D) & 3;
					this.placeDoorBlock(world, x, y, z, rotation, doorBlock);
					--itemStack.stackSize;
					
					return true;
				}
			}
			else
				return false;
		}
	}
	
	public static void placeDoorBlock(World world, int x, int y, int z, int rotataion, Block block)
    {
        byte xOffset = 0;
        byte zOffset = 0;

        if (rotataion == 0)
            zOffset = 1;
        
        if (rotataion == 1)
            xOffset = -1;
        
        if (rotataion == 2)
            zOffset = -1;
        
        if (rotataion == 3)
            xOffset = 1;
        
        int var8 = (world.isBlockNormalCube(x - xOffset, y, z - zOffset) ? 1 : 0) + (world.isBlockNormalCube(x - xOffset, y + 1, z - zOffset) ? 1 : 0);
        int var9 = (world.isBlockNormalCube(x + xOffset, y, z + zOffset) ? 1 : 0) + (world.isBlockNormalCube(x + xOffset, y + 1, z + zOffset) ? 1 : 0);
        boolean var10 = world.getBlockId(x - xOffset, y, z - zOffset) == block.blockID || world.getBlockId(x - xOffset, y + 1, z - zOffset) == block.blockID;
        boolean var11 = world.getBlockId(x + xOffset, y, z + zOffset) == block.blockID || world.getBlockId(x + xOffset, y + 1, z + zOffset) == block.blockID;
        boolean var12 = false;

        if (var10 && !var11)
        {
            var12 = true;
        }
        else if (var9 > var8)
        {
            var12 = true;
        }

        world.setBlock(x, y, z, block.blockID, rotataion, 2);
        world.setBlock(x, y + 1, z, block.blockID, 8 | (var12 ? 1 : 0), 2);
        world.notifyBlocksOfNeighborChange(x, y, z, block.blockID);
        world.notifyBlocksOfNeighborChange(x, y + 1, z, block.blockID);
    }
}
