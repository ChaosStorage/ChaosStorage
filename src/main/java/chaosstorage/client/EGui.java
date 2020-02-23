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

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import reborncore.api.blockentity.IMachineGuiHandler;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;

public enum EGui implements IMachineGuiHandler {
	CONTROLLER,
	STORAGE_BLOCK;

	private final boolean containerBuilder;

	EGui(final boolean containerBuilder) {
		this.containerBuilder = containerBuilder;
	}

	EGui() {
		this(true);
	}

	public static EGui byID(Identifier resourceLocation) {
		return Arrays.stream(values())
			.filter(eGui -> eGui.name().toLowerCase(Locale.ROOT).equals(resourceLocation.getPath()))
			.findFirst()
			.orElseThrow(() -> new RuntimeException("Failed to find gui for " + resourceLocation));
	}

	public static Stream<EGui> stream() {
		return Arrays.stream(values());
	}

	public Identifier getID() {
		return new Identifier("chaosstorage", name().toLowerCase(Locale.ROOT));
	}

	@Override
	public void open(PlayerEntity player, BlockPos pos, World world) {
		if (!world.isClient) {
			 ContainerProviderRegistry.INSTANCE.openContainer(getID(), player, packetByteBuf -> packetByteBuf.writeBlockPos(pos));
		}
	}

	public boolean useContainerBuilder() {
		return this.containerBuilder;
	}

}
