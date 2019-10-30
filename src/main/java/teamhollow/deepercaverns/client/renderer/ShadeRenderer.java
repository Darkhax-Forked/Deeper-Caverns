package teamhollow.deepercaverns.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.client.model.ShadeModel;
import teamhollow.deepercaverns.entity.ShadeEntity;

@OnlyIn(Dist.CLIENT)
public class ShadeRenderer extends LivingRenderer<ShadeEntity,ShadeModel>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(DeeperCaverns.MODID, "textures/entity/shade.png");

	public ShadeRenderer(EntityRendererManager renderManager)
	{
		super(renderManager, new ShadeModel(), 0.25F);
	}

	@Override
	protected boolean canRenderName(ShadeEntity entity)
	{
		return super.canRenderName(entity) && entity.hasCustomName();
	}

	@Override
	protected ResourceLocation getEntityTexture(ShadeEntity entity)
	{
		return TEXTURE;
	}
}
