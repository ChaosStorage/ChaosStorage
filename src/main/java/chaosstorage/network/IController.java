package chaosstorage.network;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public interface IController {
	public void scan();
	public void removeNode(INetworkNode node);
	public BlockEntity getControllerEntity();
	public ArrayList<INetworkNode> getNetworkNodes();
	public ItemStack insert(ItemStack stack);
}
