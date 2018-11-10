package net.minecraft.src;

import java.util.List;

public class DecoBlockStainedFlowerPot extends DecoBlockFlowerPot 
{
	private String m_Color;
	
	public DecoBlockStainedFlowerPot(int id, String color)
	{
		super(id);
		
		this.setUnlocalizedName("decoBlockStainedFlowerPot." + color);
		this.setHardness(0.1F);
		
		this.m_Color = color;
	}
	
	//CLIENT ONLY
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
	{
		this.blockIcon = register.registerIcon("decoBlockStainedFlowerPot_" + this.m_Color);
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon(int side, int metadata)
	{
		return this.blockIcon;
	}
}
