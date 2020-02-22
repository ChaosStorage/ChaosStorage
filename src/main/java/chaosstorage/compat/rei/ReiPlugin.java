package chaosstorage.compat.rei;

import me.shedaniel.rei.api.plugins.REIPluginV0;
import chaosstorage.ChaosStorage;
import net.minecraft.util.Identifier;

public class ReiPlugin implements REIPluginV0 {
	public static final Identifier PLUGIN = new Identifier(ChaosStorage.MOD_ID, "chaosstorage_plugin");

	public ReiPlugin() {
	}

	@Override
	public Identifier getPluginIdentifier() {
		return PLUGIN;
	}

}
