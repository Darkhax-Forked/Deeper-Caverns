package teamhollow.deepercaverns.client.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import teamhollow.deepercaverns.entity.ShadeEntity;

public class ShadeModel extends EntityModel<ShadeEntity>
{
	private final RendererModel shade;
	private final RendererModel arm1;
	private final RendererModel arm2;

	public ShadeModel()
	{
		textureWidth = 16;
		textureHeight = 16;

		shade = new RendererModel(this);
		shade.setRotationPoint(-1.0F, 16.3333F, 2.8333F);
		shade.cubeList.add(new ModelBox(shade, 0, 0, -2.0F, -9.3333F, -5.3333F, 6, 6, 6, 0.0F, false));
		shade.cubeList.add(new ModelBox(shade, 0, 0, -1.5F, -3.3333F, -4.8333F, 5, 10, 0, 0.0F, false));
		shade.cubeList.add(new ModelBox(shade, 0, 0, -1.5F, -3.3333F, 0.1667F, 5, 10, 0, 0.0F, false));
		shade.cubeList.add(new ModelBox(shade, 0, 0, 3.5F, -3.3333F, -4.8333F, 0, 10, 5, 0.0F, false));
		shade.cubeList.add(new ModelBox(shade, 0, 0, -1.5F, -3.3333F, -4.8333F, 0, 10, 5, 0.0F, false));

		arm1 = new RendererModel(this);
		arm1.setRotationPoint(0.0F, -0.3333F, -2.8333F);
		setRotationAngle(arm1, 0.0F, 0.0F, 0.6109F);
		shade.addChild(arm1);
		arm1.cubeList.add(new ModelBox(arm1, 0, 0, 1.5F, -4.0F, -0.5F, 6, 2, 2, 0.0F, false));

		arm2 = new RendererModel(this);
		arm2.setRotationPoint(0.0F, -0.3333F, -2.8333F);
		setRotationAngle(arm2, 0.0F, 0.0F, -0.6109F);
		shade.addChild(arm2);
		arm2.cubeList.add(new ModelBox(arm2, 0, 0, -5.5F, -2.8F, -0.5F, 6, 2, 2, 0.0F, false));
	}

	@Override
	public void render(ShadeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		shade.render(scale);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}