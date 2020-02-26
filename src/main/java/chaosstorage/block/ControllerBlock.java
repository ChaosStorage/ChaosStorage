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

package chaosstorage.block;


import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import reborncore.api.ToolManager;
import reborncore.api.blockentity.IMachineGuiHandler;
import reborncore.common.BaseBlockEntityProvider;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;
import reborncore.common.util.ItemHandlerUtils;
import reborncore.common.util.WrenchUtils;

import chaosstorage.client.EGui;
import chaosstorage.blockentity.ControllerEntity;

public class ControllerBlock extends BaseBlockEntityProvider {
	public String name;
	public static BooleanProperty ACTIVE; // = BooleanProperty.of("active");
	public IMachineGuiHandler gui;

	public ControllerBlock(boolean _creative) {
		super(FabricBlockSettings.of(Material.METAL).strength(2f, 2f).build());
		this.setDefaultState(this.getStateManager().getDefaultState().with(ACTIVE, false));
		this.name = "controller";
		this.gui = EGui.CONTROLLER;

		//super ("low_volt...", EGui.LOW_VOLT...);
	}

	public static boolean isActive(BlockState state) {
		return state.get(ACTIVE);
	}

	public static void setActive(Boolean active, World world, BlockPos pos) {
		BlockState state = world.getBlockState(pos).with(ACTIVE, active);
		world.setBlockState(pos, state, 1);
	}

	// BaseBlockEntityProvider
	@Override
	public void onPlaced(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		super.onPlaced(worldIn, pos, state, placer, stack);
	}

	// Block
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		ACTIVE = BooleanProperty.of("active");
		builder.add(ACTIVE);
	}

	@SuppressWarnings("deprecation")
	@Override
	public ActionResult onUse(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockHitResult hitResult) {
		ItemStack stack = playerIn.getStackInHand(Hand.MAIN_HAND);
		BlockEntity blockEntity = worldIn.getBlockEntity(pos);

		// We extended BlockTileBase. Thus we should always have blockEntity entity. I hope.
		if (blockEntity == null) {
			return ActionResult.FAIL;
		}

		if (!stack.isEmpty() && ToolManager.INSTANCE.canHandleTool(stack)) {
			if (WrenchUtils.handleWrench(stack, worldIn, pos, playerIn, hitResult.getSide())) {
				return ActionResult.SUCCESS;
			}
		}

		if (!playerIn.isSneaking() && gui != null) {
			gui.open(playerIn, pos, worldIn);
			return ActionResult.SUCCESS;
		}

		return super.onUse(state, worldIn, pos, playerIn, hand, hitResult);
	}


	@SuppressWarnings("deprecation")
	@Override
	public void onBlockRemoved(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			ItemHandlerUtils.dropContainedItems(worldIn, pos);
			super.onBlockRemoved(state, worldIn, pos, newState, isMoving);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean hasComparatorOutput(BlockState state) {
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return PowerAcceptorBlockEntity.calculateComparatorOutputFromEnergy(world.getBlockEntity(pos));
	}

	@Override
	public BlockEntity createBlockEntity(BlockView worldIn) {
		return new ControllerEntity();
	}
}
