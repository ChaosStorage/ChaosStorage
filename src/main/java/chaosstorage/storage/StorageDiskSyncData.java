package chaosstorage.storage;

public class StorageDiskSyncData {
	private int capacity;
	private int stored;

	public StorageDiskSyncData(int capacity, int stored) {
		this.capacity = capacity;
		this.stored = stored;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public int getStored() {
		return this.stored;
	}
}
