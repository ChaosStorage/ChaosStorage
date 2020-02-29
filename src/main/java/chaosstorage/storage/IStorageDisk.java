package chaosstorage.storage;

public interface IStorageDisk extends IStorage {
	public int getStored();
	public int getCapacity();
	public default int getFreeSpace() {
		return getCapacity() - getStored();
	}
}
