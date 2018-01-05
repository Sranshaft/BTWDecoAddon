package net.minecraft.src;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DecoUtils 
{
	public static void CheatBlockIDs()
	{
		FCAddOnHandler.LogMessage("[INFO]: BTW Deco Addon is extending block IDs available...");
		
		//Better Than Wolves Potential Fluid Sources.
		boolean[] NEW_m_bBlocksPotentialFluidSources = new boolean[Block.blocksList.length];
		for(int index = 0; index < FCBetterThanWolves.m_bBlocksPotentialFluidSources.length; index++)
			NEW_m_bBlocksPotentialFluidSources[index] = FCBetterThanWolves.m_bBlocksPotentialFluidSources[index];
		
		FCBetterThanWolves.m_bBlocksPotentialFluidSources = NEW_m_bBlocksPotentialFluidSources;
		for (int index = 256; index < FCBetterThanWolves.m_bBlocksPotentialFluidSources.length; ++index)
		{
			Block var2 = Block.blocksList[index];
			if (var2 != null && var2 instanceof FCIBlockFluidSource) FCBetterThanWolves.m_bBlocksPotentialFluidSources[index] = true;
			else FCBetterThanWolves.m_bBlocksPotentialFluidSources[index] = false;
		}

		//Player Block Statistics
		StatBase[] NEW_mineBlockStatArray = new StatBase[Block.blocksList.length];
		for (int index = 0; index < 256; index++)
			NEW_mineBlockStatArray[index] = StatList.mineBlockStatArray[index];
		
		for (int index = 256; index < Block.blocksList.length; ++index)
		if (Block.blocksList[index] != null && Block.blocksList[index].getEnableStats())
		{
			String Fapstation = StatCollector.translateToLocalFormatted("stat.mineBlock", new Object[] { Block.blocksList[index].getLocalizedName() });
			NEW_mineBlockStatArray[index] = (new StatCrafting(16777216 + index, Fapstation, index)).registerStat();
			
			StatList.objectMineStats.add((StatCrafting)NEW_mineBlockStatArray[index]);
		}
		
		StatList.mineBlockStatArray = NEW_mineBlockStatArray;
		
		//Blocks can catch fire and and spread fire
		int[] NEW_chanceToEncourageFire = new int[4096];
		int[] NEW_abilityToCatchFire  = new int[4096];
		for (int index = 0; index < BlockFire.chanceToEncourageFire.length; index++)
		{
			NEW_chanceToEncourageFire[index] = BlockFire.chanceToEncourageFire[index];
			NEW_abilityToCatchFire[index]  = BlockFire.abilityToCatchFire[index];
		}
		
		BlockFire.chanceToEncourageFire = NEW_chanceToEncourageFire;
		BlockFire.abilityToCatchFire  = NEW_abilityToCatchFire;
		
		//EnderMan's CarriableBlocks
		try
		{
			Field carriableBlocksField = EntityEnderman.class.getDeclaredFields()[0];
			carriableBlocksField.setAccessible(true);
			
			boolean[] NEW_carriableBlocks = new boolean[4096], OLD_carriableBlocks = (boolean[]) carriableBlocksField.get(EntityEnderman.class);
			for(int index = 0; index < 256; index++)
				NEW_carriableBlocks[index]= OLD_carriableBlocks[index];
			
			carriableBlocksField.set(EntityEnderman.class, NEW_carriableBlocks);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		//FCTileEntityBeacon BlocksList
		Field[] BeaconFields = FCTileEntityBeacon.class.getDeclaredFields();
		Field Beacon__m_iEffectsByBlockID = BeaconFields[4];
		
		//System.out.println(Beacon__m_iEffectsByBlockID.getName());
		Beacon__m_iEffectsByBlockID.setAccessible(true);
		ArrayList[] NEW_EffectsByBlockID = new ArrayList[4096];
        for (int index = 0; index < 4096; index++)
        {
            NEW_EffectsByBlockID[index] = new ArrayList();
        }
		try
		{
			Beacon__m_iEffectsByBlockID.set(FCTileEntityBeacon.class, NEW_EffectsByBlockID);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		FCTileEntityBeacon.InitializeEffectsByBlockID();
	}
	
	public static void LoadMapIDFix()
	{
		Item.m_bSuppressConflictWarnings=true;
		Item.map = (ItemMap)new DecoFixItemMapID(102);
		Item.m_bSuppressConflictWarnings=false;
	}
	
	public static void setFirePropertiesOfBlock(int blockID, int chance, int ability)
    {
        BlockFire.chanceToEncourageFire[blockID] = chance;
        BlockFire.abilityToCatchFire[blockID] = ability;
    }
}
