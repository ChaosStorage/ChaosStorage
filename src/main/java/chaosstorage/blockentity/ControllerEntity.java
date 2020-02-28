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
import chaosstorage.network.INetworkNode;
import chaosstorage.network.INetworkNodeProvider;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Direction;
import reborncore.api.blockentity.InventoryProvider;
import reborncore.client.containerBuilder.IContainerProvider;
import reborncore.client.containerBuilder.builder.BuiltContainer;
import reborncore.client.containerBuilder.builder.ContainerBuilder;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;
import reborncore.common.util.RebornInventory;
import team.reborn.energy.EnergySide;

import chaosstorage.init.CSBlockEntities;
import chaosstorage.block.ControllerBlock;
import chaosstorage.config.ChaosStorageConfig;

public class ControllerEntity extends PowerAcceptorBlockEntity implements IContainerProvider, InventoryProvider, INetworkNodeProvider {

	public RebornInventory<ControllerEntity> inventory;
	private int ticksSinceLastChange;
	//private int EnergyPerTick;
	private ControllerNode node;

	private double energy = 0;
	private boolean creative;

	public ControllerEntity(boolean creative) {
		super(creative ? CSBlockEntities.CREATIVE_CONTROLLER : CSBlockEntities.CONTROLLER);
		this.creative = creative;
		inventory = new RebornInventory<>(1, "ControllerEntity", 64, this);
		//EnergyPerTick = ChaosStorageConfig.ControllerEngergyPerTick;
		checkTier(); //?

		node = new ControllerNode(this);
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
			boolean isActive = canUseEnergy(getEuPerTick(node.getTotalEnergyUsage()));
			controllerBlock.setActive(isActive, world, pos);
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

		//if (isInvDirty()) {
			updateState();
		//}

		int EnergyPerTick = node.getTotalEnergyUsage();
		if (canUseEnergy(getEuPerTick(EnergyPerTick)) && !this.creative) {
			useEnergy(getEuPerTick(EnergyPerTick));
		}

		node.tick();
	}

	@Override
	public double getBaseMaxPower() {
		return ChaosStorageConfig.ControllerMaxPower;
	}

	@Override
	public boolean canAcceptEnergy(Direction direction) {
		return direction == null || getFacing() != direction;
	}

	@Override
	public boolean canProvideEnergy(Direction direction) {
		return false; // not providing energy
	}

	@Override
	public double getBaseMaxOutput() {
		return 0;
	}

	@Override
	public double getBaseMaxInput() {
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
		if (this.creative) {
			return this.getBaseMaxPower();
		}
		return super.getStored(face);
	}

	// InventoryProvider
	@Override
	public RebornInventory<ControllerEntity> getInventory() {
		return inventory;
	}

	@Override
	public BuiltContainer createContainer(int syncID, final PlayerEntity player) {
		return new ContainerBuilder("controller").player(player.inventory).inventory().hotbar().addInventory()
			.blockEntity(this).energySlot(0, 8, 72).syncEnergyValue().addInventory().create(this, syncID);
	}

	@Override
	public INetworkNode getNetworkNode() {
		return node;
	}
}
