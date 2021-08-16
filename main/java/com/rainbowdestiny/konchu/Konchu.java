package com.rainbowdestiny.konchu;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.rainbowdestiny.konchu.main.GeckoLib;
import com.rainbowdestiny.konchu.main.init.KonchuRegistry;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Konchu.MOD_ID)
public class Konchu
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "konchu";

    public Konchu() {
		@SuppressWarnings("unused")
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        KonchuRegistry.init();
        
		MinecraftForge.EVENT_BUS.register(this);

        GeckoLib.initialize();
        
    }

}