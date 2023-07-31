package dev.dubhe.askway.origin.entities;

import dev.dubhe.askway.origin.init.AskwayModEntities;
import dev.dubhe.askway.origin.init.AskwayModItems;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

    @Override
    public @NotNull MobType getMobType() {
        return MobType.UNDEAD;
    }

    public SpiritEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.playerInfo = null;
    }

    protected SpiritEntity(Level pLevel, PlayerInfo playerInfo) {
        super(AskwayModEntities.SPIRIT.get(), pLevel);
        this.playerInfo = playerInfo;
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 16));
        this.goalSelector.addGoal(3, new SpiritAttackGoal(this, 1.0D, false));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (pSource.getEntity() instanceof Player player) {
            ItemStack mainHand = player.getMainHandItem();
            if (mainHand.is(Items.WOODEN_SWORD))
                super.hurt(pSource, pAmount * 0.7f);
            else if (mainHand.is(AskwayModItems.PEACH_WOODEN_SWORD.get()))
                super.hurt(pSource, pAmount);
            else if (mainHand.is(AskwayModItems.LIGHTNING_PEACH_WOODEN_SWORD.get()))
                super.hurt(pSource, pAmount * 1.5f);
            else if (mainHand.is(AskwayModItems.COPPER_COIN_SWORD.get()))
                super.hurt(pSource, pAmount * 2.0f);
            else if (pSource.is(DamageTypes.MAGIC))
                super.hurt(pSource, pAmount * 2.0f);
            else return false;
        }
        return super.hurt(pSource, pAmount);
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

    private static class SpiritAttackGoal extends MeleeAttackGoal {

        private final SpiritEntity spirit;
        private int raiseArmTicks;

        public SpiritAttackGoal(SpiritEntity pSpirit, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(pSpirit, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
            this.spirit = pSpirit;
        }

        @Override
        public void start() {
            super.start();
            raiseArmTicks = 0;
        }

        @Override
        public void stop() {
            super.stop();
            this.spirit.setAggressive(false);
        }

        @Override
        public void tick() {
            super.tick();
            ++this.raiseArmTicks;
            this.spirit.setAggressive(this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2);
        }
    }
}
