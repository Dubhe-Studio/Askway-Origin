package dev.dubhe.askway.origin.magical.casters;

import dev.dubhe.askway.origin.magical.MagicGroup;
import dev.dubhe.askway.origin.magical.goals.IGoal;
import dev.dubhe.askway.origin.magical.modes.IMode;
import dev.dubhe.askway.origin.magical.targets.EntityTarget;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class LivingEntityCaster implements ICaster {
    private final LivingEntity entity;

    public LivingEntityCaster(LivingEntity entity) {
        this.entity = entity;
    }

    /**
     * 施放法术
     *
     * @param mode   施放方式
     * @param goal   目标类型
     * @param direct 直接目标
     * @param magics 法术组
     */
    @Override
    public void execute(@Nonnull IMode mode, IGoal goal, ITarget direct, MagicGroup @NotNull ... magics) {
        for (MagicGroup magic : magics) {
            if (this.entity instanceof Player player) {
                magic.setEnergy(player.experienceLevel);
                player.giveExperiencePoints(-player.experienceLevel);
            } else {
                magic.setEnergy((int) entity.getHealth());
            }
        }
        mode.execute(this, goal, direct, magics);
    }

    @Override
    public Vec3 getPos() {
        return entity.getEyePosition();
    }

    @Override
    public void setPos(Vec3 pos) {
        this.entity.moveTo(pos);
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
