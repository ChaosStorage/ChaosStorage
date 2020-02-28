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

    private final int maxStorage;
    private int usedSpace;

    public StorageDisk(int maxStorage) {
        this.maxStorage = maxStorage;
    }

    @Override
    public int getMaxSpace() {
        return this.maxStorage;
    }

    @Override
    public int getSpace() {
        return this.usedSpace;
    }


    public int calculateComparatorOutputFromStorage(BlockEntity blockentity) {
        return MathHelper.ceil(getSpace() * 15.0 / getMaxSpace());
    }

    public ItemStack insert(ItemStack stack, int size) {
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
                    this.usedSpace += remainingSpace;

                    return ItemHelper.copyStackWithSize(otherStack, size - remainingSpace);
                }
            } else {
                otherStack.setCount(otherStack.getCount() + size); // TODO??
                this.usedSpace += size;
                return ItemStack.EMPTY;
            }
        }

        if (size < this.getFreeSpace()) {
            if (this.getFreeSpace() <= 0) {
                return ItemHelper.copyStackWithSize(stack, size);
            }

            stacks.put(stack.getItem(), ItemHelper.copyStackWithSize(stack, this.getFreeSpace()));
            this.usedSpace += size;
            return ItemHelper.copyStackWithSize(stack, this.getFreeSpace() - size);
        } else {
            stacks.put(stack.getItem(), ItemHelper.copyStackWithSize(stack, size));
            this.usedSpace += size;
            return ItemStack.EMPTY;
        }
    }

    @Override
    public Collection<ItemStack> getStacks() {
        return stacks.values();
    }
}
