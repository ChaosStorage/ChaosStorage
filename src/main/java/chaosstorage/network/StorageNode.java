
package chaosstorage.network;

import chaosstorage.blockentity.StorageBlockEntity;
import chaosstorage.block.StorageBlock;
//import chaosstorage.ChaosStorageConfig;

public class StorageNode extends NetworkNode implements IStorageNode {
	private StorageBlockEntity blockEntity;

	private int maxStorage;

	public StorageNode(StorageBlockEntity blockentity, int maxStorage) {
		super(blockentity);
		this.blockEntity = blockentity;
		this.maxStorage = maxStorage;
	}

	// INetworkNode
	@Override
	public int getEnergyUsage() {
		return 10; //TODO: Config
		// TODO: per byte usage
	}

	@Override
	public int getMaxStorage() {
		return maxStorage;
	}
}
