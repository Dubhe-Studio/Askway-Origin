package dev.dubhe.askway.origin.entities;

import dev.dubhe.askway.origin.init.AskwayModItems;
import dev.dubhe.askway.origin.magical.MagicGroup;
import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.casters.LivingEntityCaster;
import dev.dubhe.askway.origin.magical.targets.BlockTarget;
import dev.dubhe.askway.origin.magical.targets.EntityTarget;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MagicEntity extends Projectile implements ItemSupplier {
    private boolean gravity = false;
    private MagicGroup[] magicGroup = {};
    private ItemStack item = ItemStack.EMPTY;

    public MagicEntity(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    public MagicEntity setGravity(boolean gravity) {
        this.gravity = gravity;
        return this;
    }

    public MagicEntity setMagicGroup(MagicGroup[] magicGroup) {
        this.magicGroup = magicGroup;
        return this;
    }

    public MagicEntity setItem(ItemStack item) {
        this.item = item;
        return this;
    }

    @Override
    public void tick() {
        super.tick();
        boolean inGround = false;
        Vec3 vec3 = this.getDeltaMovement();
        if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
            double d0 = vec3.horizontalDistance();
            this.setYRot((float) (Mth.atan2(vec3.x, vec3.z) * (double) (180F / (float) Math.PI)));
            this.setXRot((float) (Mth.atan2(vec3.y, d0) * (double) (180F / (float) Math.PI)));
            this.yRotO = this.getYRot();
            this.xRotO = this.getXRot();
        }
        BlockPos blockpos = this.blockPosition();
        BlockState blockstate = this.level().getBlockState(blockpos);
        if (!blockstate.isAir()) {
            VoxelShape voxelshape = blockstate.getCollisionShape(this.level(), blockpos);
            if (!voxelshape.isEmpty()) {
                Vec3 vec31 = this.position();
                for (AABB aabb : voxelshape.toAabbs()) {
                    if (aabb.move(blockpos).contains(vec31)) {
                        inGround = true;
                        break;
                    }
                }
            }
        }
        if (inGround) {
            Vec3 vec32 = this.position();
            Vec3 vec33 = vec32.add(vec3);
            HitResult hitresult = this.level().clip(new ClipContext(vec32, vec33, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
            if (hitresult.getType() != HitResult.Type.MISS) {
                if (!net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
                    this.onHit(hitresult);
                    this.hasImpulse = true;
                }
            }
        }

        Vec3 vec32 = this.position();
        Vec3 vec33 = vec32.add(vec3);
        HitResult hitresult = this.level().clip(new ClipContext(vec32, vec33, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        if (hitresult.getType() != HitResult.Type.MISS) {
            vec33 = hitresult.getLocation();
        }
        while (!this.isRemoved()) {
            EntityHitResult entityhitresult = this.findHitEntity(vec32, vec33);
            if (entityhitresult != null) {
                hitresult = entityhitresult;
            }

            if (hitresult != null && hitresult.getType() == HitResult.Type.ENTITY) {
                Entity entity = ((EntityHitResult) hitresult).getEntity();
                Entity entity1 = this.getOwner();
                if (entity instanceof Player && entity1 instanceof Player && !((Player) entity1).canHarmPlayer((Player) entity)) {
                    hitresult = null;
                    entityhitresult = null;
                }
            }

            if (hitresult != null && hitresult.getType() != HitResult.Type.MISS) {
                if (net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult))
                    break;
                this.onHit(hitresult);
                this.hasImpulse = true;
            }

            if (entityhitresult == null) {
                break;
            }

            hitresult = null;
        }

        vec3 = this.getDeltaMovement();
        double d5 = vec3.x;
        double d6 = vec3.y;
        double d1 = vec3.z;
        double d7 = this.getX() + d5;
        double d2 = this.getY() + d6;
        double d3 = this.getZ() + d1;
        double d4 = vec3.horizontalDistance();
        if (this.gravity) {
            this.setYRot((float) (Mth.atan2(-d5, -d1) * (double) (180F / (float) Math.PI)));
        } else {
            this.setYRot((float) (Mth.atan2(d5, d1) * (double) (180F / (float) Math.PI)));
        }

        this.setXRot((float) (Mth.atan2(d6, d4) * (double) (180F / (float) Math.PI)));
        this.setXRot(lerpRotation(this.xRotO, this.getXRot()));
        this.setYRot(lerpRotation(this.yRotO, this.getYRot()));
        float f = 0.99F;
        this.setDeltaMovement(vec3.scale(f));

        if (!this.isNoGravity() || this.gravity) {
            Vec3 vec34 = this.getDeltaMovement();
            this.setDeltaMovement(vec34.x, vec34.y - (double) 0.05F, vec34.z);
        }

        this.setPos(d7, d2, d3);
        this.checkInsideBlocks();
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    protected void onHit(@NotNull HitResult pResult) {
        super.onHit(pResult);
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult pResult) {
        if (this.getOwner() instanceof LivingEntity owner) {
            ICaster caster = new LivingEntityCaster(owner);
            ITarget target = new EntityTarget(pResult.getEntity());
            for (MagicGroup group : this.magicGroup) {
                group.execute(caster, target);
            }
        }
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {
        if (this.getOwner() instanceof LivingEntity owner) {
            ICaster caster = new LivingEntityCaster(owner);
            ITarget target = new BlockTarget(this.level(), pResult.getBlockPos());
            for (MagicGroup group : this.magicGroup) {
                group.execute(caster, target);
            }
        }
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("gravity", this.gravity);
        ListTag list = new ListTag();
        for (MagicGroup group : this.magicGroup) {
            list.add(group.toNbtTag());
        }
        pCompound.put("magic", list);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("gravity")) this.gravity = pCompound.getBoolean("gravity");
        if (pCompound.contains("magic")) {
            List<MagicGroup> magics = new ArrayList<>();
            for (Tag magic : pCompound.getList("magic", Tag.TAG_COMPOUND)) {
                if (magic instanceof CompoundTag tag)
                    magics.add(MagicGroup.fromNbtTag(tag));
            }
            this.magicGroup = magics.toArray(MagicGroup[]::new);
        }
    }

    @Nullable
    protected EntityHitResult findHitEntity(Vec3 pStartVec, Vec3 pEndVec) {
        return ProjectileUtil.getEntityHitResult(this.level(), this, pStartVec, pEndVec, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0D), this::canHitEntity);
    }

    @Override
    public @NotNull ItemStack getItem() {
        return item == ItemStack.EMPTY ? new ItemStack(AskwayModItems.TALISMAN.get()) : item;
    }
}
