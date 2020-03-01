package chaosstorage.storage;

import chaosstorage.network.IStorageNode;
import chaosstorage.utils.DebugUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;
import reborncore.common.world.DataAttachment;
import reborncore.common.world.DataAttachmentProvider;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

public class StorageDiskManager implements DataAttachment {
	public static StorageDiskManager getInstance(World world) {
		return DataAttachmentProvider.get(world, StorageDiskManager.class);
	}

	private Map<UUID, IStorageDisk> disks = new HashMap<UUID, IStorageDisk>();

	public IStorageDisk getDisk(UUID uuid) {
		return disks.get(uuid);
	}

	public IStorageDisk getDiskOrNew(UUID uuid, int capacity) {
		if (disks.containsKey(uuid)) {
			return disks.get(uuid);
		} else {
			DebugUtils.dbg("creating disk " + uuid);
			StorageDisk newDisk = new StorageDisk(capacity);
			disks.put(uuid, newDisk);
			return newDisk;
		}
	}

	@Nonnull
	@Override
	public CompoundTag write() {
		CompoundTag tag = new CompoundTag();
		disks.forEach((uuid, disk) -> {
			tag.put(uuid.toString(), disk.write());
		});
		return tag;
	}

	@Override
	public void read(@Nonnull CompoundTag tag) {
		tag.getKeys().forEach(k -> {
			StorageDisk disk = new StorageDisk();
			disk.read((CompoundTag) tag.get(k));
			disks.put(UUID.fromString(k), disk);
		});
	}
}
