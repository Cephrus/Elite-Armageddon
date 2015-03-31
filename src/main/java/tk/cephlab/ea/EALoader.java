package tk.cephlab.ea;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import tk.cephlab.ea.api.IEAPlugin;

import com.google.common.collect.ImmutableList;

public final class EALoader 
{
	protected static EALoader EAInstance;
	protected List<IEAPlugin> pluginList = new ArrayList<IEAPlugin>();
	protected EALoader() 
	{
		EAInstance = this;
	}
	
	public static EALoader instance()
	{
		return EAInstance;
	}
	
	/**
	 * @category InternalMethods
	 * THIS METHOD IS FOR INTERNAL USE ONLY
	 * DO NOT USE
	 */
	public void registerPlugins()
	{
		for(ModContainer container : Loader.instance().getModList())
		{
			System.out.println("[EAPlugins]Found mod by name " + container.getName() + "; attempting load as plugin.");
			if(container.getMod() instanceof IEAPlugin)
			{
				System.out.println("[EAPlugins]Registering Plugin " + container.getName() + ".");
				pluginList.add((IEAPlugin)container.getMod());
			}
		}
	}
	
	public ImmutableList<List<IEAPlugin>> getPluginList()
	{
		return ImmutableList.of(pluginList);
	}
	
	/**
	 * @category InternalMethods
	 * THIS METHOD IS FOR INTERNAL USE ONLY
	 * DO NOT USE
	 */
	public void loadPlugins()
	{	
		for(IEAPlugin plugin : pluginList)
		{
			plugin.loadPlugin();
		}
	}
}
