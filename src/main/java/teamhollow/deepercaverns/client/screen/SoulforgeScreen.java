package teamhollow.deepercaverns.client.screen;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.container.SoulforgeContainer;

public class SoulforgeScreen extends ContainerScreen<SoulforgeContainer>
{
	public static final ResourceLocation TEXTURE = new ResourceLocation(DeeperCaverns.MODID, "textures/gui/container/soulforge.png");

	public SoulforgeScreen(SoulforgeContainer screenContainer, PlayerInventory inv, ITextComponent title)
	{
		super(screenContainer, inv, title);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		int xPos = (width - xSize) / 2;
		int yPos = (height - ySize) / 2;

		renderBackground();
		minecraft.getTextureManager().bindTexture(TEXTURE);
		blit(xPos, yPos, 0, 0, xSize, ySize);
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		super.render(mouseX, mouseY, partialTicks);

		renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String title = getTitle().getFormattedText();

		font.drawString(title, (xSize / 2) - (font.getStringWidth(title) / 2), 5, 0x404040);
		font.drawString(playerInventory.getDisplayName().getFormattedText(), 8, ySize - 93, 0x404040);
	}
}
