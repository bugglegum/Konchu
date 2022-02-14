package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.Konchu;
import com.rainbowdestiny.konchu.world.LichenTreeDecorator;

import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Konchu.MOD_ID)
public class KonchuFeatures {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, Konchu.MOD_ID);

    public static final RegistryObject<TreeDecoratorType<?>> LICHEN_GROWTH = TREE_DECORATORS.register("lichen_growth", () -> new TreeDecoratorType<>(LichenTreeDecorator.CODEC));
}
    
