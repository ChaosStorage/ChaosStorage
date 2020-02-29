
package chaosstorage.client.gui.guibuilder;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import reborncore.client.gui.builder.GuiBase;
import reborncore.common.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GuiBuilder extends reborncore.client.gui.guibuilder.GuiBuilder {

	static Identifier resourceLocation;

	public GuiBuilder() {
		this.resourceLocation = defaultTextureSheet;
	}

	public void drawStorageBar(GuiBase<?> gui, int x, int y, int stored, int maxStored, int mouseX,
			int mouseY, int buttonID, GuiBase.Layer layer) {

		// vars?
		int xBar = 141;
		int yBar = 151;

		if (gui.hideGuiElements()) return;
		if (layer == GuiBase.Layer.BACKGROUND) {
			x += gui.getGuiLeft();
			y += gui.getGuiTop();
		}

		MinecraftClient.getInstance().getTextureManager().bindTexture(resourceLocation);
		gui.blit(x, y, xBar - 15, yBar -1, 14, 50);
		int draw = (int) ((double) stored / maxStored * (48));
		if (stored > maxStored) {
			draw = 48;
		}
		gui.blit(x + 1, y + 49 - draw, xBar, 48 + yBar - draw, 12, draw);
		int percentage = percentage(maxStored, stored);
		if (gui.isPointInRect(x + 1, y + 1, 11, 48, mouseX, mouseY)) { // Mouse tooltip
			List<Text> list = Lists.newArrayList();
			list.add(new LiteralText(stored /* TODO: format */ + "/" + maxStored /* TODO: format */ +
						" Bytes").formatted(Formatting.GOLD));
			list.add(new LiteralText(StringUtils.getPercentageColour(percentage) + "" + percentage +
						"%" + Formatting.GRAY + " " + "Used" /*StringUtils.t()*/));
			if (layer == GuiBase.Layer.FOREGROUND) {
				mouseX -= gui.getGuiLeft();
				mouseY -= gui.getGuiTop();
			}
			List<String> list1 = Lists.newArrayList();
			for (Text itextcomponent : list) {
				list1.add(itextcomponent.asFormattedString());
			}
			gui.renderTooltip(list1, mouseX, mouseY);
			RenderSystem.disableLighting();
			RenderSystem.color4f(1, 1, 1, 1);
		}
	}
}
