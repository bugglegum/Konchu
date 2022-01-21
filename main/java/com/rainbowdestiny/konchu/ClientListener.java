package com.rainbowdestiny.konchu;

import com.rainbowdestiny.konchu.client.render.entity.FrogRenderer;
import com.rainbowdestiny.konchu.client.render.entity.SnailRenderer;
import com.rainbowdestiny.konchu.main.init.KonchuEntityType;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Konchu.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener extends CommonListener {
	
	@SubscribeEvent
	public static void registerRenderers(final FMLClientSetupEvent event) {
		
		RenderingRegistry.registerEntityRenderingHandler(KonchuEntityType.SNAIL.get(), SnailRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(KonchuEntityType.FROG.get(), FrogRenderer::new);
	}
}