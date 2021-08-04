package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.Konchu;
import com.rainbowdestiny.konchu.common.entities.GardenSnailEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypesInit {	

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Konchu.MOD_ID);
	
	public static final RegistryObject<EntityType<GardenSnailEntity>> GARDEN_SNAIL_ENTITY = KonchuRegistry.ENTITIES.register("garden_snail_entity", () -> EntityType.Builder.<GardenSnailEntity>create(GardenSnailEntity::new, EntityClassification.MISC).size(0.8f,0.8f).build(new ResourceLocation(Konchu.MOD_ID, "garden_snail_entity").toString()));

}
