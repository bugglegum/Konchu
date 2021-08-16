package com.rainbowdestiny.konchu.common.entities;

import java.util.Map;

import com.google.common.collect.Maps;
import com.rainbowdestiny.konchu.main.init.KonchuEntityType;

import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
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
	
	private static final Ingredient DIET = Ingredient.of(Blocks.ACACIA_LEAVES, Blocks.BIRCH_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.JUNGLE_LEAVES, Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES);
	public static final DataParameter<Integer> DATA_TYPE_ID = EntityDataManager.defineId(GardenSnailEntity.class, DataSerializers.INT);
	public static final Map<Integer, ResourceLocation> TEXTURE_BY_TYPE = Util.make(Maps.newHashMap(), (type) -> {
	    type.put(0, new ResourceLocation("textures/entity/snail/brown_garden_snail.png"));
	    type.put(1, new ResourceLocation("textures/entity/snail/green_garden_snail.png"));
	    type.put(2, new ResourceLocation("textures/entity/snail/gray_garden_snail.png"));
	    type.put(3, new ResourceLocation("textures/entity/snail/golden_garden_snail.png"));
	});
	
	private AnimationFactory factory = new AnimationFactory(this);
	@SuppressWarnings("unused")
	private boolean ignoreFrustumCheck;

	   protected void registerGoals() {
		      this.goalSelector.addGoal(1, new TemptGoal(this, 2.0D, false, DIET));
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
    
    public int getSnailType() {
        return this.entityData.get(DATA_TYPE_ID);
     }

    public void setSnailType(int mate) {
        if (mate < 0 || mate >= 4) {
        	mate = this.random.nextInt(3);
        }

        this.entityData.set(DATA_TYPE_ID, mate);
     }
    
    public ResourceLocation getResourceLocation() {
        return TEXTURE_BY_TYPE.getOrDefault(this.getSnailType(), TEXTURE_BY_TYPE.get(0));
     }
    
    public void addAdditionalSaveData(CompoundNBT TypeData) {
        super.addAdditionalSaveData(TypeData);
        TypeData.putInt("SnailType", this.getSnailType());
     }
    
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
		return MobEntity.createLivingAttributes()
        .add(Attributes.MOVEMENT_SPEED, (double) 0.1F).add(Attributes.MAX_HEALTH, 3.0D).add(Attributes.FOLLOW_RANGE, 6.0D);
	}
    
    public boolean isFood(ItemStack itemStack) {
        return DIET.test(itemStack);
     }
      
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
	

}