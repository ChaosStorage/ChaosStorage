package chaosstorage.storage;

import com.mojang.datafixers.types.templates.CompoundList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

public interface IStorageDisk extends IStorage {
	public static String NBT_STORED = "Stored";
	public static String NBT_CAPACITY = "Capacity";
	public static String NBT_STACKS = "Stacks";

	public int getStored();
	public int getCapacity();

	public default int getFreeSpace() {
		return getCapacity() - getStored();
	}

	public default CompoundTag write() {
		CompoundTag tag = new CompoundTag();
		tag.putInt(NBT_STORED, getStored());
		tag.putInt(NBT_CAPACITY, getCapacity());

		ListTag stacks = new ListTag();
		getStacks().forEach(stack -> {
			CompoundTag stackTag = new CompoundTag();
			stacks.add(stack.toTag(stackTag));
		});

		tag.put(NBT_STACKS, stacks);
		return tag;
	}

	public void read(CompoundTag tag);
}
