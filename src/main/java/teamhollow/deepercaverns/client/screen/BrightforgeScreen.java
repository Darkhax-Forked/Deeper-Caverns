package teamhollow.deepercaverns.client.screen;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import teamhollow.deepercaverns.container.BrightforgeContainer;
import teamhollow.deepercaverns.tileentity.BrightforgeTileEntity;

public class BrightforgeScreen extends ContainerScreen<BrightforgeContainer>
{
	public static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "textures/gui/container/furnace.png");
	private final BrightforgeTileEntity te;

	public BrightforgeScreen(BrightforgeContainer screenContainer, PlayerInventory inv, ITextComponent title)
	{
		super(screenContainer, inv, title);

		te = screenContainer.te;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		int xPos = (width - xSize) / 2;
		int yPos = (height - ySize) / 2;

		renderBackground();
		minecraft.getTextureManager().bindTexture(TEXTURE);
		blit(xPos, yPos, 0, 0, xSize, ySize);

		if(te.isBurning())
		{
			int k = getBurnLeftScaled();

			blit(guiLeft + 56, guiTop + 36 + 12 - k, 176, 12 - k, 14, k + 1);
		}

		int l = getCookProgressionScaled();

		blit(guiLeft + 79, guiTop + 34, 176, 14, l + 1, 16);
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

	public int getCookProgressionScaled()
	{
		int cookTime = te.getCookTime();
		int cookTimeTotal = te.getCookTimeNeeded();

		return cookTimeTotal != 0 && cookTime != 0 ? cookTime * 24 / cookTimeTotal : 0;
	}

	public int getBurnLeftScaled()
	{
		return te.getBurnTime() * 13 / te.getDefaultBurnTime();
	}
}
