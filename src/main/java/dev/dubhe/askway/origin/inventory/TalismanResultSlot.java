package dev.dubhe.askway.origin.inventory;

import net.minecraft.core.NonNullList;
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

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean mayPlace(@NotNull ItemStack pStack) {
        return false;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new stack.
     */
    public @NotNull ItemStack remove(int pAmount) {
        if (this.hasItem()) {
            this.removeCount += Math.min(pAmount, this.getItem().getCount());
        }

        return super.remove(pAmount);
    }

    /**
     * Typically increases an internal count, then calls {@code onCrafting(item)}.
     *
     * @param pStack the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    protected void onQuickCraft(@NotNull ItemStack pStack, int pAmount) {
        this.removeCount += pAmount;
        this.checkTakeAchievements(pStack);
    }

    protected void onSwapCraft(int pNumItemsCrafted) {
        this.removeCount += pNumItemsCrafted;
    }

    /**
     * @param pStack the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
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

    public void onTake(@NotNull Player pPlayer, @NotNull ItemStack pStack) {
        this.checkTakeAchievements(pStack);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(pPlayer);
        NonNullList<ItemStack> nonnulllist = null;//TODO pPlayer.level().getRecipeManager().getRemainingItemsFor(RecipeType.CRAFTING, this.craftSlots, pPlayer.level());
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);
        for (int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = this.craftSlots.getItem(i);
            ItemStack itemstack1 = nonnulllist.get(i);
            if (!itemstack.isEmpty()) {
                this.craftSlots.removeItem(i, 1);
                itemstack = this.craftSlots.getItem(i);
            }

            if (!itemstack1.isEmpty()) {
                if (itemstack.isEmpty()) {
                    this.craftSlots.setItem(i, itemstack1);
                } else if (ItemStack.isSameItemSameTags(itemstack, itemstack1)) {
                    itemstack1.grow(itemstack.getCount());
                    this.craftSlots.setItem(i, itemstack1);
                } else if (!this.player.getInventory().add(itemstack1)) {
                    this.player.drop(itemstack1, false);
                }
            }
        }

    }
}
