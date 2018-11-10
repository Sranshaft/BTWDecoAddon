package net.minecraft.src;

public class DecoItemDoor extends ItemDoor
{
	private int m_WoodID;
	private String m_Type;
	
	public DecoItemDoor(int id, String type, int woodID)
	{
		super(id, Material.wood);
		this.setUnlocalizedName("decoItemDoor." + type);
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabRedstone);
		
		this.m_WoodID = woodID;
		this.m_Type = type;
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
        if (side != 1) return false;
        else
        {
            ++y;
            Block doorBlock = DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockDoors[this.m_WoodID];
            
            if (player.capabilities.isCreativeMode || player.canPlayerEdit(x, y, z, side, itemStack))
    		{
            	int direction = MathHelper.floor_double((double)((player.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
                placeDoorBlock(world, x, y, z, direction, doorBlock);
                
                --itemStack.stackSize;
                
                return true;
            }
        }
        
        return false;
    }

    public static void placeDoorBlock(World world, int x, int y, int z, int direction, Block doorBlock)
    {
        byte xOffset = 0;
        byte zOffset = 0;

        if (direction == 0) zOffset = 1;
        if (direction == 1) xOffset = -1;
        if (direction == 2) zOffset = -1;
        if (direction == 3) xOffset = 1;
        
        int var8 = (world.isBlockNormalCube(x - xOffset, y, z - zOffset) ? 1 : 0) + (world.isBlockNormalCube(x - xOffset, y + 1, z - zOffset) ? 1 : 0);
        int var9 = (world.isBlockNormalCube(x + xOffset, y, z + zOffset) ? 1 : 0) + (world.isBlockNormalCube(x + xOffset, y + 1, z + zOffset) ? 1 : 0);
        
        boolean isDoorOnLeft = world.getBlockId(x - xOffset, y, z - zOffset) == doorBlock.blockID || world.getBlockId(x - xOffset, y + 1, z - zOffset) == doorBlock.blockID;
        boolean isDoorOnRight = world.getBlockId(x + xOffset, y, z + zOffset) == doorBlock.blockID || world.getBlockId(x + xOffset, y + 1, z + zOffset) == doorBlock.blockID;
        boolean var12 = false;

        if (isDoorOnLeft && !isDoorOnRight) var12 = true;
        else if (var9 > var8) var12 = true;

        world.setBlock(x, y, z, doorBlock.blockID, direction, 2);
        world.setBlock(x, y + 1, z, doorBlock.blockID, 8 | (var12 ? 1 : 0), 2);
        world.notifyBlocksOfNeighborChange(x, y, z, doorBlock.blockID);
        world.notifyBlocksOfNeighborChange(x, y + 1, z, doorBlock.blockID);
    }
}
