package quantee.pgmstats;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;

public class OCCEventHandler {
	public static boolean startsWithListContent(String text, ArrayList<String> list) {
		for(String content : list) {
			if (text.startsWith(content))
				return true;
		}
		return false;
	}

	static public Boolean teamChatEnabled = true;
	static public Boolean globalChatEnabled = true;
	static public Boolean adminChatEnabled = true;
	static public ArrayList<String> ignoredPlayers = new ArrayList<String>();
	static boolean isLoaded = false;  // Are the settings loaded
	static boolean isEnabled = false; // Is the plugin enabled (connected to occ)

	@SubscribeEvent
	public void onChatReceived(ClientChatReceivedEvent e) {
		String msg = e.message.getUnformattedText();

        // Check if we are connected to OCC first
		if (isEnabled) {
			if (!globalChatEnabled) {
				if (msg.startsWith("<")) {
					e.setCanceled(true);
				}
			}
			if (!teamChatEnabled) {
				if (msg.startsWith("(")) {
					e.setCanceled(true);
				}
			}
			if (!adminChatEnabled) {
				if (msg.startsWith("[A]")) {
					e.setCanceled(true);
				}
			}

			// Check if chat message is a /msg
			if (startsWithListContent(msg, LocaleLists.fromLocale)) {  // Check if message starts with "from"
				ArrayList<String> sep = new ArrayList<String>(Arrays.asList(msg.split("\\s+")));
                // Rank flair formatting characters are still present, remove them along with the flairs
                String sender = sep.get(1).replaceAll("[*+❖:]","").replaceAll("§[0-9a-f]", "");
				if (OCCEventHandler.ignoredPlayers.contains(sender)) {
					e.setCanceled(true);

				}
			}
		}
	}

	@SubscribeEvent
	public void onClientDisconnectionFromServer(ClientDisconnectionFromServerEvent event) {
        // Save settings
		if (isLoaded) {
			try {
				SettingsSaver.saveSettings();
				System.out.println("Setting saved");
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		isLoaded = false;
		isEnabled = false;
	}

	@SubscribeEvent
	@SuppressWarnings("unchecked") // might be a bad idea but that's the best I can do
	public void onClientConnectedToServer(ClientConnectedToServerEvent event) {
		// Load the settings and make sure to check if we are connected to OCC
		isLoaded = true;
		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
		if (!event.isLocal) {
			String ip = Minecraft.getMinecraft().getCurrentServerData().serverIP;
			if (ip.endsWith("oc.tc")) {
				isEnabled = true;
			}
		}


		// check if file exists
		File f = new File("OCCStatsSaveFile.txt");
		if(!f.exists() && !f.isDirectory()) {
			isLoaded = false;
			try {
				SettingsSaver.saveSettings();
			} catch (IOException e1) {
				System.out.println(e1);
			}
		}

		try{
		    ArrayList<Object> loadlist = SettingsSaver.loadSettings();
			teamChatEnabled = (Boolean) loadlist.get(0);
			globalChatEnabled = (Boolean) loadlist.get(1);
			adminChatEnabled = (Boolean) loadlist.get(2);
			ignoredPlayers = (ArrayList<String>) loadlist.get(3);

		    isLoaded = true;

		}catch (Exception e1) {
			System.out.println("[OCCT] Load error occurred. Stack trace:");
			System.out.println(e1);
			isLoaded = false;
		}
	}
}
