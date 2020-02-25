package chaosstorage.block;

import chaosstorage.ChaosStorage;
import chaosstorage.blockentity.CableEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.block.BlockEntityProvider;


import javax.annotation.Nullable;

public class CableBlock extends ChaosBlock implements BlockEntityProvider {

	public static BooleanProperty NORTH;
	public static BooleanProperty SOUTH;
	public static BooleanProperty EAST;
	public static BooleanProperty WEST;
	public static BooleanProperty UP;
	public static BooleanProperty DOWN;

	private static final VoxelShape SHAPE_CORE = createCuboidShape(6, 6, 6, 10, 10, 10);
	private static final VoxelShape SHAPE_NORTH = createCuboidShape(6, 6, 0, 10, 10, 6);
	private static final VoxelShape SHAPE_EAST = createCuboidShape(10, 6, 6, 16, 10, 10);
	private static final VoxelShape SHAPE_SOUTH = createCuboidShape(6, 6, 10, 10, 10, 16);
	private static final VoxelShape SHAPE_WEST = createCuboidShape(0, 6, 6, 6, 10, 10);
	private static final VoxelShape SHAPE_UP = createCuboidShape(6, 10, 6, 10, 16, 10);
	private static final VoxelShape SHAPE_DOWN = createCuboidShape(6, 0, 6, 10, 6, 10);

	@Override
	@SuppressWarnings("deprecation")
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos) {
		VoxelShape shape = SHAPE_CORE;

		if (state.get(NORTH)) {
			shape = VoxelShapes.union(shape, SHAPE_NORTH);
		}

		if (state.get(EAST)) {
			shape = VoxelShapes.union(shape, SHAPE_EAST);
		}

		if (state.get(SOUTH)) {
			shape = VoxelShapes.union(shape, SHAPE_SOUTH);
		}

		if (state.get(WEST)) {
			shape = VoxelShapes.union(shape, SHAPE_WEST);
		}

		if (state.get(UP)) {
			shape = VoxelShapes.union(shape, SHAPE_UP);
		}

		if (state.get(DOWN)) {
			shape = VoxelShapes.union(shape, SHAPE_DOWN);
		}

		return shape;
	}

	public CableBlock() {
		super();
		this.setDefaultState(this.getStateManager().getDefaultState()
				.with(NORTH, false)
				.with(SOUTH, false)
				.with(EAST, false)
				.with(WEST, false)
				.with(UP, false)
				.with(DOWN, false));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		NORTH = BooleanProperty.of("north");
		SOUTH = BooleanProperty.of("south");
		EAST = BooleanProperty.of("east");
		WEST = BooleanProperty.of("west");
		UP = BooleanProperty.of("up");
		DOWN = BooleanProperty.of("down");
		builder
				.add(NORTH)
				.add(SOUTH)
				.add(EAST)
				.add(WEST)
				.add(UP)
				.add(DOWN);
	}

	/*@Override
	protected void onDirectionChanged(World world, BlockPos pos, Direction newDirection) {
		// rotate() in BaseBlock "stupidly" changes the direction without checking if our cable connections are still valid.
		// You'd expect that cable connections are not changing when simply changing the direction.
		// But they need to. For example, when rotating a constructor to connect to a neighboring cable, the connection to that neighbor
		// needs to be removed as otherwise the "holder" of the constructor will conflict with the cable connection.
		// This is already checked in hasNode().
		// But since rotate() doesn't invalidate that connection, we need to do it here.
		// Ideally, this code would be in rotate(). But rotate() doesn't have any data about the position and world, so we need to do it here.
		world.setBlockState(pos, getState(world.getBlockState(pos), world, pos));

		super.onDirectionChanged(world, pos, newDirection);
	}*/

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return getState(getDefaultState(), ctx.getWorld(), ctx.getBlockPos());
	}

	@Override
	@SuppressWarnings("deprecation")
	public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos) {
		return getState(state, world, pos);
	}

	private boolean hasNode(IWorld world, BlockPos pos, BlockState state, Direction direction) {
		// Prevent the "holder" of a cable block conflicting with a cable connection.
		/*if (getDirection() != BlockDirection.NONE && state.get(getDirection().getProperty()).getOpposite() == direction) {
			return false;
		}*/

		BlockEntity e = world.getBlockEntity(pos);
		if (e == null) {
			return false;
		}

		return true; //e.getCapability(NetworkNodeProxyCapability.NETWORK_NODE_PROXY_CAPABILITY, direction).isPresent();
	}

	private BlockState getState(BlockState currentState, IWorld world, BlockPos pos) {
		boolean north = hasNode(world, pos.offset(Direction.NORTH), currentState, Direction.SOUTH);
		boolean east = hasNode(world, pos.offset(Direction.EAST), currentState, Direction.WEST);
		boolean south = hasNode(world, pos.offset(Direction.SOUTH), currentState, Direction.NORTH);
		boolean west = hasNode(world, pos.offset(Direction.WEST), currentState, Direction.EAST);
		boolean up = hasNode(world, pos.offset(Direction.UP), currentState, Direction.DOWN);
		boolean down = hasNode(world, pos.offset(Direction.DOWN), currentState, Direction.UP);

		return currentState
				.with(NORTH, north)
				.with(EAST, east)
				.with(SOUTH, south)
				.with(WEST, west)
				.with(UP, up)
				.with(DOWN, down);
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		return new CableEntity();
	}
}