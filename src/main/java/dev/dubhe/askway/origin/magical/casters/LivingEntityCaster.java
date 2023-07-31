package dev.dubhe.askway.origin.magical.casters;

import dev.dubhe.askway.origin.magical.targets.EntityTarget;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class LivingEntityCaster implements ICaster {
    private final LivingEntity entity;

    public LivingEntityCaster(LivingEntity entity) {
        this.entity = entity;
    }

    @Override
    public Vec3 getPos() {
        return entity.getEyePosition();
    }

    @Override
    public Vec3 getViewVector() {
        return this.entity.getViewVector(1.0F);
    }

    @Override
    public Vec3 getUpVector() {
        return this.entity.getUpVector(1.0F);
    }

    @Override
    public DamageSource getDamageSource() {
        return this.entity.damageSources().source(DamageTypes.MAGIC, this.entity);
    }

    @Override
    public ITarget getTarget() {
        return new EntityTarget(entity);
    }

    public Entity getOwner() {
        return this.entity;
    }
}
