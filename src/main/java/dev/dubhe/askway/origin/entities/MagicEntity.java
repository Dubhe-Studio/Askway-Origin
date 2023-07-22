package dev.dubhe.askway.origin.entities;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

public class MagicEntity extends Projectile {
    public boolean gravity;
    private static final EntityDataAccessor<Boolean> GRAVITY = SynchedEntityData.defineId(MagicEntity.class, EntityDataSerializers.BOOLEAN);


    public MagicEntity(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {
    }
}
