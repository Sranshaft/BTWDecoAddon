package net.minecraft.src;

import java.util.Random;

public class DecoBlockSteelAxlePowerSource extends FCBlockAxlePowerSource
{
	public DecoBlockSteelAxlePowerSource(int id)
	{
		super(id);
	}
	
	/**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return DecoModuleMechanical.decoBlockSteelAxleID;
    }
}
