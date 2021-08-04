package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.Konchu;
import com.rainbowdestiny.konchu.common.blocks.BlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Konchu.MOD_ID);

	//Blocks
    public static final RegistryObject<Block> LICHEN_BLOCK = BLOCKS.register("lichen_block", () -> new BlockBase(Block.Properties.of(Material.PLANT).harvestLevel(0).strength(0.3F,0.3F).sound(SoundType.CROP)));

}
