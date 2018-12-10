package net.minecraft.src;

public class DecoItemDoorLarge extends DecoItemDoor
{
	private int m_WoodID;
	private String m_Type;
	
	public DecoItemDoorLarge(int id, String type, int woodID)
	{
		super(id, type, woodID);
		
		this.setUnlocalizedName("decoItemDoorLarge." + type);
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabRedstone);
		
		this.m_WoodID = woodID;
		this.m_Type = type;
	}
	
	public void registerIcons(IconRegister register)
    {
        this.itemIcon = register.registerIcon("decoItemDoorLarge_" + this.m_Type);
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
