package net.minecraft.src;

import java.util.Random;

public class DecoBiomeDecorator 
{
	/** The world the BiomeDecorator is currently decorating */
    protected World currentWorld;

    /** The Biome Decorator's random number generator. */
    protected Random randomGenerator;

    /** The X-coordinate of the chunk currently being decorated */
    protected int chunk_X;

    /** The Z-coordinate of the chunk currently being decorated */
    protected int chunk_Z;
    
	protected DecoWorldGenBoulder boulderGen;
    protected DecoWorldGenQuicksand quicksandGen;
    protected DecoWorldGenCoral spongeGen;
    
    public void initialize()
    {
    	this.boulderGen = new DecoWorldGenBoulder();
    	this.quicksandGen = new DecoWorldGenQuicksand(6);
    	this.spongeGen = new DecoWorldGenCoral();
    }
    
    public void decorate(World world, Random random, int chunkX, int chunkZ)
    {
    	this.currentWorld = world;
    	this.randomGenerator = random;
    	this.chunk_X = chunkX;
    	this.chunk_Z = chunkZ;
    	
    	int xCoord;
    	int yCoord;
    	int zCoord;
    	
    	if (DecoAddonManager.getConfigOption("generateBouldersInPlains"))
        {
        	if (this.randomGenerator.nextInt(100) <= 25)
        	{
        		xCoord = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
        		zCoord = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
        		yCoord = this.currentWorld.getTopSolidOrLiquidBlock(xCoord, zCoord);
        		
        		if (this.currentWorld.getBiomeGenForCoords(xCoord, zCoord).biomeName.equals(BiomeGenBase.plains.biomeName)
        				|| this.currentWorld.getBiomeGenForCoords(xCoord, zCoord).biomeName.equals(BiomeGenBase.icePlains.biomeName)
        				|| this.currentWorld.getBiomeGenForCoords(xCoord, zCoord).biomeName.equals(BiomeGenBase.extremeHillsEdge.biomeName))
        			(new DecoWorldGenBoulder()).generate(this.currentWorld, this.randomGenerator, xCoord, yCoord, zCoord);
        	}
        }
    	
    	if (DecoAddonManager.getConfigOption("generateCoralInOceans"))
        {
        	for (int index = 0; index < 5; index++)
            {
	        	if (this.randomGenerator.nextInt(100) <= 30)
	        	{
	        		xCoord = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
	        		yCoord = this.randomGenerator.nextInt(80);
	        		zCoord = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
	        		
	        		if (this.currentWorld.getBiomeGenForCoords(xCoord, zCoord).biomeName.equals(BiomeGenBase.ocean.biomeName)
	        				|| this.currentWorld.getBiomeGenForCoords(xCoord, zCoord).biomeName.equals(BiomeGenBase.beach.biomeName))
	        			(new DecoWorldGenCoral()).generate(this.currentWorld, this.randomGenerator, xCoord, yCoord, zCoord);
	        	}
            }
        }
    	
    	if (DecoAddonManager.getConfigOption("generateMudInSwampsAndOceans"))
    	{
    		xCoord = this.chunk_X;
    		zCoord = this.chunk_Z;
    		yCoord = this.currentWorld.getTopSolidOrLiquidBlock(xCoord, zCoord);
    		
    		if (this.currentWorld.getBiomeGenForCoords(xCoord, zCoord).biomeName.equals(BiomeGenBase.swampland.biomeName)
    				|| this.currentWorld.getBiomeGenForCoords(xCoord, zCoord).biomeName.equals(BiomeGenBase.ocean.biomeName))
    			(new DecoWorldGenMud()).generate(this.currentWorld, this.randomGenerator, xCoord, yCoord, zCoord);
    	}
        
        if (DecoAddonManager.getConfigOption("generateQuicksandInJungles"))
        {
        	if (this.randomGenerator.nextInt(100) <= 30)
        	{
        		xCoord = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
        		zCoord = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
        		yCoord = this.currentWorld.getTopSolidOrLiquidBlock(xCoord, zCoord);
        		
        		if (this.currentWorld.getBiomeGenForCoords(xCoord, zCoord).biomeName.equals(BiomeGenBase.jungle.biomeName)
        				|| this.currentWorld.getBiomeGenForCoords(xCoord, zCoord).biomeName.equals(BiomeGenBase.jungleHills.biomeName)
        				|| this.currentWorld.getBiomeGenForCoords(xCoord, zCoord).biomeName.equals(BiomeGenBase.desert.biomeName))
        			(new DecoWorldGenQuicksand(6)).generate(this.currentWorld, this.randomGenerator, xCoord, yCoord, zCoord);
        	}
        }
        
        if (DecoAddonManager.getConfigOption("generateWaterPlants"))
        {
        	for (int index = 0; index < 25; index++)
            {
        		xCoord = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
        		yCoord = this.randomGenerator.nextInt(65 - 60) + 60;
        		zCoord = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
        		
        		(new DecoWorldGenWaterPlant(DecoModuleWorld.decoBlockWaterPlantID)).generate(this.currentWorld, this.randomGenerator, xCoord, yCoord, zCoord);
            }
        }
        
        if (DecoAddonManager.getConfigOption("generateWildgrassAndFoliage"))
        {
        	for (int index = 0; index < 3; index++)
            {
        		xCoord = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
        		yCoord = this.randomGenerator.nextInt(80 - 40) + 40;
        		zCoord = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
        	
        		(new DecoWorldGenWildgrass()).generate(this.currentWorld, this.randomGenerator, xCoord, yCoord, zCoord);
            }
        }
    }
}
