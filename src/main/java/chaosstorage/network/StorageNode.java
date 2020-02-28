
package chaosstorage.network;

import chaosstorage.blockentity.StorageBlockEntity;
import chaosstorage.storage.StorageDisk;
import chaosstorage.storage.IStorageDisk;

import java.util.ArrayList;

//import chaosstorage.ChaosStorageConfig;

public class StorageNode extends NetworkNode implements IStorageNode {
	private StorageBlockEntity blockEntity;
	private StorageDisk disk;

	private int maxStorage;

	public StorageNode(StorageBlockEntity blockentity, int maxStorage) {
		super(blockentity);
		this.blockEntity = blockentity;
		this.maxStorage = maxStorage;
		this.disk = new StorageDisk(maxStorage);
	}

	// INetworkNode
	@Override
	public int getEnergyUsage() {
		return 10; //TODO: Config
		// TODO: per byte usage
	}

	@Override
	public int getMaxStorage() {
		int storage = 0;
		for (IStorageDisk disk: this.getDisks()) {
			storage += disk.getMaxSpace();
		}
		return storage;
	}

	@Override
	public int getStorage() {
		int storage = 0;
		for (IStorageDisk disk: this.getDisks()) {
			storage += disk.getSpace();
		}
		return storage;
	}

	@Override
	public ArrayList<IStorageDisk> getDisks() {
		ArrayList<IStorageDisk> disks = new ArrayList<IStorageDisk>();
		disks.add(this.disk);
		return disks;
	}
}
