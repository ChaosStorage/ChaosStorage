package chaosstorage.network;

import chaosstorage.blockentity.CableEntity;
import chaosstorage.block.CableBlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collector;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;

public class CableNode extends NetworkNode {
    private CableEntity blockEntity;

    public CableNode(CableEntity blockEntity) {
        super(blockEntity);
        this.blockEntity = blockEntity;
    }

    @Override
    public Direction[] getConnectionDirections() {
        BlockState bs = blockEntity.getWorld().getBlockState(blockEntity.getPos());
        CableBlock block = (CableBlock) bs.getBlock();

        if (block.hasDirectionState) {
            Direction blockDirection = bs.get(block.DIRECTION);
            return Arrays.stream(Direction.values()).filter(direction -> direction != blockDirection).toArray(Direction[]::new);
        } else {
            return Direction.values();
        }
    }
}
