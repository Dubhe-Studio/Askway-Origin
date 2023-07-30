package dev.dubhe.askway.origin.entities;

import dev.dubhe.askway.origin.init.AskwayModEntities;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class SpiritEntity extends Monster {
    private final PlayerInfo playerInfo;
    protected Vec3 deltaMovementOnPreviousTick = Vec3.ZERO;

    @Override
    public void tick() {
        this.deltaMovementOnPreviousTick = this.getDeltaMovement();
        super.tick();
    }

    @Override
    public @NotNull AttributeMap getAttributes() {
        return new AttributeMap(SpiritEntity.createAttributes().build());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public SpiritEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.playerInfo = null;
    }

    protected SpiritEntity(Level pLevel, PlayerInfo playerInfo) {
        super(AskwayModEntities.SPIRIT.get(), pLevel);
        this.playerInfo = playerInfo;
    }

    public static SpiritEntity create(Level pLevel, PlayerInfo playerInfo) {
        return new SpiritEntity(pLevel, playerInfo);
    }

    public ResourceLocation getSkinTextureLocation() {
        return playerInfo == null ? DefaultPlayerSkin.getDefaultSkin(this.getUUID()) : playerInfo.getSkinLocation();
    }

    public Vec3 getDeltaMovementLerped(float pPatialTick) {
        return this.deltaMovementOnPreviousTick.lerp(this.getDeltaMovement(), pPatialTick);
    }
}
