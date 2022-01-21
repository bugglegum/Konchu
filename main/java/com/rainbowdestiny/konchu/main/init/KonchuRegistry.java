package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.Konchu;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class KonchuRegistry {

	public static final DeferredRegister<Block> BLOCKS				= DeferredRegister.create(ForgeRegistries.BLOCKS, Konchu.MOD_ID);
	public static final DeferredRegister<Item> ITEMS				= DeferredRegister.create(ForgeRegistries.ITEMS, Konchu.MOD_ID);
	public static final DeferredRegister<SoundEvent> SOUNDS			= DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Konchu.MOD_ID);
	public static final DeferredRegister<EntityType<?>> ENTITIES	= DeferredRegister.create(ForgeRegistries.ENTITIES, Konchu.MOD_ID);
	public static final DeferredRegister<Biome> BIOMES				= DeferredRegister.create(ForgeRegistries.BIOMES,Konchu.MOD_ID);
	
	public static KonchuBlocks Blocks;
	public static KonchuItems Items;
	public static KonchuSoundEvents Sounds;
	public static KonchuEntityType Entities;

	public static void init() {
		
		Blocks		= new KonchuBlocks();
		Items		= new KonchuItems();
		Sounds		= new KonchuSoundEvents();
		Entities	= new KonchuEntityType();
		
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
