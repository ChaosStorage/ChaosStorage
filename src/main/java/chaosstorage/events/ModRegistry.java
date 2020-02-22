package chaosstorage.events;

import chaosstorage.item.UpgradeItem;
import chaosstorage.ChaosStorage;
import chaosstorage.init.CSContent;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRegistry {
  public static void setupShit() {
    registerBlocks();
    registerItems();
  }

  public static void registerBlocks() {
  }

  public static void registerItems() {
    Settings itemGroup = new Item.Settings().group(ChaosStorage.ITEMGROUP);

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

    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "wireless_crafting_monitor"), CSContent.WIRELESS_CRAFTING_MONITOR = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "wireless_fluid_grid"), CSContent.WIRELESS_FLUID_GRID = new Item(itemGroup));
    Registry.register(Registry.ITEM, new Identifier(ChaosStorage.MOD_ID, "wireless_grid"), CSContent.WIRELESS_GRID = new Item(itemGroup));

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
