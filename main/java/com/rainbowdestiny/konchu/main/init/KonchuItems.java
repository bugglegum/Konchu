package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.item.ProxyBlockItem;
import com.rainbowdestiny.konchu.item.FrogHatItem;
import com.rainbowdestiny.konchu.item.MagnifierItem;
import com.rainbowdestiny.konchu.item.NetItem;
import com.rainbowdestiny.konchu.item.ProxySpawnEggItem;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;

public class KonchuItems {

	/* Tools */
	public static final RegistryObject<Item> NET		= KonchuRegistry.ITEMS.register("net", 			() -> new NetItem(30, ItemTier.STONE, 			ItemGroup.TAB_TOOLS));
	public static final RegistryObject<Item> GOLDEN_NET	= KonchuRegistry.ITEMS.register("golden_net", 	() -> new NetItem(80, ItemTier.GOLD, 			ItemGroup.TAB_TOOLS));
	public static final RegistryObject<Item> MAGNIFIER 	= KonchuRegistry.ITEMS.register("magnifier", 	() -> new MagnifierItem(new Item.Properties(), 	ItemGroup.TAB_TOOLS));
	
	/* Wearable Items */
    public static final RegistryObject<Item> FROG_HAT = KonchuRegistry.ITEMS.register("frog_hat", () -> new FrogHatItem(EquipmentSlotType.HEAD, "Very incredible hat!", (ItemGroup.TAB_COMBAT)));
	
	/* Materials */
	public static final RegistryObject<Item> LICHEN_CLUMP 	= KonchuRegistry.ITEMS.register("lichen_clump", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<Item> MOSS_CLUMP 	= KonchuRegistry.ITEMS.register("moss_clump", 	() -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(16)));
	public static final RegistryObject<Item> ROTTEN_BARK 	= KonchuRegistry.ITEMS.register("rotten_bark", 	() -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<Item> CRAB_SHELL 	= KonchuRegistry.ITEMS.register("crab_shell",	() -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(16)));

	/* Block Items */
    public static final RegistryObject<Item> LICHEN_GROWTH_ITEM					= KonchuRegistry.ITEMS.register("lichen_growth", 				() -> new ProxyBlockItem(KonchuBlocks.LICHEN_GROWTH.get(),				ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> LICHEN_BLOCK_ITEM 					= KonchuRegistry.ITEMS.register("lichen_block", 				() -> new ProxyBlockItem(KonchuBlocks.LICHEN_BLOCK.get(),				ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> KAOLINITE_ITEM 					= KonchuRegistry.ITEMS.register("kaolinite", 					() -> new ProxyBlockItem(KonchuBlocks.KAOLINITE.get(),					ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> BIRCH_CHAIR						= KonchuRegistry.ITEMS.register("birch_chair", 					() -> new ProxyBlockItem(KonchuBlocks.BIRCH_CHAIR.get(), 				ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> SPRUCE_CHAIR						= KonchuRegistry.ITEMS.register("spruce_chair", 				() -> new ProxyBlockItem(KonchuBlocks.SPRUCE_CHAIR.get(), 				ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> DARK_OAK_CHAIR 					= KonchuRegistry.ITEMS.register("dark_oak_chair", 				() -> new ProxyBlockItem(KonchuBlocks.DARK_OAK_CHAIR.get(), 				ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> DRIFT_CHAIR 						= KonchuRegistry.ITEMS.register("drift_chair", 					() -> new ProxyBlockItem(KonchuBlocks.DRIFT_CHAIR.get(), 				ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> CHERRY_CHAIR 						= KonchuRegistry.ITEMS.register("cherry_chair", 				() -> new ProxyBlockItem(KonchuBlocks.CHERRY_CHAIR.get(), 				ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> COBBLESTONE_SHRINE_LANTERN			= KonchuRegistry.ITEMS.register("cobblestone_shrine_lantern", 	() -> new ProxyBlockItem(KonchuBlocks.COBBLESTONE_SHRINE_LANTERN.get(), 	ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> COBBLESTONE_SHRINE_PAGODA			= KonchuRegistry.ITEMS.register("cobblestone_shrine_pagoda", 	() -> new ProxyBlockItem(KonchuBlocks.COBBLESTONE_SHRINE_PAGODA.get(), 	ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> SUGAR_CANE_TATAMI_ITEM 			= KonchuRegistry.ITEMS.register("sugar_cane_tatami",			() -> new ProxyBlockItem(KonchuBlocks.SUGAR_CANE_TATAMI.get(), 			ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> BAMBOO_TATAMI_ITEM 				= KonchuRegistry.ITEMS.register("bamboo_tatami", 				() -> new ProxyBlockItem(KonchuBlocks.BAMBOO_TATAMI.get(), 				ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> PAPER_LANTERN 						= KonchuRegistry.ITEMS.register("paper_lantern", 				() -> new ProxyBlockItem(KonchuBlocks.PAPER_LANTERN.get(),				ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> WHITE_PAPER_LANTERN 				= KonchuRegistry.ITEMS.register("white_paper_lantern", 			() -> new ProxyBlockItem(KonchuBlocks.WHITE_PAPER_LANTERN.get(),			ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> ORANGE_PAPER_LANTERN 				= KonchuRegistry.ITEMS.register("orange_paper_lantern", 		() -> new ProxyBlockItem(KonchuBlocks.ORANGE_PAPER_LANTERN.get(),		ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> MAGENTA_PAPER_LANTERN 				= KonchuRegistry.ITEMS.register("magenta_paper_lantern", 		() -> new ProxyBlockItem(KonchuBlocks.MAGENTA_PAPER_LANTERN.get(),		ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> LIGHT_BLUE_PAPER_LANTERN 			= KonchuRegistry.ITEMS.register("light_blue_paper_lantern", 	() -> new ProxyBlockItem(KonchuBlocks.LIGHT_BLUE_PAPER_LANTERN.get(),	ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> YELLOW_PAPER_LANTERN 				= KonchuRegistry.ITEMS.register("yellow_paper_lantern", 		() -> new ProxyBlockItem(KonchuBlocks.YELLOW_PAPER_LANTERN.get(),		ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> LIME_PAPER_LANTERN 				= KonchuRegistry.ITEMS.register("lime_paper_lantern", 			() -> new ProxyBlockItem(KonchuBlocks.LIME_PAPER_LANTERN.get(),			ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> PINK_PAPER_LANTERN 				= KonchuRegistry.ITEMS.register("pink_paper_lantern", 			() -> new ProxyBlockItem(KonchuBlocks.PINK_PAPER_LANTERN.get(),			ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> GRAY_PAPER_LANTERN 				= KonchuRegistry.ITEMS.register("gray_paper_lantern", 			() -> new ProxyBlockItem(KonchuBlocks.GRAY_PAPER_LANTERN.get(),			ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> LIGHT_GRAY_PAPER_LANTERN 			= KonchuRegistry.ITEMS.register("light_gray_paper_lantern", 	() -> new ProxyBlockItem(KonchuBlocks.LIGHT_GRAY_PAPER_LANTERN.get(),	ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> CYAN_PAPER_LANTERN 				= KonchuRegistry.ITEMS.register("cyan_paper_lantern", 			() -> new ProxyBlockItem(KonchuBlocks.CYAN_PAPER_LANTERN.get(),			ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> PURPLE_PAPER_LANTERN 				= KonchuRegistry.ITEMS.register("purple_paper_lantern", 		() -> new ProxyBlockItem(KonchuBlocks.PURPLE_PAPER_LANTERN.get(),		ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> BLUE_PAPER_LANTERN 				= KonchuRegistry.ITEMS.register("blue_paper_lantern", 			() -> new ProxyBlockItem(KonchuBlocks.BLUE_PAPER_LANTERN.get(),			ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> BROWN_PAPER_LANTERN 				= KonchuRegistry.ITEMS.register("brown_paper_lantern", 			() -> new ProxyBlockItem(KonchuBlocks.BROWN_PAPER_LANTERN.get(),			ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> GREEN_PAPER_LANTERN 				= KonchuRegistry.ITEMS.register("green_paper_lantern", 			() -> new ProxyBlockItem(KonchuBlocks.GREEN_PAPER_LANTERN.get(),			ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> RED_PAPER_LANTERN 					= KonchuRegistry.ITEMS.register("red_paper_lantern", 			() -> new ProxyBlockItem(KonchuBlocks.RED_PAPER_LANTERN.get(),			ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> BLACK_PAPER_LANTERN 				= KonchuRegistry.ITEMS.register("black_paper_lantern", 			() -> new ProxyBlockItem(KonchuBlocks.BLACK_PAPER_LANTERN.get(),			ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> STRIPPED_INFESTED_DRIFT_LOG_ITEM 	= KonchuRegistry.ITEMS.register("stripped_infested_drift_log", 	() -> new ProxyBlockItem(KonchuBlocks.STRIPPED_INFESTED_DRIFT_LOG.get(), ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> STRIPPED_INFESTED_DRIFT_WOOD_ITEM	= KonchuRegistry.ITEMS.register("stripped_infested_drift_wood", () -> new ProxyBlockItem(KonchuBlocks.STRIPPED_INFESTED_DRIFT_WOOD.get(),ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> INFESTED_DRIFT_LOG_ITEM 			= KonchuRegistry.ITEMS.register("infested_drift_log",			() -> new ProxyBlockItem(KonchuBlocks.INFESTED_DRIFT_LOG.get(), 			ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> INFESTED_DRIFT_WOOD_ITEM 			= KonchuRegistry.ITEMS.register("infested_drift_wood", 			() -> new ProxyBlockItem(KonchuBlocks.INFESTED_DRIFT_WOOD.get(), 		ItemGroup.TAB_BUILDING_BLOCKS));   
    public static final RegistryObject<Item> STRIPPED_DRIFT_LOG_ITEM 			= KonchuRegistry.ITEMS.register("stripped_drift_log", 			() -> new ProxyBlockItem(KonchuBlocks.STRIPPED_DRIFT_LOG.get(), 			ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> STRIPPED_DRIFT_WOOD_ITEM 			= KonchuRegistry.ITEMS.register("stripped_drift_wood", 			() -> new ProxyBlockItem(KonchuBlocks.STRIPPED_DRIFT_WOOD.get(), 		ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> DRIFT_LOG_ITEM 					= KonchuRegistry.ITEMS.register("drift_log", 					() -> new ProxyBlockItem(KonchuBlocks.DRIFT_LOG.get(), 					ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> DRIFT_WOOD_ITEM 					= KonchuRegistry.ITEMS.register("drift_wood", 					() -> new ProxyBlockItem(KonchuBlocks.DRIFT_WOOD.get(), 					ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> DRIFT_PLANKS_ITEM 					= KonchuRegistry.ITEMS.register("drift_planks", 				() -> new ProxyBlockItem(KonchuBlocks.DRIFT_PLANKS.get(), 				ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> DRIFT_STAIRS_ITEM 					= KonchuRegistry.ITEMS.register("drift_stairs", 				() -> new ProxyBlockItem(KonchuBlocks.DRIFT_STAIRS.get(), 				ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> DRIFT_SLAB_ITEM 					= KonchuRegistry.ITEMS.register("drift_slab", 					() -> new ProxyBlockItem(KonchuBlocks.DRIFT_SLAB.get(), 					ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> DRIFT_DOOR_ITEM					= KonchuRegistry.ITEMS.register("drift_door", 					() -> new ProxyBlockItem(KonchuBlocks.DRIFT_DOOR.get(), 					ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Item> DRIFT_TRAPDOOR 					= KonchuRegistry.ITEMS.register("drift_trapdoor", 				() -> new ProxyBlockItem(KonchuBlocks.DRIFT_TRAPDOOR.get(), 				ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Item> DRIFT_FENCE_ITEM 					= KonchuRegistry.ITEMS.register("drift_fence", 					() -> new ProxyBlockItem(KonchuBlocks.DRIFT_FENCE.get(), 				ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> DRIFT_FENCE_GATE_ITEM 				= KonchuRegistry.ITEMS.register("drift_fence_gate", 			() -> new ProxyBlockItem(KonchuBlocks.DRIFT_FENCE_GATE.get(), 			ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Item> DRIFT_BUTTON_ITEM 					= KonchuRegistry.ITEMS.register("drift_button", 				() -> new ProxyBlockItem(KonchuBlocks.DRIFT_BUTTON.get(), 				ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Item> DRIFT_PRESSURE_PLATE_ITEM 			= KonchuRegistry.ITEMS.register("drift_pressure_plate",			() -> new ProxyBlockItem(KonchuBlocks.DRIFT_PRESSURE_PLATE.get(),		ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Item> DRIFT_SIGN_ITEM 					= KonchuRegistry.ITEMS.register("drift_sign", 					() -> new ProxyBlockItem(KonchuBlocks.DRIFT_SIGN.get(), 					ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> STRIPPED_CHERRY_LOG_ITEM 			= KonchuRegistry.ITEMS.register("stripped_cherry_log", 			() -> new ProxyBlockItem(KonchuBlocks.STRIPPED_CHERRY_LOG.get(), 		ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> STRIPPED_CHERRY_WOOD_ITEM 			= KonchuRegistry.ITEMS.register("stripped_cherry_wood", 		() -> new ProxyBlockItem(KonchuBlocks.STRIPPED_CHERRY_WOOD.get(),		ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> CHERRY_LOG_ITEM 					= KonchuRegistry.ITEMS.register("cherry_log", 					() -> new ProxyBlockItem(KonchuBlocks.CHERRY_LOG.get(), 					ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> CHERRY_WOOD_ITEM 					= KonchuRegistry.ITEMS.register("cherry_wood", 					() -> new ProxyBlockItem(KonchuBlocks.CHERRY_WOOD.get(), 				ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> CHERRY_PLANKS_ITEM 				= KonchuRegistry.ITEMS.register("cherry_planks", 				() -> new ProxyBlockItem(KonchuBlocks.CHERRY_PLANKS.get(), 				ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> CHERRY_STAIRS_ITEM 				= KonchuRegistry.ITEMS.register("cherry_stairs", 				() -> new ProxyBlockItem(KonchuBlocks.CHERRY_STAIRS.get(), 				ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> CHERRY_SLAB_ITEM 					= KonchuRegistry.ITEMS.register("cherry_slab", 					() -> new ProxyBlockItem(KonchuBlocks.CHERRY_SLAB.get(), 				ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> CHERRY_DOOR_ITEM					= KonchuRegistry.ITEMS.register("cherry_door", 					() -> new ProxyBlockItem(KonchuBlocks.CHERRY_DOOR.get(), 				ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Item> CHERRY_TRAPDOOR 					= KonchuRegistry.ITEMS.register("cherry_trapdoor", 				() -> new ProxyBlockItem(KonchuBlocks.CHERRY_TRAPDOOR.get(), 			ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Item> CHERRY_FENCE_ITEM 					= KonchuRegistry.ITEMS.register("cherry_fence", 				() -> new ProxyBlockItem(KonchuBlocks.CHERRY_FENCE.get(), 				ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> CHERRY_FENCE_GATE_ITEM 			= KonchuRegistry.ITEMS.register("cherry_fence_gate", 			() -> new ProxyBlockItem(KonchuBlocks.CHERRY_FENCE_GATE.get(), 			ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Item> CHERRY_BUTTON_ITEM 				= KonchuRegistry.ITEMS.register("cherry_button", 				() -> new ProxyBlockItem(KonchuBlocks.CHERRY_BUTTON.get(), 				ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Item> CHERRY_PRESSURE_PLATE_ITEM 		= KonchuRegistry.ITEMS.register("cherry_pressure_plate",		() -> new ProxyBlockItem(KonchuBlocks.CHERRY_LEAVES.get(), 				ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Item> CHERRY_LEAVES 						= KonchuRegistry.ITEMS.register("cherry_leaves",				() -> new ProxyBlockItem(KonchuBlocks.MARIMO.get(), 						ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> MARIMO_ITEM 						= KonchuRegistry.ITEMS.register("marimo", 						() -> new ProxyBlockItem(KonchuBlocks.MARIMO.get(), 						ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> CRAB_BLOCK_ITEM 					= KonchuRegistry.ITEMS.register("crab_block", 					() -> new ProxyBlockItem(KonchuBlocks.CRAB_BLOCK.get(), 					ItemGroup.TAB_DECORATIONS));
    
    /* Music Discs */
    public static final RegistryObject<Item> MUSIC_DISC_HELIX		= KonchuRegistry.ITEMS.register("music_disc_helix", 	() -> new MusicDiscItem(5, KonchuSoundEvents.MUSIC_DISC_HELIX, 		new Item.Properties().rarity(Rarity.RARE).tab(ItemGroup.TAB_MISC).stacksTo(1)));
    public static final RegistryObject<Item> MUSIC_DISC_SUBURBAN	= KonchuRegistry.ITEMS.register("music_disc_suburban",	() -> new MusicDiscItem(5, KonchuSoundEvents.MUSIC_DISC_SUBURBAN, 	new Item.Properties().rarity(Rarity.RARE).tab(ItemGroup.TAB_MISC).stacksTo(1)));
    
    /* Spawn Eggs */
    public static final RegistryObject<Item> SNAIL_SPAWN_EGG	= KonchuRegistry.ITEMS.register("snail_spawn_egg", 	() -> new ProxySpawnEggItem(KonchuEntityType.SNAIL, 0xAF8C6B, 0xA8C68F, (new Item.Properties()).tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> FROG_SPAWN_EGG		= KonchuRegistry.ITEMS.register("frog_spawn_egg", 	() -> new ProxySpawnEggItem(KonchuEntityType.FROG,  0x94C557, 0xFFFDAC, (new Item.Properties()).tab(ItemGroup.TAB_MISC)));

	//public static final RegistryObject<Item> FROG_BUCKET	= KonchuRegistry.ITEMS.register("frog_bucket", () -> new FishBucketItem(KonchuEntityType.FROG, Fluids.WATER, new Item.Properties().tab(ItemGroup.TAB_MISC)));
}
