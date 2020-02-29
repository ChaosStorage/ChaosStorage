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

package chaosstorage.client.gui;

import chaosstorage.network.IStorageNode;
import chaosstorage.network.StorageBlockNode;
import chaosstorage.storage.IStorageDisk;
import chaosstorage.storage.StorageDisk;
import net.minecraft.entity.player.PlayerEntity;
import reborncore.client.containerBuilder.builder.BuiltContainer;
import reborncore.client.gui.builder.GuiBase;

//import chaosstorage.blockentity.StorageBlockEntity;
import chaosstorage.blockentity.StorageBlockEntity;
import chaosstorage.client.gui.guibuilder.GuiBuilder;


public class GuiStorageBlock extends GuiBase<BuiltContainer> {
	
	public GuiBuilder builder = new GuiBuilder();

	StorageBlockEntity blockEntity;

	public GuiStorageBlock(int syncID, final PlayerEntity player, final StorageBlockEntity storageBlock) {
		super(player, storageBlock, storageBlock.createContainer(syncID, player));
		this.blockEntity = storageBlock;
	}

	@Override
	public void init() {
		super.init();
	}

	@Override
	protected void drawBackground(float partialTicks, int mouseX, int mouseY) {
		super.drawBackground(partialTicks, mouseX, mouseY);
		final Layer layer = Layer.BACKGROUND;

		drawSlot(9, 15, layer);

	}

	@Override
	protected void drawForeground(final int mouseX, final int mouseY) {
		super.drawForeground(mouseX, mouseY);
		final Layer layer = Layer.FOREGROUND;

		StorageBlockNode node = (StorageBlockNode) blockEntity.getNetworkNode();
		IStorageDisk disk = (IStorageDisk) node.getDisk();
		System.out.println(disk);

		builder.drawStorageBar(this, 9, 39, disk.getSpace(), disk.getMaxSpace(), mouseX, mouseY, 0, layer);
	}
}

