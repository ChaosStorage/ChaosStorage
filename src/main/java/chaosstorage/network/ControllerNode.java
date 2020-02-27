package chaosstorage.network;

import chaosstorage.blockentity.ControllerEntity;
import chaosstorage.network.IController;
import chaosstorage.network.INetworkNode;
import chaosstorage.network.NetworkNode;
import net.minecraft.block.entity.BlockEntity;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;

public class ControllerNode extends NetworkNode implements IController {
    private ArrayList<INetworkNode> networkMembers = new ArrayList<INetworkNode>();

    private ControllerEntity blockEntity;
    private boolean scanQueued = false;

    public ControllerNode(ControllerEntity blockEntity) {
        super(blockEntity);
        this.blockEntity = blockEntity;
        scan();
        System.out.println("queued scan");
    }

    public void scan() {
        scanQueued = true;
    }

    public void tick() {
        if (scanQueued) doScan();
    }

    private void doScan() {
        System.out.println("running scan");
        networkMembers.clear();
        Queue<INetworkNode> scanQueue = new PriorityQueue<INetworkNode>();
        scanQueue.add(this);
        while (!scanQueue.isEmpty()) {
            INetworkNode current = scanQueue.remove();
            System.out.println("adding node");
            networkMembers.add(current);
            current.adopt(this);
            for (INetworkNode neighbour : current.getNeighbours()) {
                if (networkMembers.contains(neighbour) || scanQueue.contains(neighbour))
                    continue;

                scanQueue.add(neighbour);
            }
        }
        scanQueued = false;
    }

    @Override
    public void removeNode(INetworkNode node) {
        networkMembers.remove(node);
        scan(); // TODO: make sure the block is not addded again
    }

    @Override
    public ControllerEntity getControllerEntity() {
        return blockEntity;
    }
}
