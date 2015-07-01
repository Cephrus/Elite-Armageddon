package tk.cephlab.eacontent.nukular;

import tk.cephlab.ea.api.EAPlugin;
import cpw.mods.fml.common.Mod;

/**
 * IT'S NUKULAR OKAY?!
 * @author Cephrus
 *
 */
//@Mod(modid="genuine-eanuclear", name="EA Nuclear Addon", version="1.3.3.7_a1")
public class EANukular extends EAPlugin
{
	@Override
	public void worldStart()
	{
		System.out.println("EANuke ON");
	}
	
	@Override
	public void loadPlugin() 
	{
		
	}

	@Override
	public void onTick() 
	{
		if(day <= 4) phase = day;
		else phase = 4;
		
		if(phase >= 1)
		{
			/**
			if(!(EAToolkit.getTopBlock(minecraft.theWorld, (int)minecraft.thePlayer.posX, (int)minecraft.thePlayer.posZ) >= minecraft.thePlayer.posY))
			{
				minecraft.thePlayer.addPotionEffect(new PotionEffect(Potion.poison.id, 20 * 5));
			}
			*/
		}
	}

	@Override
	public String getDisplayName() 
	{
		return "EA Nuclear";
	}

	@Override
	public String getIdentifier() 
	{
		return "EANuclear";
	}
}
