package tk.cephlab.ea;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.util.StatCollector;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import tk.cephlab.ea.api.EAPlugin;
import tk.cephlab.ea.internal.EAPluginContainer;
import tk.cephlab.ea.internal.EAWorldData;
import tk.cephlab.ea.internal.GuiApocSelect;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Handler
{
	@SideOnly(Side.CLIENT)
	GuiButton apocSelectBTN;
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void guiEvent(GuiScreenEvent.InitGuiEvent.Post event)
	{
		if(!(event.gui instanceof GuiCreateWorld)) return;
		GuiButton gmodebtn = (GuiButton) event.buttonList.get(2);
		GuiButton apocSelt = new GuiButton(11, event.gui.width / 2 + 5, 110, 150, 20, StatCollector.translateToLocal("internal.genuineea.ApocButton"));
		gmodebtn.xPosition -= 80;
		gmodebtn.yPosition -= 5;
		apocSelectBTN = apocSelt;
		event.buttonList.add(apocSelt);
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void actionEvent(ActionPerformedEvent.Post event)
	{
		if(!(event.gui instanceof GuiCreateWorld)) return;
		if(event.button.id == 11)
		{
			event.gui.mc.displayGuiScreen(new GuiApocSelect(event.gui));
		}
		else if(event.button.id == 3)
		{
			apocSelectBTN.visible = !apocSelectBTN.visible;
		}
	}
	
	@SubscribeEvent
	public void onWorldTickLoad(TickEvent.WorldTickEvent event)
	{
		EAWorldData eaWD = EAWorldData.forWorld((WorldServer)event.world);
		eaWD.markDirty();
		
		if(event.phase == TickEvent.Phase.START)
		{
			EAPlugin.internalTick();
			for(EAPluginContainer plugin : EALoader.instance().loadedPlugins)
			{	
				try
				{
					plugin.plugin.onTick(event.world);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
