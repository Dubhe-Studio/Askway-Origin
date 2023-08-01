package dev.dubhe.askway.origin.magical.targets;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class BlockTarget implements ITarget {
    private final Level level;
    private BlockPos blockPos;

    public BlockTarget(Level level, BlockPos blockPos) {
        this.level = level;
        this.blockPos = blockPos;
    }

    @Override
    public @NotNull Vec3 getPos() {
        return blockPos.getCenter();
    }

    @Override
    public void setPos(Vec3 pos) {
        BlockState state = this.level.getBlockState(blockPos);
        BlockPos newPos = new BlockPos((int) pos.x(), (int) pos.y(), (int) pos.z());
        this.level.setBlock(newPos, state, 4);
        this.level.destroyBlock(this.blockPos, false);
        this.blockPos = newPos;
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

    @Override
    public LevelChunk getChunk() {
        return this.getLevel().getChunkAt(blockPos);
    }


    public BlockPos getBlockPos() {
        return blockPos;
    }
}
