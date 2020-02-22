package chaosstorage.item;

import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.text.TranslatableText;
import net.minecraft.item.Item;
import reborncore.api.IListInfoProvider;
import chaosstorage.config.ChaosStorageConfig;

import java.util.List;

public class UpgradeItem extends Item implements IListInfoProvider {

    public enum Type {
        NORMAL(),
        SPEED(),
        RANGE(),
        CRAFTING(),
        STACK(),
        SILK_TOUCH(),
        FORTUNE_1(),
        FORTUNE_2(),
        FORTUNE_3();

        public int getEnergyUsage() {
            switch (this) {
                case NORMAL:
                    return 0;
                case RANGE:
		    return ChaosStorageConfig.RangeUpgradeUsage;
                case SPEED:
		    return ChaosStorageConfig.SpeedUpgradeUsage;
                case CRAFTING:
		    return ChaosStorageConfig.CraftingUpgradeUsage;
                case STACK:
		    return ChaosStorageConfig.StackUpgradeUsage;
                case SILK_TOUCH:
		    return ChaosStorageConfig.SilkTouchUpgradeUsage;
                case FORTUNE_1:
		    return ChaosStorageConfig.Fortune1UpgradeUsage;
                case FORTUNE_2:
		    return ChaosStorageConfig.Fortune2UpgradeUsage;
                case FORTUNE_3:
		    return ChaosStorageConfig.Fortune3UpgradeUsage;
                default:
                    throw new IllegalStateException("What even am I?");
            }
        }

        public int getFortuneLevel() {
            switch (this) {
                case FORTUNE_1:
                    return 1;
                case FORTUNE_2:
                    return 2;
                case FORTUNE_3:
                    return 3;
                default:
                    return 0;
            }
        }
    }

    private final Type type;

    public UpgradeItem(Type type, Settings itemGroup) {
        super(itemGroup);
        this.type = type;
    }

    @Override
    public void addInfo(List<Text> info, boolean isReal, boolean hasData) {

        if (type.getFortuneLevel() > 0) {
            info.add(
                    new TranslatableText("enchantment.minecraft.fortune")
                            .append(" ")
                            .append(new TranslatableText("enchantment.level." + type.getFortuneLevel()))
                            .setStyle(new Style().setColor(Formatting.GRAY))
            );
        }
    }

    public Type getType() {
        return type;
    }
}
