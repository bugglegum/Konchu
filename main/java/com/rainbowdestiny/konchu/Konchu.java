package com.rainbowdestiny.konchu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rainbowdestiny.konchu.main.ClientProxy;
import com.rainbowdestiny.konchu.main.GeckoLib;
import com.rainbowdestiny.konchu.main.ServerProxy;
import com.rainbowdestiny.konchu.main.init.KonchuRegistry;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Konchu.MOD_ID)
public class Konchu
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "konchu";

    @SuppressWarnings("deprecation")
	public static final ServerProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    
    public Konchu() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        KonchuRegistry.init();
		MinecraftForge.EVENT_BUS.register(this);
        GeckoLib.initialize();
        bus.addListener(this::doClientStuff);
    }
    
    private void doClientStuff(final FMLClientSetupEvent event) {
        PROXY.doClientStuff(event);
    }

}