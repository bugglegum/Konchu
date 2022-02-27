package com.rainbowdestiny.konchu.entity;

import javax.annotation.Nullable;

import com.rainbowdestiny.konchu.util.goals.HidingGoal;

import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.ClimberPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.easing.EasingType;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SnailEntity extends AnimalEntity implements IAnimatable {
	public static final DataParameter<Integer> SNAIL_TYPE = EntityDataManager.defineId(SnailEntity.class, DataSerializers.INT);
	private static final DataParameter<Boolean> HIDING = EntityDataManager.defineId(SnailEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> MOVING = EntityDataManager.defineId(SnailEntity.class, DataSerializers.BOOLEAN);
	private static final Ingredient DIET = Ingredient.of(Blocks.ACACIA_LEAVES, Blocks.BIRCH_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.JUNGLE_LEAVES, Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES);
	private static final DataParameter<Boolean> TRUSTING = EntityDataManager.defineId(SnailEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.defineId(SnailEntity.class, DataSerializers.BYTE);
	private SnailEntity.SnailAvoidEntityGoal<PlayerEntity> snailAvoidPlayersGoal;
	private AnimationFactory factory = new AnimationFactory(this);
	private LookRandomlyGoal lookRandom;
	private int afraid;
	private int mostAfraid = 30;

	@SuppressWarnings("unused")
	private boolean ignoreFrustumCheck;

	protected void registerGoals() {
		this.goalSelector.addGoal(5, new HidingGoal(this, 0));
		this.goalSelector.addGoal(8, new BreedGoal(this, 0.8D));
	}

	public SnailEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
		this.ignoreFrustumCheck = true;
		this.goalSelector.addGoal(5, new HidingGoal(this, 0));
		this.goalSelector.addGoal(6, new RandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, lookRandom = new LookRandomlyGoal(this));
		this.goalSelector.addGoal(8, new BreedGoal(this, 0.8D));
		this.reassessTrustingGoals();
	}

	protected PathNavigator createNavigation(World p_175447_1_) {
		return new ClimberPathNavigator(this, p_175447_1_);
	}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.4F;
	}

	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
		return MobEntity.createLivingAttributes()
				.add(Attributes.MOVEMENT_SPEED, (double) 0.1F)
				.add(Attributes.MAX_HEALTH, 3.0D)
				.add(Attributes.FOLLOW_RANGE, 6.0D)
				.add(Attributes.JUMP_STRENGTH, 0.2D)
				.add(Attributes.FOLLOW_RANGE, 2.0D);
	}

	public boolean isFood(ItemStack itemStack) {
		return DIET.test(itemStack);
	}

	public void addAdditionalSaveData(CompoundNBT nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("Trusting", this.isTrusting());
		if (this.entityData != null) {
			nbt.putInt("SnailType", this.entityData.get(SNAIL_TYPE));
		}
		nbt.putBoolean("Hiding", isHiding());
	}

	public void readAdditionalSaveData(CompoundNBT nbt) {
		super.readAdditionalSaveData(nbt);
		this.setTrusting(nbt.getBoolean("Trusting"));
		if (nbt.contains("SnailType")) {
			this.setSnailType(nbt.getInt("SnailType"));
		} else {
			this.setSnailType(this.random.nextInt(4));
		}
		if(nbt.contains("Hiding")){
			this.setHiding(nbt.getBoolean("Hiding"));
		}else{
			this.setHiding(false);
		}
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(SNAIL_TYPE, random.nextInt(4));
		this.entityData.define(HIDING, false);
		this.entityData.define(MOVING, false);
		this.entityData.define(TRUSTING, false);
		this.entityData.define(DATA_FLAGS_ID, (byte)0);
	}

	public int getSnailType() {
		return this.entityData.get(SNAIL_TYPE).intValue();
	}

	public void setSnailType(int mate) {
		if (mate < 0 || mate >= 4) {
			mate = this.random.nextInt(3);
		}
		if (this.entityData != null) {
			this.entityData.set(SNAIL_TYPE, mate);
		}
	}

	@Override
	public boolean removeWhenFarAway(double d) {
		return !this.isTrusting() && this.tickCount > 2;
	}

	boolean isTrusting() {
		return this.entityData.get(TRUSTING);
	}

	private void setTrusting(boolean b) {
		this.setHiding(false);
		this.entityData.set(TRUSTING, b);
		this.reassessTrustingGoals();
	}

	protected void reassessTrustingGoals() {
		if (this.snailAvoidPlayersGoal == null) {
			setHiding(false);
			this.snailAvoidPlayersGoal = new SnailEntity.SnailAvoidEntityGoal<>(this, PlayerEntity.class, 16.0F, 0.8D, 1.33D);
		}
		this.goalSelector.removeGoal(this.snailAvoidPlayersGoal);
		
		if (!this.isTrusting()) {
			this.goalSelector.addGoal(4, this.snailAvoidPlayersGoal);
		}
	}

	public boolean isSteppingCarefully() {
		return this.getPose() == Pose.CROUCHING || super.isSteppingCarefully();
	}

	@Override
	public void tick() {
		if (this.getDeltaMovement().x != 0) {
			if (!this.getEntityData().get(MOVING)) {
				this.getEntityData().set(MOVING, true);
			}
		} else {
			if (this.getEntityData().get(MOVING)) {
				this.getEntityData().set(MOVING, false);
			}
		}
		
		if (!this.level.isClientSide) {
			this.setClimbing(this.horizontalCollision);
		}
		super.tick();
	}

	private int getRotation(int rotation) {
		if (rotation > (  0 + 45) && rotation < ( 90 + 45)) return 1; // E
		if (rotation > ( 90 + 45) && rotation < (180 + 45)) return 2; // S
		if (rotation > (180 + 45) && rotation < (270 + 45)) return 3; // W
		return 0; 													  // N
	}
	
	public SnailEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) {
		SnailEntity snailentity = new SnailEntity(KonchuEntityType.SNAIL.get(), world);
		if (entity instanceof SnailEntity) {
			if (this.random.nextBoolean()) {
				snailentity.setSnailType(this.getSnailType());
			} else {
				snailentity.setSnailType(((SnailEntity)entity).getSnailType());
			}
		}
		return snailentity;
	}

	@Nullable
	public ILivingEntityData finalizeSpawn(IServerWorld level, DifficultyInstance instance, SpawnReason reason, @Nullable ILivingEntityData data, @Nullable CompoundNBT nbt) {
		data = super.finalizeSpawn(level, instance, reason, data, nbt);
		return data;
	}

	public void setHiding(boolean state) {
		this.getEntityData().set(HIDING, state);
		if (state) {
			this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.0);
			this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(35.0);
			this.goalSelector.removeGoal(new SnailTemptGoal(this, 2.0D, DIET, false));
			this.goalSelector.removeGoal(lookRandom);
			this.jumping = false;
		} else {
			this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1);
			this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(3.0);
			this.goalSelector.addGoal(3, new SnailTemptGoal(this, 2.0D, DIET, false));
			this.goalSelector.addGoal(6, lookRandom);        	
			this.jumping = true;
		}
	}

	public boolean isHiding() {
		return this.getEntityData().get(HIDING);
	}

	public boolean isMoving(){
		return this.getEntityData().get(MOVING);
	}

	@Override
	protected boolean isMovementNoisy() {
		return false;
	}

	public boolean isClimbing() {
		return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
	}

	public boolean onClimbable() {
		return this.isClimbing();
	}
	
	public void setClimbing(boolean p_70839_1_) {
		byte b0 = this.entityData.get(DATA_FLAGS_ID);
		if (p_70839_1_) {
			b0 = (byte) (b0 | 1);
		} else {
			b0 = (byte) (b0 & -2);
		}

		this.entityData.set(DATA_FLAGS_ID, b0);
	}
	
	@Override
	public boolean hurt(DamageSource damage, float f) {
		setHiding(true);
		this.reassessTrustingGoals();
		return super.hurt(damage, f);
	}

	public boolean causeFallDamage(float f1, float f2) {
		boolean flag = super.causeFallDamage(f1, f2);
		this.afraid = (int)((float)this.afraid + f1 * 1.5F);
		if (this.afraid > this.mostAfraid - 5) {
			this.afraid = this.mostAfraid - 5;
		}
		return flag;
	}

	static class SnailAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
		private final SnailEntity snail;

		public SnailAvoidEntityGoal(SnailEntity snail, Class<T> classObj, float f, double d1, double d2) {
			super(snail, classObj, f, d1, d2, EntityPredicates.NO_CREATIVE_OR_SPECTATOR::test);
			this.snail = snail;
		}
		public boolean canUse() {
			return !this.snail.isTrusting() && super.canUse();
		}
		public boolean canContinueToUse() {
			return !this.snail.isTrusting() && super.canContinueToUse();
		}
	}

	static class SnailTemptGoal extends TemptGoal {
		private final SnailEntity snail;

		public SnailTemptGoal(SnailEntity snail, double d, Ingredient ingredient, boolean b) {
			super(snail, d, ingredient, b);
			this.snail = snail;
		}
		protected boolean canScare() {
			return super.canScare() && !this.snail.isTrusting();
		}
	}

	@SuppressWarnings("unchecked")
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (!this.isHiding()) {
			if (this.isMoving()) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("snail_move", true));
			} else {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("snail_idle", true));       	
			}
		} else {
			if (this.isHiding()) {
				event.getController().transitionLengthTicks  = 2;
				event.getController().easingType = EasingType.EaseInOutBounce;
				event.getController().setAnimation(new AnimationBuilder().addAnimation("snail_hide", false));        	
			}
			event.getController().setAnimation(new AnimationBuilder().addAnimation("snail_hiding", true));        	
		}
		return PlayState.CONTINUE;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
	}

	public AnimationFactory getFactory() {
		return this.factory;
	}

}