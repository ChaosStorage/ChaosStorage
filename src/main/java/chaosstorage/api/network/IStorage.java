
package chaosstorage.api.network;

import net.minecraft.util.math.MathHelper;
import net.minecraft.block.entity.BlockEntity;

public interface IStorage {
	public abstract int getMaxSpace();

	public abstract int getSpace();

	public default int getFreeSpace() {
		return getMaxSpace() - getSpace();
	}

	public default int calculateComparatorOutputFromStorage(BlockEntity blockentity) {
		return MathHelper.ceil(getSpace() * 15.0 / getMaxSpace());
	}

}	
