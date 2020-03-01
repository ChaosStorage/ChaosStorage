package chaosstorage.storage;

import static chaosstorage.ChaosStorage.CS;
import chaosstorage.packets.ServerBoundPackets;
import reborncore.common.network.NetworkManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;

public class StorageDiskSync {
	private static final int THROTTLE_MS = 500;

	private Map<UUID, StorageDiskSyncData> data = new HashMap<>();
	private Map<UUID, Long> syncTime = new HashMap<>();

	@Nullable
	public StorageDiskSyncData getData(UUID id) {
		return data.get(id);
	}

	public void setData(UUID id, StorageDiskSyncData data) {
		this.data.put(id, data);
	}

	public void sendRequest(UUID id) {
		long lastSync = syncTime.getOrDefault(id, 0L);

		if (System.currentTimeMillis() - lastSync > THROTTLE_MS) {
			NetworkManager.sendToServer(ServerBoundPackets.createPacketStorageDiskSizeRequest(id));

			syncTime.put(id, System.currentTimeMillis());
		}
	}

	private static StorageDiskSync INSTANCE;
	public static StorageDiskSync getInstance() {
		if (INSTANCE == null) INSTANCE = new StorageDiskSync();
		return INSTANCE;
	}
}
