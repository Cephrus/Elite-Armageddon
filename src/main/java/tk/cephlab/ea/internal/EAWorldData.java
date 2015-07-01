package tk.cephlab.ea.internal;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.MapStorage;
import tk.cephlab.ea.EALoader;
import tk.cephlab.ea.api.EAPlugin;

public class EAWorldData extends WorldSavedData
{
	public EAWorldData(String s)
	{
		super(s);
	}
	
	public EAWorldData() 
	{
		super("EAWorldData");
	}
	
	public static EAWorldData forWorld(WorldServer world)
	{
		MapStorage storage = world.perWorldStorage;
		EAWorldData returnable = (EAWorldData)storage.loadData(EAWorldData.class, "EAWorldData");
		if(returnable == null)
		{
			returnable = new EAWorldData();
			storage.setData("EAWorldData", returnable);
		}
		return returnable;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) 
	{	
		EAPlugin.day = nbt.getInteger("eaDays");

		for(EAPluginContainer plugin : EALoader.instance().getPluginList())
		{
			System.out.println(plugin.container.getModId());
			if(nbt.getInteger("loadedPlugin" + plugin.container.getModId()) == 1)
			{
				System.out.println("Loaded");
				EALoader.instance().initPlugin(plugin);
			}
		}
		
		for(EAPluginContainer plugin : EALoader.instance().loadedPlugins)
		{
			plugin.plugin.phase = nbt.getInteger(plugin.plugin.getIdentifier() + "_phase");
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) 
	{
		nbt.setInteger("eaDays", EAPlugin.day);

		for(EAPluginContainer plugin : EALoader.instance().loadedPlugins)
		{
			nbt.setInteger(plugin.plugin.getIdentifier() + "_phase", plugin.plugin.phase);
			nbt.setInteger("loadedPlugin" + plugin.container.getModId(), 1);
		}
	}
}
