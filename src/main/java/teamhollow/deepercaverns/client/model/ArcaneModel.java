package teamhollow.deepercaverns.client.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import teamhollow.deepercaverns.entity.ArcaneEntity;

public class ArcaneModel extends EntityModel<ArcaneEntity>
{
	private final RendererModel arcane;

	public ArcaneModel()
	{
		textureWidth = 16;
		textureHeight = 16;

		arcane = new RendererModel(this);
		arcane.setRotationPoint(0.0F, 24.0F, 0.0F);
		arcane.cubeList.add(new ModelBox(arcane, 0, 0, -4.0F, -24.0F, -2.0F, 8, 12, 4, 0.0F, false));
		arcane.cubeList.add(new ModelBox(arcane, 0, 0, -4.0F, -12.0F, -2.0F, 4, 12, 4, 0.0F, false));
		arcane.cubeList.add(new ModelBox(arcane, 0, 0, 0.0F, -12.0F, -2.0F, 4, 12, 4, 0.0F, false));
		arcane.cubeList.add(new ModelBox(arcane, 0, 0, -4.0F, -32.0F, -4.0F, 8, 8, 8, 0.0F, false));
		arcane.cubeList.add(new ModelBox(arcane, 0, 0, -4.5F, -33.0F, -5.0F, 9, 9, 9, 0.0F, false));
		arcane.cubeList.add(new ModelBox(arcane, 0, 0, -8.0F, -24.0F, -2.0F, 4, 12, 4, 0.0F, false));
		arcane.cubeList.add(new ModelBox(arcane, 0, 0, 4.0F, -24.0F, -2.0F, 4, 12, 4, 0.0F, false));
	}

	@Override
	public void render(ArcaneEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		arcane.render(scale);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}