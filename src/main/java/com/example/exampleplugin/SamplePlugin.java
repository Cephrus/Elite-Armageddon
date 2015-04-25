package com.example.exampleplugin;

import net.minecraftforge.fml.common.Mod;
import tk.cephlab.ea.api.EAPlugin;

@Mod(modid="SampleEA", dependencies="required-after:genuine-ea")
public class SamplePlugin extends EAPlugin
{	
	@Override
	public void loadPlugin()
	{
		System.out.println("LONGSTRING!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!Sample Load");
	}

	@Override
	public void onTick() 
	{
		super.onTick();
	}

	@Override
	public String getDisplayName() 
	{
		return "Sample";
	}

	@Override
	public String getIdentifier() 
	{
		return "Sample";
	}
}
