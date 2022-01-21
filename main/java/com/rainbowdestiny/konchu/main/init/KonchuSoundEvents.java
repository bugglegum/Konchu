package com.rainbowdestiny.konchu.main.init;

import com.rainbowdestiny.konchu.Konchu;

import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class KonchuSoundEvents {

	public static final RegistryObject<SoundEvent> FROG_AMBIENT	= buildSound(KonchuRegistry.SOUNDS, "frog.ambient");
	public static final RegistryObject<SoundEvent> FROG_CROAK	= buildSound(KonchuRegistry.SOUNDS, "frog.croak");
	public static final RegistryObject<SoundEvent> FROG_JUMP	= buildSound(KonchuRegistry.SOUNDS, "frog.jump");
	public static final RegistryObject<SoundEvent> FROG_HURT	= buildSound(KonchuRegistry.SOUNDS, "frog.hurt");
	public static final RegistryObject<SoundEvent> FROG_DEATH	= buildSound(KonchuRegistry.SOUNDS, "frog.death");
	public static SoundType addSoundType(float vol, float pitch, java.util.function.Supplier<SoundEvent> soundSupplier) {
		return new SoundTypeBetter(vol, pitch, soundSupplier);
	}

	public static RegistryObject<SoundEvent> buildSound(DeferredRegister<SoundEvent> register, String registryName) {	
		RegistryObject<SoundEvent> SOUND = register.register(registryName,() -> new SoundEvent(new ResourceLocation(Konchu.MOD_ID,registryName)));
		return SOUND;
	}

	public static class SoundTypeBetter extends SoundType{

		private final java.util.function.Supplier<SoundEvent> soundSupplier;

		@SuppressWarnings("deprecation")
		public SoundTypeBetter(float volumeIn, float pitchIn, java.util.function.Supplier<SoundEvent> sound) {
			super(volumeIn, pitchIn, null, null, null, null, null);
			soundSupplier = sound;
		}

		@Override
		public SoundEvent getBreakSound() {
			return soundSupplier.get();
		}
		@Override
		public SoundEvent getFallSound() {
			return soundSupplier.get();
		}
		@Override
		public SoundEvent getHitSound() {
			return soundSupplier.get();
		}
		@Override
		public SoundEvent getPlaceSound() {
			return soundSupplier.get();
		}
		@Override
		public SoundEvent getStepSound() {
			return soundSupplier.get();
		}
	}
}
