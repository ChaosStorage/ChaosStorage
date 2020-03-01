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
	public static final Identifier STORAGE_DISK_SIZE_REQUEST = new Identifier(ChaosStorage.MOD_ID, "storage_disk_size_request");

	public static void init() {
		registerPacketHandler(STORAGE_DISK_SIZE_REQUEST, (extendedPacketBuffer, packetContext) -> {
			UUID uuid = extendedPacketBuffer.readUuid();
			packetContext.getTaskQueue().execute(() -> {
				ServerPlayerEntity p = (ServerPlayerEntity) packetContext.getPlayer();
				System.out.println("query " + uuid);
				IStorageDisk disk = StorageDiskManager.getInstance(p.getEntityWorld()).getDisk(uuid);
				NetworkManager.sendToPlayer(ClientBoundPackets.createPacketStorageDiskSizeResponse(uuid, disk.getCapacity(), disk.getStored()), p);
			});
		});
	}

	private static void registerPacketHandler(Identifier identifier, BiConsumer<ExtendedPacketBuffer, PacketContext> consumer){
		ServerSidePacketRegistry.INSTANCE.register(identifier, (packetContext, packetByteBuf) ->
				consumer.accept(new ExtendedPacketBuffer(packetByteBuf), packetContext));
	}

	public static Packet<ServerPlayPacketListener> createPacketStorageDiskSizeRequest(UUID uuid) {
		return NetworkManager.createServerBoundPacket(STORAGE_DISK_SIZE_REQUEST, extendedPacketBuffer -> {
			extendedPacketBuffer.writeUuid(uuid);
		});
	}
}
