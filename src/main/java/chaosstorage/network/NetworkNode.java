package chaosstorage.network;

import chaosstorage.blockentity.ControllerEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Stream;

public class NetworkNode implements INetworkNode {

	private IController controller;
	/* this implementation of INetworkNode assumes that the provider is a BlockEntity */
	private BlockEntity blockEntity;
	private boolean haveToNotifyController = true;

	public NetworkNode(BlockEntity blockEntity) {
		this.blockEntity = blockEntity;
	}

	@Override
	public void tick() {
		if (haveToNotifyController) {
			Optional<INetworkNode> neighbourWithController = getNeighbours().stream().filter(n -> n.getController() != null).findAny();
			if (neighbourWithController.isPresent()) neighbourWithController.get().getController().scan();
			haveToNotifyController = false;
		}
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
			if (e != null && e instanceof INetworkNodeProvider) {
				neighbours.add(((INetworkNodeProvider) e).getNetworkNode());
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
		System.out.println("adding node");
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
