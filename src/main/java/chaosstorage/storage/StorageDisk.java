package chaosstorage.storage;

import chaosstorage.utils.ItemHelper;
import com.google.common.collect.Multimap;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import com.google.common.collect.ArrayListMultimap;
import net.minecraft.util.math.MathHelper;

import java.util.Collection;

public class StorageDisk implements IStorageDisk {
	private Multimap<Item, ItemStack> stacks = ArrayListMultimap.create();

	private final int capacity;
	private int stored;

	public StorageDisk(int maxStorage) {
		this.capacity = maxStorage;
	}

	@Override
	public int getCapacity() {
		return this.capacity;
	}

	@Override
	public int getStored() {
		return this.stored;
	}


	public int calculateComparatorOutputFromStorage(BlockEntity blockentity) {
		return MathHelper.ceil(getStored() * 15.0 / getCapacity());
	}

	@Override
	public ItemStack insert(ItemStack stack) {
		int size = stack.getCount();

		if (stack.isEmpty()) {
			return stack;
		}

		for (ItemStack otherStack : stacks.get(stack.getItem())) {
			if (ItemStack.areItemsEqual(stack, otherStack) && ItemStack.areTagsEqual(stack, otherStack)) {
				if (size > this.getFreeSpace()) {
					int remainingSpace = this.getFreeSpace();
					if (remainingSpace <= 0) {
						return ItemHelper.copyStackWithSize(stack, size);
					}

					otherStack.setCount(otherStack.getCount() + remainingSpace);
					this.stored += remainingSpace;

					return ItemHelper.copyStackWithSize(otherStack, size - remainingSpace);
				}
			} else {
				otherStack.setCount(otherStack.getCount() + size); // TODO??
				this.stored += size;
				return ItemStack.EMPTY;
			}
		}

		if (size < this.getFreeSpace()) {
			if (this.getFreeSpace() <= 0) {
				return ItemHelper.copyStackWithSize(stack, size);
			}

			stacks.put(stack.getItem(), ItemHelper.copyStackWithSize(stack, this.getFreeSpace()));
			this.stored += size;
			return ItemHelper.copyStackWithSize(stack, this.getFreeSpace() - size);
		} else {
			stacks.put(stack.getItem(), ItemHelper.copyStackWithSize(stack, size));
			this.stored += size;
			return ItemStack.EMPTY;
		}
	}

	@Override
	public Collection<ItemStack> getStacks() {
		return stacks.values();
	}
}
