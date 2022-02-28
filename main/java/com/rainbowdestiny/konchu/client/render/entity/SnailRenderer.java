package com.rainbowdestiny.konchu.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.rainbowdestiny.konchu.client.render.model.SnailModel;
import com.rainbowdestiny.konchu.entity.SnailEntity;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.vector.Vector3f;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SnailRenderer extends GeoEntityRenderer<SnailEntity> {
	public SnailRenderer(EntityRendererManager renderManager) {
		super(renderManager, new SnailModel());
	}

	@Override
	public void render(SnailEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {

		// attempting to tweak rotations along wall
		
		if (entity.isClimbing()) {
			int rotation = getRotation(entity.yBodyRot + 180);
			System.out.println(rotation);
			switch (rotation) {
				case 0: default: {
					stack.mulPose(Vector3f.XP.rotationDegrees(-90));
					stack.mulPose(Vector3f.ZP.rotationDegrees(180));

					break;
				}
				case 1: {
					stack.mulPose(Vector3f.ZP.rotationDegrees(90));
					break;
				}
				case 2: {
					stack.mulPose(Vector3f.XP.rotationDegrees(-90));
					break;
				}
				case 3: {
					stack.mulPose(Vector3f.ZP.rotationDegrees(-90));
					break;			
				}
			}
			stack.translate(0, 4 / 16D, 0);
		}
			
		super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);

	}

	public static int getRotation(float rotation) {
		if (rotation > (0 + 45) && rotation < (90 + 45)) return 1; // E
		if (rotation > (90 + 45) && rotation < (180 + 45)) return 2; // S
		if (rotation > (180 + 45) && rotation < (270 + 45)) return 3; // W
		return 0; // N
	}
}