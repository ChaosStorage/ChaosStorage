package chaosstorage.item;

import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import reborncore.api.IListInfoProvider;
import reborncore.common.util.ItemDurabilityExtensions;
import reborncore.common.util.ItemUtils;
import reborncore.common.powerSystem.PowerSystem;
import team.reborn.energy.EnergyHolder;
import team.reborn.energy.EnergyTier;

import chaosstorage.config.ChaosStorageConfig;

import java.util.List;

public class WirelessGrid extends Item implements EnergyHolder, ItemDurabilityExtensions/*, IListInfoProvider*/ {
	public enum Type {
		CRAFTING(),
		FLUID(),
		NORMAL();

		public int getEnergyUsage() {
			// TODO: implement
			return 0;
		}

	}

	private final Type type;
	private boolean linked;

	public WirelessGrid(Type type, Settings itemGroup) {
		super(itemGroup);
		this.type = type;
		this.linked = false;
	}

	public Type getType() {
		return type;
	}

	// IListInfoProvider
	/*@Override
	public void addInfo(List<Text> info, boolean isReal, boolean hasData) {
		//super.addInfo(info, isReal, hasData);
		if (!this.linked) {
			info.add(
				new TranslatableText("item.chaosstorage.wireless_unlinked")
				    .setStyle(new Style().setColor(Formatting.GRAY))
			);
		}
	}*/

	// ItemDurabilityExtensions
	@Override
	public double getDurability(ItemStack stack) {
		return 1 - ItemUtils.getPowerForDurabilityBar(stack);
	}

	@Override
	public boolean showDurability(ItemStack stack) {
		/*if (ItemUtils.getPowerForDurabilityBar(stack) == 1.0) {
			return false;
		}*/
		return true;
	}

	@Override
	public int getDurabilityColor(ItemStack stack) {
		return PowerSystem.getDisplayPower().colour;
	}

	// EnergyHolder
	@Override
	public double getMaxStoredPower() {
		return ChaosStorageConfig.WirelessGridStoredPower;
	}
	@Override
	public EnergyTier getTier() {
		return EnergyTier.HIGH;
	}
}
