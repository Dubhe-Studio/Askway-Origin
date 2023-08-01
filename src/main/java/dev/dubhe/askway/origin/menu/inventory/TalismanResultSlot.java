package dev.dubhe.askway.origin.menu.inventory;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TalismanResultSlot extends Slot {

    private final TalismanTableContainer craftSlots;
    private final Player player;
    private int removeCount;

    public TalismanResultSlot(Player pPlayer, TalismanTableContainer pCraftSlots, Container pContainer, int pSlot, int pXPosition, int pYPosition) {
        super(pContainer, pSlot, pXPosition, pYPosition);
        this.player = pPlayer;
        this.craftSlots = pCraftSlots;

    }

    @Override
    public boolean mayPlace(@NotNull ItemStack pStack) {
        return false;
    }

    @Override
    public @NotNull ItemStack remove(int pAmount) {
        if (this.hasItem()) {
            this.removeCount += Math.min(pAmount, this.getItem().getCount());
        }

        return super.remove(pAmount);
    }

    @Override
    protected void onQuickCraft(@NotNull ItemStack pStack, int pAmount) {
        this.removeCount += pAmount;
        this.checkTakeAchievements(pStack);
    }

    @Override
    protected void onSwapCraft(int pNumItemsCrafted) {
        this.removeCount += pNumItemsCrafted;
    }


    @Override
    protected void checkTakeAchievements(@NotNull ItemStack pStack) {
        if (this.removeCount > 0) {
            pStack.onCraftedBy(this.player.level(), this.player, this.removeCount);
            net.minecraftforge.event.ForgeEventFactory.firePlayerCraftingEvent(this.player, pStack, this.craftSlots);
        }

        Container container = this.container;
        if (container instanceof RecipeHolder recipeholder) {
            recipeholder.awardUsedRecipes(this.player, this.craftSlots.getItems());
        }

        this.removeCount = 0;
    }

    @Override
    public void onTake(@NotNull Player pPlayer, @NotNull ItemStack pStack) {
        this.checkTakeAchievements(pStack);
        for (int i = 2; i < 12; ++i) {
            this.craftSlots.removeItem(i, 1);
        }
        for (int i = 0; i < 2; ++i) {
            ItemStack stack = this.craftSlots.getItem(i);
            if (pPlayer instanceof ServerPlayer serverPlayer) stack.hurt(1, pPlayer.getRandom(), serverPlayer);
        }
    }
}
