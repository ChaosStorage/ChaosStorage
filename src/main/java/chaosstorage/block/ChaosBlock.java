package chaosstorage.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.block.Material;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ChaosBlock extends Block {
  public boolean hasDirectionState, hasConnectedState;
  public static boolean hasDirectionState2, hasConnectedState2;
  public static final DirectionProperty DIRECTION = DirectionProperty.of("direction", new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP, Direction.DOWN});
  public static final BooleanProperty CONNECTED = BooleanProperty.of("connected");

  public ChaosBlock() {
    this(false, false);
  }

  /*
   * This is a huge hack: We want to share this class between blocks that have a
   * direction property in their BlockState, and those that don't.
   * But because the Block() constructor calls appendProperties(), we will not
   * have had any chance to apply the hasDirectionState parameter to the new
   * object. This is why we have the static field hasDirectionState2, which is
   * used to pass this information into appendProperties, assuming that the
   * static variable will not be changed between the call of
   * rememberStateSettings() and the call of appendProperties().
   */
  private static Settings rememberStateSettings(Settings settings, boolean hasDirectionState, boolean hasConnectedState) {
    hasDirectionState2 = hasDirectionState;
    hasConnectedState2 = hasConnectedState;
    return settings;
  }

  public ChaosBlock(boolean hasDirectionState, boolean hasConnectedState) {
    super(rememberStateSettings(FabricBlockSettings.of(Material.STONE).strength(1.9f, 1.9f).sounds(BlockSoundGroup.STONE).build(), hasDirectionState, hasConnectedState));

    this.hasDirectionState = hasDirectionState;
    this.hasConnectedState = hasConnectedState;

    if (hasDirectionState) {
      this.setDefaultState(this.getStateManager().getDefaultState().with(DIRECTION, Direction.NORTH));
    }

    if (hasConnectedState) {
      this.setDefaultState(this.getStateManager().getDefaultState().with(CONNECTED, false));
    }
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    if (hasDirectionState2) {
      builder.add(DIRECTION);
    }
    if (hasConnectedState2) {
      builder.add(CONNECTED);
    }
  }

  @SuppressWarnings("deprecation")
  @Override
  public BlockState rotate(BlockState state, BlockRotation rotation) {
    if (hasDirectionState) {
      return state.with(DIRECTION, rotation.rotate(state.get(DIRECTION)));
    } else {
      return super.rotate(state, rotation);
    }
  }

  @Override
  public void onPlaced(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
    super.onPlaced(worldIn, pos, state, placer, stack);
    if (hasDirectionState) {
      Direction direction = placer.getHorizontalFacing().getOpposite();
      if (placer.pitch < -50) {
        direction = Direction.DOWN;
      } else if (placer.pitch > 50) {
        direction = Direction.UP;
      }
      setDirection(direction, worldIn, pos);
    }
  }

  public void setDirection(Direction direction, World world, BlockPos pos) {
    world.setBlockState(pos, world.getBlockState(pos).with(DIRECTION, direction));
  }
}
