package chaosstorage.network;

import chaosstorage.blockentity.ControllerEntity;
import chaosstorage.config.ChaosStorageConfig;

import java.util.ArrayList;

public class ControllerNode extends NetworkNode implements IController {
	private ArrayList<INetworkNode> networkMembers = new ArrayList<INetworkNode>();
	private ArrayList<IStorageNode> storageMembers = new ArrayList<IStorageNode>();

	private ControllerEntity blockEntity;
	private boolean scanQueued = false;
	private int totalEnergyUsage;
	private int totalStorage;

	public ControllerNode(ControllerEntity blockEntity) {
		super(blockEntity);
		this.blockEntity = blockEntity;
		this.totalEnergyUsage = ChaosStorageConfig.ControllerEngergyPerTick;
		scan();
	}

	public void scan() {
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
	public ArrayList<INetworkNode> getNetworkNodes() {
		return networkMembers;
	}

	@Override
	public int getEnergyUsage() {
		return ChaosStorageConfig.ControllerEngergyPerTick;
	}

	public void doScan() {
		System.out.println("scanning");
		scanQueued = false;

		for (INetworkNode current : networkMembers) {
			current.unadopt();
		}

		networkMembers.clear();
		totalEnergyUsage = 0;
		totalStorage = 0;

		adopt(this, networkMembers);
		boolean invalid = networkMembers.stream().anyMatch(n -> n != this && n instanceof IController);
		for (INetworkNode current : networkMembers) {
			// TODO: do something with invalid

			this.totalEnergyUsage += current.getEnergyUsage();
			if (current instanceof IStorageNode) {
				IStorageNode storage = (IStorageNode) current;
				storageMembers.add(storage);
				this.totalStorage += storage.getMaxStorage();
			}
		}
	}
}
