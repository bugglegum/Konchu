package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.common.blocks.LogBlock;
import com.rainbowdestiny.konchu.common.blocks.MarimoBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.VineBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;

public class KonchuBlocks {

	/* Lichen Blocks */
	public static final RegistryObject<Block> LICHEN_BLOCK	= KonchuRegistry.BLOCKS.register("lichen_block", 	() -> new Block		(AbstractBlock.Properties.of(Material.PLANT).harvestLevel(0).strength(0.3F,0.3F).sound(SoundType.CROP)));
	public static final RegistryObject<Block> LICHEN_GROWTH	= KonchuRegistry.BLOCKS.register("lichen_growth", 	() -> new VineBlock (AbstractBlock.Properties.of(Material.PLANT).harvestLevel(0).strength(0.3F,0.3F).sound(SoundType.CROP).noCollission()));

	/* Wood Blocks */	
	public static final RegistryObject<Block> STRIPPED_DRIFT_LOG	= KonchuRegistry.BLOCKS.register("stripped_drift_log", 			() -> new RotatedPillarBlock (AbstractBlock.Properties.of(Material.WOOD).harvestLevel(1).strength(1.6F, 2.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> STRIPPED_DRIFT_WOOD	= KonchuRegistry.BLOCKS.register("stripped_drift_wood", 		() -> new Block				 (AbstractBlock.Properties.of(Material.WOOD).harvestLevel(1).strength(1.6F, 2.0F).sound(SoundType.WOOD)));	
	public static final RegistryObject<Block> DRIFT_LOG				= KonchuRegistry.BLOCKS.register("drift_log", 					() -> new LogBlock			 (AbstractBlock.Properties.of(Material.WOOD).harvestLevel(1).strength(1.6F, 2.0F).sound(SoundType.WOOD), STRIPPED_DRIFT_LOG));
	public static final RegistryObject<Block> DRIFT_WOOD			= KonchuRegistry.BLOCKS.register("drift_wood", 					() -> new Block				 (AbstractBlock.Properties.of(Material.WOOD).harvestLevel(1).strength(1.6F, 2.0F).sound(SoundType.WOOD)));

	public static final RegistryObject<Block> STRIPPED_INFESTED_DRIFT_LOG	= KonchuRegistry.BLOCKS.register("stripped_infested_drift_log",  () -> new RotatedPillarBlock (AbstractBlock.Properties.of(Material.WOOD).harvestLevel(1).strength(1.2F, 1.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> STRIPPED_INFESTED_DRIFT_WOOD	= KonchuRegistry.BLOCKS.register("stripped_infested_drift_wood", () -> new Block				 (AbstractBlock.Properties.of(Material.WOOD).harvestLevel(1).strength(1.2F, 1.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> INFESTED_DRIFT_LOG			= KonchuRegistry.BLOCKS.register("infested_drift_log", 			 () -> new LogBlock			 (AbstractBlock.Properties.of(Material.WOOD).harvestLevel(1).strength(1.2F, 1.0F).sound(SoundType.WOOD), STRIPPED_INFESTED_DRIFT_LOG));
	public static final RegistryObject<Block> INFESTED_DRIFT_WOOD			= KonchuRegistry.BLOCKS.register("infested_drift_wood", 		 () -> new Block				 (AbstractBlock.Properties.of(Material.WOOD).harvestLevel(1).strength(1.2F, 1.0F).sound(SoundType.WOOD)));

	/* Misc Blocks */
	public static final RegistryObject<Block> MARIMO 		= KonchuRegistry.BLOCKS.register("marimo", 		() -> new MarimoBlock		(AbstractBlock.Properties.of(Material.VEGETABLE).harvestLevel(1).strength( 1F,    1F).sound(SoundType.WET_GRASS)));
	public static final RegistryObject<Block> CRAB_BLOCK	= KonchuRegistry.BLOCKS.register("crab_block", 	() -> new CarvedPumpkinBlock(AbstractBlock.Properties.of(Material.STONE)	.harvestLevel(1).strength(25F, 1200F).sound(SoundType.NETHERRACK)));
}





