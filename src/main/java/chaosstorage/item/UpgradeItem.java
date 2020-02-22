package chaosstorage.item;

import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.text.TranslatableText;
import net.minecraft.item.Item;
import reborncore.api.IListInfoProvider;

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

        /*public int getEnergyUsage() {
            switch (this) {
                case NORMAL:
                    return 0;
                case RANGE:
                    return RS.SERVER_CONFIG.getUpgrades().getRangeUpgradeUsage();
                case SPEED:
                    return RS.SERVER_CONFIG.getUpgrades().getSpeedUpgradeUsage();
                case CRAFTING:
                    return RS.SERVER_CONFIG.getUpgrades().getCraftingUpgradeUsage();
                case STACK:
                    return RS.SERVER_CONFIG.getUpgrades().getStackUpgradeUsage();
                case SILK_TOUCH:
                    return RS.SERVER_CONFIG.getUpgrades().getSilkTouchUpgradeUsage();
                case FORTUNE_1:
                    return RS.SERVER_CONFIG.getUpgrades().getFortune1UpgradeUsage();
                case FORTUNE_2:
                    return RS.SERVER_CONFIG.getUpgrades().getFortune2UpgradeUsage();
                case FORTUNE_3:
                    return RS.SERVER_CONFIG.getUpgrades().getFortune3UpgradeUsage();
                default:
                    throw new IllegalStateException("What even am I?");
            }
        }*/

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
