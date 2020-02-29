package chaosstorage.network;

import chaosstorage.storage.IStorageDisk;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public interface IStorageNode extends INetworkNode {

    public int getMaxStorage();

    public int getStorage();

    public default int getFreeStorage() {
        return getMaxStorage() - getStorage();
    }

    public ArrayList<IStorageDisk> getDisks();

    public default ItemStack insert(ItemStack stack, int size) {
        ArrayList<IStorageDisk> disks = this.getDisks();
        ItemStack retStack = stack;
        for (IStorageDisk disk: disks) {
            if (retStack.isEmpty()) {
                return retStack;
            }
            retStack = disk.insert(retStack, retStack.getCount());
        }
        return retStack;
    }


}
