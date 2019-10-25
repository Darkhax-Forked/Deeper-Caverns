package teamhollow.deepercaverns.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import teamhollow.deepercaverns.entity.WitherCrusherEntity;

/**
 * @author Endergized
 */
public class WitherCrusherModel extends BipedModel<WitherCrusherEntity>
{
	public RendererModel ribs;
	public RendererModel ribsL;
	public RendererModel headL;
	public RendererModel leftArmL;
	public RendererModel rightArmL;
	public RendererModel leftLegL;
	public RendererModel rightLegL;

	public WitherCrusherModel()
	{
		textureWidth = 164;
		textureHeight = 132;
		bipedHead = new RendererModel(this, 0, 0);
		bipedHead.setRotationPoint(0.0F, -14.0F, -4.0F);
		bipedHead.addBox(-5.0F, -10.0F, -7.0F, 10, 10, 10, 0.0F);
		setRotateAngle(bipedHead, -0.045553093477052F, 0.0F, 0.0F);
		bipedLeftLeg = new RendererModel(this, 90, 0);
		bipedLeftLeg.setRotationPoint(5.0F, 12.0F, 0.0F);
		bipedLeftLeg.addBox(-3.5F, 0.0F, -3.5F, 7, 22, 7, 0.0F);
		setRotateAngle(bipedLeftLeg, 0.0F, -0.17453292519943295F, 0.0F);
		leftLegL = new RendererModel(this, 60, 80);
		leftLegL.setRotationPoint(-4.0F, -0.5F, -4.0F);
		leftLegL.addBox(0.0F, 0.0F, 0.0F, 8, 23, 8, 0.0F);
		bipedRightLeg = new RendererModel(this, 90, 0);
		bipedRightLeg.mirror = true;
		bipedRightLeg.setRotationPoint(-5.0F, 12.0F, 0.0F);
		bipedRightLeg.addBox(-3.5F, 0.0F, -3.5F, 7, 22, 7, 0.0F);
		setRotateAngle(bipedRightLeg, 0.0F, 0.17453292519943295F, 0.0F);
		bipedRightArm = new RendererModel(this, 120, 0);
		bipedRightArm.mirror = true;
		bipedRightArm.setRotationPoint(-13.0F, -10.0F, -6.0F);
		bipedRightArm.addBox(-3.0F, -10.0F, -3.0F, 6, 35, 6, 0.0F);
		setRotateAngle(bipedRightArm, -0.2617993877991494F, 0.17453292519943295F, 0.0F);
		rightArmL = new RendererModel(this, 30, 82);
		rightArmL.setRotationPoint(-3.5F, -10.5F, -3.5F);
		rightArmL.addBox(0.0F, 0.0F, 0.0F, 7, 36, 7, 0.0F);
		rightLegL = new RendererModel(this, 94, 80);
		rightLegL.setRotationPoint(-4.0F, -0.5F, -4.0F);
		rightLegL.addBox(0.0F, 0.0F, 0.0F, 8, 23, 8, 0.0F);
		ribs = new RendererModel(this, 0, 24);
		ribs.setRotationPoint(0.0F, 0.0F, 5.0F);
		ribs.addBox(-10.0F, -15.0F, -15.0F, 20, 15, 15, 0.0F);
		setRotateAngle(ribs, 0.3490658503988659F, -0.019198621771937624F, 0.0F);
		headL = new RendererModel(this, 0, 60);
		headL.setRotationPoint(-5.5F, -10.5F, -7.5F);
		headL.addBox(0.0F, 0.0F, 0.0F, 11, 11, 11, 0.0F);
		bipedLeftArm = new RendererModel(this, 120, 0);
		bipedLeftArm.setRotationPoint(13.0F, -10.0F, -6.0F);
		bipedLeftArm.addBox(-3.0F, -10.0F, -3.0F, 6, 35, 6, 0.0F);
		setRotateAngle(bipedLeftArm, -0.2617993877991494F, -0.17453292519943295F, 0.0F);
		bipedBody = new RendererModel(this, 40, 0);
		bipedBody.setRotationPoint(0.0F, -10.0F, 2.5F);
		bipedBody.addBox(-7.5F, 0.0F, -5.0F, 15, 13, 10, 0.0F);
		ribsL = new RendererModel(this, 60, 40);
		ribsL.setRotationPoint(-10.5F, -15.5F, -15.5F);
		ribsL.addBox(0.0F, 0.0F, 0.0F, 21, 16, 16, 0.0F);
		setRotateAngle(ribsL, 0.36425021489121656F, 0.0F, 0.0F);
		leftArmL = new RendererModel(this, 0, 82);
		leftArmL.setRotationPoint(-3.5F, -10.5F, -3.5F);
		leftArmL.addBox(0.0F, 0.0F, 0.0F, 7, 36, 7, 0.0F);
		ribs.addChild(bipedHead);
		bipedBody.addChild(bipedLeftLeg);
		bipedLeftLeg.addChild(leftLegL);
		bipedBody.addChild(bipedRightLeg);
		ribs.addChild(bipedRightArm);
		bipedRightArm.addChild(rightArmL);
		bipedRightLeg.addChild(rightLegL);
		bipedBody.addChild(ribs);
		bipedHead.addChild(headL);
		ribs.addChild(bipedLeftArm);
		ribs.addChild(ribsL);
		bipedLeftArm.addChild(leftArmL);
	}

	@Override
	public void render(WitherCrusherEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
	{
		bipedBody.render(scaleFactor);
	}

	@Override
	public void setRotationAngles(WitherCrusherEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
	{
		//TODO
	}

	public void setRotateAngle(RendererModel rendererModel, float x, float y, float z)
	{
		rendererModel.rotateAngleX = x;
		rendererModel.rotateAngleY = y;
		rendererModel.rotateAngleZ = z;
	}
}
