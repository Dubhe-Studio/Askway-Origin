package dev.dubhe.askway.origin.init.items;

import dev.dubhe.askway.origin.init.AskwayModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class ScraperItem extends Item {
    public ScraperItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return toolAction == ToolActions.AXE_STRIP;
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        Player player = pContext.getPlayer();
        ItemStack stack = pContext.getItemInHand();
        BlockState state = level.getBlockState(pos);

        Optional<BlockState> strippedTo = Optional.ofNullable(state.getToolModifiedState(pContext, ToolActions.AXE_STRIP, false));
        if (strippedTo.isPresent()) {
            level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);

            ItemStack dropItem = new ItemStack(AskwayModItems.BARK.get(), 1);
            ItemEntity dropItemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), dropItem);
            dropItemEntity.setPickUpDelay(0);
            level.addFreshEntity(dropItemEntity);

            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);
            }

            level.setBlock(pos, strippedTo.get(), 11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, strippedTo.get()));
            if (player != null) {
                stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(pContext.getHand()));
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {

        if (pInteractionTarget instanceof Sheep target) {
            Level level = pPlayer.level();
            if (level.isClientSide) return InteractionResult.SUCCESS;

            BlockPos pos = BlockPos.containing(target.position());
            if (target.isShearable(pStack, level, pos)) {
                List<ItemStack> drops = target.onSheared(pPlayer, pStack, pInteractionTarget.level(), pos, 0);
                drops.add(new ItemStack(AskwayModItems.LANA.get(), 1));
                drops.forEach(stack -> {
                    ItemEntity itemEntity = pInteractionTarget.spawnAtLocation(stack, 1.0F);
                });
                pStack.hurtAndBreak(1, pPlayer, e -> e.broadcastBreakEvent(pUsedHand));
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
