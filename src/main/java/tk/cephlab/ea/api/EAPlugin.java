package tk.cephlab.ea.api;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.client.IRenderHandler;

public abstract class EAPlugin 
{
	/**
	 * Apocalypse Phase <br>
	 * Use this to differentiate between effects.
	 * <br>
	 * This is synchronized with the client when on a server. <br>
	 */
	public int phase = 0;
	
	/**
	 * Day in world
	 * <br><br>
	 * Increased by internal onTick() method (call super.onTick() from your own)
	 * whenever the world time hits 0.
	 * <br>
	 * Default (new world) value is 0.
	 * <br>
	 * DO NOT Base effects off this number; use phase for that. <br>
	 * YOU CAN use this number to affect phase, however. <br>
	 * THIS INTEGER IS SHARED ACROSS ALL LOADED PLUGINS. <br>
	 * MODIFICATION MAY RESULT IN CRASHES OR UNWANTED EFFECTS
	 * ON CERTAIN INSTALLATIONS.
	 */
	public static int day = 0;
	
	/**
	 * Minecraft Server Instance <br>
	 * <br>
	 * All tick references are Server-Side. Do not attempt to use Minecraft.class to refer to them.
	 */
	protected MinecraftServer server = MinecraftServer.getServer();
	
	private static World world;
	
	/** Plugin load method. <br>
	 * DO NOT CONFUSE WITH FORGE LOAD METHODS! */
	public abstract void loadPlugin();

	/**
	 * Returns display name of the plugin.
	 */
	public abstract String getDisplayName();
	
	/**
	 * Unique identifier of the plugin.
	 */
	public abstract String getIdentifier();
	
	/** Called once each tick per active plugin. <br>
	 * Can be substituted with Forge events if you wish.
	 * */
	public void onTick() {}
	
	public void onTick(World world)
	{
		this.world = world;
		onTick();
	}
	
	/**
	 * Internal tick method <br>
	 * 
	 * DO NOT OVERRIDE, <br>
	 * use onTick() instead.
	 */
	public static void internalTick()
	{
		try
		{
			day = (int) Math.floor((double)world.getWorldTime() / 24000L);
		}
		catch(NullPointerException e)
		{;}
	}
	
	/**
	 * Called when the world has loaded (when player has just entered a world).
	 * 
	 */
	public void worldStart()
	{
		
	}
	
	/**
	 * Called when the world is exiting (when player is leaving the world)
	 * 
	 */
	public void worldStop()
	{
		
	}
	
	/**
	 * Implement to add your own custom sky renderer.
	 */
	public IRenderHandler getCustomSkyRenderer()
	{
		return null;
	}
}
