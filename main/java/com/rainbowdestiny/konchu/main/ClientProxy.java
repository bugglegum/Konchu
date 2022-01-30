package com.rainbowdestiny.konchu.main;

import com.rainbowdestiny.konchu.client.init.ModeledItems;
import com.rainbowdestiny.konchu.main.init.KonchuBlocks;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientProxy extends ServerProxy {

    public void doClientStuff(FMLClientSetupEvent event) {
    	ModeledItems.registerItems();
		RenderTypeLookup.setRenderLayer(KonchuBlocks.LICHEN_GROWTH.get(), 				RenderType.cutout());
		RenderTypeLookup.setRenderLayer(KonchuBlocks.RICE_COOKER.get(), 				RenderType.cutout());
		RenderTypeLookup.setRenderLayer(KonchuBlocks.COBBLESTONE_SHRINE_PAGODA.get(), 	RenderType.cutout());
		RenderTypeLookup.setRenderLayer(KonchuBlocks.COBBLESTONE_SHRINE_LANTERN.get(), 	RenderType.cutout());
    }
}