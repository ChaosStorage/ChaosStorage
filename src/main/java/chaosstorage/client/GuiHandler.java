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

package chaosstorage.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.ingame.ContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import reborncore.RebornCore;
import reborncore.client.containerBuilder.IContainerProvider;

import chaosstorage.blockentity.ControllerEntity;
import chaosstorage.blockentity.StorageBlockEntity;
import chaosstorage.client.gui.*;


public class GuiHandler {
	public static void register() {
		EGui.stream().forEach(gui -> ContainerProviderRegistry.INSTANCE.registerFactory(gui.getID(), (syncID, identifier, playerEntity, packetByteBuf) -> {
			final BlockEntity blockEntity = playerEntity.world.getBlockEntity(packetByteBuf.readBlockPos());
			return ((IContainerProvider) blockEntity).createContainer(syncID, playerEntity);
		}));

		RebornCore.clientOnly(() -> () -> EGui.stream().forEach(gui -> ScreenProviderRegistry.INSTANCE.registerFactory(gui.getID(), (syncID, identifier, playerEntity, packetByteBuf) -> getClientGuiElement(EGui.byID(identifier), playerEntity, packetByteBuf.readBlockPos(), syncID))));
	}

	@Environment(EnvType.CLIENT)
	private static ContainerScreen<?> getClientGuiElement(final EGui gui, final PlayerEntity player, BlockPos pos, int syncID) {
		final BlockEntity blockEntity = player.world.getBlockEntity(pos);

		/*if (blockEntity instanceof DataDrivenBEProvider.DataDrivenBlockEntity) {
			return new DataDrivenGui(syncID, player, (DataDrivenBEProvider.DataDrivenBlockEntity) blockEntity);
		}*/

		switch (gui) {
			case CONTROLLER:
				return new GuiController(syncID, player, (ControllerEntity) blockEntity);
			case STORAGE_BLOCK:
				return new GuiStorageBlock(syncID, player, (StorageBlockEntity) blockEntity);
			default:
				break;
		}
		return null;
	}
}
