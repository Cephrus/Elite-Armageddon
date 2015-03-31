package tk.cephlab.ea;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;

@Mod(modid="notea", name="Elite Armageddon", version="1.3.3.7_a1")
public class EliteArmageddon 
{
	@EventHandler
	public void preInit(FMLPreInitializationEvent pIEvent)
	{
		new EALoader().registerPlugins();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent iEvent)
	{
		MinecraftForge.EVENT_BUS.register(new Handler());
		EALoader.instance().loadPlugins();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent PIEvent)
	{
		
	}
	
	@EventHandler
	public void sABTS(FMLServerAboutToStartEvent sabtsEvent)
	{
		
	}
	
	@EventHandler
	public void serverStarted(FMLServerStartedEvent ssEvent)
	{
		
	}
}
