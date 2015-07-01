package tk.cephlab.eacontent.solar;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockObsGlass extends ItemBlock 
{
	public String[] subNames = new String[] {"obsidianGlass", "reinGlass", "netherGlass", "pwrdGlass"};
	
	public ItemBlockObsGlass(Block p_i45328_1_) 
	{
		super(p_i45328_1_);
		this.setHasSubtypes(true);
		this.setUnlocalizedName("saObsGlass");
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) 
	{
		return getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}
	
	@Override
	public int getMetadata(int damageValue) 
	{
		return damageValue;
	}
}
