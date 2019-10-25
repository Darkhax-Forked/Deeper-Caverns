package teamhollow.deepercaverns.client.renderer;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.client.model.WitherCrusherModel;
import teamhollow.deepercaverns.entity.WitherCrusherEntity;

@OnlyIn(Dist.CLIENT)
public class WitherCrusherRenderer extends BipedRenderer<WitherCrusherEntity,WitherCrusherModel>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(DeeperCaverns.MODID, "textures/entity/wither_crusher.png");

	public WitherCrusherRenderer(EntityRendererManager renderManager)
	{
		super(renderManager, new WitherCrusherModel(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(WitherCrusherEntity entity)
	{
		return TEXTURE;
	}
}
