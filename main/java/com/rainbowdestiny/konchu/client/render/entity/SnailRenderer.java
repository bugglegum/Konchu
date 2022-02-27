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
		
		if(entity.isClimbing()) {
			int rotation = getRotation((int) entityYaw + 180);
			// rotation * 90  	== get 0/90/180/270 cardinal
			// entityYaw + 180	== get direction it's looking at
			// cardinal - direction it's looking at
			
			// Ex. Needs to be at 0deg, it's at 45deg (once added with 180) 
			// 0 - 45 == -45, snail must move -45deg to set itself to be 0
			stack.mulPose(Vector3f.YP.rotationDegrees(rotation * 90 - (entityYaw + 180)));
		}

		super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);

	}
	
	private int getRotation(int rotation) {
		if (rotation > (  0 + 45) && rotation < ( 90 + 45)) return 1; // E
		if (rotation > ( 90 + 45) && rotation < (180 + 45)) return 2; // S
		if (rotation > (180 + 45) && rotation < (270 + 45)) return 3; // W
		return 0; 													  // N
	}
}