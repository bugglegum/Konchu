package com.rainbowdestiny.konchu.util.goals;

import java.util.EnumSet;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;

public class HidingGoal extends Goal {
	protected final CreatureEntity mob;
	protected final double speedModifier;

	public HidingGoal(CreatureEntity entity, double d) {
		this.mob = entity;
		this.speedModifier = d;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if (this.mob.getSensing() == null) {
		return false;
		} else {
			if (this.mob.getLastHurtByMob() != null) {
	        	return true;		
			}
			
		}
        return this.stopMovement();
		
	}

	private boolean stopMovement() {
        this.mob.xxa = 0.0F;
        this.mob.zza = 0.0F;
        
        return false;
	}

}
