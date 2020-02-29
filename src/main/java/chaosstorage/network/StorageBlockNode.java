
package chaosstorage.network;

import chaosstorage.blockentity.StorageBlockEntity;
import chaosstorage.storage.IStorage;
import chaosstorage.storage.StorageDisk;
import chaosstorage.storage.IStorageDisk;

import java.util.ArrayList;

//import chaosstorage.ChaosStorageConfig;

public class StorageBlockNode extends NetworkNode implements IStorageNode {
	private StorageBlockEntity blockEntity;
	private StorageDisk disk;

	public StorageBlockNode(StorageBlockEntity blockentity, int maxStorage) {
		super(blockentity);
		this.blockEntity = blockentity;
		this.disk = new StorageDisk(maxStorage);
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

	public StorageDisk getDisk() {
		return disk;
	}
}
