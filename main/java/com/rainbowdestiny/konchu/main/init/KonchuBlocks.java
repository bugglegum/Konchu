package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.common.block.LogBlock;
import com.rainbowdestiny.konchu.common.block.MarimoBlock;
import com.rainbowdestiny.konchu.common.block.RiceCookerBlock;
import com.rainbowdestiny.konchu.common.block.RotatedBlock;
import com.rainbowdestiny.konchu.common.block.ShrineLanternBlock;
import com.rainbowdestiny.konchu.common.block.ShrinePagodaBlock;
import com.rainbowdestiny.konchu.common.block.StairsBlock;
import com.rainbowdestiny.konchu.common.block.TatamiBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.VineBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;

public class KonchuBlocks {

	/* Lichen Blocks */
	public static RegistryObject<Block> KAOLINITE		= KonchuRegistry.BLOCKS.register("kaolinite", 		() -> new Block		(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_LIGHT_GRAY).harvestLevel(0).strength(0.8F,0.2F).sound(SoundType.GRAVEL)));
	public static RegistryObject<Block> LICHEN_BLOCK	= KonchuRegistry.BLOCKS.register("lichen_block", 	() -> new Block		(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_LIGHT_GREEN).harvestLevel(0).strength(0.3F,0.3F).sound(SoundType.CROP)));
	public static RegistryObject<Block> LICHEN_GROWTH	= KonchuRegistry.BLOCKS.register("lichen_growth", 	() -> new VineBlock (AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_LIGHT_GREEN).harvestLevel(0).strength(0.3F,0.3F).sound(SoundType.CROP).noCollission()));

	/* Wood Blocks */	
	public static RegistryObject<Block> STRIPPED_INFESTED_DRIFT_LOG		= KonchuRegistry.BLOCKS.register("stripped_infested_drift_log",  () -> new RotatedPillarBlock	(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).harvestLevel(1).strength(1.2F, 1.0F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> STRIPPED_INFESTED_DRIFT_WOOD	= KonchuRegistry.BLOCKS.register("stripped_infested_drift_wood", () -> new Block				(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).harvestLevel(1).strength(1.2F, 1.0F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> INFESTED_DRIFT_LOG				= KonchuRegistry.BLOCKS.register("infested_drift_log", 			 () -> new LogBlock			 	(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY).harvestLevel(1).strength(1.2F, 1.0F).sound(SoundType.WOOD), STRIPPED_INFESTED_DRIFT_LOG));
	public static RegistryObject<Block> INFESTED_DRIFT_WOOD				= KonchuRegistry.BLOCKS.register("infested_drift_wood", 		 () -> new Block				(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY).harvestLevel(1).strength(1.2F, 1.0F).sound(SoundType.WOOD)));
	
	public static RegistryObject<Block> STRIPPED_DRIFT_LOG	= KonchuRegistry.BLOCKS.register("stripped_drift_log", 	() -> new RotatedPillarBlock (AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).harvestLevel(1).strength(1.6F, 2.0F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> STRIPPED_DRIFT_WOOD	= KonchuRegistry.BLOCKS.register("stripped_drift_wood", () -> new Block				 (AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).harvestLevel(1).strength(1.6F, 2.0F).sound(SoundType.WOOD)));	
	public static RegistryObject<Block> DRIFT_LOG			= KonchuRegistry.BLOCKS.register("drift_log", 			() -> new LogBlock			 (AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY).harvestLevel(1).strength(1.6F, 2.0F).sound(SoundType.WOOD), STRIPPED_DRIFT_LOG));
	public static RegistryObject<Block> DRIFT_WOOD			= KonchuRegistry.BLOCKS.register("drift_wood", 			() -> new Block				 (AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY).harvestLevel(1).strength(1.6F, 2.0F).sound(SoundType.WOOD)));

	public static RegistryObject<Block> DRIFT_PLANKS = KonchuRegistry.BLOCKS.register("drift_planks",	() -> new Block			(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).harvestLevel(1)	.strength(1.8F, 2.3F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> DRIFT_STAIRS = KonchuRegistry.BLOCKS.register("drift_stairs",	() -> new StairsBlock	(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK)					.strength(1.8F, 2.3F).sound(SoundType.WOOD)).setStair(() -> {return DRIFT_PLANKS;}));
	public static RegistryObject<Block> DRIFT_SLAB   = KonchuRegistry.BLOCKS.register("drift_slab", 	() -> new SlabBlock		(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK)					.strength(1.8F, 2.3F).sound(SoundType.WOOD)));
	
	public static RegistryObject<Block> DRIFT_PRESSURE_PLATE	= KonchuRegistry.BLOCKS.register("drift_pressure_plate", 	() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,  AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(0.5F).sound(SoundType.WOOD).noCollission()));
	public static RegistryObject<Block> DRIFT_BUTTON			= KonchuRegistry.BLOCKS.register("drift_button", 			() -> new WoodButtonBlock												(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(0.5F).sound(SoundType.WOOD).noCollission()));

	public static RegistryObject<Block> DRIFT_DOOR			= KonchuRegistry.BLOCKS.register("drift_door",			() -> new DoorBlock		(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(      3.0F).sound(SoundType.WOOD).noOcclusion()));
	public static RegistryObject<Block> DRIFT_TRAPDOOR 		= KonchuRegistry.BLOCKS.register("drift_trapdoor", 		() -> new TrapDoorBlock (AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(      5.0F).sound(SoundType.WOOD).noOcclusion()));	
	public static RegistryObject<Block> DRIFT_FENCE			= KonchuRegistry.BLOCKS.register("drift_fence",			() -> new FenceBlock	(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> DRIFT_FENCE_GATE	= KonchuRegistry.BLOCKS.register("drift_fence_gate", 	() -> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	
	public static RegistryObject<Block> DRIFT_SIGN		= KonchuRegistry.BLOCKS.register("drift_sign", 		() -> new StandingSignBlock	(AbstractBlock.Properties.of(Material.WOOD).strength(1.0F).sound(SoundType.WOOD), WoodType.OAK));
	public static RegistryObject<Block> DRIFT_WALL_SIGN	= KonchuRegistry.BLOCKS.register("drift_wall_sign", () -> new WallSignBlock		(AbstractBlock.Properties.of(Material.WOOD).strength(1.0F).sound(SoundType.WOOD), WoodType.OAK));

	/* Bamboo Blocks */
	public static RegistryObject<Block> SUGAR_CANE_TATAMI	= KonchuRegistry.BLOCKS.register("sugar_cane_tatami", 	() -> new TatamiBlock(AbstractBlock.Properties.of(Material.BAMBOO, MaterialColor.COLOR_LIGHT_GREEN)	.harvestLevel(1).strength( 0.5F, 1.0F).sound(SoundType.BAMBOO).noOcclusion()));
	public static RegistryObject<Block> BAMBOO_TATAMI		= KonchuRegistry.BLOCKS.register("bamboo_tatami", 		() -> new TatamiBlock(AbstractBlock.Properties.of(Material.BAMBOO, MaterialColor.COLOR_YELLOW)		.harvestLevel(1).strength( 0.5F, 1.0F).sound(SoundType.BAMBOO).noOcclusion()));
	
	/* Misc Blocks */
	public static RegistryObject<Block> MARIMO 						= KonchuRegistry.BLOCKS.register("marimo", 						() -> new MarimoBlock		(AbstractBlock.Properties.of(Material.VEGETABLE, 	MaterialColor.TERRACOTTA_GREEN)	.harvestLevel(1).strength( 1.0F,   1.0F).sound(SoundType.WET_GRASS)));
	public static RegistryObject<Block> RICE_COOKER					= KonchuRegistry.BLOCKS.register("rice_cooker",	 				() -> new RiceCookerBlock	(AbstractBlock.Properties.of(Material.METAL,	 	MaterialColor.SNOW)				.harvestLevel(1).strength( 1.3F,   6.0F).sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops()));
	public static RegistryObject<Block> COBBLESTONE_SHRINE_LANTERN	= KonchuRegistry.BLOCKS.register("cobblestone_shrine_lantern", 	() -> new ShrineLanternBlock(AbstractBlock.Properties.of(Material.STONE,	 	MaterialColor.COLOR_GRAY)		.harvestLevel(1).strength( 2.0F,   6.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));
	public static RegistryObject<Block> COBBLESTONE_SHRINE_PAGODA	= KonchuRegistry.BLOCKS.register("cobblestone_shrine_pagoda", 	() -> new ShrinePagodaBlock	(AbstractBlock.Properties.of(Material.STONE,	 	MaterialColor.COLOR_GRAY)		.harvestLevel(1).strength( 2.0F,   6.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));
	public static RegistryObject<Block> CRAB_BLOCK					= KonchuRegistry.BLOCKS.register("crab_block", 					() -> new RotatedBlock		(AbstractBlock.Properties.of(Material.STONE, 		MaterialColor.COLOR_RED)		.harvestLevel(1).strength(25.0F, 900.0F).sound(SoundType.NETHERRACK)));
}





