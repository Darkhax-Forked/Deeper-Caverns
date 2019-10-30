package teamhollow.deepercaverns.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.client.model.ArcaneModel;
import teamhollow.deepercaverns.entity.ArcaneEntity;

@OnlyIn(Dist.CLIENT)
public class ArcaneRenderer extends LivingRenderer<ArcaneEntity,ArcaneModel>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(DeeperCaverns.MODID, "textures/entity/arcane.png");

	public ArcaneRenderer(EntityRendererManager renderManager)
	{
		super(renderManager, new ArcaneModel(), 1.0F);
	}

	@Override
	protected boolean canRenderName(ArcaneEntity entity)
	{
		return super.canRenderName(entity) && entity.hasCustomName();
	}

	@Override
	protected ResourceLocation getEntityTexture(ArcaneEntity entity)
	{
		return TEXTURE;
	}
}
