package chaosstorage.network;

import chaosstorage.storage.IStorage;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public interface IStorageNode extends INetworkNode {
	public ArrayList<IStorage> getStorages();
}
