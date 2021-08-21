package com.rainbowdestiny.konchu;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.rainbowdestiny.konchu.client.gui.RenderingEvents;
import com.rainbowdestiny.konchu.client.gui.RenderingEvents.ItemRenderInfo;
import com.rainbowdestiny.konchu.client.gui.RenderingEvents.ItemRenderInfo.ItemModelMatch;
import com.rainbowdestiny.konchu.client.render.entity.GardenSnailRenderer;
import com.rainbowdestiny.konchu.main.init.KonchuEntityType;

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
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Konchu.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener extends CommonListener {
	
	@SubscribeEvent
	public static void registerRenderers(final FMLClientSetupEvent event) {
		
		RenderingRegistry.registerEntityRenderingHandler(KonchuEntityType.GARDEN_SNAIL.get(), GardenSnailRenderer::new);
	
	}
	
	@SubscribeEvent
	public static void modelBakeEvent(ModelBakeEvent event) {

		Map<ResourceLocation, IBakedModel> map = event.getModelRegistry();

		for(ItemRenderInfo ir : RenderingEvents.getGuiRenders()) {

			IBakedModel defaultModel = map.get(ir.getDefaultModelLocation());

			for(ItemModelMatch imm : ir.getTransforms().values()) {
				imm.setModel(map.get(imm.getModelLocation()));
				map.put(imm.getModelLocation(), imm.getModel());
			}

			IBakedModel modelWrapper = new IBakedModel() {
					
			@SuppressWarnings("deprecation")
			@Override
			public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand) {
				return defaultModel.getQuads(state, side, rand);
			}

			@Override
			public boolean isGui3d() {
				return defaultModel.isGui3d();
			}

			@Override
			public ItemOverrideList getOverrides() {
				return defaultModel.getOverrides();
			}

			@Override
			public IBakedModel handlePerspective(ItemCameraTransforms.TransformType transformType, MatrixStack mat) {
				IBakedModel modelToUse = defaultModel;
				if(ir.getTransforms().containsKey(transformType)) {
					modelToUse = ir.getTransforms().get(transformType).getModel();
				}

				for(@SuppressWarnings("unused") ItemModelMatch imm : ir.getTransforms().values()) {
							
				}

				if(modelToUse == null) {
					modelToUse = defaultModel;
				}

				return ForgeHooksClient.handlePerspective(modelToUse, transformType, mat);
				}

				@Override
				public boolean useAmbientOcclusion() {
					return false;
				}

				@Override
				public boolean usesBlockLight() {
					return false;
				}

				@Override
				public boolean isCustomRenderer() {
					return false;
				}

				@Override
				public TextureAtlasSprite getParticleIcon() {
					return null;
				}
				
			};
			
			map.put(ir.getDefaultModelLocation(), modelWrapper);
			
		}
		
	}
	
}