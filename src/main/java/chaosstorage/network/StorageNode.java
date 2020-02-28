
package chaosstorage.network;

import chaosstorage.blockentity.StorageBlockEntity;
import chaosstorage.block.StorageBlock;
//import chaosstorage.ChaosStorageConfig;

public class StorageNode extends NetworkNode {
	private StorageBlockEntity blockEntity;

	public StorageNode(StorageBlockEntity blockentity) {
		super(blockentity);
		this.blockEntity = blockentity;
	}

	// INetworkNode
	@Override
	public int getEnergyUsage() {
		return 10; //TODO: Config
		// TODO: per byte usage
	}
}
