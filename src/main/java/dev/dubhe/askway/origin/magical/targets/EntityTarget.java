package dev.dubhe.askway.origin.magical.targets;

import net.minecraft.world.phys.Vec3;

import net.minecraft.world.entity.Entity;

public class EntityTarget implements ITarget{
    private final Entity entity;

    public EntityTarget(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public Vec3 getPos() {
        return entity.getEyePosition();
    }
}
