package chaosstorage;

import chaosstorage.packets.ClientBoundPackets;
import chaosstorage.packets.ServerBoundPackets;
import chaosstorage.storage.StorageDiskManager;
import chaosstorage.utils.DebugUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

import reborncore.common.config.Configuration;

import chaosstorage.config.ChaosStorageConfig;
import chaosstorage.events.ModRegistry;
import chaosstorage.init.CSContent;
import chaosstorage.client.GuiHandler;
import reborncore.common.world.DataAttachment;


public class ChaosStorage implements ModInitializer {
	public static final String MOD_ID = "chaosstorage";
	public static ChaosStorage CS;

	public static final ItemGroup ITEMGROUP = FabricItemGroupBuilder.build(
			new Identifier("chaosstorage", "item_group"),
			() -> new ItemStack(CSContent.Controllers.CREATIVE_CONTROLLER));

	@Override
	public void onInitialize() {
		DebugUtils.dbg("ChaosStorage initializing");
		CS = this;
		DebugUtils.dbg("Reading configuration");
		new Configuration(ChaosStorageConfig.class, "chaosstorage");

		ModRegistry.setupShit();
		GuiHandler.register();
		DebugUtils.dbg("Registering packets");
		ServerBoundPackets.init();
		ClientBoundPackets.init();
		DebugUtils.dbg("Registering data attachments");
		DataAttachment.REGISTRY.register(StorageDiskManager.class, StorageDiskManager::new);

		DebugUtils.dbg("ChaosStorage setup done!");
	}
}
