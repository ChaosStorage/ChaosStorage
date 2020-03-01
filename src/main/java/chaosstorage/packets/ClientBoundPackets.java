package chaosstorage.packets;

import chaosstorage.ChaosStorage;
import chaosstorage.storage.StorageDiskSync;
import chaosstorage.storage.StorageDiskSyncData;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.util.Identifier;
import reborncore.common.network.ExtendedPacketBuffer;
import reborncore.common.network.NetworkManager;

import java.util.UUID;
import java.util.function.BiConsumer;

import static chaosstorage.ChaosStorage.CS;

public class ClientBoundPackets {
	public static final Identifier STORAGE_DISK_SIZE_RESPONSE = new Identifier(ChaosStorage.MOD_ID, "storage_disk_size_response");
	public static void init() {
		registerPacketHandler(STORAGE_DISK_SIZE_RESPONSE, (extendedPacketBuffer, packetContext) -> {
			UUID uuid = extendedPacketBuffer.readUuid();
			int capacity = extendedPacketBuffer.readInt();
			int used = extendedPacketBuffer.readInt();
			packetContext.getTaskQueue().execute(() -> {
				StorageDiskSync.getInstance().setData(uuid, new StorageDiskSyncData(capacity, used));
			});
		});
	}

	private static void registerPacketHandler(Identifier identifier, BiConsumer<ExtendedPacketBuffer, PacketContext> consumer){
		ClientSidePacketRegistry.INSTANCE.register(identifier, (packetContext, packetByteBuf)
				-> consumer.accept(new ExtendedPacketBuffer(packetByteBuf), packetContext));
	}

	public static Packet<ClientPlayPacketListener> createPacketStorageDiskSizeResponse(UUID uuid, int capacity, int used) {
		return NetworkManager.createClientBoundPacket(STORAGE_DISK_SIZE_RESPONSE, extendedPacketBuffer -> {
			extendedPacketBuffer.writeUuid(uuid);
			extendedPacketBuffer.writeInt(capacity);
			extendedPacketBuffer.writeInt(used);
		});
	}
}
