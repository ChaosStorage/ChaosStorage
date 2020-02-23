package chaosstorage.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;

import chaosstorage.block.ChaosBlock;
import chaosstorage.block.CableBlock;
import chaosstorage.item.ChaosItem;
import chaosstorage.item.UpgradeItem;
import chaosstorage.item.WirelessGrid;
import chaosstorage.block.ControllerBlock;
import chaosstorage.blockentity.ControllerEntity;
import chaosstorage.utils.InitUtils;
import chaosstorage.events.ModRegistry;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Function;

public class CSContent {

	private static String makeName(String enumName) {
		String ret = enumName.toLowerCase(Locale.ROOT);
		while (ret.startsWith("_")) {
			ret = ret.substring(1, ret.length());
		}
		return ret;
	}

	/* Blocks */
	public enum Blocks implements ItemConvertible {
		IMPORTER(new ChaosBlock()),
		EXPORTER(new ChaosBlock()),
		DETECTOR(new ChaosBlock()),
		RELAY(new ChaosBlock()),
		NETWORK_TRANSMITTER(new ChaosBlock()),
		NETWORK_RECEIVER(new ChaosBlock()),

		QUARTZ_ENRICHED_IRON_BLOCK(new ChaosBlock()),
		MACHINE_CASING(new ChaosBlock()),
		CONTROLLER(new ControllerBlock(false)),
		CREATIVE_CONTROLLER(new ControllerBlock(true)),
		CABLE(new CableBlock()),
		DISK_DRIVE(new ChaosBlock()),
		EXTERNAL_STORAGE(new ChaosBlock()),
		GRID(new ChaosBlock()),
		CRAFTING_GRID(new ChaosBlock()),
		PATTERN_GRID(new ChaosBlock()),
		FLUID_GRID(new ChaosBlock()),

		SECURITY_MANAGER(new ChaosBlock()),
		INTERFACE(new ChaosBlock()),
		FLUID_INTERFACE(new ChaosBlock()),
		WIRELESS_TRANSMITTER(new ChaosBlock()),
		STORAGE_MONITOR(new ChaosBlock()),
		CONSTRUCTOR(new ChaosBlock()),
		DESTRUCTOR(new ChaosBlock()),
		DISK_MANIPULATOR(new ChaosBlock()),
		PORTABLE_GRID(new ChaosBlock()),
		CREATIVE_PORTABLE_GRID(new ChaosBlock()),
		CRAFTER(new ChaosBlock()),
		CRAFTER_MANAGER(new ChaosBlock()),
		CRAFTING_MONITOR(new ChaosBlock()),

		_1K_STORAGE_BLOCK(new ChaosBlock()),
		_4K_STORAGE_BLOCK(new ChaosBlock()),
		_16K_STORAGE_BLOCK(new ChaosBlock()),
		_64K_STORAGE_BLOCK(new ChaosBlock()),
		CREATIVE_STORAGE_BLOCK(new ChaosBlock()),

		_64K_FLUID_STORAGE_BLOCK(new ChaosBlock()),
		_256K_FLUID_STORAGE_BLOCK(new ChaosBlock()),
		_1024K_FLUID_STORAGE_BLOCK(new ChaosBlock()),
		_4096K_FLUID_STORAGE_BLOCK(new ChaosBlock()),
		CREATIVE_FLUID_STORAGE_BLOCK(new ChaosBlock());

		public final Block block;
		
		Blocks(Block b) {
			String name = makeName(this.toString());
			block = b;
			InitUtils.setup(block, name);
		}

		@Override
		public Item asItem() {
			return block.asItem();
		}
	}

	/* Items */
	public enum Items implements ItemConvertible {
		QUARTZ_ENRICHED_IRON(new ChaosItem()),
		SILICON(new ChaosItem()),

		PROCESSOR_BINDING(new ChaosItem()),
		RAW_BASIC_PROCESSOR(new ChaosItem()),
		RAW_IMPROVED_PROCESSOR(new ChaosItem()),
		RAW_ADVANCED_PROCESSOR(new ChaosItem()),

		BASIC_PROCESSOR(new ChaosItem()),
		IMPROVED_PROCESSOR(new ChaosItem()),
		ADVANCED_PROCESSOR(new ChaosItem()),

		CONSTRUCTION_CORE(new ChaosItem()),
		DESTRUCTION_CORE(new ChaosItem()),

		UPGRADE(new UpgradeItem(UpgradeItem.Type.NORMAL)),
		CRAFTING_UPGRADE(new UpgradeItem(UpgradeItem.Type.SPEED)),
		RANGE_UPGRADE(new UpgradeItem(UpgradeItem.Type.RANGE)),
		SPEED_UPGRADE(new UpgradeItem(UpgradeItem.Type.CRAFTING)),
		STACK_UPGRADE(new UpgradeItem(UpgradeItem.Type.STACK)),
		SILK_TOUCH_UPGRADE(new UpgradeItem(UpgradeItem.Type.SILK_TOUCH)),
		FORTUNE_1_UPGRADE(new UpgradeItem(UpgradeItem.Type.FORTUNE_1)),
		FORTUNE_2_UPGRADE(new UpgradeItem(UpgradeItem.Type.FORTUNE_2)),
		FORTUNE_3_UPGRADE(new UpgradeItem(UpgradeItem.Type.FORTUNE_3)),

		WRENCH(new ChaosItem()),
		FILTER(new ChaosItem()),
		PATTERN(new ChaosItem()),
		NETWORK_CARD(new ChaosItem()),
		SECURITY_CARD(new ChaosItem()),

		WIRELESS_CRAFTING_MONITOR(new ChaosItem()),
		WIRELESS_CRAFTING_GRID(new WirelessGrid(WirelessGrid.Type.CRAFTING)),
		WIRELESS_FLUID_GRID(new WirelessGrid(WirelessGrid.Type.FLUID)),
		WIRELESS_GRID(new WirelessGrid(WirelessGrid.Type.NORMAL)),

		STORAGE_HOUSING(new ChaosItem()),

		_1K_STORAGE_PART(new ChaosItem()),
		_4K_STORAGE_PART(new ChaosItem()),
		_16K_STORAGE_PART(new ChaosItem()),
		_64K_STORAGE_PART(new ChaosItem()),

		_64K_FLUID_STORAGE_PART(new ChaosItem()),
		_256K_FLUID_STORAGE_PART(new ChaosItem()),
		_1024K_FLUID_STORAGE_PART(new ChaosItem()),
		_4096K_FLUID_STORAGE_PART(new ChaosItem()),

		_64K_FLUID_STORAGE_DISK(new ChaosItem()),
		_256K_FLUID_STORAGE_DISK(new ChaosItem()),
		_1024K_FLUID_STORAGE_DISK(new ChaosItem()),
		_4096K_FLUID_STORAGE_DISK(new ChaosItem()),
		CREATIVE_FLUID_STORAGE_DISK(new ChaosItem()),

		_1K_STORAGE_DISK(new ChaosItem()),
		_4K_STORAGE_DISK(new ChaosItem()),
		_16K_STORAGE_DISK(new ChaosItem()),
		_64K_STORAGE_DISK(new ChaosItem()),
		CREATIVE_STORAGE_DISK(new ChaosItem());

		public final Item item;
		
		Items(Item i) {
			String name = makeName(this.toString());
			item = i;
			InitUtils.setup(item, name);
		}

		@Override
		public Item asItem() {
			return item;
		}
	}
}
