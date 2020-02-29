package chaosstorage.network;

import chaosstorage.blockentity.ControllerEntity;
import chaosstorage.config.ChaosStorageConfig;
import chaosstorage.storage.IStorage;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ControllerNode extends NetworkNode implements IController {
	private ArrayList<INetworkNode> networkMembers = new ArrayList<INetworkNode>();

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

		adopt(this, networkMembers);
		boolean invalid = networkMembers.stream().anyMatch(n -> n != this && n instanceof IController);
		for (INetworkNode current : networkMembers) {
			// TODO: do something with invalid

			this.totalEnergyUsage += current.getEnergyUsage();
			if (current instanceof IStorageNode) {
				IStorageNode storage = (IStorageNode) current;
			}
		}
	}

	public ArrayList<IStorageNode> getStorageNodes() {
		return getNetworkNodes().stream()
				.filter(n -> n instanceof IStorageNode)
				.map(n -> (IStorageNode) n)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public ArrayList<IStorage> getStorages() {
		return getStorageNodes().stream()
				.flatMap(n -> n.getStorages().stream())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public ItemStack insert(ItemStack stack) {
		ArrayList<IStorage> storages = getStorages();
		ItemStack retStack = stack;
		for (IStorage storage : storages) {
			if (retStack.isEmpty()) {
				return retStack;
			}
			System.out.println(storage);
			retStack = storage.insert(retStack);
		}
		return retStack;
	}
}
