package com.rainbowdestiny.konchu;

import com.rainbowdestiny.konchu.common.entities.GardenSnailEntity;
import com.rainbowdestiny.konchu.main.init.KonchuEntityTypes;

import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Konchu.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonListener {
	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
			GlobalEntityTypeAttributes.put(KonchuEntityTypes.GARDEN_SNAIL_ENTITY.get(), GardenSnailEntity.setCustomAttributes().build());
	}
}