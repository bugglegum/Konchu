package com.rainbowdestiny.konchu.common.entity;

import javax.annotation.Nullable;

import com.rainbowdestiny.konchu.main.init.KonchuEntityType;
import com.rainbowdestiny.konchu.main.init.KonchuSoundEvents;

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
import net.minecraft.entity.ai.controller.JumpController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.easing.EasingType;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class FrogEntity extends AnimalEntity implements IAnimatable  {

	//Entity Setup
	public static final DataParameter<Integer> FROG_OR_TOAD = EntityDataManager.defineId(FrogEntity.class, DataSerializers.INT);
	private static final DataParameter<Boolean> MOVING = EntityDataManager.defineId(FrogEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> TRUSTING = EntityDataManager.defineId(FrogEntity.class, DataSerializers.BOOLEAN);
	private FrogEntity.FrogAvoidEntityGoal<PlayerEntity> frogAvoidPlayersGoal;
	private AnimationFactory factory = new AnimationFactory(this);
	private int jumpTicks;
	private int jumpDuration;
	private boolean wasOnGround;
	private int jumpDelayTicks;

	@SuppressWarnings("unused")
	private boolean ignoreFrustumCheck;

	protected void registerGoals() {
		this.goalSelector.addGoal(8, new BreedGoal(this, 0.8D));
	}

	public FrogEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
		this.ignoreFrustumCheck = true;

		this.goalSelector.addGoal(4, new FrogEntity.FrogAvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 2.2D, 2.2D));
		//this.goalSelector.addGoal(5, new swim
		this.goalSelector.addGoal(6, new RandomWalkingGoal(this, 5.0D));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(8, new BreedGoal(this, 1.0D));
		this.jumpControl = new FrogEntity.JumpHelperController(this);
		this.moveControl = new FrogEntity.MoveHelperController(this);
		this.setSpeedModifier(2.0D);
		this.reassessTrustingGoals();
	}

	protected float getJumpPower() {
		if (!this.horizontalCollision && (!this.moveControl.hasWanted() || !(this.moveControl.getWantedY() > this.getY() + 0.5D))) {
			Path path = this.navigation.getPath();
			if (path != null && !path.isDone()) {
				Vector3d vector3d = path.getNextEntityPos(this);
				if (vector3d.y > this.getY() + 2.0D) {
					return 1.5F;
				}
			}
			return this.moveControl.getSpeedModifier() <= 0.6D ? 0.2F : 0.3F;
		} else {
			return 1.5F;
		}
	}	

	protected void jumpFromGround() {
		super.jumpFromGround();
		double d0 = this.moveControl.getSpeedModifier();
		if (d0 > 0.0D) {
			double d1 = getHorizontalDistanceSqr(this.getDeltaMovement());
			if (d1 < 0.01D) {
				this.moveRelative(0.1F, new Vector3d(0.0D, 0.0D, 5.0D));
			}
		}

		if (!this.level.isClientSide) {
			this.level.broadcastEntityEvent(this, (byte)1);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public float getJumpCompletion(float f) {
		return this.jumpDuration == 0 ? 0.0F : ((float)this.jumpTicks + f) / (float)this.jumpDuration;
	}

	public void setSpeedModifier(double d) {
		this.getNavigation().setSpeedModifier(d);
		this.moveControl.setWantedPosition(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ(), d);
	}

	public void setJumping(boolean b) {
		super.setJumping(b);
		if ((b) && (!this.isInWater())) {
			this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
		}
	}

	public void startJumping() {
		this.setJumping(true);
		this.jumpDuration = 15;
		this.jumpTicks = 0;
	}

	public void customServerAiStep() {
		if (this.jumpDelayTicks > 0) {
			--this.jumpDelayTicks;
		}

		if (this.onGround) {
			if (!this.wasOnGround) {
				this.setJumping(false);
				this.checkLandingDelay();
			}

			if (this.jumpDelayTicks == 0) {
				LivingEntity livingentity = this.getTarget();
				if (livingentity != null && this.distanceToSqr(livingentity) < 16.0D) {
					this.facePoint(livingentity.getX(), livingentity.getZ());
					this.moveControl.setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), this.moveControl.getSpeedModifier());
					this.startJumping();
					this.wasOnGround = true;
				}
			}
			FrogEntity.JumpHelperController frogentity$jumphelpercontroller = (FrogEntity.JumpHelperController)this.jumpControl;
			if (!frogentity$jumphelpercontroller.wantJump()) {
				if (this.moveControl.hasWanted() && this.jumpDelayTicks == 0) {
					Path path = this.navigation.getPath();
					Vector3d vector3d = new Vector3d(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ());
					if (path != null && !path.isDone()) {
						vector3d = path.getNextEntityPos(this);
					}
					this.facePoint(vector3d.x, vector3d.z);
					this.startJumping();
				}
			} else if (!frogentity$jumphelpercontroller.canJump()) {
				this.enableJumpControl();
			}
		}

		this.wasOnGround = this.onGround;
	}

	private void facePoint(double d1, double d2) {
		this.yRot = (float)(MathHelper.atan2(d1 - this.getZ(), d2 - this.getX()) * (double)(180F / (float)Math.PI)) - 90.0F;
	}

	private void enableJumpControl() {
		((FrogEntity.JumpHelperController)this.jumpControl).setCanJump(true);
	}

	private void disableJumpControl() {
		((FrogEntity.JumpHelperController)this.jumpControl).setCanJump(false);
	}

	private void setLandingDelay() {
		if (this.moveControl.getSpeedModifier() < 2.2D) {
			this.jumpDelayTicks = 10;
		} else {
			this.jumpDelayTicks = 1;
		}

	}

	private void checkLandingDelay() {
		this.setLandingDelay();
		this.disableJumpControl();
	}

	public void aiStep() {
		super.aiStep();
		if (this.jumpTicks != this.jumpDuration) {
			++this.jumpTicks;
		} else if (this.jumpDuration != 0) {
			this.jumpTicks = 0;
			this.jumpDuration = 0;
			this.setJumping(false);
		}

	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.4F;
	}

	public boolean canBreatheUnderwater() {
		return true;
	}
	
	//Attributes
	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
		return MobEntity.createLivingAttributes()
				.add(Attributes.MOVEMENT_SPEED, (double) 1F)
				.add(Attributes.MAX_HEALTH, 3.0D)
				.add(Attributes.FOLLOW_RANGE, 6.0D);
	}

	//Frog Data
	public void addAdditionalSaveData(CompoundNBT nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("Trusting", this.isTrusting());
		if (this.entityData != null) {
			nbt.putInt("FrogType", this.entityData.get(FROG_OR_TOAD));
		}
	}

	public void readAdditionalSaveData(CompoundNBT nbt) {
		super.readAdditionalSaveData(nbt);
		this.setTrusting(nbt.getBoolean("Trusting"));
		if (nbt.contains("FrogType")) {
			this.setFrogType(nbt.getInt("FrogType"));
		} else {
			this.setFrogType(this.random.nextInt(4));
		}
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(FROG_OR_TOAD, random.nextInt(4));
		this.entityData.define(MOVING, false);
		this.entityData.define(TRUSTING, false);
	}

	public int getFrogType() {
		return this.entityData.get(FROG_OR_TOAD).intValue();
	}

	public void setFrogType(int mate) {
		if (mate < 0 || mate >= 1) {
			mate = this.random.nextInt(3);
		}
		if (this.entityData != null) {
			this.entityData.set(FROG_OR_TOAD, mate);
		}
	}

	@Override
	public boolean removeWhenFarAway(double d) {
		return !this.isTrusting() && this.tickCount > 10;
	}

	boolean isTrusting() {
		return this.entityData.get(TRUSTING);
	}

	private void setTrusting(boolean b) {
		this.entityData.set(TRUSTING, b);
		this.reassessTrustingGoals();
	}

	protected void reassessTrustingGoals() {
		if (this.frogAvoidPlayersGoal == null) {
			this.frogAvoidPlayersGoal = new FrogEntity.FrogAvoidEntityGoal<>(this, PlayerEntity.class, 16.0F, 0.8D, 1.33D);
		}
		this.goalSelector.removeGoal(this.frogAvoidPlayersGoal);

		if (!this.isTrusting()) {
			this.goalSelector.addGoal(4, this.frogAvoidPlayersGoal);
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
		super.tick();
	}

	public FrogEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) {
		FrogEntity frogentity = new FrogEntity(KonchuEntityType.FROG.get(), world);
		if (entity instanceof FrogEntity) {
			if (this.random.nextBoolean()) {
				frogentity.setFrogType(this.getFrogType());
			} else {
				frogentity.setFrogType(((FrogEntity)entity).getFrogType());
			}
		}
		return frogentity;
	}

	@Nullable
	public ILivingEntityData finalizeSpawn(IServerWorld level, DifficultyInstance instance, SpawnReason reason, @Nullable ILivingEntityData data, @Nullable CompoundNBT nbt) {
		data = super.finalizeSpawn(level, instance, reason, data, nbt);
		return data;
	}

	public boolean isMoving(){
		return this.getEntityData().get(MOVING);
	}

	//Jumping Control
	public class JumpHelperController extends JumpController {
		private final FrogEntity frog;
		private boolean canJump;

		public JumpHelperController(FrogEntity frog) {
			super(frog);
			this.frog = frog;
		}

		public boolean wantJump() {
			return this.jump;
		}

		public boolean canJump() {
			return this.canJump;
		}

		public void setCanJump(boolean b) {
			this.canJump = b;
		}

		public void tick() {
			if (this.jump) {
				this.frog.startJumping();
				this.jump = false;
			}
		}
	}

	static class MoveHelperController extends MovementController {
		private final FrogEntity frog;
		private double nextJumpSpeed;

		public MoveHelperController(FrogEntity frog) {
			super(frog);
			this.frog = frog;
		}

		public void tick() {
			if (this.frog.onGround && !this.frog.jumping && !((FrogEntity.JumpHelperController)this.frog.jumpControl).wantJump()) {
				this.frog.setSpeedModifier(0.0D);
			} else if (this.hasWanted()) {
				this.frog.setSpeedModifier(this.nextJumpSpeed);
			}

			super.tick();
		}

		public void setWantedPosition(double d1, double d2, double d3, double d4) {
			if (this.frog.isInWater()) {
				d4 = 1.5D;
			}

			super.setWantedPosition(d1, d2, d3, d4);
			if (d4 > 0.0D) {
				this.nextJumpSpeed = d4;
			}
		}
	}

	public boolean hurt(DamageSource d, float f) {
		return this.isInvulnerableTo(d) ? false : super.hurt(d, f);
	}

	/*
	protected ItemStack getBucketItemStack() {
		return new ItemStack(KonchuItems.FROG_BUCKET.get());
	}
	 */

	//Goal Setup
	static class FrogAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
		private final FrogEntity frog;

		public FrogAvoidEntityGoal(FrogEntity frog, Class<T> classObj, float f, double d1, double d2) {
			super(frog, classObj, f, d1, d2, EntityPredicates.NO_CREATIVE_OR_SPECTATOR::test);
			this.frog = frog;
		}

		public boolean canUse() {
			return !this.frog.isTrusting() && super.canUse();
		}

		public boolean canContinueToUse() {
			return !this.frog.isTrusting() && super.canContinueToUse();
		}
	}

	//Animation Controllers
	@SuppressWarnings("unchecked")
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (!this.isInWater()) {
			if (this.isMoving() && this.isOnGround()) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frog.walk", true));
			} else {
				if (!this.isOnGround()) {
					event.getController().easingType = EasingType.EaseInOutBounce;
					event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frog.jump", false));       	
				} else {
					event.getController().easingType = EasingType.EaseInElastic;
					event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frog.idle", true));       					
				}
			}
		} else {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frog.swim", true));       					
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

	//Sounds
	protected SoundEvent getJumpSound() {
		return KonchuSoundEvents.FROG_JUMP.get();
	}

	protected SoundEvent getAmbientSound() {
		return KonchuSoundEvents.FROG_AMBIENT.get();
	}

	protected SoundEvent getHurtSound(DamageSource d) {
		return KonchuSoundEvents.FROG_HURT.get();
	}

	protected SoundEvent getDeathSound() {
		return KonchuSoundEvents.FROG_JUMP.get();
	}
}