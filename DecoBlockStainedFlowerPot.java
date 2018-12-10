package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockStainedFlowerPot extends DecoBlockFlowerPot 
{
	private String m_ColorName;
	private int m_ColorIndex;
	
	public DecoBlockStainedFlowerPot(int id, String colorName, int colorIndex)
	{
		super(id);
		
		this.setUnlocalizedName("decoBlockStainedFlowerPot." + colorName);
		this.setHardness(0.1F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(null);
		
		this.m_ColorName = colorName; 
		this.m_ColorIndex = colorIndex;
	}
	
	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	public int idPicked(World world, int x, int y, int z)
	{
		return DecoModuleDecoration.decoSubModuleStainedFlowerPot.decoItemStainedFlowerPot[this.m_ColorIndex].itemID;
	}
	
	/**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
    	return DecoModuleDecoration.decoSubModuleStainedFlowerPot.decoItemStainedFlowerPot[this.m_ColorIndex].itemID;
    }
	
	//CLIENT ONLY
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
	{
		this.blockIcon = register.registerIcon("decoBlockStainedFlowerPot_" + this.m_ColorName);
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon(int side, int metadata)
	{
		return this.blockIcon;
	}
}
