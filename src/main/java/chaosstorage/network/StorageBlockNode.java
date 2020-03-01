
package chaosstorage.network;

import chaosstorage.blockentity.StorageBlockEntity;
import chaosstorage.storage.IStorage;
import chaosstorage.storage.StorageDisk;
import chaosstorage.storage.IStorageDisk;
import chaosstorage.storage.StorageDiskManager;

import java.util.ArrayList;
import java.util.UUID;

//import chaosstorage.ChaosStorageConfig;

public class StorageBlockNode extends NetworkNode implements IStorageNode {
	private IStorageDisk disk;

	public StorageBlockNode(StorageBlockEntity blockentity) {
		super(blockentity);
		this.disk = StorageDiskManager.getInstance(blockentity.getWorld()).getDiskOrNew(blockentity.getUUID(), blockentity.getCapacity());
	}

	// INetworkNode
	@Override
	public int getEnergyUsage() {
		return 10; //TODO: Config
		// TODO: per byte usage
	}

	@Override
	public ArrayList<IStorage> getStorages() {
		ArrayList<IStorage> storages = new ArrayList<IStorage>();
		storages.add(disk);
		return storages;
	}

	public IStorageDisk getDisk() {
		return disk;
	}
}
