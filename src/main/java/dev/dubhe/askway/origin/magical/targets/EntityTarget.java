package dev.dubhe.askway.origin.magical.targets;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.phys.Vec3;

import net.minecraft.world.entity.Entity;

public class EntityTarget implements ITarget {
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

    @Override
    public Level getLevel() {
        return entity.level();
    }

    @Override
    @SuppressWarnings("resource")
    public LevelChunk getChunk() {
        return entity.level().getChunkAt(entity.getOnPos());
    }
}
