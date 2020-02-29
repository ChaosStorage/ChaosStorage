
package chaosstorage.blockentity;

import net.minecraft.entity.player.PlayerEntity;
import reborncore.common.blockentity.MachineBaseBlockEntity;
import reborncore.client.containerBuilder.builder.BuiltContainer;
import reborncore.client.containerBuilder.builder.ContainerBuilder;
import reborncore.common.util.RebornInventory;
import reborncore.api.blockentity.InventoryProvider;

import chaosstorage.init.CSBlockEntities;
import chaosstorage.network.StorageNode;
import chaosstorage.network.INetworkNode;
import chaosstorage.network.INetworkNodeProvider;

public class StorageBlockEntity extends MachineBaseBlockEntity implements InventoryProvider, INetworkNodeProvider { // ITick??

	public RebornInventory<StorageBlockEntity> inventory;
	public final String name;
	private StorageNode node;

	public StorageBlockEntity(int maxStorage, String name) {
		super(CSBlockEntities.STORAGE_BLOCK);
		this.name = name;
		this.node = new StorageNode(this, maxStorage);
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
