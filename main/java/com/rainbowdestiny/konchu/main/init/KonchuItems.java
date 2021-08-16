package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.common.items.BlockItemBase;
import com.rainbowdestiny.konchu.common.items.NetItem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraftforge.fml.RegistryObject;

public class KonchuItems {

	
	//Tool Items
	public static final RegistryObject<Item> WOODEN_NET = KonchuRegistry.ITEMS.register("wooden_net", () -> new NetItem(25, ItemTier.WOOD));
	public static final RegistryObject<Item> CLOTH_NET = KonchuRegistry.ITEMS.register("cloth_net", () -> new NetItem(30, ItemTier.STONE));
	public static final RegistryObject<Item> GOLDEN_NET = KonchuRegistry.ITEMS.register("golden_net", () -> new NetItem(80, ItemTier.GOLD));
	public static final RegistryObject<Item> IRON_NET = KonchuRegistry.ITEMS.register("iron_net", () -> new NetItem(50, ItemTier.IRON));
	public static final RegistryObject<Item> DIAMOND_NET = KonchuRegistry.ITEMS.register("diamond_net", () -> new NetItem(70, ItemTier.DIAMOND));
	public static final RegistryObject<Item> NETHERITE_NET = KonchuRegistry.ITEMS.register("netherite_net", () -> new NetItem(85, ItemTier.NETHERITE));
	
	
	//Misc Items
	public static final RegistryObject<Item> NET = KonchuRegistry.ITEMS.register("net", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<Item> LICHEN_CLUMP = KonchuRegistry.ITEMS.register("lichen_clump", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<Item> CRAB_SHELL = KonchuRegistry.ITEMS.register("crab_shell",() -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<Item> ROTTEN_BARK = KonchuRegistry.ITEMS.register("rotten_bark", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));

	//Block Items
    public static final RegistryObject<Item> LICHEN_BLOCK_ITEM = KonchuRegistry.ITEMS.register("lichen_block", () -> new BlockItemBase(KonchuBlocks.LICHEN_BLOCK.get(),ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> CRAB_BLOCK_ITEM = KonchuRegistry.ITEMS.register("crab_block", () -> new BlockItemBase(KonchuBlocks.CRAB_BLOCK.get(), ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> DRIFT_LOG_ITEM = KonchuRegistry.ITEMS.register("drift_log", () -> new BlockItemBase(KonchuBlocks.DRIFT_LOG.get(), ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> DRIFT_WOOD_ITEM = KonchuRegistry.ITEMS.register("drift_wood", () -> new BlockItemBase(KonchuBlocks.DRIFT_WOOD.get(), ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Item> INFESTED_DRIFT_LOG_ITEM = KonchuRegistry.ITEMS.register("infested_drift_log", () -> new BlockItemBase(KonchuBlocks.INFESTED_DRIFT_LOG.get(), ItemGroup.TAB_BUILDING_BLOCKS));
    
}