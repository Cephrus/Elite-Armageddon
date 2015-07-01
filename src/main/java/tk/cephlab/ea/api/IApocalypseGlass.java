package tk.cephlab.ea.api;

import net.minecraft.entity.Entity;

/**
 * For Solar Apocalypse Integration.
 * 
 * Implement this class on your block <<or>> tileentity class to create a protective glass for SA.
 * @author Cephrus
 */
public interface IApocalypseGlass 
{
	boolean doesGlassBurnEntity(BlockPos coords, Entity entityUnderGlass);
}
