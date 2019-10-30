package teamhollow.deepercaverns.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import teamhollow.deepercaverns.entity.SoulfurBlockEntity;
import teamhollow.deepercaverns.reg.BlockRegistrar;

public class SoulfurBlockEntityRenderer extends EntityRenderer<SoulfurBlockEntity>
{
	public SoulfurBlockEntityRenderer(EntityRendererManager renderManager)
	{
		super(renderManager);
		this.shadowSize = 0.5F;
	}

	@Override
	public void doRender(SoulfurBlockEntity entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		BlockRendererDispatcher brd = Minecraft.getInstance().getBlockRendererDispatcher();
		GlStateManager.pushMatrix();
		GlStateManager.translatef((float)x, (float)y + 0.5F, (float)z);

		if (entity.getFuse() - partialTicks + 1.0F < 10.0F)
		{
			float f = 1.0F - (entity.getFuse() - partialTicks + 1.0F) / 10.0F;
			float scale;

			f = MathHelper.clamp(f, 0.0F, 1.0F);
			f = f * f;
			f = f * f;
			scale = 1.0F + f * 0.3F;
			GlStateManager.scalef(scale, scale, scale);
		}

		float alpha = (1.0F - (entity.getFuse() - partialTicks + 1.0F) / 100.0F) * 0.8F;

		bindEntityTexture(entity);
		GlStateManager.rotatef(-90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
		brd.renderBlockBrightness(BlockRegistrar.SOULFUR_BLOCK.getDefaultState(), entity.getBrightness());
		GlStateManager.translatef(0.0F, 0.0F, 1.0F);

		if(renderOutlines)
		{
			GlStateManager.enableColorMaterial();
			GlStateManager.setupSolidRenderingTextureCombine(getTeamColor(entity));
			brd.renderBlockBrightness(BlockRegistrar.SOULFUR_BLOCK.getDefaultState(), 1.0F);
			GlStateManager.tearDownSolidRenderingTextureCombine();
			GlStateManager.disableColorMaterial();
		}
		else if(entity.getFuse() / 5 % 2 == 0)
		{
			GlStateManager.disableTexture();
			GlStateManager.disableLighting();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.DST_ALPHA);
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, alpha);
			GlStateManager.polygonOffset(-3.0F, -3.0F);
			GlStateManager.enablePolygonOffset();
			brd.renderBlockBrightness(BlockRegistrar.SOULFUR_BLOCK.getDefaultState(), 1.0F);
			GlStateManager.polygonOffset(0.0F, 0.0F);
			GlStateManager.disablePolygonOffset();
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.disableBlend();
			GlStateManager.enableLighting();
			GlStateManager.enableTexture();
		}

		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(SoulfurBlockEntity entity)
	{
		return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
	}
}
