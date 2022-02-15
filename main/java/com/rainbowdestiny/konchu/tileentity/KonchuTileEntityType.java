package com.rainbowdestiny.konchu.tileentity;

import com.rainbowdestiny.konchu.Konchu;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Konchu.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KonchuTileEntityType<T extends TileEntity> extends net.minecraftforge.registries.ForgeRegistryEntry<TileEntityType<?>> {

	//public static final RegistryObject<TileEntityType<SignTileEntity>> SIGN		= KonchuRegistry.TILE_ENTITIES.register("sign", 	TileEntityType.Builder.of(SignTileEntity::new, KonchuBlocks.DRIFT_SIGN.get()).build(null));
	//public static final RegistryObject<TileEntityType<BaseTileEntity>> PAGODA	= KonchuRegistry.TILE_ENTITIES.register("pagoda", 	TileEntityType.Builder.of(BaseTileEntity::new, KonchuBlocks.COBBLESTONE_PAGODA.get()).build(null));
}
