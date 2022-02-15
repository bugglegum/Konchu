package com.rainbowdestiny.konchu.world.biome;

import com.google.common.base.Supplier;
import com.rainbowdestiny.konchu.Konchu;
import com.rainbowdestiny.konchu.KonchuRegistry;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;

public class KonchuBiomes {
	public static RegistryKey<Biome> CHERRY_FOREST = registerBiome("cherry_forest");

	static {
		createBiome("cherry_forest", BiomeMaker::theVoidBiome);
	}
	
	public static void registerBiomes() {
    	BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(KonchuBiomes.CHERRY_FOREST, 1));
		BiomeDictionary.addTypes(KonchuBiomes.CHERRY_FOREST, Type.FOREST, Type.OVERWORLD);	
	}
	
	public static RegistryKey<Biome> registerBiome(String biomeName) {
		return RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(Konchu.MOD_ID, biomeName));
	}
	
	public static RegistryObject<Biome> createBiome(String biomeName, Supplier<Biome> biome) {
		return KonchuRegistry.BIOMES.register(biomeName, biome);
	}
}

