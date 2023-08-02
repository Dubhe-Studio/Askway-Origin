package dev.dubhe.askway.origin.blocks.lightning_peach;

import dev.dubhe.askway.origin.blocks.StrippableRotatedPillarBlock;
import dev.dubhe.askway.origin.init.AskwayModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class LightningPeachRotatedPillarBlock extends RotatedPillarBlock {

    public LightningPeachRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @NotNull Entity pEntity) {
        if (pEntity instanceof LivingEntity entity && entity.getMobType() == MobType.UNDEAD) {
            pEntity.hurt(pLevel.damageSources().hotFloor(), 1.0F);
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    public static void onLighting(Level level,BlockPos pos){
        BlockState state = level.getBlockState(pos);
        if (!state.is(AskwayModBlocks.PEACH_LOG.get())) return;
        level.setBlockAndUpdate(pos, LightningPeachRotatedPillarBlock.getLightingState(state));
        BlockPos.MutableBlockPos mutable = pos.mutable();
        int i = level.random.nextInt(3) + 3;
        for (int j = 0; j < i; ++j) {
            int k = level.random.nextInt(8) + 1;
            LightningPeachRotatedPillarBlock.randomWalk(level, pos, mutable, k);
        }
    }

    public static BlockState getLightingState(BlockState state) {
        return AskwayModBlocks.LIGHTNING_PEACH_LOG.getDefaultState().setValue(LightningPeachRotatedPillarBlock.AXIS, state.getValue(StrippableRotatedPillarBlock.AXIS));
    }

    public static void randomWalk(Level pLevel, BlockPos pPos, BlockPos.MutableBlockPos pMutable, int pSteps) {
        pMutable.set(pPos);
        for (int i = 0; i < pSteps; ++i) {
            Optional<BlockPos> optional = randomStep(pLevel, pMutable);
            if (optional.isEmpty()) {
                break;
            }
            pMutable.set(optional.get());
        }
    }

    private static Optional<BlockPos> randomStep(Level pLevel, BlockPos pPos) {
        for (BlockPos blockpos : BlockPos.randomInCube(pLevel.random, 10, pPos, 1)) {
            BlockState blockstate = pLevel.getBlockState(blockpos);
            if (blockstate.getBlock() == AskwayModBlocks.PEACH_LOG.get()) {
                pLevel.setBlockAndUpdate(blockpos, getLightingState(blockstate));
                pLevel.levelEvent(3002, blockpos, -1);
                return Optional.of(blockpos);
            }
        }
        return Optional.empty();
    }
}