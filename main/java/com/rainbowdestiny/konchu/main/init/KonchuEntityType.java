package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.Konchu;
import com.rainbowdestiny.konchu.entity.ChairEntity;
import com.rainbowdestiny.konchu.entity.FrogEntity;
import com.rainbowdestiny.konchu.entity.SnailEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class KonchuEntityType {	
	
	public static final RegistryObject<EntityType<SnailEntity>> SNAIL = 		KonchuRegistry.ENTITIES.register("snail", 		() -> EntityType.Builder.<SnailEntity>of(SnailEntity::new, 	EntityClassification.CREATURE)	.sized(  1.0F,    0.9F).build(new ResourceLocation(Konchu.MOD_ID, "snail").toString()));
	public static final RegistryObject<EntityType<FrogEntity>> 	FROG = 			KonchuRegistry.ENTITIES.register("frog", 		() -> EntityType.Builder.<FrogEntity>of	(FrogEntity::new, 	EntityClassification.CREATURE)	.sized(  1.0F,    0.6F).build(new ResourceLocation(Konchu.MOD_ID, "frog").toString()));
	
	public static final RegistryObject<EntityType<ChairEntity>> CHAIR = 		KonchuRegistry.ENTITIES.register("chair", 		() -> EntityType.Builder.<ChairEntity>of(ChairEntity::new, 	EntityClassification.MISC)		.sized(  1.0F,    1.0F).build(new ResourceLocation(Konchu.MOD_ID, "chair").toString()));
	public static final RegistryObject<EntityType<BoatEntity>> 	DRIFT_BOAT = 	KonchuRegistry.ENTITIES.register("drift_boat", 	() -> EntityType.Builder.<BoatEntity>of	(BoatEntity::new, 	EntityClassification.MISC)		.sized(1.375F, 0.5625F).build(new ResourceLocation(Konchu.MOD_ID, "drift_boat").toString()));
}
