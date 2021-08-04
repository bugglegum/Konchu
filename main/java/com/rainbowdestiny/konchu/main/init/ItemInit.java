package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.Konchu;
import com.rainbowdestiny.konchu.common.items.ItemBase;
import com.rainbowdestiny.konchu.common.items.NetItem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Konchu.MOD_ID);
	
	//Items
	public static final RegistryObject<Item> WOODEN_NET = ITEMS.register("wooden_net", () -> new NetItem(new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
	public static final RegistryObject<Item> CLOTH_NET = ITEMS.register("cloth_net", () -> new NetItem(new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
	public static final RegistryObject<Item> GOLDEN_NET = ITEMS.register("golden_net", () -> new NetItem(new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
	public static final RegistryObject<Item> IRON_NET = ITEMS.register("iron_net", () -> new NetItem(new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
	public static final RegistryObject<Item> DIAMOND_NET = ITEMS.register("diamond_net", () -> new NetItem(new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
	public static final RegistryObject<Item> NETHERITE_NET = ITEMS.register("netherite_net", () -> new NetItem(new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
	
	public static final RegistryObject<Item> NET = ITEMS.register("net", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<Item> LICHEN_CLUMP = ITEMS.register("lichen_clump", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));

	//Block Items
    public static final RegistryObject<Item> LICHEN_BLOCK_ITEM = ItemInit.ITEMS.register("lichen_block", () -> new ItemBase(BlockInit.LICHEN_BLOCK.get(),ItemGroup.TAB_DECORATIONS));

}
