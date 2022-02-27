package com.rainbowdestiny.konchu.world.feature;

import com.rainbowdestiny.konchu.Konchu;

import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Konchu.MOD_ID)
public class KonchuFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Konchu.MOD_ID);

	public static final RegistryObject<Feature<BaseTreeFeatureConfig>> WHITE_CHERRY 	= FEATURES.register("white_cherry", 	()->new TreeFeature(BaseTreeFeatureConfig.CODEC));
	public static final RegistryObject<Feature<BaseTreeFeatureConfig>> PINK_CHERRY 		= FEATURES.register("pink_cherry", 		()->new TreeFeature(BaseTreeFeatureConfig.CODEC));
	public static final RegistryObject<Feature<BaseTreeFeatureConfig>> MAGENTA_CHERRY 	= FEATURES.register("magenta_cherry", 	()->new TreeFeature(BaseTreeFeatureConfig.CODEC));

    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, Konchu.MOD_ID);
	
    public static final RegistryObject<TreeDecoratorType<?>> LICHEN_GROWTH = TREE_DECORATORS.register("lichen_growth", () -> new TreeDecoratorType<>(LichenGrowthFeature.CODEC));
}
    
