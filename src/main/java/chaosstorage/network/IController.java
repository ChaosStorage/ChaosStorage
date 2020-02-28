package chaosstorage.network;

import net.minecraft.block.entity.BlockEntity;

public interface IController {
	public void scan();
	public void removeNode(INetworkNode node);
	public BlockEntity getControllerEntity();
}
