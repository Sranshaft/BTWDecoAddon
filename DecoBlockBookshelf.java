package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockBookshelf extends Block
{
	private static String[] m_BookshelfSides = new String[] { "bookshelf", "bookshelf_spruce", "bookshelf_birch", "bookshelf_jungle", "bookshelf_bloodwood", "bookshelf_ebonwood", "bookshelf_ironwood" };
	private Icon[] m_IconByMetadataArray;
	
    public DecoBlockBookshelf(int id)
    {
        super(id, Material.wood);
        
        this.setUnlocalizedName("bookshelf");
		this.setHardness(Block.planks.blockHardness);
		this.setResistance(Block.planks.blockResistance);
		this.setStepSound(Block.soundWoodFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
        
        DecoAddonManager.register(this, DecoUtilsStrings.WOOD_PLANK_TYPES, DecoUtilsStrings.WOOD_PLANK_NAMES, " Bookshelf");
    }
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int itemID, Random random, int amount)
    {
        return Item.book.itemID;
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 3;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int metadata)
    {
        return metadata;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
        return side > 1 ? this.m_IconByMetadataArray[metadata] : DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockWood.getIcon(side, metadata);
    }
    
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
        this.m_IconByMetadataArray = new Icon[this.m_BookshelfSides.length];
        
        for (int index = 0; index < this.m_BookshelfSides.length; index++)
        {
        	this.m_IconByMetadataArray[index] = register.registerIcon(this.m_BookshelfSides[index]);
        }
    }
    
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for (int index = 0; index < this.m_BookshelfSides.length; index++)
        {
    		par3List.add(new ItemStack(par1, 1, index));
        }
    }
}