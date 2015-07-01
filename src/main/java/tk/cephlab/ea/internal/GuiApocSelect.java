package tk.cephlab.ea.internal;

import java.io.IOException;
import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import tk.cephlab.ea.EALoader;

/**
 * Based on code by cpw.
 * <br>Implementation Authorized by Sharing clause in FML Licensing.
 * 
 * @author Cephrus
 */
public class GuiApocSelect extends GuiScreen
{
	public GuiScreen parent;
	public GuiApocList apocList;
	public List<EAPluginContainer> availablePlugins;
	public GuiButton buttonEnable;
	public GuiButton buttonDone;
	public int lWidth = 0;
	public int selected = -1;
	public EAPluginContainer selectedPlugin;
	
	private static final String enableTxt = StatCollector.translateToLocal("internal.genuineea.EnableApoc");
	private static final String disableTxt = StatCollector.translateToLocal("internal.genuineea.DisableApoc");
	
	public GuiApocSelect(GuiScreen parent)
	{
		this.parent = parent;
		this.availablePlugins = EALoader.instance().getPluginList();
	}
	
	@Override
	public void initGui()
	{
		for(EAPluginContainer plugin : this.availablePlugins)
		{
			lWidth = Math.max(lWidth, this.fontRendererObj.getStringWidth(plugin.plugin.getDisplayName()) + 10);
		}
		lWidth = Math.min(lWidth, 150);
		
		this.apocList = new GuiApocList(this, this.availablePlugins, this.lWidth);
		this.apocList.registerScrollButtons(buttonList, 7, 8);
		
		this.buttonEnable = new GuiButton(9, 15, this.height - 49, 100, 20, enableTxt);
		this.buttonDone = new GuiButton(6, ((apocList.right + this.width) / 2) - 100, this.height - 38, I18n.format("gui.done"));
		this.buttonList.add(buttonDone);
		this.buttonList.add(buttonEnable);
	}
	
	@Override
	public void drawScreen(int var1, int var2, float var3)
	{
		this.apocList.drawScreen(var1, var2, var3);
		this.drawCenteredString(this.fontRendererObj, StatCollector.translateToLocal("internal.genuineea.ApocButton"), this.width / 2, 16, 0xFFFFFF);
		
		if(this.selectedPlugin != null)
		{
			GL11.glEnable(GL11.GL_BLEND);
			this.fontRendererObj.drawStringWithShadow(selectedPlugin.plugin.getDisplayName(), lWidth + 20, 35, 0xFFFFFF);
		}
		
		super.drawScreen(var1, var2, var3);
	}
	
	@Override
	public void actionPerformed(GuiButton button)
	{
		if(button == this.buttonEnable && this.selectedPlugin != null && !EALoader.isPluginLoaded(selectedPlugin))
		{
			button.displayString = this.disableTxt;
			EALoader.instance().initPlugin(this.selectedPlugin);
		}
		else if(button == this.buttonEnable && this.selectedPlugin != null && EALoader.isPluginLoaded(selectedPlugin))
		{
			button.displayString = this.enableTxt;
			EALoader.instance().unloadPlugin(selectedPlugin);
		}
		else if(button.id == 6)
		{
			this.mc.displayGuiScreen(this.parent);
			return;
		}
		
		super.actionPerformed(button);
	}
	
	public void selectIndex(int index, boolean dClick)
	{
		this.selected = index;
		
		if(this.selected >= 0 && index <= this.availablePlugins.size())
		{
			this.selectedPlugin = this.availablePlugins.get(this.selected);
		}
		else this.selectedPlugin = null;
		
		if(dClick && this.selectedPlugin != null)
		{
			EALoader.instance().initPlugin(this.selectedPlugin);
		}
		
		this.buttonEnable.displayString = !EALoader.isPluginLoaded(this.selectedPlugin) ? this.enableTxt : this.disableTxt;
	}
	
	public FontRenderer getFontRenderer()
	{
		return this.fontRendererObj;
	}
}
