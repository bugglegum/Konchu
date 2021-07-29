package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.Konchu;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Konchu.MOD_ID);
	
	public static final RegistryObject<Item> NET = ITEMS.register("net", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
}
