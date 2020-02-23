package chaosstorage.item;

import chaosstorage.ChaosStorage;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;

public class ChaosItem extends Item {
	public ChaosItem() {
		super(new Item.Settings().group(ChaosStorage.ITEMGROUP));
	}

	public ChaosItem(Settings settings) {
		super(settings);
	}
}
