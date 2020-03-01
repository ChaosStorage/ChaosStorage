package chaosstorage.utils;

import chaosstorage.blockentity.CableEntity;
import chaosstorage.network.IController;
import chaosstorage.network.INetworkNode;
import chaosstorage.network.INetworkNodeProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DebugUtils {
	public static void printController(World world, BlockPos pos, Hand hand) {
		if (!world.isClient() && hand == Hand.MAIN_HAND) {
			printController(world, pos);
		}
	}
	public static void printController(World world, BlockPos pos) {
		BlockEntity _e = world.getBlockEntity(pos);
		if (_e instanceof INetworkNodeProvider) {
			INetworkNodeProvider e = (INetworkNodeProvider) _e;
			IController ctrl = e.getNetworkNode().getController();
			if (ctrl != null) {
				DebugUtils.dbg("Controller Position: " + ctrl.getControllerEntity().getPos());
			} else {
				DebugUtils.dbg("No controller");
			}
		} else {
			DebugUtils.dbg("Not an INetworkNodeProvider");
		}
	}

	public static void print(String text) {
		System.out.println("[ChaosStorage][INFO] " + text);
	}

	public static void dbg(String text) {
		System.out.println("[ChaosStorage][DEBUG] " + text);
	}
}
