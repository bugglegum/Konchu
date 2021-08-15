package com.rainbowdestiny.konchu.main.init;

import net.minecraft.block.Block;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;

public class KonchuBlocks {

	//Blocks

	//Lichen Blocks
    public static final RegistryObject<Block> LICHEN_BLOCK = KonchuRegistry.BLOCKS.register("lichen_block", () -> new Block(Block.Properties.of(Material.PLANT).harvestLevel(0).strength(0.3F,0.3F).sound(SoundType.CROP)));

    //Crab Block
    public static final RegistryObject<Block> CRAB_BLOCK = KonchuRegistry.BLOCKS.register("crab_block", () -> new CarvedPumpkinBlock(Block.Properties.of(Material.STONE).harvestLevel(1).strength(25F, 1200F).sound(SoundType.NETHERRACK)));
    
    public static final RegistryObject<Block> DRIFT_LOG = KonchuRegistry.BLOCKS.register("drift_log", () -> new RotatedPillarBlock(Block.Properties.of(Material.WOOD).harvestLevel(1).strength(1.6F, 2F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> DRIFT_WOOD = KonchuRegistry.BLOCKS.register("drift_wood", () -> new Block(Block.Properties.of(Material.WOOD).harvestLevel(1).strength(1.6F, 2F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> INFESTED_DRIFT_LOG = KonchuRegistry.BLOCKS.register("infested_drift_log", () -> new RotatedPillarBlock(Block.Properties.of(Material.WOOD).harvestLevel(1).strength(1.2F, 1F).sound(SoundType.WOOD)));
    		
}

