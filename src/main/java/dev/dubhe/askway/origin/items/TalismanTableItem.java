package dev.dubhe.askway.origin.items;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class TalismanTableItem extends BlockItem {
    public TalismanTableItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    protected boolean placeBlock(BlockPlaceContext pContext, @NotNull BlockState pState) {
        return pContext.getLevel().setBlock(pContext.getClickedPos(), pState, 26);
    }
}
