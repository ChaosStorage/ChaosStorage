package chaosstorage.events;

import reborncore.RebornRegistry;
import chaosstorage.item.UpgradeItem;
import chaosstorage.item.WirelessGrid;
import chaosstorage.ChaosStorage;
import chaosstorage.init.CSContent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Arrays;

public class ModRegistry {
	private static final Item.Settings itemGroup = new Item.Settings().group(ChaosStorage.ITEMGROUP);

	public static void setupShit() {
		registerBlocks();
		registerItems();
	}

	private static void registerBlocks() {
		Arrays.stream(CSContent.Blocks.values()).forEach(value -> RebornRegistry.registerBlock(value.block, itemGroup));
	}

	public static void registerItems() {
		Arrays.stream(CSContent.Items.values()).forEach(value -> RebornRegistry.registerItem(value.item));
	}
}
