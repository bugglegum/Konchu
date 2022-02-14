package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.block.ChairBlock;
import com.rainbowdestiny.konchu.block.LogBlock;
import com.rainbowdestiny.konchu.block.MarimoBlock;
import com.rainbowdestiny.konchu.block.PaperLanternBlock;
import com.rainbowdestiny.konchu.block.RiceCookerBlock;
import com.rainbowdestiny.konchu.block.RotatedBlock;
import com.rainbowdestiny.konchu.block.ShrineLanternBlock;
import com.rainbowdestiny.konchu.block.ShrinePagodaBlock;
import com.rainbowdestiny.konchu.block.StairsBlock;
import com.rainbowdestiny.konchu.block.TatamiBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.LeavesBlock;
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

	/* Materials */
	public static RegistryObject<Block> LICHEN_GROWTH	= KonchuRegistry.BLOCKS.register("lichen_growth", 	() -> new VineBlock (AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_LIGHT_GREEN)	.harvestLevel(0).strength(0.3F,0.3F).sound(SoundType.CROP).noCollission()));	
	public static RegistryObject<Block> LICHEN_BLOCK	= KonchuRegistry.BLOCKS.register("lichen_block", 	() -> new Block		(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_LIGHT_GREEN)	.harvestLevel(0).strength(0.3F,0.3F).sound(SoundType.CROP)));
	public static RegistryObject<Block> KAOLINITE		= KonchuRegistry.BLOCKS.register("kaolinite", 		() -> new Block		(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_LIGHT_GRAY)	.harvestLevel(0).strength(0.8F,0.2F).sound(SoundType.GRAVEL)));

	/* Furnishing */
	public static RegistryObject<Block> OAK_CHAIR		= KonchuRegistry.BLOCKS.register("oak_chair", 		() -> new ChairBlock (AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD)				.harvestLevel(1).strength(3.0F, 2.5F).sound(SoundType.WOOD).noOcclusion(), 0.0F));
	public static RegistryObject<Block> SPRUCE_CHAIR	= KonchuRegistry.BLOCKS.register("spruce_chair",  	() -> new ChairBlock (AbstractBlock.Properties.of(Material.WOOD, MaterialColor.PODZOL)				.harvestLevel(1).strength(3.0F, 2.5F).sound(SoundType.WOOD).noOcclusion(), 0.0F));
	public static RegistryObject<Block> BIRCH_CHAIR		= KonchuRegistry.BLOCKS.register("birch_chair",  	() -> new ChairBlock (AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND)				.harvestLevel(1).strength(3.0F, 2.5F).sound(SoundType.WOOD).noOcclusion(), 0.0F));
	public static RegistryObject<Block> JUNGLE_CHAIR	= KonchuRegistry.BLOCKS.register("jungle_chair",  	() -> new ChairBlock (AbstractBlock.Properties.of(Material.WOOD, MaterialColor.DIRT)				.harvestLevel(1).strength(3.0F, 2.5F).sound(SoundType.WOOD).noOcclusion(), 0.0F));
	public static RegistryObject<Block> ACACIA_CHAIR	= KonchuRegistry.BLOCKS.register("acacia_chair",  	() -> new ChairBlock (AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE)		.harvestLevel(1).strength(3.0F, 2.5F).sound(SoundType.WOOD).noOcclusion(), 0.0F));
	public static RegistryObject<Block> DARK_OAK_CHAIR	= KonchuRegistry.BLOCKS.register("dark_oak_chair",  () -> new ChairBlock (AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)			.harvestLevel(1).strength(3.0F, 2.5F).sound(SoundType.WOOD).noOcclusion(), 0.0F));
	public static RegistryObject<Block> CRIMSON_CHAIR	= KonchuRegistry.BLOCKS.register("crimson_chair",  	() -> new ChairBlock (AbstractBlock.Properties.of(Material.WOOD, MaterialColor.CRIMSON_HYPHAE)		.harvestLevel(1).strength(3.0F, 2.5F).sound(SoundType.WOOD).noOcclusion(), 0.0F));
	public static RegistryObject<Block> WARPED_CHAIR	= KonchuRegistry.BLOCKS.register("warped_chair",  	() -> new ChairBlock (AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WARPED_WART_BLOCK)	.harvestLevel(1).strength(3.0F, 2.5F).sound(SoundType.WOOD).noOcclusion(), 0.0F));
	public static RegistryObject<Block> DRIFT_CHAIR		= KonchuRegistry.BLOCKS.register("drift_chair",  	() -> new ChairBlock (AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK)	.harvestLevel(1).strength(3.0F, 2.5F).sound(SoundType.WOOD).noOcclusion(), 0.0F));
	public static RegistryObject<Block> CHERRY_CHAIR	= KonchuRegistry.BLOCKS.register("cherry_chair",  	() -> new ChairBlock (AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK)	.harvestLevel(1).strength(3.0F, 2.5F).sound(SoundType.WOOD).noOcclusion(), 0.0F));

	public static RegistryObject<Block> COBBLESTONE_SHRINE_LANTERN	= KonchuRegistry.BLOCKS.register("cobblestone_shrine_lantern", 	() -> new ShrineLanternBlock(AbstractBlock.Properties.of(Material.STONE,	 	MaterialColor.COLOR_GRAY).harvestLevel(1).strength( 2.0F,   6.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));
	public static RegistryObject<Block> COBBLESTONE_SHRINE_PAGODA	= KonchuRegistry.BLOCKS.register("cobblestone_shrine_pagoda", 	() -> new ShrinePagodaBlock	(AbstractBlock.Properties.of(Material.STONE,	 	MaterialColor.COLOR_GRAY).harvestLevel(1).strength( 2.0F,   6.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

	public static RegistryObject<Block> SUGAR_CANE_TATAMI	= KonchuRegistry.BLOCKS.register("sugar_cane_tatami", 	() -> new TatamiBlock(AbstractBlock.Properties.of(Material.BAMBOO, MaterialColor.COLOR_LIGHT_GREEN)	.harvestLevel(1).strength( 0.5F, 1.0F).sound(SoundType.BAMBOO).noOcclusion()));
	public static RegistryObject<Block> BAMBOO_TATAMI		= KonchuRegistry.BLOCKS.register("bamboo_tatami", 		() -> new TatamiBlock(AbstractBlock.Properties.of(Material.BAMBOO, MaterialColor.COLOR_YELLOW)		.harvestLevel(1).strength( 0.5F, 1.0F).sound(SoundType.BAMBOO).noOcclusion()));

	public static RegistryObject<Block> PAPER_LANTERN				= KonchuRegistry.BLOCKS.register("paper_lantern",			() -> new PaperLanternBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.SNOW)				.strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).noOcclusion()));
	public static RegistryObject<Block> WHITE_PAPER_LANTERN			= KonchuRegistry.BLOCKS.register("white_paper_lantern",		() -> new PaperLanternBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.SNOW)				.strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).noOcclusion()));
	public static RegistryObject<Block> ORANGE_PAPER_LANTERN		= KonchuRegistry.BLOCKS.register("orange_paper_lantern",	() -> new PaperLanternBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.COLOR_ORANGE)		.strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).noOcclusion()));
	public static RegistryObject<Block> MAGENTA_PAPER_LANTERN		= KonchuRegistry.BLOCKS.register("magenta_paper_lantern",	() -> new PaperLanternBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.COLOR_MAGENTA)		.strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).noOcclusion()));
	public static RegistryObject<Block> LIGHT_BLUE_PAPER_LANTERN	= KonchuRegistry.BLOCKS.register("light_blue_paper_lantern",() -> new PaperLanternBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.COLOR_LIGHT_BLUE)	.strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).noOcclusion()));
	public static RegistryObject<Block> YELLOW_PAPER_LANTERN		= KonchuRegistry.BLOCKS.register("yellow_paper_lantern",	() -> new PaperLanternBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.COLOR_YELLOW)		.strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).noOcclusion()));
	public static RegistryObject<Block> LIME_PAPER_LANTERN			= KonchuRegistry.BLOCKS.register("lime_paper_lantern",		() -> new PaperLanternBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.COLOR_LIGHT_GREEN)	.strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).noOcclusion()));
	public static RegistryObject<Block> PINK_PAPER_LANTERN 			= KonchuRegistry.BLOCKS.register("pink_paper_lantern",		() -> new PaperLanternBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.COLOR_PINK)			.strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).noOcclusion()));
	public static RegistryObject<Block> GRAY_PAPER_LANTERN			= KonchuRegistry.BLOCKS.register("gray_paper_lantern",		() -> new PaperLanternBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.COLOR_GRAY)			.strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).noOcclusion()));
	public static RegistryObject<Block> LIGHT_GRAY_PAPER_LANTERN	= KonchuRegistry.BLOCKS.register("light_gray_paper_lantern",() -> new PaperLanternBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.COLOR_LIGHT_GRAY)	.strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).noOcclusion()));
	public static RegistryObject<Block> CYAN_PAPER_LANTERN			= KonchuRegistry.BLOCKS.register("cyan_paper_lantern",		() -> new PaperLanternBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.COLOR_CYAN)			.strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).noOcclusion()));
	public static RegistryObject<Block> PURPLE_PAPER_LANTERN		= KonchuRegistry.BLOCKS.register("purple_paper_lantern",	() -> new PaperLanternBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.COLOR_PURPLE)		.strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).noOcclusion()));
	public static RegistryObject<Block> BLUE_PAPER_LANTERN			= KonchuRegistry.BLOCKS.register("blue_paper_lantern",		() -> new PaperLanternBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.COLOR_BLUE)			.strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).noOcclusion()));
	public static RegistryObject<Block> BROWN_PAPER_LANTERN			= KonchuRegistry.BLOCKS.register("brown_paper_lantern",		() -> new PaperLanternBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.COLOR_BROWN)			.strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).noOcclusion()));
	public static RegistryObject<Block> GREEN_PAPER_LANTERN			= KonchuRegistry.BLOCKS.register("green_paper_lantern",		() -> new PaperLanternBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.COLOR_GREEN)			.strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).noOcclusion()));
	public static RegistryObject<Block> RED_PAPER_LANTERN			= KonchuRegistry.BLOCKS.register("red_paper_lantern",		() -> new PaperLanternBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.COLOR_RED)			.strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).noOcclusion()));	
	public static RegistryObject<Block> BLACK_PAPER_LANTERN			= KonchuRegistry.BLOCKS.register("black_paper_lantern",		() -> new PaperLanternBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.COLOR_BLACK)			.strength(0.5F, 0.5F).sound(SoundType.SCAFFOLDING).noOcclusion()));

	/* Wood Types */
	public static RegistryObject<Block> STRIPPED_INFESTED_DRIFT_LOG		= KonchuRegistry.BLOCKS.register("stripped_infested_drift_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(1.2F, 1.0F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> STRIPPED_INFESTED_DRIFT_WOOD	= KonchuRegistry.BLOCKS.register("stripped_infested_drift_wood",() -> new Block				(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(1.2F, 1.0F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> INFESTED_DRIFT_LOG				= KonchuRegistry.BLOCKS.register("infested_drift_log", 			() -> new LogBlock			(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY).strength(1.2F, 1.0F).sound(SoundType.WOOD), STRIPPED_INFESTED_DRIFT_LOG));
	public static RegistryObject<Block> INFESTED_DRIFT_WOOD				= KonchuRegistry.BLOCKS.register("infested_drift_wood", 		() -> new Block				(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY).strength(1.2F, 1.0F).sound(SoundType.WOOD)));
	
	public static RegistryObject<Block> STRIPPED_DRIFT_LOG				= KonchuRegistry.BLOCKS.register("stripped_drift_log", 			() -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(1.6F, 2.0F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> STRIPPED_DRIFT_WOOD				= KonchuRegistry.BLOCKS.register("stripped_drift_wood", 		() -> new Block				(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(1.6F, 2.0F).sound(SoundType.WOOD)));	
	public static RegistryObject<Block> DRIFT_LOG						= KonchuRegistry.BLOCKS.register("drift_log", 					() -> new LogBlock			(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY).strength(1.6F, 2.0F).sound(SoundType.WOOD), STRIPPED_DRIFT_LOG));
	public static RegistryObject<Block> DRIFT_WOOD						= KonchuRegistry.BLOCKS.register("drift_wood", 					() -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY).strength(1.6F, 2.0F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> DRIFT_PLANKS					= KonchuRegistry.BLOCKS.register("drift_planks",				() -> new Block				(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(1.8F, 2.3F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> DRIFT_STAIRS					= KonchuRegistry.BLOCKS.register("drift_stairs",				() -> new StairsBlock		(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(1.8F, 2.3F).sound(SoundType.WOOD)).setStair(() -> {return DRIFT_PLANKS;}));
	public static RegistryObject<Block> DRIFT_SLAB  					= KonchuRegistry.BLOCKS.register("drift_slab", 					() -> new SlabBlock			(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(1.8F, 2.3F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> DRIFT_DOOR						= KonchuRegistry.BLOCKS.register("drift_door",					() -> new DoorBlock			(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(      3.0F).sound(SoundType.WOOD).noOcclusion()));
	public static RegistryObject<Block> DRIFT_TRAPDOOR 					= KonchuRegistry.BLOCKS.register("drift_trapdoor", 				() -> new TrapDoorBlock 	(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(      5.0F).sound(SoundType.WOOD).noOcclusion()));	
	public static RegistryObject<Block> DRIFT_FENCE						= KonchuRegistry.BLOCKS.register("drift_fence",					() -> new FenceBlock		(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> DRIFT_FENCE_GATE				= KonchuRegistry.BLOCKS.register("drift_fence_gate", 			() -> new FenceGateBlock	(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> DRIFT_SIGN						= KonchuRegistry.BLOCKS.register("drift_sign", 					() -> new StandingSignBlock	(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(      1.0F).sound(SoundType.WOOD), WoodType.OAK));
	public static RegistryObject<Block> DRIFT_WALL_SIGN					= KonchuRegistry.BLOCKS.register("drift_wall_sign", 			() -> new WallSignBlock		(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(      1.0F).sound(SoundType.WOOD), WoodType.OAK));
	public static RegistryObject<Block> DRIFT_BUTTON					= KonchuRegistry.BLOCKS.register("drift_button", 				() -> new WoodButtonBlock	(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(      0.5F).sound(SoundType.WOOD).noCollission()));
	public static RegistryObject<Block> DRIFT_PRESSURE_PLATE			= KonchuRegistry.BLOCKS.register("drift_pressure_plate", 		() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(0.5F).sound(SoundType.WOOD).noCollission()));

	public static RegistryObject<Block> STRIPPED_CHERRY_LOG		= KonchuRegistry.BLOCKS.register("stripped_cherry_log", 	() -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BROWN)	.strength(1.6F, 2.0F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> STRIPPED_CHERRY_WOOD	= KonchuRegistry.BLOCKS.register("stripped_cherry_wood",	() -> new Block				(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BROWN)	.strength(1.6F, 2.0F).sound(SoundType.WOOD)));	
	public static RegistryObject<Block> CHERRY_LOG				= KonchuRegistry.BLOCKS.register("cherry_log", 				() -> new LogBlock			(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_PINK)		.strength(1.6F, 2.0F).sound(SoundType.WOOD), STRIPPED_CHERRY_LOG));
	public static RegistryObject<Block> CHERRY_WOOD				= KonchuRegistry.BLOCKS.register("cherry_wood", 			() -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_PINK)		.strength(1.6F, 2.0F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> CHERRY_PLANKS 			= KonchuRegistry.BLOCKS.register("cherry_planks",			() -> new Block				(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_PINK)		.strength(1.8F, 2.3F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> CHERRY_STAIRS 			= KonchuRegistry.BLOCKS.register("cherry_stairs",			() -> new StairsBlock		(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_PINK)		.strength(1.8F, 2.3F).sound(SoundType.WOOD)).setStair(() -> {return CHERRY_PLANKS;}));
	public static RegistryObject<Block> CHERRY_SLAB  			= KonchuRegistry.BLOCKS.register("cherry_slab", 			() -> new SlabBlock			(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_PINK)		.strength(1.8F, 2.3F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> CHERRY_DOOR				= KonchuRegistry.BLOCKS.register("cherry_door",				() -> new DoorBlock			(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_PINK)		.strength(      3.0F).sound(SoundType.WOOD).noOcclusion()));
	public static RegistryObject<Block> CHERRY_TRAPDOOR			= KonchuRegistry.BLOCKS.register("cherry_trapdoor", 		() -> new TrapDoorBlock 	(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_PINK)		.strength(      5.0F).sound(SoundType.WOOD).noOcclusion()));	
	public static RegistryObject<Block> CHERRY_FENCE			= KonchuRegistry.BLOCKS.register("cherry_fence",			() -> new FenceBlock		(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_PINK)		.strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> CHERRY_FENCE_GATE		= KonchuRegistry.BLOCKS.register("cherry_fence_gate", 		() -> new FenceGateBlock	(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_PINK)		.strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static RegistryObject<Block> CHERRY_SIGN				= KonchuRegistry.BLOCKS.register("cherry_sign", 			() -> new StandingSignBlock	(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_PINK)		.strength(		1.0F).sound(SoundType.WOOD), WoodType.OAK));
	public static RegistryObject<Block> CHERRY_WALL_SIGN		= KonchuRegistry.BLOCKS.register("cherry_wall_sign", 		() -> new WallSignBlock		(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_PINK)		.strength(		1.0F).sound(SoundType.WOOD), WoodType.OAK));
	public static RegistryObject<Block> CHERRY_BUTTON			= KonchuRegistry.BLOCKS.register("cherry_button", 			() -> new WoodButtonBlock	(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_PINK)		.strength(		0.5F).sound(SoundType.WOOD).noCollission()));
	public static RegistryObject<Block> CHERRY_PRESSURE_PLATE	= KonchuRegistry.BLOCKS.register("cherry_pressure_plate", 	() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_PINK).strength(0.5F).sound(SoundType.WOOD).noCollission()));
		
	/* Devices */
	public static RegistryObject<Block> RICE_COOKER	= KonchuRegistry.BLOCKS.register("rice_cooker",	() -> new RiceCookerBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.SNOW).harvestLevel(1).strength(1.3F, 6.0F).sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops()));
	
	/* Flora */
	public static RegistryObject<Block> CHERRY_LEAVES	= KonchuRegistry.BLOCKS.register("cherry_leaves", 	() -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES, 		MaterialColor.COLOR_PINK)		.strength(0.2F, 0.2F).sound(SoundType.CROP).noOcclusion()));
	public static RegistryObject<Block> MARIMO			= KonchuRegistry.BLOCKS.register("marimo", 			() -> new MarimoBlock(AbstractBlock.Properties.of(Material.VEGETABLE, 	MaterialColor.TERRACOTTA_GREEN)	.strength(1.0F, 1.0F).sound(SoundType.WET_GRASS)));
	
	/* Miscellaneous */
	public static RegistryObject<Block> CRAB_BLOCK = KonchuRegistry.BLOCKS.register("crab_block", () -> new RotatedBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_RED).harvestLevel(1).strength(25.0F, 900.0F).sound(SoundType.NETHERRACK)));
}





