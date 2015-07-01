package tk.cephlab.ea;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import tk.cephlab.ea.internal.EAPluginContainer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.relauncher.Side;

@Mod(modid="genuine-ea", name="Elite Armageddon", version="1.3.3.7_a1")
public class EliteArmageddon 
{
	@Instance("genuine-ea")
	public static EliteArmageddon instance;
	
	public Configuration config;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent pIEvent)
	{
		new EALoader().registerPlugins();
		EALoader.instance().loadPlugins();
		
		if(pIEvent.getSide() == Side.SERVER)
		{
			config = new Configuration(pIEvent.getSuggestedConfigurationFile());
			config.load();
			
			for(EAPluginContainer plug : EALoader.instance().getPluginList())
			{
				if(config.getBoolean(plug.plugin.getDisplayName(), "EAPlugins", false, "Enable this to enable the plugin in the world."))
					EALoader.instance().initPlugin(plug);
			}
			
			config.save();
		}
	}
	
	@EventHandler
	public void init(FMLInitializationEvent iEvent)
	{
		MinecraftForge.EVENT_BUS.register(new Handler());
		FMLCommonHandler.instance().bus().register(new Handler());
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		//event.registerServerCommand(new EACommand());
	}
	
	@EventHandler
	public void serverStopping(FMLServerStoppingEvent sabtsEvent)
	{
		for(EAPluginContainer plugin : EALoader.instance().loadedPlugins)
		{
			plugin.plugin.worldStop();
		}
		EALoader.instance().loadedPlugins.clear();
	}
	
	@EventHandler
	public void serverStarted(FMLServerStartedEvent ssEvent)
	{
		
	}
}
