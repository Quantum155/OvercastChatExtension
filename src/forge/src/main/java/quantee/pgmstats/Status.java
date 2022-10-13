package quantee.pgmstats;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Status extends CommandBase {
	final static EnumChatFormatting gr = EnumChatFormatting.GREEN;
	final static EnumChatFormatting bl = EnumChatFormatting.BLUE;
	final static EnumChatFormatting rd = EnumChatFormatting.RED;
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}
	
	@Override
	public String getCommandName() {
		return "chatextension";
	}
	
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "Command to manage the occ chat extension plugin";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		sender.addChatMessage(new ChatComponentText("\n" + gr + " ----------- [Chat Extension] -----------"));
		if (args.length == 0) {
			sender.addChatMessage(new ChatComponentText(bl + "status" + gr + " - Check the status of the plugin"));
			sender.addChatMessage(new ChatComponentText(bl + "toggle [team|global|admin]" + gr + " - Toggle between enabling/disabling specific channels"));
			sender.addChatMessage(new ChatComponentText(bl + "ignore [<player>|list]" + gr + " - Ignore a player or list ignored players"));
			sender.addChatMessage(new ChatComponentText(bl + "unignore <player>" + gr + " - Uningore a player"));
		}
		else {
			if (args[0].equals("status")) {
				sender.addChatMessage(new ChatComponentText(gr + "Plugin active: " + BoolRenderer.formatBool(OCCEventHandler.isEnabled)));
				sender.addChatMessage(new ChatComponentText(gr + "Team chat enabled: " + BoolRenderer.formatBool(OCCEventHandler.teamChatEnabled)));
				sender.addChatMessage(new ChatComponentText(gr + "Global chat enabled: " + BoolRenderer.formatBool(OCCEventHandler.globalChatEnabled)));
				sender.addChatMessage(new ChatComponentText(gr + "Admin chat enabled: " + BoolRenderer.formatBool(OCCEventHandler.adminChatEnabled)));
			}
			else if (args[0].equals("toggle")) {
				if (args[1] == null) {  // TODO Currently doesnt work, but it's not priority
					sender.addChatMessage(new ChatComponentText(rd + "Usage: /chatextension <team|global|admin>"));
				}
				else if (args[1].equals("team")) {
					OCCEventHandler.teamChatEnabled = !OCCEventHandler.teamChatEnabled;
					sender.addChatMessage(new ChatComponentText(gr + "Team chat is now: " + BoolRenderer.edFormatBool(OCCEventHandler.teamChatEnabled)));
				}
				else if (args[1].equals("global")) {
					OCCEventHandler.globalChatEnabled = !OCCEventHandler.globalChatEnabled;
					sender.addChatMessage(new ChatComponentText(gr + "Global chat is now: " + BoolRenderer.edFormatBool(OCCEventHandler.globalChatEnabled)));
				}
				else if (args[1].equals("admin")) {
					OCCEventHandler.adminChatEnabled = !OCCEventHandler.adminChatEnabled;
					sender.addChatMessage(new ChatComponentText(gr + "Admin chat is now: " + BoolRenderer.edFormatBool(OCCEventHandler.adminChatEnabled)));
				}
				else {
					sender.addChatMessage(new ChatComponentText(rd + "Usage: /chatextension <team|global|admin>"));
				}
			}
			else if (args[0].equals("ignore")) {
				if (args[1].equals("list")) {
					sender.addChatMessage(new ChatComponentText(OCCEventHandler.ignoredPlayers.toString()));
				}
				else {
					if (OCCEventHandler.ignoredPlayers.contains(args[1])) {
						sender.addChatMessage(new ChatComponentText(bl + args[1] + rd + " is already ignored!"));
					}
					else {
						OCCEventHandler.ignoredPlayers.add(args[1]);
						sender.addChatMessage(new ChatComponentText(gr + "Now ignoring: " + bl + args[1]));
					}
				}

			}
			else if (args[0].equals("unignore")) {
				if (OCCEventHandler.ignoredPlayers.contains(args[1])) {
					OCCEventHandler.ignoredPlayers.remove(args[1]);
					sender.addChatMessage(new ChatComponentText(bl + args[1] + gr + " is now removed from the ignore list"));
				}
				else {
					sender.addChatMessage(new ChatComponentText(bl + args[1] + rd + " is not ignored, unable to remove"));
				}
			}
			else {
				sender.addChatMessage(new ChatComponentText(rd + "Invalid argument. Run " + bl + "/chatextension" + rd + " for help"));
			}
		}


	}
}
