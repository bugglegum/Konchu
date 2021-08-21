package com.rainbowdestiny.konchu.main;

import com.rainbowdestiny.konchu.client.gui.RenderingEvents;
import com.rainbowdestiny.konchu.main.init.KonchuBlocks;
import com.rainbowdestiny.konchu.main.init.KonchuItems;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientProxy extends ServerProxy {

    public void doClientStuff(FMLClientSetupEvent event) {
    	
		RenderTypeLookup.setRenderLayer(KonchuBlocks.LICHEN_GROWTH.get(), RenderType.cutout());

		RenderingEvents.ItemRenderInfo CLOTH_NET = RenderingEvents.addItemRenderer(KonchuItems.CLOTH_NET.get());
		System.out.println("Test");
		CLOTH_NET.addTransformType("gui", ItemCameraTransforms.TransformType.GUI);
		CLOTH_NET.addTransformType("gui", ItemCameraTransforms.TransformType.FIXED);
    }
    
}