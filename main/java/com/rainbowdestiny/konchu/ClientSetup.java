package com.rainbowdestiny.konchu;

import com.rainbowdestiny.konchu.block.KonchuBlocks;
import com.rainbowdestiny.konchu.client.init.ModeledItems;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup extends CommonSetup {

    public void clientSetup(FMLClientSetupEvent event) {
    	ModeledItems.registerItems();
		RenderTypeLookup.setRenderLayer(KonchuBlocks.LICHEN_GROWTH.get(), 	RenderType.cutout());
		RenderTypeLookup.setRenderLayer(KonchuBlocks.RICE_COOKER.get(), 	RenderType.cutout());
		
		RenderTypeLookup.setRenderLayer(KonchuBlocks.OAK_CHAIR.get(), 		RenderType.cutout());
		RenderTypeLookup.setRenderLayer(KonchuBlocks.SPRUCE_CHAIR.get(), 	RenderType.cutout());
		RenderTypeLookup.setRenderLayer(KonchuBlocks.BIRCH_CHAIR.get(), 	RenderType.cutout());
		RenderTypeLookup.setRenderLayer(KonchuBlocks.JUNGLE_CHAIR.get(), 	RenderType.cutout());
		RenderTypeLookup.setRenderLayer(KonchuBlocks.ACACIA_CHAIR.get(), 	RenderType.cutout());
		RenderTypeLookup.setRenderLayer(KonchuBlocks.SPRUCE_CHAIR.get(), 	RenderType.cutout());
		RenderTypeLookup.setRenderLayer(KonchuBlocks.DARK_OAK_CHAIR.get(), 	RenderType.cutout());
		RenderTypeLookup.setRenderLayer(KonchuBlocks.CRIMSON_CHAIR.get(), 	RenderType.cutout());
		RenderTypeLookup.setRenderLayer(KonchuBlocks.WARPED_CHAIR.get(), 	RenderType.cutout());
		RenderTypeLookup.setRenderLayer(KonchuBlocks.DRIFT_CHAIR.get(), 	RenderType.cutout());
		RenderTypeLookup.setRenderLayer(KonchuBlocks.CHERRY_CHAIR.get(), 	RenderType.cutout());

		RenderTypeLookup.setRenderLayer(KonchuBlocks.CHERRY_DOOR.get(), 	RenderType.cutout());
		RenderTypeLookup.setRenderLayer(KonchuBlocks.CHERRY_TRAPDOOR.get(), RenderType.cutout());
		
		RenderTypeLookup.setRenderLayer(KonchuBlocks.CHERRY_LEAVES.get(), 	RenderType.translucent());

		RenderTypeLookup.setRenderLayer(KonchuBlocks.COBBLESTONE_SHRINE_PAGODA.get(), 	RenderType.cutout());
		RenderTypeLookup.setRenderLayer(KonchuBlocks.COBBLESTONE_SHRINE_LANTERN.get(), 	RenderType.cutout());
    }
}