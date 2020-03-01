

package chaosstorage.block;

import chaosstorage.blockentity.NetworkEntity;
import chaosstorage.network.StorageBlockNode;
import chaosstorage.utils.DebugUtils;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import reborncore.api.blockentity.IMachineGuiHandler;
import chaosstorage.client.EGui;
import chaosstorage.blockentity.StorageBlockEntity;

public class StorageBlock extends NetworkBlock<StorageBlockNode> {
	public enum Type {
		Item,
		Fluid;
	}

	String name;
	int size;
	Type type;
	public IMachineGuiHandler gui;

	private static String makeName(Type type, int size) {
		String name = "";
		if (size == Integer.MAX_VALUE) {
			name += "Creative ";
		} else {
			name += String.valueOf(size / 1024) + "K ";
		}
		if (type == Type.Fluid) {
			name += "Fluid ";
		}
		name += "Storage Block";
		return name;
	}

	public StorageBlock(Type type, int size) {
		this(type, size, makeName(type, size));
	}

	public StorageBlock(Type type, int size, String name) {
		super();
		this.name = name;
		this.size = size;
		this.type = type;
		this.gui = EGui.STORAGE_BLOCK;
	}

	@SuppressWarnings("deprecation")
	@Override
	public ActionResult onUse(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockHitResult hitResult) {
		ItemStack stack = playerIn.getStackInHand(Hand.MAIN_HAND);
		BlockEntity blockEntity = worldIn.getBlockEntity(pos);

		// We implement BlockEntityProvider. Thus we should always have blockEntity entity. I hope.
		if (blockEntity == null) {
			return ActionResult.FAIL;
		}

		DebugUtils.printController(worldIn, pos, hand);

		/*if (!stack.isEmpty() && ToolManager.INSTANCE.canHandleTool(stack)) {
			if (WrenchUtils.handleWrench(stack, worldIn, pos, playerIn, hitResult.getSide())) {
			return ActionResult.SUCCESS;
			}
			}*/

		if (!playerIn.isSneaking() && gui != null) {
			gui.open(playerIn, pos, worldIn);
			return ActionResult.SUCCESS;
		}

		return super.onUse(state, worldIn, pos, playerIn, hand, hitResult);
	}

	// TODO: Comperator output

	// BlockEntityProvider
	@Override
	public BlockEntity createBlockEntity(BlockView worldIn) {
		return new StorageBlockEntity(this.size, this.name);
	}
}
