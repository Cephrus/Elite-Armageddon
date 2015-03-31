package com.example.exampleplugin;

import net.minecraftforge.fml.common.Mod;
import tk.cephlab.ea.api.IEAPlugin;

@Mod(modid="SampleEA", dependencies="required-after:notea")
public class SamplePlugin implements IEAPlugin
{	
	@Override
	public void loadPlugin()
	{
		System.out.println("LONGSTRING!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!Sample Load");
	}
}
