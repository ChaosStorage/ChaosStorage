package chaosstorage.block;

import net.minecraft.block.Block;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.block.Material;
import net.fabricmc.fabric.api.block.FabricBlockSettings;

public class ChaosBlock extends Block {
	public ChaosBlock() {
		super(FabricBlockSettings.of(Material.STONE).strength(1.9f, 1.9f).sounds(BlockSoundGroup.STONE).build());
	}
}
