package tk.cephlab.ea;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Handler
{
	GuiButton apocSelectBTN;
	
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
	
	@SubscribeEvent
	public void actionEvent(ActionPerformedEvent.Post event)
	{
		if(!(event.gui instanceof GuiCreateWorld)) return;
		if(event.button.id == 11)
		{
			
		}
		else if(event.button.id == 3)
		{
			apocSelectBTN.visible = !apocSelectBTN.visible;
		}
	}
}
