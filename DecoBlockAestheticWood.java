package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockAestheticWood extends Block
{
	private static final String[] AESTHETIC_TYPE = new String[] { "wattle", "daubWet", "daubDry", "paperWall",
																  "frame", "frameSlash", "frameBackslash", "frameCrossed", 
																  "carvedOak", "carvedSpruce", "carvedBirch", "carvedJungle", "carvedBloodwood", "carvedEbonwood", "carvedIronwood" };
	private static final String[] AESTHETIC_NAME = new String[] { "Wattle", "Wet Daub", "Daub", "Paper Wall", 
																  "Framed Daub", "Slash Framed Daub", "Backslash Framed Daub", "Crossed Frame Daub",
																  "Carved Oak", "Carved Spruce", "Carved Birch", "Carved Jungle", "Carved Bloodwood", "Carved Ebonwood", "Carved Ironwood" };
	
	private Icon[] m_IconByMetadataArray;
	private int m_ChanceToDry = 35;
	
	public DecoBlockAestheticWood(int id)
	{
		super(id, Material.wood);
		
        this.setUnlocalizedName("decoBlockAestheticWood");
		this.setHardness(2.0F);
        this.setResistance(1.0F);
        this.setStepSound(Block.soundWoodFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        
        this.needsRandomTick = true;
        this.setTickRandomly(true);
        
        DecoAddonManager.register(this, this.AESTHETIC_TYPE, this.AESTHETIC_NAME);
	}
	
	public void updateTick(World world, int x, int y, int z, Random random)
	{
    	if (world.getBlockMetadata(x, y, z) == 1 && random.nextInt(100) <= this.m_ChanceToDry)
		{
			world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
			world.setBlockMetadataWithNotify(x, y, z, 2);
		}
	}
	
	/**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (player.getCurrentEquippedItem() != null)
        {
	    	ItemStack currentItem = player.getCurrentEquippedItem();
	
	        if (currentItem.itemID == Item.clay.itemID && world.getBlockMetadata(x, y, z) == 0)
	        {
	        	world.playSoundEffect((double)x, (double)y, (double)z, FCBetterThanWolves.fcSoundSquishFootstep.getStepSound(), 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F); 
    			world.setBlockMetadataWithNotify(x, y, z, 1);
    			
    			currentItem.stackSize--;

                if (currentItem.stackSize <= 0)
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
    			
    			return true;
	        }
        }
        
        return false;
    }
    
    public int damageDropped(int metadata)
	{
		return metadata;
	}

	public int idDropped(int par1, Random random, int par3)
	{
		return DecoModuleBuilding.decoBlockAestheticWoodID;
	}
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
	{
		this.m_IconByMetadataArray = new Icon[this.AESTHETIC_TYPE.length];
		
		for (int index = 0; index < this.AESTHETIC_TYPE.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon("decoBlockAestheticWood_" + this.AESTHETIC_TYPE[index]);
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
    	for (int index = 0; index < this.AESTHETIC_TYPE.length; index++)
    	{
    		var3.add(new ItemStack(var1, 1, index));
    	}
    }
    
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World World, int x, int y, int z)
    {
        return World.getBlockId(x, y, z);
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World World, int x, int y, int z)
    {
        return World.getBlockMetadata(x, y, z);
    }    
}