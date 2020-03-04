package chaosstorage.events;

import chaosstorage.blockentity.CableEntity;
import chaosstorage.blockentity.ControllerEntity;
import chaosstorage.blockentity.StorageBlockEntity;
import chaosstorage.init.CSBlockEntities;
import chaosstorage.utils.DebugUtils;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemConvertible;
import org.apache.commons.lang3.Validate;
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
		DebugUtils.dbg("Registering blocks");
		registerBlocks();
		DebugUtils.dbg("Registering items");
		registerItems();
		DebugUtils.dbg("Registering block entities");
		registerBlockEntities();
	}

	private static void registerBlocks() {
		Arrays.stream(CSContent.Controllers.values()).forEach(value -> RebornRegistry.registerBlock(value.block, itemGroup));
		Arrays.stream(CSContent.StorageBlocks.values()).forEach(value -> RebornRegistry.registerBlock(value.block, itemGroup));
		Arrays.stream(CSContent.Cables.values()).forEach(value -> RebornRegistry.registerBlock(value.block, itemGroup));
		Arrays.stream(CSContent.Blocks.values()).forEach(value -> RebornRegistry.registerBlock(value.block, itemGroup));
	}

	public static void registerItems() {
		Arrays.stream(CSContent.Items.values()).forEach(value -> RebornRegistry.registerItem(value.item));
	}

	public static void registerBlockEntities() {
		CSBlockEntities.CONTROLLER = registerBlockEntity(ControllerEntity.class, "controller", CSContent.Controllers.values());
		CSBlockEntities.STORAGE_BLOCK = registerBlockEntity(StorageBlockEntity.class, "storage_block", CSContent.StorageBlocks.values());
		CSBlockEntities.CABLE = registerBlockEntity(CableEntity.class, "cable", CSContent.Cables.values());
	}

	public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(Class<T> tClass, String name, ItemConvertible... items) {
		return registerBlockEntity(tClass, name, Arrays.stream(items).map(itemConvertible -> Block.getBlockFromItem(itemConvertible.asItem())).toArray(Block[]::new));
	}

	public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(Class<T> tClass, String name, Block... blocks) {
		Validate.isTrue(blocks.length > 0, "no blocks for blockEntity entity type!");
		return registerBlockEntity(new Identifier(ChaosStorage.MOD_ID, name).toString(), BlockEntityType.Builder.create(() -> createBlockEntity(tClass), blocks));
	}

	private static <T extends BlockEntity> T createBlockEntity(Class<T> tClass){
		try {
			return tClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("Failed to createBlockEntity blockEntity", e);
		}
	}

	public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String id, BlockEntityType.Builder<T> builder) {
		BlockEntityType<T> blockEntityType = builder.build(null);
		Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(id), blockEntityType);
		CSBlockEntities.TYPES.add(blockEntityType);
		return blockEntityType;
	}
}
