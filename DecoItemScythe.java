package net.minecraft.src;

public class DecoItemScythe extends ItemTool
{
	private static Block[] m_BlockEffectiveAgainst = new Block[] { Block.leaves, Block.tallGrass, Block.deadBush, Block.plantRed, Block.plantYellow, 
			DecoSubModuleFlowers.decoBlockFlowerExtended, DecoSubModuleFlowers.decoBlockFlowerTulip, DecoModuleWorld.decoBlockFoliage };
	
	private int m_BreakRadius = 1;
	private String m_Type;
	private boolean m_bIsDamagedByVegetation = false;
    private boolean m_bConsumeHungerOnZeroHardnessVegetation = false;
	
	public DecoItemScythe(int id, EnumToolMaterial toolMaterial, String type, int radius)
	{
		super(id, 3, toolMaterial, m_BlockEffectiveAgainst);
		
		this.setUnlocalizedName("decoItemScythe." + type);
		this.setMaxDamage(toolMaterial.getMaxUses() * 3);
        this.setCreativeTab(CreativeTabs.tabTools);
		
		this.m_Type = type;
		this.m_BreakRadius = radius;
	}
	
	public void registerIcons(IconRegister register)
    {
        this.itemIcon = register.registerIcon("decoItemScythe_" + this.m_Type);
    }
	
	public boolean onBlockDestroyed(ItemStack itemStack, World world, int blockID, int x, int y, int z, EntityLiving entity)
    {
		if (entity.isSneaking())
			return false;
		
		MovingObjectPosition object = DecoUtilsMath.raytraceFromEntity(world, entity, 4.5D);
		
		if (object == null)
			return false;
		
		int side = object.sideHit;
		int xRange = this.m_BreakRadius;
        int yRange = this.m_BreakRadius;
        int zRange = this.m_BreakRadius;
        
        for (int xIndex = x - xRange; xIndex <= x + xRange; xIndex++)
		{
			for (int yIndex = y - yRange; yIndex <= y + yRange; yIndex++)
			{
				for (int zIndex = z - zRange; zIndex <= z + zRange; zIndex++)
				{
					if (this.checkBlockBreak(world, entity, xIndex, yIndex, zIndex, blockID, side, itemStack))
					{
						super.onBlockDestroyed(itemStack, world, blockID, xIndex, yIndex, zIndex, entity);
					}
				}
			}
		}
		
		return true;
	}
	
	public boolean hitEntity(ItemStack itemStack, EntityLiving entity, EntityPlayer player) 
	{
        if (player instanceof EntityPlayer)
        {
        	itemStack.damageItem(5, player);
        	return true;
        }
        else return false;
    }

	public boolean checkBlockBreak(World world, EntityLiving entity, int xIndex, int yIndex, int zIndex, int orginalBlockID, int side, ItemStack itemStack)
	{
		if (!(entity instanceof EntityPlayerMP))
			return false;
		
		EntityPlayerMP player = (EntityPlayerMP) entity;
		
		Block block = Block.blocksList[world.getBlockId(xIndex, yIndex, zIndex)];
		int metadata = world.getBlockMetadata(xIndex, yIndex, zIndex);
		
		if (player.capabilities.isCreativeMode || player.canPlayerEdit(xIndex, yIndex, zIndex, metadata, itemStack))
		{
			if (isEffective(block) && world.canMineBlock(player, xIndex, yIndex, zIndex))
			{
				world.destroyBlock(xIndex, yIndex, zIndex, true);
				
				if (!world.isRemote)
					player.playerNetServerHandler.sendPacketToPlayer(new Packet14BlockDig(4, xIndex, yIndex, zIndex, side));
				
				return true;
			}
		}
		
		return false;
	}
	
	public Block[] GetBlocksEffectiveAgainst() 
	{
		return this.m_BlockEffectiveAgainst;
	}
	
	private boolean isEffective(Block checkblock) 
	{
		for (Block block : GetBlocksEffectiveAgainst())
		{
			if (checkblock == null) return false;
			else if (checkblock == block) return true;
		}
		
		return false;
    }
}
