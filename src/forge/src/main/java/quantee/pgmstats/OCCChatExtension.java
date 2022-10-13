package quantee.pgmstats;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "chatextension", name = "OCC Chat Extension", version = "1.0")
public class OCCChatExtension {

	private OCCEventHandler occeventhandler = new OCCEventHandler();


	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ClientCommandHandler.instance.registerCommand(new Status());
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(occeventhandler);
		MinecraftForge.EVENT_BUS.register(this);
		LocaleLists.init();
	}
}
