package dev.dubhe.askway.origin.blocks.entities;

import dev.dubhe.askway.origin.init.AskwayModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TalismanTableBlockEntity extends BlockEntity {
    public TalismanTableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(AskwayModBlockEntities.TALISMAN_TABLE.get(), pPos, pBlockState);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
