package tk.cephlab.eacontent.lunar;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tk.cephlab.ea.api.EAPlugin;
import tk.cephlab.ea.api.EAToolkit;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

@Mod(modid="genuineea-lunarapoc", name="Lunar Apocalypse", version="1.3.3.7_a1")
public class LunarDeath extends EAPlugin
{
	int playerBuffer = 0;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		FMLCommonHandler.instance().bus().register(this);
	}
	
	@Override
	public void loadPlugin()
	{
		
	}
	
	@SubscribeEvent
	public void onTick(TickEvent.WorldTickEvent worldt)
	{
		World world = worldt.world;
		super.internalTick();
		
		if(day >= 8) phase = day;
		else phase = 8;
		
		if(phase >= 1)
		{
			world.getWorldInfo().setRaining(true);
			world.getWorldInfo().setRainTime(24000);
		}
		
		for(int i = 0; i < world.playerEntities.size(); i++)
		{
			EntityPlayer focusPlayer = (EntityPlayer)world.playerEntities.get(i);
			
			int randX = world.rand.nextInt(16);
			int randZ = world.rand.nextInt(16);
			int playerPosX = MathHelper.floor_double(focusPlayer.posX);
			int playerPosZ = MathHelper.floor_double(focusPlayer.posZ);
			int randY = EAToolkit.getTopBlock(world, randX + playerPosX, randZ + playerPosZ);
			
			byte effectRange = 7;
			for(int xOffset = -effectRange; xOffset <= effectRange; xOffset++)
			{
				for(int zOffset = -effectRange; zOffset <= effectRange; zOffset++)
				{
					if(phase >= 1)
					{
						Block block = world.getBlock(randX + playerPosX, randY, randZ + playerPosZ);
					}
				}
			}
		}
	}

	@Override
	public String getDisplayName() 
	{
		return "Lunar Apocalypse";
	}

	@Override
	public String getIdentifier() 
	{
		return "ealunar";
	}
}
