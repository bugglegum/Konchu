
package com.rainbowdestiny.konchu.main;
import org.apache.commons.lang3.tuple.Pair;

import com.rainbowdestiny.konchu.Konchu;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = Konchu.MOD_ID, bus = Bus.MOD)
public class KonchuConfig {
	public static class Common {

		public final ForgeConfigSpec.BooleanValue net_whitelist;

		public Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Konchu Configuration Settings");

			net_whitelist = builder
					.comment("Adds entities to the list of viable net catches. (LivingEntity mobs only) DEFAULT: true")
					.translation("konchu.config.net_whitelist")
					.worldRestart()
					.define("net_whitelist", true);
		}
	}

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading event) {
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfig.Reloading event) {
	}
}