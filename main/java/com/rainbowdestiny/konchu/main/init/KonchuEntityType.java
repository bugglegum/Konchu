package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.Konchu;
import com.rainbowdestiny.konchu.common.entities.FrogEntity;
import com.rainbowdestiny.konchu.common.entities.SnailEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class KonchuEntityType {	
	
	public static final RegistryObject<EntityType<SnailEntity>> SNAIL = KonchuRegistry.ENTITIES.register("snail", () -> EntityType.Builder.<SnailEntity>of(SnailEntity::new, EntityClassification.CREATURE).sized(1f,1f).build(new ResourceLocation(Konchu.MOD_ID, "snail").toString()));
	public static final RegistryObject<EntityType<FrogEntity>> FROG = KonchuRegistry.ENTITIES.register("frog", () -> EntityType.Builder.<FrogEntity>of(FrogEntity::new, EntityClassification.CREATURE).sized(1f,1f).build(new ResourceLocation(Konchu.MOD_ID, "frog").toString()));
}
