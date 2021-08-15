package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.common.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;

public class BlockInit {

	//Blocks
	//Lichen Blocks
    public static final RegistryObject<Block> LICHEN_BLOCK = KonchuRegistry.BLOCKS.register("lichen_block", () -> new BlockBase(Block.Properties.of(Material.PLANT).harvestLevel(0).strength(0.3F,0.3F).sound(SoundType.CROP)));

    //Crab Block
    public static final RegistryObject<Block> CRAB_BLOCK = KonchuRegistry.BLOCKS.register("crab_block", () -> new CarvedPumpkinBlock(Block.Properties.of(Material.STONE).harvestLevel(1).strength(25F, 1200F).sound(SoundType.NETHERRACK)));
    		
}
