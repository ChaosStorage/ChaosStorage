

package chaosstorage.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.Material;
import net.minecraft.world.BlockView;
import reborncore.common.BaseBlockEntityProvider;


import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import reborncore.api.ToolManager;
import reborncore.api.blockentity.IMachineGuiHandler;
import reborncore.common.BaseBlockEntityProvider;
import reborncore.common.blocks.BlockWrenchEventHandler;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;
import reborncore.common.util.ItemHandlerUtils; // FIXME: cleanup

import chaosstorage.client.EGui;
import chaosstorage.blockentity.StorageBlockEntity;
// StorageEntity


public class StorageBlock extends BaseBlockEntityProvider {
	public enum Type {
		Item,
		Fluid;
	}

	public enum Item {
		_1K_STORAGE_BLOCK(Type.Item, 1024, "1K Storage Block"),
		_4K_STORAGE_BLOCK(Type.Item, 4096, "4K Storage Block"),
		_16K_STORAGE_BLOCK(Type.Item, 16384, "16K Storage Block"),
		_64K_STORAGE_BLOCK(Type.Item, 65536, "64K Storage Block"),
		CREATIVE_STORAGE_BLOCK(Type.Item, Integer.MAX_VALUE, "Creative Storage Block");


		protected Type type;
		protected String name;
		protected int size;

		Item(Type type, int size, String name) {
			this.type = type;
			this.size = size;
			this.name = name;
		}
	}

	String name;
	int size;
	Type type;
	public IMachineGuiHandler gui;


	public StorageBlock(Item item) {
		super(FabricBlockSettings.of(Material.METAL).strength(2f, 2f).build());
		this.name = item.name;
		this.size = item.size;
		this.type = item.type;
		this.gui = EGui.STORAGE_BLOCK;
	}

	@SuppressWarnings("deprecation")
	@Override
	public ActionResult onUse(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockHitResult hitResult) {
		ItemStack stack = playerIn.getStackInHand(Hand.MAIN_HAND);
		BlockEntity blockEntity = worldIn.getBlockEntity(pos);

		// We extended BlockTileBase. Thus we should always have blockEntity entity. I hope.
		if (blockEntity == null) {
			return ActionResult.FAIL;
		}

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

	
	//BaseBlockEntityProvider
	@Override
        public BlockEntity createBlockEntity(BlockView worldIn) {
		System.out.println("add entity for " + this.name);
		return new StorageBlockEntity(this.size, this.name);
        }
}
