package chaosstorage.network;

import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public interface INetworkNode {
	public IController getController();

	public void tick();
	public void markRemoved();
	public void unadopt();

	public ArrayList<INetworkNode> getNeighbours();
	public void adopt(IController controller, ArrayList<INetworkNode> networkMembers);

	public default int getEnergyUsage() {
		return 0;
	}
}
