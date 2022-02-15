package com.rainbowdestiny.konchu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rainbowdestiny.konchu.util.GeckoLib;
import com.rainbowdestiny.konchu.world.OreGeneration;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Konchu.MOD_ID)
public class Konchu {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "konchu";

    @SuppressWarnings("deprecation")
	public static final CommonSetup PROXY = DistExecutor.runForDist(() -> ClientSetup::new, () -> CommonSetup::new);
    
    public Konchu() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        KonchuRegistry.init();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);
		MinecraftForge.EVENT_BUS.register(this);
        GeckoLib.initialize();
        bus.addListener(this::clientSetup);
    }
    
    private void clientSetup(final FMLClientSetupEvent event) {
        PROXY.clientSetup(event);
    }
}