package chaosstorage.network;

import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public interface INetworkNode {
	public IController getController();

	void tick();

	public ArrayList<INetworkNode> getNeighbours();
	public void adopt(IController controller, ArrayList<INetworkNode> networkMembers);

	public default int getEnergyUsage() {
		return 0;
	}
}
