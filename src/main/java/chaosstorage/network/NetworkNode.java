package chaosstorage.network;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.Optional;

public abstract class NetworkNode implements INetworkNode {

	private IController controller;
	/* this implementation of INetworkNode assumes that the provider is a BlockEntity */
	private BlockEntity blockEntity;

	public NetworkNode(BlockEntity blockEntity) {
		this.blockEntity = blockEntity;
	}

	@Override
	public void initiateScan() {
		if (!blockEntity.hasWorld()) {
			System.out.println("blockEntity has no world, refusing to initiate scan!");
			return;
		}
		Optional<INetworkNode> neighbourWithController = getNeighbours().stream().filter(n -> n.getController() != null).findAny();
		if (neighbourWithController.isPresent()) neighbourWithController.get().getController().scan();
	}

	@Override
	public void tick() {
	}

	@Override
	public void markRemoved() {
		if (controller != null) {
			controller.scan();
		}
	}

	@Override
	public void unadopt() {
		controller = null;
	}

	public Direction[] getConnectionDirections() {
		return Direction.values();
	}

	@Override
	public ArrayList<INetworkNode> getNeighbours() {
		ArrayList<INetworkNode> neighbours = new ArrayList<INetworkNode>();
		for (Direction d : getConnectionDirections()) {
			BlockPos pos = blockEntity.getPos().offset(d);
			BlockEntity e = blockEntity.getWorld().getBlockEntity(pos);
			if (e instanceof INetworkNodeProvider) {
				INetworkNode n = ((INetworkNodeProvider) e).getNetworkNode();
				if (n != null) neighbours.add(n);
			}
		}
		return neighbours;
	}

	@Override
	public IController getController() {
		return controller;
	}

	@Override
	public void adopt(IController controller, ArrayList<INetworkNode> networkMembers) {
		networkMembers.add(this);
		this.controller = controller;
		for (INetworkNode neighbour : getNeighbours()) {
			if (networkMembers.contains(neighbour)) {
				continue;
			}

			neighbour.adopt(controller, networkMembers);
		}
	}

}
