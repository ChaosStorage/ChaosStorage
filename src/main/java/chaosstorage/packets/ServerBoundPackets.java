package chaosstorage.packets;

import static chaosstorage.ChaosStorage.CS;

import chaosstorage.ChaosStorage;
import chaosstorage.storage.IStorageDisk;
import chaosstorage.storage.StorageDiskManager;
import net.fabricmc.fabric.api.network.PacketContext;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.Packet;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import reborncore.common.network.ExtendedPacketBuffer;
import reborncore.common.network.NetworkManager;

import java.util.function.BiConsumer;
import java.util.UUID;

public class ServerBoundPackets {
	public static void init() {
	}

	private static void registerPacketHandler(Identifier identifier, BiConsumer<ExtendedPacketBuffer, PacketContext> consumer){
		ServerSidePacketRegistry.INSTANCE.register(identifier, (packetContext, packetByteBuf) ->
				consumer.accept(new ExtendedPacketBuffer(packetByteBuf), packetContext));
	}
}
