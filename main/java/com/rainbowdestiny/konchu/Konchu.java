package com.rainbowdestiny.konchu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rainbowdestiny.konchu.main.GeckoLib;
import com.rainbowdestiny.konchu.main.init.BlockInit;
import com.rainbowdestiny.konchu.main.init.ItemInit;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Konchu.MOD_ID)
public class Konchu
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "konchu";

    public Konchu() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);

        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        
		MinecraftForge.EVENT_BUS.register(this);
        
        GeckoLib.initialize();
    }

    private void setup(final FMLCommonSetupEvent event){
    }
}