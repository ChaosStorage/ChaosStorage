package chaosstorage.block;

import chaosstorage.blockentity.NetworkEntity;
import chaosstorage.network.INetworkNode;
import chaosstorage.network.INetworkNodeProvider;
import chaosstorage.network.NetworkNode;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class NetworkBlock<N extends NetworkNode> extends ChaosBlock implements BlockEntityProvider {
	public NetworkBlock(boolean hasDirectionState, boolean hasConnectedState, boolean allDirections) {
		super(hasDirectionState, hasConnectedState, allDirections);
	}

	public NetworkBlock() {
		super();
	}

	// MUST implement INetworkNodeProvider
	@Override
	public abstract BlockEntity createBlockEntity(BlockView view);

	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		super.onPlaced(world, pos, state, placer, itemStack);
		if (world.isClient) return;
		INetworkNodeProvider p = (INetworkNodeProvider) world.getBlockEntity(pos);
		p.getNetworkNode().initiateScan();
	}
}
