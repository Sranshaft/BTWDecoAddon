package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DecoBlockFoliage extends BlockFlower
{
	private static final String[] FOLIAGE_TYPE = new String[] { "algae", "shortGrass", "mediumGrass", "bush", "poisonIvy", "tallGrassBottom", "tallGrassTop", "tallFernBottom", "tallFernTop" };
	private static final String[] FOLIAGE_NAME = new String[] { "Algae", "Short Grass", "Medium Grass", "Bush", "Poison Ivy", "Tall Grass", "Tall Grass", "Tall Fern", "Tall Fern" };
	
	private Icon[] m_IconByMetadataArray;
	private int m_Algae = 0;
	private int m_ShortGrass = 1;
	private int m_MediumGrass = 2;
	private int m_Bush = 3;
	private int m_PoisonIvy = 4;
	private int m_TallGrassBottom = 5;
	private int m_TallGrassTop = 6;
	private int m_TallFernBottom = 7;
	private int m_TallFernTop = 8;
	
	public DecoBlockFoliage(int id)
	{
		super(id, Material.vine);
		
		this.setUnlocalizedName("decoBlockFoliage");
		this.setStepSound(Block.soundGrassFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
		
		DecoAddonManager.register(this, this.FOLIAGE_TYPE, this.FOLIAGE_NAME);
	}
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
	{
		this.m_IconByMetadataArray = new Icon[this.FOLIAGE_TYPE.length];
		
		for (int index = 0; index < this.FOLIAGE_TYPE.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon("decoBlockFoliage_" + this.FOLIAGE_TYPE[index]);
		}
	}

	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon(int side, int metadata)
	{
		return this.m_IconByMetadataArray[metadata];
	}
	
	 /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
    	for (int index = 0; index < this.FOLIAGE_TYPE.length; index++)
    	{
    		if (index != this.m_TallGrassTop && index != this.m_TallFernTop)
    			var3.add(new ItemStack(var1, 1, index));
    	}
    }
    
    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        if (!world.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == Item.shears.itemID)
        {
            player.addStat(StatList.mineBlockStatArray[this.blockID], 1);
            this.dropBlockAsItem_do(world, x, y, z, new ItemStack(this, 1, 0));
        }
        else
        	super.harvestBlock(world, player, x, y, z, 0);
    }
    
    public boolean isBlockReplaceable(World world, int x, int y, int z)
	{
		return true;
	}

	public boolean isShearable(ItemStack item, World world, int x, int y, int z)
	{
		return true;
	}

	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		if (world.getBlockMetadata(x, y, z) == this.m_TallGrassTop) 
			ret.add(new ItemStack(Block.tallGrass, 1, 1));
		else if (world.getBlockMetadata(x, y, z) == this.m_TallFernTop) 
			ret.add(new ItemStack(Block.tallGrass, 1, 2));
		else 
			ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
		
		return ret;
	}

	public boolean isBlockFoliage(World world, int x, int y, int z)
	{
		return true;
	}
    
    public int getDamageValue(World world, int x, int y, int z)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (metadata == this.m_TallGrassTop)
			metadata = this.m_TallGrassBottom;
		
		if (metadata == this.m_TallFernTop)
			metadata = this.m_TallFernBottom;
		
		return metadata;
	}
    
    public int damageDropped(int metadata)
	{
		return metadata;
	}

	public int idDropped(int par1, Random random, int par3)
	{
		return -1;
	}

	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int id = world.getBlockId(x, y - 1, z);
		int metadata = itemStack.getItemDamage();

		if (itemStack.itemID == blockID) 
		{
			switch (metadata)
			{
				case 0:
					return id == Block.waterStill.blockID;
				case 6:
				case 8:
					return id == this.blockID;
				default:
					return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID || id == FCBetterThanWolves.fcPlanter.blockID;
			}
		} 
		else
			return this.canPlaceBlockOnSide(world, x, y, z, side);
	}

	protected boolean canThisPlantGrowOnThisBlockID(int id)
	{
		return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID || id == this.blockID || id == FCBetterThanWolves.fcPlanter.blockID;
	}

	protected boolean canThisPlantGrowOnThisBlockID(int blockID, int metadata)
	{
		if (metadata == this.m_TallGrassTop || metadata == this.m_TallFernTop)
			return blockID == this.blockID;
		else if (metadata == this.m_Algae)
			return blockID == Block.waterStill.blockID;
		else
			return blockID == Block.grass.blockID || blockID == Block.dirt.blockID || blockID == Block.tilledField.blockID || blockID == FCBetterThanWolves.fcPlanter.blockID;
	}

	public boolean canBlockStay(World world, int x, int y, int z)
	{
		if (world.getBlockId(x, y, z) != blockID)
			return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
		else
			return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
	}
	
	/**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
    	if (metadata == this.m_TallGrassBottom && world.isAirBlock(x, y + 1, z))
    		world.setBlockAndMetadata(x, y + 1, z, this.blockID, this.m_TallGrassTop);
    	else if (metadata == this.m_TallFernBottom && world.isAirBlock(x, y + 1, z))
    		world.setBlockAndMetadata(x, y + 1, z, this.blockID, this.m_TallFernTop);
    	
        return metadata;
    }

	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
	{
		super.onNeighborBlockChange(world, x, y, z, neighborID);
		this.checkFlowerChange(world, x, y, z);
		
		if (world.getBlockMetadata(x, y, z) == this.m_TallGrassTop && world.getBlockId(x, y - 1, z) == blockID && world.getBlockMetadata(x, y - 1, z) != this.m_TallGrassBottom)
			world.setBlockToAir(x, y, z);
		
		if (world.getBlockMetadata(x, y, z) == this.m_TallFernTop && world.getBlockId(x, y - 1, z) == blockID && world.getBlockMetadata(x, y - 1, z) != this.m_TallFernBottom)
			world.setBlockToAir(x, y, z);
		
		if (world.getBlockMetadata(x, y, z) == this.m_TallGrassBottom && world.getBlockId(x, y + 1, z) != blockID)
			world.setBlock(x, y, z, Block.tallGrass.blockID, 1, 2);
	}

	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity par5Entity)
	{
		int metadata = world.getBlockMetadata(x, y, z);

		if (!world.isRemote && metadata == this.m_PoisonIvy) 
		{
			if (par5Entity instanceof EntityLiving)
			{
				if (par5Entity instanceof EntityPlayer)
				{
					InventoryPlayer inventory = ((EntityPlayer)par5Entity).inventory;

					if (!((inventory.armorInventory[0] != null && inventory.armorInventory[0].itemID == Item.bootsLeather.itemID) && (inventory.armorInventory[1] != null && inventory.armorInventory[1].itemID == Item.legsLeather.itemID)))
						((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100));
				}
				else
				{
					((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100));
				}
			}
		}
	}

	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
			case 0:
			case 4:
				return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 0.015625D, z + 1.0D);
			case 1: // Short Grass
				return AxisAlignedBB.getBoundingBox(x + 0.1D, y, z + 0.1D, x + 0.9D, y + 0.25D, z + 0.9D);
			case 2: // Medium Grass
				return AxisAlignedBB.getBoundingBox(x + 0.1D, y, z + 0.1D, x + 0.9D, y + 0.6D, z + 0.9D);
			default:
				return AxisAlignedBB.getBoundingBox(x + 0.1D, y, z + 0.1D, x + 0.9D, y + 0.8D, z + 0.9D);
		}
	}

	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int x, int y, int z)
	{
		int metadata = iblockaccess.getBlockMetadata(x, y, z);

		float minX;
		float minY;
		float minZ;
		float maxX;
		float maxY;
		float maxZ;

		switch (metadata)
		{
			case 0:
			case 4:
			{
				minX = minY = minZ = 0F;
				maxX = maxZ = 1.0F;
				maxY = 0.015625F;
				break;
			}
			case 1: // Short grass
			{
				minX = minZ = 0.1F;
				minY = 0.0F;
				maxX = maxZ = 0.9F;
				maxY = 0.25F;
				break;
			}
			case 2: // Medium grass
			{
				minX = minZ = 0.1F;
				minY = 0.0F;
				maxX = maxZ = 0.9F;
				maxY = 0.6F;
				break;
			}
			default:
			{
				minX = minZ = 0.1F;
				minY = 0.0F;
				maxX = maxZ = 0.9F;
				maxY = 0.8F;
				break;
			}
		}

		setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}
	
	public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
	{
		int metadata = render.blockAccess.getBlockMetadata(x, y, z);
		
		switch (metadata)
		{
			case 0:
			{
				render.setRenderBounds(0.0D, -0.15D, 0.0D, 1.0D, 0.165D, 1.0D);
				render.renderStandardBlock(this, x, y, z);
				break;
			}
			case 4:
			{
				render.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.0025D, 1.0D);
				render.renderStandardBlock(this, x, y, z);
				break;
			}
			default:
			{
				render.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
				render.renderCrossedSquares(this, x, y, z);
				break;
			}
		}
		return true;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }
	
	public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return -1;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public int getBlockColor()
    {
        double var1 = 0.5D;
        double var3 = 1.0D;
        return ColorizerGrass.getGrassColor(var1, var3);
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int par1)
    {
        return this.getBlockColor();
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess bAccess, int x, int y, int z)
    {
        int var5 = 0;
        int var6 = 0;
        int var7 = 0;

        for (int zIndex = -1; zIndex <= 1; ++zIndex)
        {
            for (int xIndex = -1; xIndex <= 1; ++xIndex)
            {
                int var10 = bAccess.getBiomeGenForCoords(x + xIndex, z + zIndex).getBiomeGrassColor();
                var5 += (var10 & 16711680) >> 16;
                var6 += (var10 & 65280) >> 8;
                var7 += var10 & 255;
            }
        }

        return (var5 / 9 & 255) << 16 | (var6 / 9 & 255) << 8 | var7 / 9 & 255;
    }
}