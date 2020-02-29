package chaosstorage.storage;

import net.minecraft.item.ItemStack;

import java.util.Collection;

public interface IStorage {
	public ItemStack insert(ItemStack stack);
	public Collection<ItemStack> getStacks();
}
