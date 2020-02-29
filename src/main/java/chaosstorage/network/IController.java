package chaosstorage.network;

import net.minecraft.block.entity.BlockEntity;

import java.util.ArrayList;

public interface IController {
	public void scan();
	public void removeNode(INetworkNode node);
	public BlockEntity getControllerEntity();
	public ArrayList<INetworkNode> getNetworkNodes();
}
