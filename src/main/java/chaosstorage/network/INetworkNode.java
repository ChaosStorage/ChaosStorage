package chaosstorage.network;

import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public interface INetworkNode {
		public IController getController();
		public ArrayList<INetworkNode> getNeighbours();
		public void adopt(IController controller);

		public default int getEnergyUsage() {
			return 0;
		}
}
