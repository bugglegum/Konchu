package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.common.items.ItemBase;
import com.rainbowdestiny.konchu.common.items.NetItem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraftforge.fml.RegistryObject;

public class ItemInit {

	
	//Items
	public static final RegistryObject<Item> WOODEN_NET = KonchuRegistry.ITEMS.register("wooden_net", () -> new NetItem(25, ItemTier.WOOD));
	public static final RegistryObject<Item> CLOTH_NET = KonchuRegistry.ITEMS.register("cloth_net", () -> new NetItem(30, ItemTier.STONE));
	public static final RegistryObject<Item> GOLDEN_NET = KonchuRegistry.ITEMS.register("golden_net", () -> new NetItem(80, ItemTier.GOLD));
	public static final RegistryObject<Item> IRON_NET = KonchuRegistry.ITEMS.register("iron_net", () -> new NetItem(50, ItemTier.IRON));
	public static final RegistryObject<Item> DIAMOND_NET = KonchuRegistry.ITEMS.register("diamond_net", () -> new NetItem(70, ItemTier.DIAMOND));
	public static final RegistryObject<Item> NETHERITE_NET = KonchuRegistry.ITEMS.register("netherite_net", () -> new NetItem(85, ItemTier.NETHERITE));
	
	public static final RegistryObject<Item> NET = KonchuRegistry.ITEMS.register("net", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<Item> LICHEN_CLUMP = KonchuRegistry.ITEMS.register("lichen_clump", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));

	//Block Items
    public static final RegistryObject<Item> LICHEN_BLOCK_ITEM = KonchuRegistry.ITEMS.register("lichen_block", () -> new ItemBase(BlockInit.LICHEN_BLOCK.get(),ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Item> CRAB_BLOCK_ITEM = KonchuRegistry.ITEMS.register("crab_block", () -> new ItemBase(BlockInit.CRAB_BLOCK.get(), ItemGroup.TAB_BUILDING_BLOCKS));

}
