
package chaosstorage.blockentity;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import reborncore.client.containerBuilder.IContainerProvider;
import reborncore.client.containerBuilder.builder.BuiltContainer;
import reborncore.client.containerBuilder.builder.ContainerBuilder;
import reborncore.common.util.RebornInventory;
import reborncore.api.blockentity.InventoryProvider;

import chaosstorage.init.CSBlockEntities;
import chaosstorage.network.StorageBlockNode;
import chaosstorage.network.INetworkNodeProvider;

import java.util.UUID;

public class StorageBlockEntity extends NetworkMachineEntity<StorageBlockNode> implements InventoryProvider, INetworkNodeProvider, BlockEntityClientSerializable, IContainerProvider {
		//MachineBaseBlockEntity
	public static String NBT_UUID = "Uuid";

	public RebornInventory<StorageBlockEntity> inventory;
	public final String name;
	public final int capacity;
	private UUID uuid;
	private int clientStored;

	public StorageBlockEntity(int capacity, String name) {
		super(CSBlockEntities.STORAGE_BLOCK);
		this.name = name;
		this.capacity = capacity;
	}

	public StorageBlockEntity() {
		this(0, "");
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
		return new ContainerBuilder("storage")
				.player(player.inventory).inventory().hotbar().addInventory()
				.blockEntity(this) .sync(this::getStored, this::setStored) .addInventory()
				.create(this, syncID);
	}

	@Override
	public StorageBlockNode createNetworkNode() {
		return new StorageBlockNode(this);
	}

	@Override
	public void fromTag(CompoundTag nbttagcompound) {
		super.fromTag(nbttagcompound);
		setUUID(UUID.fromString(nbttagcompound.getString(NBT_UUID)));
	}

	@Override
	public CompoundTag toTag(CompoundTag nbttagcompound) {
		super.toTag(nbttagcompound);
		nbttagcompound.putString(NBT_UUID, getUUID().toString());
		return nbttagcompound;
	}

	@Override
	public void fromClientTag(CompoundTag tag) {
		fromTag(tag);
	}

	@Override
	public CompoundTag toClientTag(CompoundTag tag) {
		return toTag(tag);
	}

	public int getCapacity() {
		return capacity;
	}

	public int getStored() {
		if (world.isClient) {
			return clientStored;
		} else {
			return getNetworkNode().getDisk().getStored();
		}
	}

	public void setStored(int amount) {
		if (!world.isClient) return;
		clientStored = amount;
	}

	public UUID getUUID() {
		if (!world.isClient && uuid == null) setUUID(UUID.randomUUID());
		return uuid;
	}

	public void setUUID(UUID uuid) {
		this.uuid = uuid;
		if (hasWorld() && world.isClient) syncWithAll();
	}
}
