package chaosstorage.network;

import chaosstorage.blockentity.ControllerEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;

public class NetworkNode implements INetworkNode {

    private IController controller;
    /* this implementation of INetworkNode assumes that the provider is a BlockEntity */
    private BlockEntity blockEntity;

    public NetworkNode(BlockEntity blockEntity) {
        this.blockEntity = blockEntity;
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
    public void adopt(IController controller) {
        this.controller = controller;
    }
}
