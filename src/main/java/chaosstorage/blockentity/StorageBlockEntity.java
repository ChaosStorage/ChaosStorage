
package chaosstorage.blockentity;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import reborncore.common.blockentity.MachineBaseBlockEntity;
import reborncore.client.containerBuilder.builder.BuiltContainer;
import reborncore.client.containerBuilder.builder.ContainerBuilder;

import chaosstorage.api.network.IStorage;
import chaosstorage.init.CSBlockEntities;

public class StorageBlockEntity extends MachineBaseBlockEntity implements IStorage { // ITick??

	public final String name;
	private final int maxStorage;
	private int usedSpace;

	public StorageBlockEntity(int maxStorage, String name) {
		super(CSBlockEntities.STORAGE_BLOCK);
		this.maxStorage = maxStorage;
		this.name = name;
		this.usedSpace = 0;
	}

	// IStorage
	@Override
	public int getMaxSpace() {
		return this.maxStorage;
	}

	@Override
	public int getSpace() {
		return this.usedSpace;
	}

	// MachineBaseBlockEntity
	@Override
        public boolean canBeUpgraded() {
                return false;
        }

	public BuiltContainer createContainer(int syncID, final PlayerEntity player) {
		//return new ContainerBuilder(this.name).player(player.inventory).inventory().hotbar().addInventory()
		//	.blockEntity(this);
		return new ContainerBuilder("controller").player(player.inventory).inventory().hotbar().addInventory()
			.blockEntity(this).energySlot(0, 8, 72).syncEnergyValue().addInventory().create(this, syncID);
	}
}	
