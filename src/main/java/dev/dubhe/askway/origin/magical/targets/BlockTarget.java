package dev.dubhe.askway.origin.magical.targets;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class BlockTarget implements ITarget {
    private final Level level;
    private final BlockPos blockPos;

    public BlockTarget(Level level, BlockPos blockPos) {
        this.level = level;
        this.blockPos = blockPos;
    }

    @Override
    public @NotNull Vec3 getPos() {
        return blockPos.getCenter();
    }

    public @NotNull BlockState getState() {
        return this.level.getBlockState(blockPos);
    }

    public @NotNull Block getBlock() {
        return this.getState().getBlock();
    }

    @Override
    public Level getLevel() {
        return level;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }
}
