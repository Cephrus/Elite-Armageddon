package tk.cephlab.ea.api;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class EABlock extends Block 
{
	public EABlock(Material material, String unlocName, SoundType sound)
	{
		super(material);
		this.setBlockName(unlocName);
		this.setStepSound(sound);
	}
}
