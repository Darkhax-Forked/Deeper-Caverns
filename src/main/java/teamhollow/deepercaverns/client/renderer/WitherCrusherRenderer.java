package teamhollow.deepercaverns.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.WitherSkeletonRenderer;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitherCrusherRenderer extends WitherSkeletonRenderer
{
	public WitherCrusherRenderer(EntityRendererManager renderManager)
	{
		super(renderManager);
	}

	@Override
	protected void preRenderCallback(AbstractSkeletonEntity entity, float partialTickTime)
	{
		GlStateManager.scalef(1.6F, 1.6F, 1.6F);
	}
}
