package tk.cephlab.eacontent.solar;

import java.util.List;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockObsidianGlass extends BlockBreakable implements ITileEntityProvider
{
	public static int[] textureRefByID = { 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 4, 4, 5, 5, 4, 4, 5, 5, 17, 17, 22, 26, 17, 17, 22, 26, 16, 16, 20, 20, 16, 16, 28, 28, 21, 21, 46, 42, 21, 21, 43, 38, 4, 4, 5, 5, 4, 4, 5, 5, 9, 9, 30, 12, 9, 9, 30, 12, 16, 16, 20, 20, 16, 16, 28, 28, 25, 25, 45, 37, 25, 25, 40, 32, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 4, 4, 5, 5, 4, 4, 5, 5, 17, 17, 22, 26, 17, 17, 22, 26, 7, 7, 24, 24, 7, 7, 10, 10, 29, 29, 44, 41, 29, 29, 39, 33, 4, 4, 5, 5, 4, 4, 5, 5, 9, 9, 30, 12, 9, 9, 30, 12, 7, 7, 24, 24, 7, 7, 10, 10, 8, 8, 36, 35, 8, 8, 34, 11 };
	public IIcon[] obsGlassIcns = new IIcon[47];
	public IIcon reinGlass;
	public IIcon nethGlass;
	public IIcon pwrdGlass;
	
	public BlockObsidianGlass() 
	{
		super("saObsGlass", Material.glass, false);
		this.setBlockName("saObsGlass");
		this.setCreativeTab(CreativeTabs.tabTools);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public int getRenderBlockPass()
	{
        return 0;
    }
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item unknown, CreativeTabs tab, List subItems) 
	{
		for (int ix = 0; ix < 4; ix++) 
		{
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
	
	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		return new TileEntityObsidianGlass();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		for (int i = 0; i < 47; i++) obsGlassIcns[i] = reg.registerIcon("genuine-easapoc:woodGlass_" + (i+1));
		reinGlass = reg.registerIcon("genuine-easapoc:glass");
		nethGlass = reg.registerIcon("genuine-easapoc:reinforced");
		pwrdGlass = reg.registerIcon("genuine-easapoc:pwrGlass");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		switch(meta)
		{
		case 1: return reinGlass;
		case 2: return nethGlass;
		case 3: return pwrdGlass;
		default: return obsGlassIcns[0];
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if(meta == 0)
		{
			boolean[] bitMatrix = new boolean[8];
			
			if (side == 0 || side == 1)
			{
				bitMatrix[0] = world.getBlock(x-1, y, z-1) == this;
				bitMatrix[1] = world.getBlock(x, y, z-1) == this;
				bitMatrix[2] = world.getBlock(x+1, y, z-1) == this;
				bitMatrix[3] = world.getBlock(x-1, y, z) == this;
				bitMatrix[4] = world.getBlock(x+1, y, z) == this;
				bitMatrix[5] = world.getBlock(x-1, y, z+1) == this;
				bitMatrix[6] = world.getBlock(x, y, z+1) == this;
				bitMatrix[7] = world.getBlock(x+1, y, z+1) == this;
			}
			if (side == 2 || side == 3)
			{
				bitMatrix[0] = world.getBlock(x+(side==2?1:-1), y+1, z) == this;
				bitMatrix[1] = world.getBlock(x, y+1, z) == this;
				bitMatrix[2] = world.getBlock(x+(side==3?1:-1), y+1, z) == this;
				bitMatrix[3] = world.getBlock(x+(side==2?1:-1), y, z) == this;
				bitMatrix[4] = world.getBlock(x+(side==3?1:-1), y, z) == this;
				bitMatrix[5] = world.getBlock(x+(side==2?1:-1), y-1, z) == this;
				bitMatrix[6] = world.getBlock(x, y-1, z) == this;
				bitMatrix[7] = world.getBlock(x+(side==3?1:-1), y-1, z) == this;
			}
			if (side == 4 || side == 5)
			{
				bitMatrix[0] = world.getBlock(x, y+1, z+(side==5?1:-1)) == this;
				bitMatrix[1] = world.getBlock(x, y+1, z) == this;
				bitMatrix[2] = world.getBlock(x, y+1, z+(side==4?1:-1)) == this;
				bitMatrix[3] = world.getBlock(x, y, z+(side==5?1:-1)) == this;
				bitMatrix[4] = world.getBlock(x, y, z+(side==4?1:-1)) == this;
				bitMatrix[5] = world.getBlock(x, y-1, z+(side==5?1:-1)) == this;
				bitMatrix[6] = world.getBlock(x, y-1, z) == this;
				bitMatrix[7] = world.getBlock(x, y-1, z+(side==4?1:-1)) == this;
			}
			
			int idBuilder = 0;
			for (int i = 0; i <= 7; i++) idBuilder = idBuilder + (bitMatrix[i]?(i==0?1:(i==1?2:(i==2?4:(i==3?8:(i==4?16:(i==5?32:(i==6?64:128))))))):0);
			return idBuilder>255||idBuilder<0?obsGlassIcns[0]:obsGlassIcns[textureRefByID[idBuilder]];
		}
		else if(meta == 1) return reinGlass;
		else if(meta == 2) return nethGlass;
		else if(meta == 3) return pwrdGlass;
		//else return obsGlassIcns[0];
		return null;
	}
}
