package quantee.pgmstats;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SettingsSaver {
	
	static void saveSettings() throws IOException {
		ArrayList<Object> savelist = new ArrayList<Object>();
		savelist.add(OCCEventHandler.teamChatEnabled);
		savelist.add(OCCEventHandler.globalChatEnabled);
		savelist.add(OCCEventHandler.adminChatEnabled);
		savelist.add(OCCEventHandler.ignoredPlayers);

		// write savelist to savefile
		FileOutputStream writeData = new FileOutputStream("OCCChatExtension.txt");
		ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

		writeStream.writeObject(savelist);
		writeStream.flush();
		writeStream.close();
	}
	
	@SuppressWarnings("unchecked")
	static ArrayList<Object> loadSettings() throws Exception {
		FileInputStream readData = new FileInputStream("OCCChatExtension.txt");
	    ObjectInputStream readStream = new ObjectInputStream(readData);

	    ArrayList<Object> loadlist = (ArrayList<Object>) readStream.readObject();
	    readStream.close();
	    return loadlist;
	}	
}
