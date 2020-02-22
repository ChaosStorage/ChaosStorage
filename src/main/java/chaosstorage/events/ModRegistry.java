package chaosstorage.events;

import reborncore.RebornRegistry;
import chaosstorage.item.UpgradeItem;
import chaosstorage.item.WirelessGrid;
import chaosstorage.ChaosStorage;
import chaosstorage.init.CSContent;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.block.FabricBlockSettings;

public class ModRegistry {
  public /*private*/ static final Item.Settings itemGroup = new Item.Settings().group(ChaosStorage.ITEMGROUP);

  public static void setupShit() {
    registerBlocks();
    registerItems();
  }

  public static void registerBlocks() {
    Block.Settings settings = FabricBlockSettings.of(Material.STONE).strength(1.9f, 1.9f).sounds(BlockSoundGroup.STONE).build();

    RebornRegistry.registerBlock(CSContent.IMPORTER = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "importer"));
    RebornRegistry.registerBlock(CSContent.EXPORTER = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "exporter"));
    RebornRegistry.registerBlock(CSContent.DETECTOR = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "detector"));
    RebornRegistry.registerBlock(CSContent.RELAY = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "relay"));
    RebornRegistry.registerBlock(CSContent.NETWORK_TRANSMITTER = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "network_transmitter"));
    RebornRegistry.registerBlock(CSContent.NETWORK_RECEIVER = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "network_receiver"));

    RebornRegistry.registerBlock(CSContent.QUARTZ_ENRICHED_IRON_BLOCK = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "quartz_enriched_iron_block"));
    RebornRegistry.registerBlock(CSContent.MACHINE_CASING = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "machine_casing"));
    //RebornRegistry.registerBlock(CSContent.CONTROLLER = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "controller"));
    //RebornRegistry.registerBlock(CSContent.Machine.CREATIVE_CONTROLLER.block, itemGroup, new Identifier(ChaosStorage.MOD_ID, "creative_controller"));
    RebornRegistry.registerBlock(CSContent.CABLE = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "cable"));
    RebornRegistry.registerBlock(CSContent.DISK_DRIVE = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "disk_drive"));
    RebornRegistry.registerBlock(CSContent.EXTERNAL_STORAGE = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "external_storage"));
    RebornRegistry.registerBlock(CSContent.GRID = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "grid"));
    RebornRegistry.registerBlock(CSContent.CRAFTING_GRID = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "crafting_grid"));
    RebornRegistry.registerBlock(CSContent.PATTERN_GRID = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "pattern_grid"));
    RebornRegistry.registerBlock(CSContent.FLUID_GRID = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "fluid_grid"));

    RebornRegistry.registerBlock(CSContent.SECURITY_MANAGER = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "security_manager"));
    RebornRegistry.registerBlock(CSContent.INTERFACE = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "interface"));
    RebornRegistry.registerBlock(CSContent.FLUID_INTERFACE = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "fluid_interface"));
    RebornRegistry.registerBlock(CSContent.WIRELESS_TRANSMITTER = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "wireless_transmitter"));
    RebornRegistry.registerBlock(CSContent.STORAGE_MONITOR = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "storage_monitor"));
    RebornRegistry.registerBlock(CSContent.CONSTRUCTOR = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "constructor"));
    RebornRegistry.registerBlock(CSContent.DESTRUCTOR = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "destructor"));
    RebornRegistry.registerBlock(CSContent.DISK_MANIPULATOR = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "disk_manipulator"));
    RebornRegistry.registerBlock(CSContent.PORTABLE_GRID = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "portable_grid"));
    RebornRegistry.registerBlock(CSContent.CREATIVE_PORTABLE_GRID = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "creative_portable_grid"));
    RebornRegistry.registerBlock(CSContent.CRAFTER = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "crafter"));
    RebornRegistry.registerBlock(CSContent.CRAFTER_MANAGER = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "crafter_manager"));
    RebornRegistry.registerBlock(CSContent.CRAFTING_MONITOR = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "crafting_monitor"));

    RebornRegistry.registerBlock(CSContent._1K_STORAGE_BLOCK = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "1k_storage_block"));
    RebornRegistry.registerBlock(CSContent._4K_STORAGE_BLOCK = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "4k_storage_block"));
    RebornRegistry.registerBlock(CSContent._16K_STORAGE_BLOCK = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "16k_storage_block"));
    RebornRegistry.registerBlock(CSContent._64K_STORAGE_BLOCK = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "64k_storage_block"));
    RebornRegistry.registerBlock(CSContent.CREATIVE_STORAGE_BLOCK = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "creative_storage_block"));

    RebornRegistry.registerBlock(CSContent._64K_FLUID_STORAGE_BLOCK = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "64k_fluid_storage_block"));
    RebornRegistry.registerBlock(CSContent._256K_FLUID_STORAGE_BLOCK = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "256k_fluid_storage_block"));
    RebornRegistry.registerBlock(CSContent._1024K_FLUID_STORAGE_BLOCK = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "1024k_fluid_storage_block"));
    RebornRegistry.registerBlock(CSContent._4096K_FLUID_STORAGE_BLOCK = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "4096k_fluid_storage_block"));
    RebornRegistry.registerBlock(CSContent.CREATIVE_FLUID_STORAGE_BLOCK = new Block(settings), itemGroup, new Identifier(ChaosStorage.MOD_ID, "creative_fluid_storage_block"));
  }

  public static void registerItems() {
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "quartz_enriched_iron"), CSContent.QUARTZ_ENRICHED_IRON = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "silicon"), CSContent.SILICON = new Item(itemGroup));

    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "processor_binding"), CSContent.PROCESSOR_BINDING = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "raw_basic_processor"), CSContent.RAW_BASIC_PROCESSOR = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "raw_improved_processor"), CSContent.RAW_IMPROVED_PROCESSOR = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "raw_advanced_processor"), CSContent.RAW_ADVANCED_PROCESSOR = new Item(itemGroup));

    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "basic_processor"), CSContent.BASIC_PROCESSOR = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "improved_processor"), CSContent.IMPROVED_PROCESSOR = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "advanced_processor"), CSContent.ADVANCED_PROCESSOR = new Item(itemGroup));

    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "construction_core"), CSContent.CONSTRUCTION_CORE = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "destruction_core"), CSContent.DESTRUCTION_CORE = new Item(itemGroup));

    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "upgrade"), CSContent.UPGRADE = new UpgradeItem(UpgradeItem.Type.NORMAL, itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "crafting_upgrade"), CSContent.CRAFTING_UPGRADE = new UpgradeItem(UpgradeItem.Type.CRAFTING, itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "range_upgrade"), CSContent.RANGE_UPGRADE = new UpgradeItem(UpgradeItem.Type.RANGE, itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "speed_upgrade"), CSContent.SPEED_UPGRADE = new UpgradeItem(UpgradeItem.Type.SPEED, itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "stack_upgrade"), CSContent.STACK_UPGRADE = new UpgradeItem(UpgradeItem.Type.STACK, itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "silk_touch_upgrade"), CSContent.SILK_TOUCH_UPGRADE = new UpgradeItem(UpgradeItem.Type.SILK_TOUCH, itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "fortune_1_upgrade"), CSContent.FORTUNE_1_UPGRADE = new UpgradeItem(UpgradeItem.Type.FORTUNE_1, itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "fortune_2_upgrade"), CSContent.FORTUNE_2_UPGRADE = new UpgradeItem(UpgradeItem.Type.FORTUNE_2, itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "fortune_3_upgrade"), CSContent.FORTUNE_3_UPGRADE = new UpgradeItem(UpgradeItem.Type.FORTUNE_3, itemGroup));

    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "wrench"), CSContent.WRENCH = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "filter"), CSContent.FILTER = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "pattern"), CSContent.PATTERN = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "network_card"), CSContent.NETWORK_CARD = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "security_card"), CSContent.SECURITY_CARD = new Item(itemGroup));

    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "wireless_crafting_monitor"), CSContent.WIRELESS_CRAFTING_MONITOR = new WirelessGrid(WirelessGrid.Type.CRAFTING, itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "wireless_fluid_grid"), CSContent.WIRELESS_FLUID_GRID = new WirelessGrid(WirelessGrid.Type.FLUID, itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "wireless_grid"), CSContent.WIRELESS_GRID = new WirelessGrid(WirelessGrid.Type.NORMAL, itemGroup));

    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "storage_housing"), CSContent.STORAGE_HOUSING = new Item(itemGroup));

    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "1k_storage_part"), CSContent._1K_STORAGE_PART = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "4k_storage_part"), CSContent._4K_STORAGE_PART = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "16k_storage_part"), CSContent._16K_STORAGE_PART = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "64k_storage_part"), CSContent._64K_STORAGE_PART = new Item(itemGroup));

    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "64k_fluid_storage_part"), CSContent._64K_FLUID_STORAGE_PART = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "256k_fluid_storage_part"), CSContent._256K_FLUID_STORAGE_PART = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "1024k_fluid_storage_part"), CSContent._1024K_FLUID_STORAGE_PART = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "4096k_fluid_storage_part"), CSContent._4096K_FLUID_STORAGE_PART = new Item(itemGroup));

    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "1k_storage_disk"), CSContent._1K_STORAGE_DISK = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "4k_storage_disk"), CSContent._4K_STORAGE_DISK = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "16k_storage_disk"), CSContent._16K_STORAGE_DISK = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "64k_storage_disk"), CSContent._64K_STORAGE_DISK = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "creative_storage_disk"), CSContent.CREATIVE_STORAGE_DISK = new Item(itemGroup));

    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "64k_fluid_storage_disk"), CSContent._64K_FLUID_STORAGE_DISK = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "256k_fluid_storage_disk"), CSContent._256K_FLUID_STORAGE_DISK = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "1024k_fluid_storage_disk"), CSContent._1024K_FLUID_STORAGE_DISK = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "4096k_fluid_storage_disk"), CSContent._4096K_FLUID_STORAGE_DISK = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "creative_fluid_storage_disk"), CSContent.CREATIVE_FLUID_STORAGE_DISK = new Item(itemGroup));
  }
}
