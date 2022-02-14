package com.rainbowdestiny.konchu.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.rainbowdestiny.konchu.Konchu;
import com.rainbowdestiny.konchu.entity.ChairEntity;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EmptyRenderer extends EntityRenderer<ChairEntity> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(Konchu.MOD_ID, "textures/block/lichen_block.png");

	public EmptyRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	public ResourceLocation getTextureLocation(ChairEntity entity) {
		return TEXTURE;
	}

	@Override
	public void render(ChairEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		matrixStackIn.translate(0, 0.15f, 0);
		matrixStackIn.translate(0, -1.45f, 0);
		matrixStackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
}
