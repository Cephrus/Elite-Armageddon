package tk.cephlab.ea;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatComponentText;
import tk.cephlab.ea.api.EAPlugin;
import tk.cephlab.ea.internal.EAPluginContainer;

public class EACommand extends CommandBase
{
	@Override
	public String getCommandName() 
	{
		return "ea";
	}
	
	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) 
	{
		return "/ea <phase/reset> [+/-]";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args)
			throws CommandException 
	{
		if(args[0].equals("phase"))
		{
			if(args[1].equals("+"))
			{
				for(EAPluginContainer plugin : EALoader.instance().loadedPlugins)
				{
					plugin.plugin.phase++;
					sender.addChatMessage(new ChatComponentText("The Apocalypse phase is now " + plugin.plugin.phase + "."));
				}
			}
			else if(args[1].equals("-"))
			{
				for(EAPluginContainer plugin : EALoader.instance().loadedPlugins)
				{
					plugin.plugin.phase--;
					sender.addChatMessage(new ChatComponentText("The Apocalypse phase is now " + plugin.plugin.phase + "."));
				}
			}
		}
		else if(args[0].equals("reset"))
		{
			EAPlugin.day = 0;
			
			for(EAPluginContainer cont : EALoader.instance().loadedPlugins)
			{
				cont.plugin.phase = 0;
			}
		}
		else
		{
			throw new WrongUsageException(getCommandUsage(sender), new Object[0]);
		}
	}

}
