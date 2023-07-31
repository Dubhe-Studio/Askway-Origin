package dev.dubhe.askway.origin.blocks.lightning_peach;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.jetbrains.annotations.NotNull;

public class LightningPeachDoorBlock extends DoorBlock {

    public LightningPeachDoorBlock(Properties pProperties, BlockSetType pType) {
        super(pProperties, pType);
    }

    @Override
    public void stepOn(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @NotNull Entity pEntity) {
        if (pEntity instanceof LivingEntity entity && entity.getMobType() == MobType.UNDEAD) {
            pEntity.hurt(pLevel.damageSources().hotFloor(), 1.0F);
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}