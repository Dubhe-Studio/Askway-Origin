package dev.dubhe.askway.origin.magical.casters;

import net.minecraft.world.damagesource.DamageSource;
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
    public DamageSource getDamageSource() {
        return this.entity.damageSources().mobAttack(this.entity);
    }
}
