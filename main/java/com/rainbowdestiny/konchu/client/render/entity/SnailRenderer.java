package com.rainbowdestiny.konchu.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.rainbowdestiny.konchu.client.render.model.SnailModel;
import com.rainbowdestiny.konchu.entity.SnailEntity;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SnailRenderer extends GeoEntityRenderer<SnailEntity> {
	public SnailRenderer(EntityRendererManager renderManager) {
		super(renderManager, new SnailModel());
	}

	@Override
	public void render(SnailEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {		
		if (entity.isClimbing()) {
			int rotation = getRotation(entity);
			switch (rotation) {
				case 0: default: {
					stack.mulPose(Vector3f.XP.rotationDegrees(90));
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
			stack.translate(0, -4 / 16D, 0);
		}
			
		super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);

	}

	public static int getRotation(SnailEntity entity) {
		if(entity == null || entity.level == null) return 0;
		
		float rotation = (entity.yBodyRot + 180);
		World world = entity.level;
		BlockPos pos = new BlockPos(entity.position());
		
		if (rotation > (0 + 45) && rotation < (90 + 45) && !world.isEmptyBlock(pos.east())) return 1; // E
		if (rotation > (90 + 45) && rotation < (180 + 45) && !world.isEmptyBlock(pos.south())) return 2; // S
		if (rotation > (180 + 45) && rotation < (270 + 45) && !world.isEmptyBlock(pos.west())) return 3; // W
		else if(!world.isEmptyBlock(pos.north())) return 0;
		return getRotationFromBlock(pos, world); // N
	}
	
	public static int getRotationFromBlock(BlockPos pos, World world) {
		if(world == null) return 0;
		
		if(!world.isEmptyBlock(pos.north())) return 0; // N
		if(!world.isEmptyBlock(pos.east() )) return 1; // E
		if(!world.isEmptyBlock(pos.south())) return 2; // S
		return 3; // W
	}
	
}