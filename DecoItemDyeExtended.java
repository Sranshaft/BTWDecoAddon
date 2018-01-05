package net.minecraft.src;

import java.util.List;

public class DecoItemDyeExtended extends FCItemDye
{
	private Icon[] m_Icons = new Icon[16];
	private final String[] m_dyeExtendedColorNames = new String[] {"black","red","green","brown","blue","purple","cyan","silver","gray","pink","lime","yellow","lightBlue","magenta","orange", "white","black2","red2","green2","brown2","blue2","purple2","cyan2","silver2","gray2","pink2","lime2","yellow2","lightBlue2","magenta2","orange2","white2"};
	
	public DecoItemDyeExtended(int ID)
	{
		super(ID);
		
		this.setUnlocalizedName("dyePowder");
		this.SetBellowsBlowDistance(2);
	}
	
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int var7, float var8, float var9, float var10)
	{
		return player != null && !player.canPlayerEdit(x, y, z, var7, itemStack) ? false : (itemStack.getItemDamage() == 15 ?
				DecoUtilsCrops.hasAppliedBonemeal(itemStack, player, world, x, y, z, var7, var8, var9, var10): (itemStack.getItemDamage() == 3 ?
				this.ApplyCocoaBeans(itemStack, player, world, x, y, z, var7, var8, var9, var10) : false
			));
	}
	
	private boolean ApplyCocoaBeans(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
	{
		int var11 = var3.getBlockId(var4, var5, var6);
		int var12 = var3.getBlockMetadata(var4, var5, var6);
		if (var11 == Block.wood.blockID && BlockLog.limitToValidMetadata(var12) == 3)
		{
			if (var7 != 0 && var7 != 1)
			{
				FCUtilsBlockPos var13 = new FCUtilsBlockPos(var4, var5, var6);
				var13.AddFacingAsOffset(var7);
				if (var3.isAirBlock(var13.i, var13.j, var13.k))
				{
					int var14 = Block.cocoaPlant.blockID;
					int var15 = Block.blocksList[var14].onBlockPlaced(var3, var13.i, var13.j, var13.k, var7, var8, var9, var10, 0);
					var3.setBlockAndMetadataWithNotify(var13.i, var13.j, var13.k, var14, var15);
					if (var2 == null || !var2.capabilities.isCreativeMode)
						--var1.stackSize;
				}
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return super.getUnlocalizedName() + "." + this.m_dyeExtendedColorNames[itemStack.getItemDamage() & 31];
	}
	
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int var4 = 0; var4 < 16; ++var4)
		{
			par3List.add(new ItemStack(par1, 1, var4));
		}
		
		par3List.add(new ItemStack(par1, 1, 20));
		par3List.add(new ItemStack(par1, 1, 31));
	}
	
	public Icon getIconFromDamage(int metadata)
	{
		return metadata > 15 ? this.m_Icons[metadata - 16] : super.getIconFromDamage(metadata);
	}
	
	public void registerIcons(IconRegister var1)
	{
		super.registerIcons(var1);
		int index = 0;
		
		while(index < 16)
		{
			this.m_Icons[index] = var1.registerIcon("decoItemDye_" + this.m_dyeExtendedColorNames[index]);
			index++;
		}
	}
}
