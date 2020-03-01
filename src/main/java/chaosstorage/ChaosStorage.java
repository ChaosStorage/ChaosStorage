package chaosstorage;

import chaosstorage.packets.ClientBoundPackets;
import chaosstorage.packets.ServerBoundPackets;
import chaosstorage.storage.StorageDiskManager;
import chaosstorage.storage.StorageDiskSync;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

import net.minecraft.world.World;
import reborncore.common.config.Configuration;

import chaosstorage.config.ChaosStorageConfig;
import chaosstorage.events.ModRegistry;
import chaosstorage.init.CSContent;
import chaosstorage.client.GuiHandler;
import reborncore.common.world.DataAttachment;


public class ChaosStorage implements ModInitializer {
	public static final String MOD_ID = "chaosstorage";
	public static ChaosStorage CS;
	private StorageDiskSync storageDiskSync = new StorageDiskSync();

	public static final ItemGroup ITEMGROUP = FabricItemGroupBuilder.build(
			new Identifier("chaosstorage", "item_group"),
			() -> new ItemStack(CSContent.Controllers.CREATIVE_CONTROLLER));

	@Override
	public void onInitialize() {
		System.out.println("Hello Fabric world!");
		CS = this;
		System.out.println("Reading configuration");
		new Configuration(ChaosStorageConfig.class, "chaosstorage");

		ModRegistry.setupShit();
		GuiHandler.register();
		System.out.println("Registering packets");
		ServerBoundPackets.init();
		ClientBoundPackets.init();
		System.out.println("Registering data attachments");
		DataAttachment.REGISTRY.register(StorageDiskManager.class, StorageDiskManager::new);

		System.out.println("ChaosStorage setup done!");
	}
}
