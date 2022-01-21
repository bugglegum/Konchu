package com.rainbowdestiny.konchu.client.init;

import com.rainbowdestiny.konchu.client.render.ModeledItemRenderer;
import com.rainbowdestiny.konchu.main.init.KonchuItems;

public class ModeledItems {
	public static void registerItems() {
		ModeledItemRenderer.addItemRender(KonchuItems.FROG_HAT.get());
	}
}
