package tk.cephlab.ea.internal;

import java.io.File;

import tk.cephlab.ea.api.EAPlugin;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ModMetadata;

public class EAPluginContainer 
{
	public EAPlugin plugin;
	public ModContainer container;
	
	public EAPluginContainer(EAPlugin pl, ModContainer mc)
	{
		this.plugin = pl;
		this.container = mc;
	}
	
	public EAPluginContainer(File file)
	{
		ModMetadata stub = new ModMetadata();
		stub.modId = file.getName().toLowerCase();
		stub.name = file.getName();
		stub.version = "eaLua Compatible Plugin 1.0";
		this.container = (ModContainer)new DummyModContainer(stub);
	}
}
