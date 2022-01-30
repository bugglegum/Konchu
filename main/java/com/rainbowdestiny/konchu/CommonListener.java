package com.rainbowdestiny.konchu;

import com.rainbowdestiny.konchu.common.entity.SnailEntity;
import com.rainbowdestiny.konchu.main.init.KonchuEntityType;

import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Konchu.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonListener {
	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
			GlobalEntityTypeAttributes.put(KonchuEntityType.SNAIL.get(), SnailEntity.setCustomAttributes().build());
			GlobalEntityTypeAttributes.put(KonchuEntityType.FROG.get(), SnailEntity.setCustomAttributes().build());
	}
}