package chaosstorage.blockentity;

import chaosstorage.network.CableNode;
import chaosstorage.network.INetworkNode;
import chaosstorage.network.INetworkNodeProvider;
import chaosstorage.network.NetworkNode;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Tickable;

public abstract class NetworkEntity<N extends NetworkNode> extends BlockEntity implements INetworkNodeProvider, Tickable {
	public NetworkEntity(BlockEntityType<?> type) {
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
