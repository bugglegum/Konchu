package com.rainbowdestiny.konchu.event;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.rainbowdestiny.konchu.Konchu;
import com.rainbowdestiny.konchu.client.render.ModeledItemRenderer;
import com.rainbowdestiny.konchu.client.render.ModeledItemRenderer.ItemRenderInfo;
import com.rainbowdestiny.konchu.client.render.ModeledItemRenderer.ItemRenderInfo.OtherModel;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Konchu.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderingEvents {

	@SubscribeEvent
	@SuppressWarnings("deprecation")
	public static void modelBakeEvent(ModelBakeEvent event) {
		Map<ResourceLocation, IBakedModel> modelRegistry = event.getModelRegistry();

		for (ItemRenderInfo renderInfo : ModeledItemRenderer.getRenders()) {
			IBakedModel baseModel = modelRegistry.get(renderInfo.getBaseLocation());

			for (OtherModel otherModel : renderInfo.getTransforms().values()) {
				otherModel.setModel(modelRegistry.get(otherModel.getLocation()));
				modelRegistry.put(otherModel.getLocation(), otherModel.getModel());
			}

			IBakedModel bakedModel = new IBakedModel() {
				@Override
				public IBakedModel handlePerspective(ItemCameraTransforms.TransformType transformType, MatrixStack mat) {
					IBakedModel model = renderInfo.getTransforms().get(transformType).getModel();
					if(model == null) model = baseModel;

					return ForgeHooksClient.handlePerspective(model, transformType, mat);
				}
				
				@Override
				public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand) {
					return baseModel.getQuads(state, side, rand);
				}
				
				@Override
				public boolean useAmbientOcclusion() {
					return baseModel.useAmbientOcclusion();
				}
				
				@Override
				public boolean isGui3d() {
					return baseModel.isGui3d();
				}
				@Override
				public boolean isCustomRenderer() {
					return baseModel.isCustomRenderer();
				}
				
				@Override
				public TextureAtlasSprite getParticleIcon() {
					return baseModel.getParticleIcon();
				}
				
				@Override
				public ItemOverrideList getOverrides() {
					return baseModel.getOverrides();
				}
				
				@Override
				public boolean usesBlockLight() {
					return false;
				}
			};
			modelRegistry.put(renderInfo.getBaseLocation(), bakedModel);
		}
	}
}
