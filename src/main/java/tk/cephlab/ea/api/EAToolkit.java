package tk.cephlab.ea.api;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import cpw.mods.fml.common.registry.GameRegistry;

public final class EAToolkit 
{
	/**
	 * Finds top block in provided x and z coordinates.
	 * <br>
	 * @param world The current world object.
	 * @param i x coordinate.
	 * @param j z coordinate.
	 * @return y coordinate of top block.
	 */
	public static int getTopBlock(World world, int i, int j)
	{
		try
		{
			Chunk chunk = world.getChunkFromChunkCoords(i, j);
			
			int k = 255;
			//i &= 15;
			//j &= 15;
			
			while(k > 0)
			{
				Block l = chunk.getBlock(i&15, k, j&15);
				if(l == Blocks.air) k--;
				else return k;
			}
			
			return -1;
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 1.8 Compatibility Method
	 * 
	 * @param world World Object
	 * @param coords BlockPos of Coordinates
	 * @return Block at that location
	 */
	public static Block getBlockState(World world, BlockPos coords)
	{
		return world.getBlock(coords.xCoord, coords.yCoord, coords.zCoord);
	}
	
	/**
	 * 1.8 Compatibility Method
	 * 
	 * @param world World Object
	 * @param coords BlockPos Coordinates
	 */
	public static void markBlockForUpdate(World world, BlockPos coords)
	{
		world.markBlockForUpdate(coords.xCoord, coords.yCoord, coords.zCoord);
	}
	
	/**
	 * Registers new Content Block. Do not use.
	 * 
	 * @deprecated Not Fully Implemented
	 * @param blockMaterial Material of Block
	 * @param blockName Unlocalized Name of Block
	 * @param stepSound Blocks step sound
	 * @return instance of the block
	 */
	public static EABlock registerNewBlock(Material blockMaterial, String blockName, SoundType stepSound)
	{
		EABlock blockInstance = new EABlock(blockMaterial, blockName, stepSound);
		GameRegistry.registerBlock(blockInstance, blockName);
		return blockInstance;
	}
}
