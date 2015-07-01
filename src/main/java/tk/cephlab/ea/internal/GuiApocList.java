package tk.cephlab.ea.internal;

import java.util.List;

import net.minecraft.client.renderer.Tessellator;
import tk.cephlab.ea.api.EAPlugin;
import cpw.mods.fml.client.GuiScrollingList;

/**
 * Based on code by cpw.
 * <br> Implementation authorized by Sharing clause on FML licensing.
 * 
 * @author Cephrus
 */
public class GuiApocList extends GuiScrollingList
{
	public GuiApocSelect parent;
	public List<EAPluginContainer> activePlugins;
	public int right;
	
	public GuiApocList(GuiApocSelect parent, List<EAPluginContainer> plugins, int width) 
	{
		super(parent.mc.getMinecraft(), width, parent.height, 32, parent.height - 84, 10, 32);
		this.parent = parent;
		this.activePlugins = plugins;
		this.right = this.left + width;
	}

	@Override
	protected int getSize() 
	{
		return activePlugins.size();
	}

	@Override
	protected void elementClicked(int index, boolean doubleClick) 
	{
		this.parent.selectIndex(index, doubleClick);
	}

	@Override
	protected boolean isSelected(int index) 
	{
		return this.parent.selected == index;
	}

	@Override
	protected void drawBackground() 
	{
		this.parent.drawDefaultBackground();
	}

	@Override
	protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5) 
	{
		EAPlugin plugin = this.activePlugins.get(var1).plugin;
		
		this.parent.getFontRenderer().drawString(this.parent.getFontRenderer().trimStringToWidth(plugin.getDisplayName(), listWidth - 10), this.left + 3, var3 + 2, 0xFFFFFF);
	}
}
