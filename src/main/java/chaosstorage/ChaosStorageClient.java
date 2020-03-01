package chaosstorage;

import chaosstorage.utils.DebugUtils;
import net.fabricmc.api.ClientModInitializer;

public class ChaosStorageClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		DebugUtils.dbg("Hello Fabric client world!");
	}
}
