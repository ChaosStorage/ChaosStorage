package chaosstorage.blockentity;

import chaosstorage.network.INetworkNode;
import chaosstorage.network.INetworkNodeProvider;
import chaosstorage.network.NetworkNode;
import net.minecraft.block.entity.BlockEntityType;
import reborncore.common.blockentity.MachineBaseBlockEntity;

public abstract class NetworkMachineEntity<N extends NetworkNode> extends MachineBaseBlockEntity implements INetworkNodeProvider {
	public NetworkMachineEntity(BlockEntityType<?> type) {
		super(type);
	}

	private N node;

	public abstract N createNetworkNode();

	@Override
	public N getNetworkNode() {
		if (node == null) node = createNetworkNode();
		return node;
	}

	@Override
	public void tick() {
		if (!world.isClient) getNetworkNode().tick();
	}

	@Override
	public void markRemoved() {
		super.markRemoved();
		if (!world.isClient) getNetworkNode().markRemoved();
	}
}
