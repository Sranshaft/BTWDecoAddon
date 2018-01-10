package net.minecraft.src;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;

import java.io.FileInputStream;

public class DecoAddonManager extends FCAddOn
{
	private static final boolean DEBUG_ADDON_LOAD = false;
	
	private static final String m_fcVersionString = "4.A3 Headed Beastie";
	private static final String m_decoAddonVersionString = "2.A1 Bodacious Beaver";
	
	private static boolean m_ConfigFound = false;
	
	public static HashMap<Object, String> m_ModBlockList = new HashMap();
	public static HashMap<String, Integer> m_ModBlockIDList = new HashMap();
	private static HashMap<String, Boolean> m_ModConfigOptions = new HashMap();
	private static HashMap<String, Boolean> m_ModModuleOptions = new HashMap();
	
	private static ArrayList<String> m_LoadedModules = new ArrayList<String>();
	
	public void Initialize()
	{
		FCAddOnHandler.LogMessage("[INFO]: BTW Deco Addon Version " + this.m_decoAddonVersionString + " Initializing...");
		FCAddOnHandler.LogMessage("[INFO]: This mod was built on " + this.m_fcVersionString + "! Any other BTW version might cause conflicts, glitches or unwanted outcomes...");
		
		DecoUtils.CheatBlockIDs();
		DecoUtils.LoadMapIDFix();
		
		this.readModConfigFile();
		
		for (Map.Entry<String, Boolean> entry : this.m_ModModuleOptions.entrySet())
		{	
			if (entry.getValue())
				this.loadModule(entry.getKey());
		}
		
		FCAddOnHandler.LogMessage("[INFO]: BTW Deco Addon Initialization Complete.");
	}
	
	public void OnLanguageLoaded(StringTranslate var1)
	{
		if (!this.m_ConfigFound)
			return;
		
		FCAddOnHandler.LogMessage("[INFO]: BTW Deco Addon loading language...");
		
		for (Map.Entry<Object, String> entry : this.m_ModBlockList.entrySet())
		{
			if (entry.getKey() instanceof Block)
			{
				this.addBlockName(var1, (Block)entry.getKey(), entry.getValue());
			}
			else if (entry.getKey() instanceof Item)
			{
				this.addItemName(var1, (Item)entry.getKey(), entry.getValue());
			}
			else if (entry.getKey() instanceof ItemStack)
			{
				this.addItemStackName(var1, (ItemStack)entry.getKey(), entry.getValue());
			}
			else if (entry.getKey() instanceof String)
			{
				this.addStringName(var1, (String)entry.getKey(), (String)entry.getValue());
			}
		}
		
		if (this.getConfigOption("enableDiamondium"))
		{
			FCAddOnHandler.LogMessage("[INFO]: Modifying the names of blocks and items made with diamond ingots...");
			this.replaceBlockName(var1, Block.blockDiamond, "Block of Diamond");
			this.replaceItemName(var1, FCBetterThanWolves.fcItemIngotDiamond, "Diamondium Ingot");
			this.replaceItemName(var1, Item.pickaxeDiamond, "Diamondium Pickaxe");
			this.replaceItemName(var1, Item.axeDiamond, "Diamondium Axe");
			this.replaceItemName(var1, Item.shovelDiamond, "Diamondium Spade");
			this.replaceItemName(var1, Item.hoeDiamond, "Diamondium Hoe");
			this.replaceItemName(var1, Item.swordDiamond, "Diamondium Sword");
		}
		
		if (this.getConfigOption("enableFireproofBloodWood"))
		{
			FCAddOnHandler.LogMessage("[INFO]: Modifying bloodwood logs and leaves to make them fireproof...");
			FCBetterThanWolves.fcBloodWood.setStepSound(Block.soundWoodFootstep);
			FCBetterThanWolves.SetFirePropertiesOfBlock(FCBetterThanWolves.fcBloodWood.blockID, 0, 0);
			FCBetterThanWolves.SetFirePropertiesOfBlock(FCBetterThanWolves.fcLeaves.blockID, 0, 0);
		}
		
		// CHANGES BTW BLOCKS AND ITEMS
		FCAddOnHandler.LogMessage("[INFO]: Modifying the names of blocks and items from BTW...");
		this.replaceItemName(var1, FCBetterThanWolves.fcItemRottenArrow, "Rotten Arrow");
		this.addStringName(var1, "pottery.crucible", "Crucible");
		this.addStringName(var1, "pottery.planter", "Planter");
		this.addStringName(var1, "pottery.vase", "Vase");
		this.addStringName(var1, "pottery.urn", "Urn");
		this.addStringName(var1, "pottery.mould", "Mould");
		this.addStringName(var1, "planter.soil", "Soil");
		this.addStringName(var1, "planter.fertilizedSoil", "Fertilized Soil");
		this.addStringName(var1, "planter.soulsand", "Soul Sand");
		this.addStringName(var1, "planter.grass", "Grass");
		this.addStringName(var1, FCBetterThanWolves.fcAestheticOpaque.getUnlocalizedName() + ".whitecobble", "White Cobblestone");
		
		// CHANGES VANILLA BLOCKS AND ITEMS
		FCAddOnHandler.LogMessage("[INFO]: Modifying the names of blocks and items from vanilla...");
		this.replaceBlockName(var1, Block.cobblestoneMossy, "Mossy Cobblestone");
		this.replaceBlockName(var1, Block.netherBrick, "Nether Bricks");
		this.replaceBlockName(var1, Block.stairsCobblestone, "Cobblestone Stairs");
		this.replaceBlockName(var1, Block.trapdoor, "Oak Trapdoor");
		this.replaceItemName(var1, Item.doorWood, "Oak Door");
		this.replaceItemName(var1, Item.seeds, "Wheat Seeds");
		
		this.addStringName(var1, "item.skull.spider.name", "Spider Head");
		this.addStringName(var1, "item.skull.enderman.name", "Enderman Head");
		this.addStringName(var1, "item.skull.pigzombie.name", "Zombie Pigman Head");
		this.addStringName(var1, "item.skull.fire.name", "Blaze Head");
		
		FCAddOnHandler.LogMessage("[INFO]: BTW Deco Addon language loaded...");
	}
	
	public void PostInitialize()
	{
		if (!this.m_ConfigFound)
		{
			FCAddOnHandler.LogMessage("[WARN]: BTW Deco Addon was not loaded! Be careful when opening worlds with BTW Deco Addon installed as it will cause issues!");
			return;
		}
		
		for (Map.Entry<String, Boolean> entry : this.m_ModModuleOptions.entrySet())
		{
			FCAddOnHandler.LogMessage("[INFO]: Module " + entry.getKey() + ": " + entry.getValue().toString());
		}
		
		for (Map.Entry<String, Boolean> entry : this.m_ModConfigOptions.entrySet())
		{
			FCAddOnHandler.LogMessage("[INFO]: Option " + entry.getKey() + ": " + entry.getValue().toString());
		}
		
		FCAddOnHandler.LogMessage("[INFO]: " + this.m_ModBlockIDList.values().size() + " IDs used starting at " + this.m_ModBlockIDList.values().toArray()[0] 
				+ " and ending at " + this.m_ModBlockIDList.values().toArray()[this.m_ModBlockIDList.values().size() - 1] );
	}
	
	public static void register(Object target, String localizedName)
	{
		if (m_ModBlockList.containsKey(target))
		{
			FCAddOnHandler.LogMessage("[WARN]: Target already registered! " + localizedName); 
			return;
		}
		
		if (target instanceof Block)
		{
			Item.itemsList[((Block)target).blockID] = new ItemBlock(((Block) target).blockID - 256);
			m_ModBlockList.put(target, localizedName.trim());
		}
		else if (target instanceof Item)
			m_ModBlockList.put(target, localizedName.trim());
		else if (target instanceof ItemStack)
			m_ModBlockList.put(target, localizedName.trim());
		else if (target instanceof String)
			m_ModBlockList.put(target, localizedName.trim());
		else
			FCAddOnHandler.LogMessage("[WARN]: Could not register target! Valid targets must be Block, Item, ItemStack or String. " + localizedName);
	}
	
	public static void register(Block target)
	{
		Item.itemsList[target.blockID] = new ItemBlock(target.blockID - 256);
	}
	
	public static void register(Block target, String[] tags, String[] localizedNames)
	{
		Item.itemsList[target.blockID] = new DecoItemBlock(target, tags, "", localizedNames, "");
	}
	
	public static void register(Block target, String[] tags, String prefix, String[] localizedNames)
	{
		Item.itemsList[target.blockID] = new DecoItemBlock(target, tags, prefix, localizedNames, "");
	}
	
	public static void register(Block target, String[] tags, String[] localizedNames, String suffix)
	{
		Item.itemsList[target.blockID] = new DecoItemBlock(target, tags, "", localizedNames, suffix);
	}
	
	public static void register(Block target, String[] tags, String prefix, String[] localizedNames, String suffix)
	{
		Item.itemsList[target.blockID] = new DecoItemBlock(target, tags, prefix, localizedNames, suffix);
	}
	
	public static void registerSubBlocks(Block sidingAndCorner, Block mouldingAndDecorative, String localizedName)
	{
		Item.m_bSuppressConflictWarnings = true;
		
		Item.itemsList[sidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(sidingAndCorner.blockID - 256);
		Item.itemsList[mouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(mouldingAndDecorative.blockID - 256);
		
		String Tag = sidingAndCorner.getUnlocalizedName();
		register(Tag + ".siding" + ".name", localizedName + " Siding");
		register(Tag + ".corner" + ".name", localizedName + " Corner");
		register(Tag + ".bench" + ".name", localizedName + " Bench");
		register(Tag + ".fence" + ".name", localizedName + " Fence");
	
		Tag = mouldingAndDecorative.getUnlocalizedName();
		register(Tag + ".moulding" + ".name", localizedName + " Moulding");
		register(Tag + ".column" + ".name", localizedName + " Column");
		register(Tag + ".pedestal" + ".name", localizedName + " Pedestal");
		register(Tag + ".table" + ".name", localizedName + " Table");
		
		Item.m_bSuppressConflictWarnings = false;
	}
	
	public static int replaceBlockID(Block target)
	{
		Block.blocksList[target.blockID] = null;
		return target.blockID;
	}
	
	public static int replaceItemID(Item target)
	{
		Item.itemsList[target.itemID] = null;
		return target.itemID;
	}
	
	public static boolean getConfigOption(String optionName)
	{
		if (m_ModConfigOptions.containsKey(optionName))
			return m_ModConfigOptions.get(optionName).booleanValue();
		else
		{
			FCAddOnHandler.LogMessage("Deco Addon config file does not contain the option: " + optionName);
			return false;
		}
	}
	
	public static int getBlockID(String blockName)
	{
		if (m_ModBlockIDList.containsKey(blockName))
			return m_ModBlockIDList.get(blockName);
		else
		{
			FCAddOnHandler.LogMessage("Deco Addon config file cannot find the id for: " + blockName);
			return -1;
		}
	}
	
	public static boolean isModuleLoaded(String name)
	{
		return m_LoadedModules.contains(name);
	}
	
	private void addConfigOption(String name, Boolean value)
	{
		m_ModConfigOptions.put(name, value);
	}
	
	private void addModuleOption(String name, Boolean value)
	{
		m_ModModuleOptions.put(name, value);
	}
	
	private void addBlockID(String name, int value)
	{
		m_ModBlockIDList.put(name, value);
	}
	
	private void addBlockName(StringTranslate var1, Block block, String name)
	{
		try
		{
			String var4 = block.getUnlocalizedName() + ".name";
			var1.GetTranslateTable().put(var4, name);
			
			if (DEBUG_ADDON_LOAD) FCAddOnHandler.LogMessage("[INFO]: Adding block - " + var4 + " : " + name);
		}
		catch (Exception ex) 
		{
			System.out.println("[WARN]: Problem adding block name: " + name);
			if (DEBUG_ADDON_LOAD) ex.printStackTrace();
		}
	}
	
	private void addItemName(StringTranslate var1, Item item, String name)
	{
		try
		{
			String var4 = item.getUnlocalizedName() + ".name";
			var1.GetTranslateTable().put(var4, name);
			
			if (DEBUG_ADDON_LOAD) FCAddOnHandler.LogMessage("[INFO]: Adding item - " + var4 + " : " + name);
		}
		catch (Exception ex) 
		{
			System.out.println("[WARN]: Problem adding item name: " + name);
			if (DEBUG_ADDON_LOAD) ex.printStackTrace();
		}
	}
	
	private void addItemStackName(StringTranslate var1, ItemStack item, String name)
	{
		try
		{
			String var4 = Item.itemsList[item.itemID].getUnlocalizedName(item) + ".name";
			var1.GetTranslateTable().put(var4, name);
			
			if (DEBUG_ADDON_LOAD) FCAddOnHandler.LogMessage("[INFO]: Adding itemstack - " + var4 + " : " + name);
		}
		catch (Exception ex) 
		{
			System.out.println("[WARN]: Problem adding itemstack name: " + name);
			if (DEBUG_ADDON_LOAD) ex.printStackTrace();
		}
	}
	
	private void addStringName(StringTranslate var1, String string, String name)
	{
		try
		{
			var1.GetTranslateTable().put(string, name);
		}
		catch (Exception ex) 
		{
			System.out.println("[WARN]: Problem adding string name: " + name);
			if (DEBUG_ADDON_LOAD) ex.printStackTrace();
		}
	}
	
	private void replaceBlockName(StringTranslate var1, Block block, String name)
	{
		String var4 = block.getUnlocalizedName() + ".name";
		var1.GetTranslateTable().put(var4, name);
	}
	
	private void replaceItemName(StringTranslate var1, Item item, String name)
	{
		String var4 = item.getUnlocalizedName() + ".name";
		var1.GetTranslateTable().put(var4, name);
	}
	
	private void readModConfigFile()
	{
		File var0 = new File(Minecraft.getMinecraftDir(), "DecoAddonConfig.txt");
		
		try
		{
			if (!var0.exists())
			{
				System.out.println(Minecraft.getMinecraftDir().getAbsolutePath());
				System.out.println("[WARN]: Could not find DecoAddonConfig.txt, loading of deco addons was cancelled.");
				return;
			}
			
			this.m_ConfigFound = true;
			
			FCAddOnHandler.LogMessage("[INFO]: Reading the config file...");
			BufferedReader var1 = new BufferedReader(new FileReader(var0));
	        String var2 = "";
	        
	        while ((var2 = var1.readLine()) != null)
	        {
	        	if (!var2.startsWith("//"))
	        	{
	            	if (var2.contains("=") && var2.startsWith("module"))
	            	{
	            		String[] var3 = var2.split("=");
	            		for (int var4 = 0; var4 < var3.length; ++var4)
	            		{
	            			var3[var4] = var3[var4].trim();
	            		}
	            		
	            		String var5 = var3[0];
	            		if (var3[0].contains("."))
		            		 var5 = var3[0].substring(var3[0].indexOf('.') + 1);
	            		
	            		addModuleOption(var5, Boolean.valueOf(var3[1]));
	            	}
	            	else if (var2.contains("=") && var2.startsWith("option"))
	            	{
	            		String[] var3 = var2.split("=");
	            		for (int var4 = 0; var4 < var3.length; ++var4)
	            		{
	            			var3[var4] = var3[var4].trim();
	            		}
	            		
	            		String var5 = var3[0];
	            		if (var3[0].contains("."))
		            		 var5 = var3[0].substring(var3[0].indexOf('.') + 1);
	            		
	            		addConfigOption(var5, Boolean.valueOf(var3[1]));
	            	}
	            	else if (var2.contains("=") && var2.contains("ID"))
	            	{
	            		String[] var3 = var2.split("=");
	            		for (int var4 = 0; var4 < var3.length; ++var4)
	            		{
	            			var3[var4] = var3[var4].trim();
	            		}
	            		
	            		addBlockID(var3[0], Integer.valueOf(var3[1]));
	            	}
	        	}
	        }
	        
	        var1.close();
	    }
	    catch (Exception var5)
	    {
	        System.out.println("[WARN]: Failed to load DecoAddonConfig.txt...");
	        var5.printStackTrace();
	    }
	}
	
	private boolean loadModule(String name)
	{
		try
		{
			Class.forName("DecoModule" + name).newInstance();
			System.out.println("[INFO]: Loaded module: " + name);
			this.m_LoadedModules.add(name);
			return true;
		}
		catch (ClassNotFoundException ex1)
		{
			try
			{
				Class.forName(DecoAddonManager.class.getPackage().getName()+".DecoModule" + name).newInstance();
				System.out.println("[INFO]: Loaded module: " + name);
				this.m_LoadedModules.add(name);
				return true;
			}
			catch (ClassNotFoundException ex2)
			{
				System.out.println("[INFO]: Module not found: " + name);
			}
			catch (Exception ex3)
			{
				System.out.println("[WARN]: Problem loading module: " + name);
				if (DEBUG_ADDON_LOAD) ex3.printStackTrace();
			}
			return false;
		}
		catch (Exception ex4)
		{
			System.out.println("[WARN]: Problem loading module: " + name);
			if (DEBUG_ADDON_LOAD) ex4.printStackTrace();
			return false;
		}
	}
	
	public boolean ClientCustomPacketReceived(Minecraft var1, Packet250CustomPayload var2)
	{
		return DecoUtilsPacketHandler.packetReceived(var1, var2);
	}
}
