package chaosstorage.network;

import chaosstorage.api.network.IStorage;
import chaosstorage.blockentity.ControllerEntity;
import chaosstorage.config.ChaosStorageConfig;

import java.lang.reflect.Array;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Stack;

public class ControllerNode extends NetworkNode implements IController {
	private ArrayList<INetworkNode> networkMembers = new ArrayList<INetworkNode>();
	private ArrayList<IStorageNode> storageMembers = new ArrayList<IStorageNode>();

	private ControllerEntity blockEntity;
	private boolean scanQueued = false;
	private int totalEnergyUsage;

	public ControllerNode(ControllerEntity blockEntity) {
		super(blockEntity);
		this.blockEntity = blockEntity;
		this.totalEnergyUsage = ChaosStorageConfig.ControllerEngergyPerTick;
		scan();
	}

	public void scan() {
		System.out.println("queued scan");
		scanQueued = true;
	}

	@Override
	public void tick() {
		super.tick();
		if (scanQueued) doScan();
	}

	public int getTotalEnergyUsage() {
		return this.totalEnergyUsage;
	}

	@Override
	public void removeNode(INetworkNode node) {
		scan();
	}

	@Override
	public ControllerEntity getControllerEntity() {
		return blockEntity;
	}

	@Override
	public int getEnergyUsage() {
		return ChaosStorageConfig.ControllerEngergyPerTick;
	}

	public void doScan() {
		scanQueued = false;
		System.out.println("running scan");

		networkMembers.clear();
		totalEnergyUsage = 0;
		totalStorage = 0;

		adopt(this, networkMembers);
		boolean invalid = networkMembers.stream().anyMatch(n -> n != this && n instanceof IController);
		for (INetworkNode current : networkMembers) {
			// TODO: do something with invalid

			this.totalEnergyUsage += current.getEnergyUsage();
			if (current instanceof IStorageNode) {
				storageMembers.add((IStorageNode) current);
				this.totalStorage += storage.getMaxStorage();
			}
		}
	}
}
