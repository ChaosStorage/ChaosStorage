package chaosstorage.storage;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

public interface IStorageDisk {
    public int getSpace();

    public int getMaxSpace();

    public default int getFreeSpace() {
        return getMaxSpace() - getSpace();
    }


    public ItemStack insert(ItemStack stack, int size);

    public Collection<ItemStack> getStacks();
}
