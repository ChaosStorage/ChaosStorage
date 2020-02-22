package chaosstorage;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import chaosstorage.events.ModRegistry;
import chaosstorage.init.CSContent;

public class ChaosStorage implements ModInitializer {
  public static final String MOD_ID = "chaosstorage";


  public static final ItemGroup ITEMGROUP = FabricItemGroupBuilder.build(
      new Identifier("chaosstorage", "item_group"),
      () -> new ItemStack(CSContent.QUARTZ_ENRICHED_IRON));

  @Override
  public void onInitialize() {
    System.out.println("Hello Fabric world!");

    ModRegistry.setupShit();
  }
}
