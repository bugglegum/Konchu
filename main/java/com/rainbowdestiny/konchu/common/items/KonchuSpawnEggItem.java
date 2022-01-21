package com.rainbowdestiny.konchu.common.items;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class KonchuSpawnEggItem extends SpawnEggItem {

	protected static final ArrayList<KonchuSpawnEggItem> EGGS_TO_ADD = new ArrayList<KonchuSpawnEggItem>();
	private final int color1;
	private final int color2;

	private final Lazy<? extends EntityType<?>> lazyEntity;

	public KonchuSpawnEggItem(final RegistryObject<? extends EntityType<?>> entity, int color1, int color2, final Properties properties) {
		super(null, color1, color2, properties);
		this.color1 = 0;
		this.color2 = 0;
		this.lazyEntity = Lazy.of(entity::get);
		EGGS_TO_ADD.add(this);
	}

	@Override
	public int getItemStackLimit(ItemStack stack) {
		return 1;
	}

	@OnlyIn(Dist.CLIENT)
	public int getColor(int color) {
		return color == 0 ? this.color1 : this.color2;
	}

	public static void initSpawnEggs() {
		final Map<EntityType<?>, SpawnEggItem> EGGS = ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "field_195987_b");

		for (final SpawnEggItem item : EGGS_TO_ADD) {
			EGGS.put(item.getType(null), item);
		}
		EGGS_TO_ADD.clear();
	}

	@Override
	public EntityType<?> getType(CompoundNBT nbt) {
		return this.lazyEntity.get();
	}

	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getLevel();
		if (!(world instanceof ServerWorld)) {
			return ActionResultType.SUCCESS;
		} else {
			ItemStack itemstack = context.getItemInHand();
			BlockPos blockpos = context.getClickedPos();
			Direction direction = context.getClickedFace();
			BlockState blockstate = world.getBlockState(blockpos);

			BlockPos blockpos1;
			if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
				blockpos1 = blockpos;
			} else {
				blockpos1 = blockpos.relative(direction);
			}

			EntityType<?> entitytype = this.getType(itemstack.getTag());

			CompoundNBT NBT = new CompoundNBT();

			if (itemstack.hasTag()) {
				NBT.merge(itemstack.getTag());
			}
			if (!NBT.contains("EntityTag")) {
				NBT.put("EntityTag", new CompoundNBT());
			}

			Entity summonedEntity = entitytype.spawn((ServerWorld) world, NBT, null, context.getPlayer(), blockpos1, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);

			if (summonedEntity != null) {
				itemstack.shrink(1);
			}

			return ActionResultType.CONSUME;
		}
	}
}
