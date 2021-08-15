package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.Konchu;
import com.rainbowdestiny.konchu.common.entities.GardenSnailEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class KonchuEntityTypes {	
	
	public static final RegistryObject<EntityType<GardenSnailEntity>> GARDEN_SNAIL_ENTITY = KonchuRegistry.ENTITIES.register("garden_snail", () -> EntityType.Builder.<GardenSnailEntity>of(GardenSnailEntity::new, EntityClassification.CREATURE).sized(1f,1f).build(new ResourceLocation(Konchu.MOD_ID, "garden_snail").toString()));
}
