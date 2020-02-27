package chaosstorage;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

import reborncore.common.config.Configuration;

import chaosstorage.config.ChaosStorageConfig;
import chaosstorage.events.ModRegistry;
import chaosstorage.init.CSContent;
import chaosstorage.init.CSBlockEntities;
import chaosstorage.client.GuiHandler;


public class ChaosStorage implements ModInitializer {
	public static final String MOD_ID = "chaosstorage";
	public static ChaosStorage INSTANCE;

	public static final ItemGroup ITEMGROUP = FabricItemGroupBuilder.build(
			new Identifier("chaosstorage", "item_group"),
			() -> new ItemStack(CSContent.Controllers.CREATIVE_CONTROLLER));

	@Override
	public void onInitialize() {
		System.out.println("Hello Fabric world!");
		INSTANCE = this;
		new Configuration(ChaosStorageConfig.class, "chaosstorage");

		ModRegistry.setupShit();
		GuiHandler.register();

		System.out.println("ChaosStorage setup done!");
	}
}
