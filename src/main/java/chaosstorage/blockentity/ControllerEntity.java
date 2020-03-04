/*
 * This file is part of ChaosStorage, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2020 ChaosStorage
 * Copyright (c) 2020 TechReborn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package chaosstorage.blockentity;

import chaosstorage.network.ControllerNode;
import chaosstorage.network.INetworkNodeProvider;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;
import reborncore.api.IListInfoProvider;
import reborncore.api.blockentity.InventoryProvider;
import reborncore.client.containerBuilder.IContainerProvider;
import reborncore.client.containerBuilder.builder.BuiltContainer;
import reborncore.client.containerBuilder.builder.ContainerBuilder;
import reborncore.common.util.RebornInventory;
import team.reborn.energy.Energy;
import team.reborn.energy.EnergySide;

import chaosstorage.init.CSBlockEntities;
import chaosstorage.block.ControllerBlock;
import chaosstorage.config.ChaosStorageConfig;
import team.reborn.energy.EnergyStorage;
import team.reborn.energy.EnergyTier;

public class ControllerEntity extends NetworkMachineEntity<ControllerNode> implements IContainerProvider, InventoryProvider, INetworkNodeProvider, EnergyStorage, IListInfoProvider {
	private static final String NBT_CREATIVE = "Creative";
	public RebornInventory<ControllerEntity> inventory;
	private int ticksSinceLastChange;
	//private int EnergyPerTick;

	private double energy = 0;
	private boolean creative;
	private EnergyTier energyTier = EnergyTier.INSANE;

	public ControllerEntity(boolean creative) {
		super(CSBlockEntities.CONTROLLER);
		this.creative = creative;
		inventory = new RebornInventory<>(1, "ControllerEntity", 64, this);
		//EnergyPerTick = ChaosStorageConfig.ControllerEngergyPerTick;
	}

	public ControllerEntity() {
		this(false);
	}

	public void setInvDirty(boolean isDirty) {
		 inventory.setChanged(isDirty);
	}

	public boolean isInvDirty() {
		return inventory.hasChanged();
	}

	public void updateState() {
		Block block = getWorld().getBlockState(pos).getBlock();

		if (block instanceof ControllerBlock) {
			ControllerBlock controllerBlock = (ControllerBlock) block;
			boolean isActive = canUseEnergy(getEuPerTick(getNetworkNode().getTotalEnergyUsage()));
			controllerBlock.setActive(isActive, world, pos);
		}

		world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
	}

	public boolean canUseEnergy(double input) {
		return input <= getEnergy();
	}
	public double useEnergy(double energy) {
		return useEnergy(energy, false);
	}

	public double useEnergy(double extract, boolean simulate) {
		if (extract > energy) {
			extract = energy;
		}
		if (!simulate) {
			setStored(energy - extract);
		}
		return extract;
	}

	public double getFreeSpace() {
		return getMaxPower() - getEnergy();
	}

	public void charge(int slot) {
		if (world.isClient) {
			return;
		}

		double chargeEnergy = Math.min(getFreeSpace(), getMaxInput(EnergySide.UNKNOWN));
		if (chargeEnergy <= 0.0) {
			return;
		}
		if (!getOptionalInventory().isPresent()) {
			return;
		}
		ItemStack batteryStack = getOptionalInventory().get().getInvStack(slot);
		if (batteryStack.isEmpty()) {
			return;
		}

		if (Energy.valid(batteryStack)) {
			Energy.of(batteryStack)
					.into(
							Energy
									.of(this)
					)
					.move();
		}

	}

	// PowerAcceptorBlockEntity
	@Override
	public void tick() {
		super.tick();
		charge(0);
		if (world.isClient) {
			return;
		}

		ticksSinceLastChange++;

		if (ticksSinceLastChange == 20) {
			setInvDirty(true);
			ticksSinceLastChange = 0;
		}

		if (isInvDirty()) {
			updateState();
		}

		int EnergyPerTick = getNetworkNode().getTotalEnergyUsage();
		if (canUseEnergy(getEuPerTick(EnergyPerTick)) && !this.creative) {
			useEnergy(getEuPerTick(EnergyPerTick));
		}
	}

	public double getMaxPower() {
		return ChaosStorageConfig.ControllerMaxPower;
	}

	public boolean canAcceptEnergy(Direction direction) {
		return direction == null || getFacing() != direction;
	}

	public boolean canProvideEnergy(Direction direction) {
		return false; // not providing energy
	}

	public double getMaxOutput() {
		return 0;
	}

	public double getMaxInput() {
		return ChaosStorageConfig.ControllerMaxInput;
	}

	@Override
	public boolean canBeUpgraded() {
		return false;
	}

	@Override
	public boolean hasSlotConfig() {
		return false;
	}

	@Override
	public double getStored(EnergySide face) {
		return getEnergy();
	}

	public double getEnergy() {
		if (this.creative) {
			return this.getMaxPower();
		}
		return energy;
	}

	@Override
	public void setStored(double amount) {
		energy = amount;
	}

	// InventoryProvider
	@Override
	public RebornInventory<ControllerEntity> getInventory() {
		return inventory;
	}

	@Override
	public BuiltContainer createContainer(int syncID, final PlayerEntity player) {
		return new ContainerBuilder("controller").player(player.inventory).inventory().hotbar().addInventory()
				.blockEntity(this)
				.energySlot(0, 8, 72)
				.sync(this::getEnergy, this::setStored)
				.addInventory()
				.create(this, syncID);
	}

	@Override
	public ControllerNode createNetworkNode() {
		return new ControllerNode(this);
	}

	@Override
	public double getMaxStoredPower() {
		return getMaxPower();
	}

	@Override
	public void markRemoved() {
		getNetworkNode().disconnectAll();
	}

	@Override
	public EnergyTier getTier() {
		return energyTier;
	}

	@Override
	public void fromTag(CompoundTag tag) {
		super.fromTag(tag);
		this.creative = tag.getBoolean(NBT_CREATIVE);
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		super.toTag(tag);
		tag.putBoolean(NBT_CREATIVE, this.creative);
		return tag;
	}
}
