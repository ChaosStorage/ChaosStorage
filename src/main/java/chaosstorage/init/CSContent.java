package chaosstorage.init;

import chaosstorage.blockentity.CableEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;

import chaosstorage.block.ChaosBlock;
import chaosstorage.block.CableBlock;
import chaosstorage.block.DetectorBlock;
import chaosstorage.item.ChaosItem;
import chaosstorage.item.UpgradeItem;
import chaosstorage.item.WirelessGrid;
import chaosstorage.block.ControllerBlock;
import chaosstorage.block.StorageBlock;
import chaosstorage.utils.InitUtils;

import java.awt.*;
import java.util.Locale;

public class CSContent {

	private static String makeName(String enumName) {
		String ret = enumName.toLowerCase(Locale.ROOT);
		while (ret.startsWith("_")) {
			ret = ret.substring(1, ret.length());
		}
		return ret;
	}

	public enum Cables implements ItemConvertible {
		CABLE(new CableBlock(CableBlock.Type.NORMAL)),
		EXTERNAL_STORAGE(new CableBlock(CableBlock.Type.EXTERNAL_STORAGE)),
		CONSTRUCTOR(new CableBlock(CableBlock.Type.CONSTRUCTOR)),
		DESTRUCTOR(new CableBlock(CableBlock.Type.DESTRUCTOR)),
		IMPORTER(new CableBlock(CableBlock.Type.IMPORTER)),
		EXPORTER(new CableBlock(CableBlock.Type.EXPORTER));

		public final Block block;

		Cables(Block b) {
			String name = makeName(this.toString());
			block = b;
			InitUtils.setup(block, name);
		}

		@Override
		public Item asItem() {
			return block.asItem();
		}
	}

	public enum Controllers implements ItemConvertible {
		CONTROLLER(new ControllerBlock(false)),
		CREATIVE_CONTROLLER(new ControllerBlock(true));

		public final Block block;

		Controllers(Block b) {
			String name = makeName(this.toString());
			block = b;
			InitUtils.setup(block, name);
		}

		@Override
		public Item asItem() {
			return block.asItem();
		}
	}

	public enum StorageBlocks implements ItemConvertible {
		_1K_STORAGE_BLOCK(new StorageBlock(StorageBlock.Type.Item, 1024)),
		_4K_STORAGE_BLOCK(new StorageBlock(StorageBlock.Type.Item, 1024 * 4)),
		_16K_STORAGE_BLOCK(new StorageBlock(StorageBlock.Type.Item, 1024 * 16)),
		_64K_STORAGE_BLOCK(new StorageBlock(StorageBlock.Type.Item, 1024 * 64)),
		CREATIVE_STORAGE_BLOCK(new StorageBlock(StorageBlock.Type.Item, Integer.MAX_VALUE)),

		_64K_FLUID_STORAGE_BLOCK(new StorageBlock(StorageBlock.Type.Fluid, 1024 * 64)),
		_256K_FLUID_STORAGE_BLOCK(new StorageBlock(StorageBlock.Type.Fluid, 1024 * 256)),
		_1024K_FLUID_STORAGE_BLOCK(new StorageBlock(StorageBlock.Type.Fluid, 1024 * 1024)),
		_4096K_FLUID_STORAGE_BLOCK(new StorageBlock(StorageBlock.Type.Fluid, 1024 * 4096)),
		CREATIVE_FLUID_STORAGE_BLOCK(new StorageBlock(StorageBlock.Type.Fluid, Integer.MAX_VALUE));

		public final Block block;

		StorageBlocks(Block b) {
			String name = makeName(this.toString());
			block = b;
			InitUtils.setup(block, name);
		}

		@Override
		public Item asItem() {
			return block.asItem();
		}
	}

	public enum Blocks implements ItemConvertible {
		/* "Dumb" blocks */
		QUARTZ_ENRICHED_IRON_BLOCK(new ChaosBlock()),
		MACHINE_CASING(new ChaosBlock()),

		DETECTOR(new DetectorBlock()),

		DISK_DRIVE(new ChaosBlock()),
		GRID(new ChaosBlock(true, true, false)),
		CRAFTING_GRID(new ChaosBlock(true, true, false)),
		PATTERN_GRID(new ChaosBlock(true, true, false)),
		FLUID_GRID(new ChaosBlock(true, true, false)),
		SECURITY_MANAGER(new ChaosBlock(true, true, false)),
		CRAFTER_MANAGER(new ChaosBlock(true, true, false)),
		CRAFTING_MONITOR(new ChaosBlock(true, true, false)),

		STORAGE_MONITOR(new ChaosBlock(true, false, false)),
		CRAFTER(new ChaosBlock(true, true, false)),

		INTERFACE(new ChaosBlock(false, true)),
		FLUID_INTERFACE(new ChaosBlock(false, true)),
		WIRELESS_TRANSMITTER(new ChaosBlock(false, true)),
		NETWORK_TRANSMITTER(new ChaosBlock(false, true)),
		NETWORK_RECEIVER(new ChaosBlock(false, true)),
		RELAY(new ChaosBlock(false, true)),

		DISK_MANIPULATOR(new ChaosBlock()),
		PORTABLE_GRID(new ChaosBlock()),
		CREATIVE_PORTABLE_GRID(new ChaosBlock());

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
