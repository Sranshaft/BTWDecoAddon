package net.minecraft.src;

public class DecoUtilsWorld 
{
	public static int GetHeightForBiome(BiomeGenBase biome)
    {	
    	if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.beach.biomeName))
    		return 1;
    	else if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.desert.biomeName))
    		return 1;
    	else if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.desertHills.biomeName))
    		return 1;
    	else if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.extremeHills.biomeName))
    		return 1;
    	else if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.frozenOcean.biomeName))
    		return 1;
    	else if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.frozenRiver.biomeName))
    		return 1;
    	else if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.icePlains.biomeName))
    		return 1;
    	else if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.iceMountains.biomeName))
    		return 1;
    	else if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.taiga.biomeName))
    		return 1;
    	else if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.extremeHillsEdge.biomeName))
    		return 2;
    	else if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.forestHills.biomeName))
    		return 2;
    	else if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.plains.biomeName))
    		return 2;
    	else if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.taigaHills.biomeName))
    		return 2;
    	else if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.forest.biomeName))
    		return 3;
    	else if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.river.biomeName))
    		return 3;
    	else if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.swampland.biomeName))
    		return 4;
    	else if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.jungle.biomeName))
    		return 5;
    	else if (biome.biomeName.equalsIgnoreCase(BiomeGenBase.jungleHills.biomeName))
    		return 5;
    	else		
    		return -1;		
    }
}
