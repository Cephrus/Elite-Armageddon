package tk.cephlab.eacontent.solar;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tk.cephlab.ea.EALoader;
import tk.cephlab.ea.api.BlockPos;
import tk.cephlab.ea.api.EAPlugin;
import tk.cephlab.ea.api.EAToolkit;
import tk.cephlab.ea.api.IApocalypseGlass;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid="genuine-easapoc", name="Solar Apocalypse", version="1.3.3.7_a1")
public class SolarApocalypse extends EAPlugin 
{
	Block obsGlass;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		obsGlass = new BlockObsidianGlass();
		GameRegistry.registerBlock(obsGlass, ItemBlockObsGlass.class, "saObsGlass");
		FMLCommonHandler.instance().bus().register(this);
	}
	
	@Override
	public void loadPlugin() 
	{
		System.out.println("Solar Apocalypse Plugin Initialized");
	}
	
	@SubscribeEvent
	public void onTick(TickEvent.WorldTickEvent worldt)
	{
		if(worldt.phase != TickEvent.Phase.START) return;
		if(!EALoader.isPluginLoaded(this)) return;
		World world = worldt.world;
		
		super.internalTick();
		
		if(world.provider.isSurfaceWorld()) // Only tick on Overworld
		{	
			if(day <= 4) phase = day;
			else phase = 4;
			
			//if(phase == 1) blockBufferMax = 30;
			//else if(phase == 2) blockBufferMax = 20;
			//else if(phase == 3) blockBufferMax = 10;
			//else blockBufferMax = 1;
			
			if(phase >= 1)
			{
				world.getWorldInfo().setRaining(false);
				world.getWorldInfo().setThundering(false);
				Blocks.grass.setTickRandomly(false);
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
							BlockPos blk = new BlockPos(randX + playerPosX, randY, randZ + playerPosZ);
							Block block = EAToolkit.getBlockState(world, blk);
							
							if(block == Blocks.grass || block == Blocks.mycelium)
							{
								world.setBlock(blk.xCoord, blk.yCoord, blk.zCoord, Blocks.dirt);
								EAToolkit.markBlockForUpdate(world, blk);
							}
							
							if(block == Blocks.tallgrass || block == Blocks.red_flower || block == Blocks.yellow_flower)
							{
								if(phase >= 4) world.setBlock(blk.xCoord, blk.yCoord, blk.zCoord, Blocks.fire);
								else world.setBlock(blk.xCoord, blk.yCoord, blk.zCoord, Blocks.deadbush);
								EAToolkit.markBlockForUpdate(world, blk);
							}
							
							if(block == Blocks.snow || block == Blocks.snow_layer)
							{
								world.setBlock(blk.xCoord, blk.yCoord, blk.zCoord, Blocks.air);
								EAToolkit.markBlockForUpdate(world, blk);
							}
							
							if(block == Blocks.leaves)
							{
								world.setBlock(blk.xCoord, blk.yCoord, blk.zCoord, Blocks.air);
								EAToolkit.markBlockForUpdate(world, blk);
							}
							
							if((block == Blocks.water || block == Blocks.flowing_water) && phase >=3)
							{
								world.setBlock(blk.xCoord, blk.yCoord, blk.zCoord, Blocks.air);
								EAToolkit.markBlockForUpdate(world, blk);
								world.playSoundEffect(blk.xCoord, blk.yCoord, blk.zCoord, "random.fizz", 0.5F, 0.3F);
						        //world.spawnParticle("large_smoke", (double)blk.xCoord, (double)blk.yCoord + 1.2D, (double)blk.zCoord, 0.0D, 0.0D, 0.0D);
							}
							
							if(phase >= 4 && block.isFlammable(world, blk.xCoord, blk.yCoord, blk.zCoord, ForgeDirection.UP))
							{
								world.setBlock(blk.xCoord, blk.yCoord + 1, blk.zCoord, Blocks.fire);
								world.notifyBlockOfNeighborChange(blk.xCoord, blk.yCoord, blk.zCoord, block);
							}
					}
				}
			}
		
			if(phase >= 4)
			{
				for(Object entityo : world.loadedEntityList.toArray())
				{
					if(entityo instanceof Entity)
					{	
						Entity entity = (Entity)entityo;
						if(world.canBlockSeeTheSky((int)entity.posX, (int)entity.posY, (int)entity.posZ) && world.isDaytime())
						{
							BlockPos blkPos = new BlockPos(MathHelper.floor_double(entity.posX), EAToolkit.getTopBlock(world, (int)Math.floor(entity.posX), (int)Math.floor(entity.posZ)), MathHelper.floor_double(entity.posZ));
							Block blk = EAToolkit.getBlockState(world, blkPos);
							boolean doBurn = true;
							
							if(entity.isImmuneToFire() || entity.isInWater())
								doBurn = false;
							if(blk instanceof IApocalypseGlass)
								doBurn = ((IApocalypseGlass) blk).doesGlassBurnEntity(blkPos, entity);
							if(world.getTileEntity(blkPos.xCoord, blkPos.yCoord, blkPos.zCoord) != null && world.getTileEntity(blkPos.xCoord, blkPos.yCoord, blkPos.zCoord) instanceof IApocalypseGlass)
								doBurn = ((IApocalypseGlass)world.getTileEntity(blkPos.xCoord, blkPos.yCoord, blkPos.zCoord)).doesGlassBurnEntity(blkPos, entity);
										
							if(doBurn)
							{
								entity.setFire(5);
							}
						}
					}
				}
			}
			}
		}
	}

	@Override
	public String getDisplayName() 
	{
		return "Solar Apocalypse";
	}

	@Override
	public String getIdentifier() 
	{
		return "sapoc";
	}
}
