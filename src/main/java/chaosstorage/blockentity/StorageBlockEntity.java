
package chaosstorage.blockentity;

import net.minecraft.entity.player.PlayerEntity;
import reborncore.common.blockentity.MachineBaseBlockEntity;
import reborncore.client.containerBuilder.builder.BuiltContainer;
import reborncore.client.containerBuilder.builder.ContainerBuilder;
import reborncore.common.util.RebornInventory;
import reborncore.api.blockentity.InventoryProvider;

import chaosstorage.api.network.IStorage;
import chaosstorage.init.CSBlockEntities;
import chaosstorage.network.StorageNode;
import chaosstorage.network.INetworkNode;
import chaosstorage.network.INetworkNodeProvider;

public class StorageBlockEntity extends MachineBaseBlockEntity implements IStorage, InventoryProvider, INetworkNodeProvider { // ITick??

	public RebornInventory<StorageBlockEntity> inventory;
	public final String name;
	private final int maxStorage;
	private int usedSpace;

	private StorageNode node;

	public StorageBlockEntity(int maxStorage, String name) {
		super(CSBlockEntities.STORAGE_BLOCK);
		this.maxStorage = maxStorage;
		this.name = name;
		this.usedSpace = 0;
		this.node = new StorageNode(this, maxStorage);
	}

	// IStorage
	@Override
	public int getMaxSpace() {
		return this.maxStorage;
	}

	@Override
	public int getSpace() {
		return 512;
		//return this.usedSpace;
	}

	// InventoryProvider
	@Override
	public RebornInventory<StorageBlockEntity> getInventory() {
		return inventory;
	}

	// MachineBaseBlockEntity
	@Override
	public boolean canBeUpgraded() {
		return false;
	}

	// IContainerProvider
	public BuiltContainer createContainer(int syncID, final PlayerEntity player) {
		//return new ContainerBuilder(this.name).player(player.inventory).inventory().hotbar().addInventory()
		//	.blockEntity(this);
		return new ContainerBuilder("storage").player(player.inventory).inventory().hotbar().addInventory()
			.blockEntity(this).energySlot(0, 9, 15).syncEnergyValue().addInventory().create(this, syncID);
	}

	// INetworkNodeProvider
	public INetworkNode getNetworkNode() {
		return node;
	}

	@Override
	public void tick() {
		super.tick();
		node.tick();
	}

	@Override
	public void markRemoved() {
		super.markRemoved();
		node.markRemoved();
	}
}
