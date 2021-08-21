package com.rainbowdestiny.konchu.common.entities;

import javax.annotation.Nullable;

import com.rainbowdestiny.konchu.main.init.KonchuEntityType;

import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GardenSnailEntity extends AnimalEntity implements IAnimatable {
	
	//Entity Setup
	public static final DataParameter<Integer> SNAIL_TYPE = EntityDataManager.defineId(GardenSnailEntity.class, DataSerializers.INT);
	private static final EntityPredicate SNAIL_RESTING_TARGETING = (new EntityPredicate()).range(4.0D).allowSameTeam();
	private static final DataParameter<Boolean> DATA_ID_FLAGS = EntityDataManager.defineId(GardenSnailEntity.class, DataSerializers.BOOLEAN);
	private static final Ingredient DIET = Ingredient.of(Blocks.ACACIA_LEAVES, Blocks.BIRCH_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.JUNGLE_LEAVES, Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES);
	private AnimationFactory factory = new AnimationFactory(this);
	private BlockPos targetPosition;
	
	@SuppressWarnings("unused")
	private boolean ignoreFrustumCheck;
	
	protected void registerGoals() {
		this.goalSelector.addGoal(2, new TemptGoal(this, 2.0D, false, DIET));
		this.goalSelector.addGoal(10, new BreedGoal(this, 0.8D));
		this.goalSelector.addGoal(11, new WaterAvoidingRandomWalkingGoal(this, 0.8D, 1.0000001E-5F));
	}
	
    public GardenSnailEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
        this.ignoreFrustumCheck = true;
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 0.5D));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(32, new BreedGoal(this, 0.8D));
    }
    
    //Attributes
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
		return MobEntity.createLivingAttributes()
        .add(Attributes.MOVEMENT_SPEED, (double) 0.1F).add(Attributes.MAX_HEALTH, 3.0D).add(Attributes.FOLLOW_RANGE, 6.0D);
	}
    
    public boolean isFood(ItemStack itemStack) {
        return DIET.test(itemStack);
    }
    
    public boolean isResting() {
        return this.entityData.get(DATA_ID_FLAGS);
     }

     public void setResting(boolean entity) {
           this.entityData.set(DATA_ID_FLAGS, entity);

     }
     
     public void tick() {
         super.tick();
         if (this.isResting()) {
             this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
         } else {
             this.setDeltaMovement(Vector3d.ZERO);
             this.setPosRaw(this.getX(), (double)MathHelper.floor(this.getY()) + 1.0D - (double)this.getBbHeight(), this.getZ());
         }

      }
     
     protected void customServerAiStep() {
         super.customServerAiStep();
         BlockPos blockpos = this.blockPosition();
         BlockPos blockpos1 = blockpos.above();
         if (this.isResting()) {
            boolean flag = this.isSilent();
            if (this.level.getBlockState(blockpos1).isRedstoneConductor(this.level, blockpos)) {
               if (this.random.nextInt(200) == 0) {
                  this.yHeadRot = (float)this.random.nextInt(360);
               }

               if (this.level.getNearestPlayer(SNAIL_RESTING_TARGETING, this) != null) {
                  this.setResting(false);
                  if (!flag) {
                     this.level.levelEvent((PlayerEntity)null, 1025, blockpos, 0);
                  }
               }
            } else {
               this.setResting(false);
               if (!flag) {
                  this.level.levelEvent((PlayerEntity)null, 1025, blockpos, 0);
               }
            }
         } else {
            if (this.targetPosition != null && (!this.level.isEmptyBlock(this.targetPosition) || this.targetPosition.getY() < 1)) {
               this.targetPosition = null;
            }

            if (this.targetPosition == null || this.random.nextInt(30) == 0 || this.targetPosition.closerThan(this.position(), 2.0D)) {
               this.targetPosition = new BlockPos(this.getX() + (double)this.random.nextInt(7) - (double)this.random.nextInt(7), this.getY() + (double)this.random.nextInt(6) - 2.0D, this.getZ() + (double)this.random.nextInt(7) - (double)this.random.nextInt(7));
            }

            double d2 = (double)this.targetPosition.getX() + 0.5D - this.getX();
            double d0 = (double)this.targetPosition.getY() + 0.1D - this.getY();
            double d1 = (double)this.targetPosition.getZ() + 0.5D - this.getZ();
            Vector3d vector3d = this.getDeltaMovement();
            Vector3d vector3d1 = vector3d.add((Math.signum(d2) * 0.5D - vector3d.x) * (double)0.1F, (Math.signum(d0) * (double)0.7F - vector3d.y) * (double)0.1F, (Math.signum(d1) * 0.5D - vector3d.z) * (double)0.1F);
            this.setDeltaMovement(vector3d1);
            float f = (float)(MathHelper.atan2(vector3d1.z, vector3d1.x) * (double)(180F / (float)Math.PI)) - 90.0F;
            float f1 = MathHelper.wrapDegrees(f - this.yRot);
            this.zza = 0.5F;
            this.yRot += f1;
            if (this.random.nextInt(100) == 0 && this.level.getBlockState(blockpos1).isRedstoneConductor(this.level, blockpos1)) {
               this.setResting(true);
            }
            
         }

      }
     
    //Snail Data
	public void addAdditionalSaveData(CompoundNBT nbt) {
		super.addAdditionalSaveData(nbt);
		if (this.entityData != null) {
			nbt.putInt("SnailType", this.entityData.get(SNAIL_TYPE));
		}
		
	}
	
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("SnailType")) {
        	this.setSnailType(nbt.getInt("SnailType"));
        } else {
        	this.setSnailType(this.random.nextInt(4));
        }
     }
	
    @Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(SNAIL_TYPE, random.nextInt(4));
		this.getEntityData().define(DATA_ID_FLAGS, false);

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
    
    public GardenSnailEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) {
    	GardenSnailEntity snailentity = new GardenSnailEntity(KonchuEntityType.GARDEN_SNAIL.get(), world);
        if (entity instanceof GardenSnailEntity) {
           if (this.random.nextBoolean()) {
              snailentity.setSnailType(this.getSnailType());
           } else {
        	   snailentity.setSnailType(((GardenSnailEntity)entity).getSnailType());
           }
           
        }
        return snailentity;
    }
    
    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld level, DifficultyInstance instance, SpawnReason reason, @Nullable ILivingEntityData data, @Nullable CompoundNBT nbt) {
    	data = super.finalizeSpawn(level, instance, reason, data, nbt);
    	return data;
    }
    
    //Animation Controllers
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.snail.move", true));
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