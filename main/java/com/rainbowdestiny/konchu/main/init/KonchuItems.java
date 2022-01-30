package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.common.item.BaseBlockItem;
import com.rainbowdestiny.konchu.common.item.FrogHatItem;
import com.rainbowdestiny.konchu.common.item.MagnifierItem;
import com.rainbowdestiny.konchu.common.item.NetItem;
import com.rainbowdestiny.konchu.common.item.ProxySpawnEggItem;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraftforge.fml.RegistryObject;

public class KonchuItems {

	/* Tool Items */
	public static final RegistryObject<Item> WOODEN_NET		= KonchuRegistry.ITEMS.register("wooden_net", 		() -> new NetItem(25, ItemTier.WOOD, 			ItemGroup.TAB_TOOLS));
	public static final RegistryObject<Item> CLOTH_NET		= KonchuRegistry.ITEMS.register("cloth_net", 		() -> new NetItem(30, ItemTier.STONE, 			ItemGroup.TAB_TOOLS));
	public static final RegistryObject<Item> GOLDEN_NET		= KonchuRegistry.ITEMS.register("golden_net", 		() -> new NetItem(80, ItemTier.GOLD, 			ItemGroup.TAB_TOOLS));
	public static final RegistryObject<Item> IRON_NET		= KonchuRegistry.ITEMS.register("iron_net", 		() -> new NetItem(50, ItemTier.IRON, 			ItemGroup.TAB_TOOLS));
	public static final RegistryObject<Item> DIAMOND_NET	= KonchuRegistry.ITEMS.register("diamond_net", 		() -> new NetItem(70, ItemTier.DIAMOND, 		ItemGroup.TAB_TOOLS));
	public static final RegistryObject<Item> NETHERITE_NET	= KonchuRegistry.ITEMS.register("netherite_net", 	() -> new NetItem(85, ItemTier.NETHERITE, 		ItemGroup.TAB_TOOLS));
	public static final RegistryObject<Item> MAGNIFIER 		= KonchuRegistry.ITEMS.register("magnifier", 		() -> new MagnifierItem(new Item.Properties(), 	ItemGroup.TAB_TOOLS));
	
	/* Wearable Items */
    public static final RegistryObject<Item> FROG_HAT 		= KonchuRegistry.ITEMS.register("frog_hat", () -> new FrogHatItem(EquipmentSlotType.HEAD, "Very incredible hat!", (ItemGroup.TAB_COMBAT)));
	
	/* Miscellaneous Items */
	public static final RegistryObject<Item> NET 			= KonchuRegistry.ITEMS.register("net", 			() -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<Item> LICHEN_CLUMP 	= KonchuRegistry.ITEMS.register("lichen_clump", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<Item> MOSS_CLUMP 	= KonchuRegistry.ITEMS.register("moss_clump", 	() -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<Item> CRAB_SHELL 	= KonchuRegistry.ITEMS.register("crab_shell",	() -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<Item> ROTTEN_BARK 	= KonchuRegistry.ITEMS.register("rotten_bark", 	() -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
	//public static final RegistryObject<Item> FROG_BUCKET	= KonchuRegistry.ITEMS.register("frog_bucket", () -> new FishBucketItem(KonchuEntityType.FROG, Fluids.WATER, new Item.Properties().tab(ItemGroup.TAB_MISC)));

	/* Block Items */
    public static final RegistryObject<Item> KAOLINITE_ITEM 	= KonchuRegistry.ITEMS.register("kaolinite_block", 		() -> new BaseBlockItem(KonchuBlocks.KAOLINITE.get(),		ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> LICHEN_BLOCK_ITEM 	= KonchuRegistry.ITEMS.register("lichen_block", 		() -> new BaseBlockItem(KonchuBlocks.LICHEN_BLOCK.get(),		ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> LICHEN_GROWTH_ITEM = KonchuRegistry.ITEMS.register("lichen_growth", 		() -> new BaseBlockItem(KonchuBlocks.LICHEN_GROWTH.get(),		ItemGroup.TAB_DECORATIONS));

    public static final RegistryObject<Item> STRIPPED_INFESTED_DRIFT_LOG_ITEM 	= KonchuRegistry.ITEMS.register("stripped_infested_drift_log", 	() -> new BaseBlockItem(KonchuBlocks.STRIPPED_INFESTED_DRIFT_LOG.get(), 	ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> STRIPPED_INFESTED_DRIFT_WOOD_ITEM	= KonchuRegistry.ITEMS.register("stripped_infested_drift_wood", () -> new BaseBlockItem(KonchuBlocks.STRIPPED_INFESTED_DRIFT_WOOD.get(), 	ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> INFESTED_DRIFT_LOG_ITEM 			= KonchuRegistry.ITEMS.register("infested_drift_log",			() -> new BaseBlockItem(KonchuBlocks.INFESTED_DRIFT_LOG.get(), 				ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> INFESTED_DRIFT_WOOD_ITEM 			= KonchuRegistry.ITEMS.register("infested_drift_wood", 			() -> new BaseBlockItem(KonchuBlocks.INFESTED_DRIFT_WOOD.get(), 			ItemGroup.TAB_BUILDING_BLOCKS));   
    
    public static final RegistryObject<Item> STRIPPED_DRIFT_LOG_ITEM 	= KonchuRegistry.ITEMS.register("stripped_drift_log", 	() -> new BaseBlockItem(KonchuBlocks.STRIPPED_DRIFT_LOG.get(), 	ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> STRIPPED_DRIFT_WOOD_ITEM 	= KonchuRegistry.ITEMS.register("stripped_drift_wood", 	() -> new BaseBlockItem(KonchuBlocks.STRIPPED_DRIFT_WOOD.get(), ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> DRIFT_LOG_ITEM 			= KonchuRegistry.ITEMS.register("drift_log", 			() -> new BaseBlockItem(KonchuBlocks.DRIFT_LOG.get(), 			ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> DRIFT_WOOD_ITEM 			= KonchuRegistry.ITEMS.register("drift_wood", 			() -> new BaseBlockItem(KonchuBlocks.DRIFT_WOOD.get(), 			ItemGroup.TAB_BUILDING_BLOCKS));
    
    public static final RegistryObject<Item> DRIFT_PLANKS_ITEM 	= KonchuRegistry.ITEMS.register("drift_planks", () -> new BaseBlockItem(KonchuBlocks.DRIFT_PLANKS.get(), 	ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> DRIFT_STAIRS_ITEM 	= KonchuRegistry.ITEMS.register("drift_stairs", () -> new BaseBlockItem(KonchuBlocks.DRIFT_STAIRS.get(), 	ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> DRIFT_SLAB_ITEM 	= KonchuRegistry.ITEMS.register("drift_slab", 	() -> new BaseBlockItem(KonchuBlocks.DRIFT_SLAB.get(), 		ItemGroup.TAB_BUILDING_BLOCKS));

    public static final RegistryObject<Item> DRIFT_PRESSURE_PLATE_ITEM 	= KonchuRegistry.ITEMS.register("drift_pressure_plate", () -> new BaseBlockItem(KonchuBlocks.DRIFT_PRESSURE_PLATE.get(), 	ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Item> DRIFT_BUTTON_ITEM 			= KonchuRegistry.ITEMS.register("drift_button", 		() -> new BaseBlockItem(KonchuBlocks.DRIFT_BUTTON.get(), 			ItemGroup.TAB_REDSTONE));
   
    public static final RegistryObject<Item> DRIFT_DOOR_ITEM		= KonchuRegistry.ITEMS.register("drift_door", 		() -> new BaseBlockItem(KonchuBlocks.DRIFT_DOOR.get(), 			ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Item> DRIFT_TRAP_DOOR 		= KonchuRegistry.ITEMS.register("drift_trap_door", 	() -> new BaseBlockItem(KonchuBlocks.DRIFT_TRAPDOOR.get(), 		ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Item> DRIFT_FENCE_ITEM 		= KonchuRegistry.ITEMS.register("drift_fence", 		() -> new BaseBlockItem(KonchuBlocks.DRIFT_FENCE.get(), 		ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> DRIFT_FENCE_GATE_ITEM 	= KonchuRegistry.ITEMS.register("drift_fence_gate", () -> new BaseBlockItem(KonchuBlocks.DRIFT_FENCE_GATE.get(), 	ItemGroup.TAB_REDSTONE));

    public static final RegistryObject<Item> DRIFT_SIGN_ITEM = KonchuRegistry.ITEMS.register("drift_sign", () -> new BaseBlockItem(KonchuBlocks.DRIFT_SIGN.get(), ItemGroup.TAB_DECORATIONS));

    public static final RegistryObject<Item> SUGAR_CANE_TATAMI_ITEM = KonchuRegistry.ITEMS.register("sugar_cane_tatami",	() -> new BaseBlockItem(KonchuBlocks.SUGAR_CANE_TATAMI.get(), 	ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> BAMBOO_TATAMI_ITEM 	= KonchuRegistry.ITEMS.register("bamboo_tatami", 		() -> new BaseBlockItem(KonchuBlocks.BAMBOO_TATAMI.get(), 		ItemGroup.TAB_DECORATIONS));
    
    public static final RegistryObject<Item> MARIMO_ITEM 				= KonchuRegistry.ITEMS.register("marimo", 						() -> new BaseBlockItem(KonchuBlocks.MARIMO.get(), 						ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> COBBLESTONE_SHRINE_LANTERN	= KonchuRegistry.ITEMS.register("cobblestone_shrine_lantern", 	() -> new BaseBlockItem(KonchuBlocks.COBBLESTONE_SHRINE_LANTERN.get(), 	ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> COBBLESTONE_SHRINE_PAGODA	= KonchuRegistry.ITEMS.register("cobblestone_shrine_pagoda", 	() -> new BaseBlockItem(KonchuBlocks.COBBLESTONE_SHRINE_PAGODA.get(), 	ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> CRAB_BLOCK_ITEM 			= KonchuRegistry.ITEMS.register("crab_block", 					() -> new BaseBlockItem(KonchuBlocks.CRAB_BLOCK.get(), 					ItemGroup.TAB_DECORATIONS));
    
    /* Spawn Items */
    public static final RegistryObject<Item> SNAIL_SPAWN_EGG	= KonchuRegistry.ITEMS.register("snail_spawn_egg", 	() -> new ProxySpawnEggItem(KonchuEntityType.SNAIL, 10581318, 12693373, (new Item.Properties()).tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> FROG_SPAWN_EGG		= KonchuRegistry.ITEMS.register("frog_spawn_egg", 	() -> new ProxySpawnEggItem(KonchuEntityType.FROG,   7844695, 16776620, (new Item.Properties()).tab(ItemGroup.TAB_MISC)));
}
