package tk.cephlab.ea;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import tk.cephlab.ea.api.EAPlugin;
import tk.cephlab.ea.internal.EAPluginContainer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.relauncher.Side;

public final class EALoader 
{
	private Side mcSide = FMLCommonHandler.instance().getSide();
	protected static EALoader EAInstance;
	protected List<EAPluginContainer> pluginList = new ArrayList<EAPluginContainer>();
	public List<EAPluginContainer> loadedPlugins = new ArrayList<EAPluginContainer>();
	public File eaLuaDir = new File((mcSide == Side.CLIENT ? Minecraft.getMinecraft().mcDataDir.getAbsolutePath() : MinecraftServer.getServer().getFolderName()) + "/eaplugins");
	
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
		eaLuaDir.mkdir();
		for(ModContainer container : Loader.instance().getModList())
		{
			if(container.getMod() instanceof EAPlugin)
			{
				EAPluginContainer pluginContainer = new EAPluginContainer((EAPlugin)container.getMod(), container);
				if(pluginList.contains(pluginContainer)) continue;
				System.out.println("[EAPlugins]Registering Plugin " + container.getName() + ".");
				pluginList.add(pluginContainer);
			}
		}
		
		registerLuaPlugins();
	}
	
	public void registerLuaPlugins()
	{
		/**
		for(File content : this.eaLuaDir.listFiles())
		{
			if(content.getName().endsWith(".lua")) // Load all plugins that end with .lua from the eaplugins folder.
			{
				System.out.println("[EAPlugins-Lua]Registering Plugin " + content.getName() + ".");
				EAPluginContainer cont = new EAPluginContainer(null, null);
			}
		}
		*/
	}
	
	public List<EAPluginContainer> getPluginList()
	{
		return this.pluginList;
	}
	
	/**
	 * @category InternalMethods
	 * THIS METHOD IS FOR INTERNAL USE ONLY
	 * DO NOT USE
	 */
	public void loadPlugins()
	{	
		for(EAPluginContainer plugin : pluginList)
		{
			plugin.plugin.loadPlugin();
		}
	}
	
	public static boolean isPluginLoaded(EAPluginContainer plugin)
	{
		return instance().loadedPlugins.contains(plugin);
	}
	
	public static boolean isPluginLoaded(EAPlugin plugin)
	{
		boolean isLoaded = false;
		for(EAPluginContainer cont : instance().loadedPlugins)
		{
			if(cont.plugin == plugin)
			{
				isLoaded = true;
				break;
			}
		}
		return isLoaded;
	}
	
	/**
	 * @category InternalMethods
	 * THIS METHOD IS FOR INTERNAL USE ONLY
	 * DO NOT USE
	 * @param plugin
	 */
	public void initPlugin(EAPluginContainer plugin)
	{
		if(this.pluginList.contains(plugin) && !this.loadedPlugins.contains(plugin))
		{
			this.loadedPlugins.add(plugin);
		}
	}
	
	/**
	 * @category InternalMethods
	 * THIS METHOD IS FOR INTERNAL USE ONLY
	 * DO NOT USE
	 */
	public void unloadPlugin(EAPluginContainer plugin)
	{
		if(this.pluginList.contains(plugin) && this.loadedPlugins.contains(plugin))
		{
			this.loadedPlugins.remove(plugin);
		}
	}
}
