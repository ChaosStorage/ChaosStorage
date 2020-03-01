package chaosstorage.packets;

import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.util.Identifier;
import reborncore.common.network.ExtendedPacketBuffer;

import java.util.function.BiConsumer;

public class ClientBoundPackets {
	public static void init() {
	}

	private static void registerPacketHandler(Identifier identifier, BiConsumer<ExtendedPacketBuffer, PacketContext> consumer){
		ClientSidePacketRegistry.INSTANCE.register(identifier, (packetContext, packetByteBuf)
				-> consumer.accept(new ExtendedPacketBuffer(packetByteBuf), packetContext));
	}
}
