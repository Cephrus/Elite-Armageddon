package tk.cephlab.eacontent.solar;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import tk.cephlab.ea.api.BlockPos;
import tk.cephlab.ea.api.IApocalypseGlass;

public class TileEntityObsidianGlass extends TileEntity implements IApocalypseGlass
{
	@Override
	public boolean doesGlassBurnEntity(BlockPos position, Entity entityUnderGlass) 
	{
		return false;
	}
}
