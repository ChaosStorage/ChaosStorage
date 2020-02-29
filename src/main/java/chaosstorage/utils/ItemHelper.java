package chaosstorage.utils;

import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemHelper {

	@Nonnull
	public static ItemStack copyStackWithSize(@Nonnull ItemStack itemStack, int size) {
		if (size == 0) {
			return ItemStack.EMPTY;
		}
		ItemStack copy = itemStack.copy();
		copy.setCount(size);
		return copy;
	}

}
