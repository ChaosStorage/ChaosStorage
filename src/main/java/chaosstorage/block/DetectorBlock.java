package chaosstorage.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;

public class DetectorBlock extends ChaosBlock {
	public static final BooleanProperty POWERED = BooleanProperty.of("powered");

	public DetectorBlock() {
    super();
		this.setDefaultState(this.getStateManager().getDefaultState().with(POWERED, false));
  }

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(POWERED);
  }
}
