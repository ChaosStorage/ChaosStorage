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

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Direction;
import reborncore.api.blockentity.InventoryProvider;
import reborncore.client.containerBuilder.IContainerProvider;
import reborncore.client.containerBuilder.builder.BuiltContainer;
import reborncore.client.containerBuilder.builder.ContainerBuilder;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;
import reborncore.common.util.RebornInventory;
import team.reborn.energy.EnergyTier;

import chaosstorage.init.CSBlockEntities;
import chaosstorage.init.CSContent;
import chaosstorage.blocks.ControllerBlock;
import chaosstorage.config.ChaosStorageConfig;

public class ControllerEntity extends PowerAcceptorBlockEntity implements IContainerProvider, InventoryProvider {

	public RebornInventory<ControllerEntity> inventory;
	int ticksSinceLastChange;
	int EnergyPerTick;	

	public ControllerEntity() {
		super(CSBlockEntities.CONTROLLER);
		inventory = new RebornInventory<>(2, "controllerBlockEntity", 64, this);
		EnergyPerTick = ChaosStorageConfig.ControllerEngergyPerTick;
		checkTier(); //?

		//super(CSBlockEntities.Controller, "controller", 2, CSContent.Machine.CONTROLLER.block, EnergyTier.INFINITY, 40_000);
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
			boolean isActive = (getEnergy() > 0);
			controllerBlock.setActive(isActive, world, pos);
		}
	}

	// PowerAcceptorBlockEntity
	@Override
	public void tick() {
		super.tick();
		//charge(2);
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

		if (canUseEnergy(getEuPerTick(EnergyPerTick))) {
			useEnergy(getEuPerTick(EnergyPerTick));
		}


		// Enegry movment??
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

/*	// MachineBaseBlockEntity
	@Override
	public void setFacing(Direction enumFacing) {
		if (world == null) { return; }
		world.setBlockState(pos, world.getBlockState(pos).with(ControllerBlock.FACING, enumFacing));
	}

	@Override
	public Direction getFacingEnum() {
		if (world == null ) { return null; }
		Block block = world.getBlockState(pos).getBlock();
		if (block instanceof ControllerBlock) {
			return ((ControllerBlock) block).getFacing(world.getBlockState(pos));
		}
		return null;
	}

	@Override
	public boolean canBeUpgraded() {
		return false;
	}
*/
	// InventoryProvider
	@Override
	public RebornInventory<ControllerEntity> getInventory() {
		return inventory;
	}


	@Override
	public BuiltContainer createContainer(int syncID, final PlayerEntity player) {
		return new ContainerBuilder("controller").player(player.inventory).inventory().hotbar().addInventory()
			.blockEntity(this).energySlot(0, 62, 45).energySlot(1, 98, 45).syncEnergyValue().addInventory().create(this, syncID);
	}
}
