package net.minecraft.src;

public class DecoItemMallet extends ItemPickaxe
{
	public static Material[] m_MaterialEffectiveAgainst = new Material[] { Material.rock, Material.iron, Material.ice, Material.glass, Material.piston, Material.anvil };
	
	private int m_BreakRadius = 1;
	private int m_BreakDepth = 0;
	private String m_Type;
	
	public DecoItemMallet(int id, EnumToolMaterial toolMaterial, String type, int radius)
	{
		super(id, toolMaterial);
		
		this.setUnlocalizedName("decoItemMallet." + type);
		this.setMaxDamage(toolMaterial.getMaxUses() * 3);
        this.setCreativeTab(CreativeTabs.tabTools);
		
		this.m_Type = type;
		this.m_BreakRadius = radius;
	}
	
	public void registerIcons(IconRegister register)
    {
        this.itemIcon = register.registerIcon("decoItemMallet_" + this.m_Type);
    }
	
	public boolean onBlockDestroyed(ItemStack itemStack, World world, int blockID, int x, int y, int z, EntityLiving entity)
    {
		if (entity.isSneaking())
			return false;
		
		MovingObjectPosition object = DecoUtilsMath.raytraceFromEntity(world, entity, 5.0D);
		
		if (object == null)
			return false;
		
		int side = object.sideHit;
		int xRange = this.m_BreakRadius;
        int yRange = this.m_BreakRadius;
        int zRange = this.m_BreakDepth;
		
		switch (side) 
		{
	        case 0:
	        case 1:
	            yRange = this.m_BreakDepth;
	            zRange = this.m_BreakRadius;
	            break;
	        case 2:
	        case 3:
	            xRange = this.m_BreakRadius;;
	            zRange = this.m_BreakDepth;
	            break;
	        case 4:
	        case 5:
	            xRange = this.m_BreakDepth;
	            zRange = this.m_BreakRadius;
	            break;
		}

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
		
		if (world.isAirBlock(xIndex, yIndex, zIndex))
			return false;
		
		if (player.capabilities.isCreativeMode || player.canPlayerEdit(xIndex, yIndex, zIndex, metadata, itemStack))
		{
			if (isEffective(block.blockMaterial) && world.canMineBlock(player, xIndex, yIndex, zIndex))
			{
				world.destroyBlock(xIndex, yIndex, zIndex, true);
				itemStack.damageItem(1, player);
				
				if (!world.isRemote)
					player.playerNetServerHandler.sendPacketToPlayer(new Packet14BlockDig(4, xIndex, yIndex, zIndex, side));
				
				return true;
			}
		}
		
		return false;
	}
	
	protected Material[] GetMaterialEffectiveAgainst() 
	{
        return this.m_MaterialEffectiveAgainst;
    }
	
	private boolean isEffective(Material checkMaterial) 
	{
		for (Material material : GetMaterialEffectiveAgainst())
		{
			if (checkMaterial == material) return true;
		}
		
		return false;
    }
}