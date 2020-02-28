package chaosstorage.network;

import chaosstorage.blockentity.ControllerEntity;
import chaosstorage.config.ChaosStorageConfig;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;

public class ControllerNode extends NetworkNode implements IController {
		private ArrayList<INetworkNode> networkMembers = new ArrayList<INetworkNode>();

		private ControllerEntity blockEntity;
		private boolean scanQueued = false;
		//private static final int baseEnergyUsage = ChaosStorageConfig.ControllerEngergyPerTick;
		private int energyUsage;

		public ControllerNode(ControllerEntity blockEntity) {
				super(blockEntity);
				this.blockEntity = blockEntity;
				this.energyUsage = ChaosStorageConfig.ControllerEngergyPerTick;
				scan();
				System.out.println("queued scan");
		}

		public void scan() {
				scanQueued = true;
		}

		public void tick() {
				if (scanQueued) doScan();
		}

		public int addEnergy(int usage) {
			System.out.print("usage add: " + this.getTotalEnergyUsage() + " + " + usage + " = " );
			this.energyUsage = this.getTotalEnergyUsage() + usage;
			System.out.println(this.energyUsage);
			return this.getTotalEnergyUsage();
		}

		public int getTotalEnergyUsage() {
			return this.energyUsage;
		}

		public void resetEnergyUsage() {
			this.energyUsage = 0;
		}

		private void doScan() {
				System.out.println("running scan");
				this.resetEnergyUsage();
				networkMembers.clear();
				Queue<INetworkNode> scanQueue = new PriorityQueue<INetworkNode>();
				scanQueue.add(this);
				while (!scanQueue.isEmpty()) {
						INetworkNode current = scanQueue.remove();
						System.out.println("adding node");
						networkMembers.add(current);
						current.adopt(this);
						this.addEnergy(current.getEnergyUsage());
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

		@Override
		public	int getEnergyUsage() {
				return ChaosStorageConfig.ControllerEngergyPerTick;
		}
}
